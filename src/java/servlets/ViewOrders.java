/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Entities.Orders;
import Entities.User;
import businesslogic.OrderService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Knyfe
 */
public class ViewOrders extends HttpServlet
{
    //Do Get
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //Get session variable
        //Create user variable to put into session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userObj");
        
        try {
            OrderService os = new OrderService();
            List<Orders> orders = os.getAll();
            ArrayList<Orders> userOrders = new ArrayList<>();

            for (int i = 0; i < orders.size(); i++)
            {
                if (orders.get(i).getUserId().getUserId() == user.getUserId())
                {
                    userOrders.add(orders.get(i));
                }
            }

            //Push a message to the page if there are no orders
            if (userOrders.isEmpty())
            {
                request.setAttribute("error", "No orders have been placed yet.");
                getServletContext().getRequestDispatcher("/WEB-INF/orders.jsp").forward(request, response);
            }
            
            //Push orderList to page
            session.setAttribute("orderList", userOrders);
            getServletContext().getRequestDispatcher("/WEB-INF/orders.jsp").forward(request, response);
            }
        //Mkae sure that a user who is not logged in cannot view orders.
        catch(NullPointerException ex)
        {
            request.setAttribute("error", "Please Log In to View Orders");
            getServletContext().getRequestDispatcher("/WEB-INF/orders.jsp").forward(request, response);
        }
    }

    //Do Post
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //get the order number from request.
        HttpSession session = request.getSession();
        String order_no = request.getParameter("deleteOrder");
        int orderNo = Integer.parseInt(order_no);
        User user = (User) session.getAttribute("userObj");
        int user_id = user.getUserId();
        int active = 0;
        int confirmed = 0;
        int paid = 0;

        //check if the order can still be cancelled
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstonedb", "root", "password");
            String prepStatement = "Select active,confirmed,paid from Orders Where user_id = ? And order_no = ?;";
            PreparedStatement ps = connection.prepareStatement(prepStatement);

            ps.setInt(1, user_id);
            ps.setInt(2, orderNo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            active = rs.getInt("active");
            confirmed = rs.getInt("confirmed");
            paid = rs.getInt("paid");

        }
        catch(ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(ViewOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        //if active and not confirmed or paid yet delete
        //update page and inform user
        ArrayList orderList = (ArrayList) session.getAttribute("orderList");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstonedb", "root", "password");
            String prepUpdateStatement = "UPDATE orders SET active = false WHERE user_id = ? AND order_no = ?;";
            PreparedStatement ps2 = connection.prepareStatement(prepUpdateStatement);
            ps2.setInt(1, user_id);
            ps2.setInt(2, orderNo);
            ps2.executeUpdate(prepUpdateStatement);
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(ViewOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("orderList", session.getAttribute("orderList"));
        getServletContext().getRequestDispatcher("/WEB-INF/orders.jsp").forward(request, response);
    }
}
