/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Entities.Orders;
import Entities.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
public class ViewOrders extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Get session variable
            //Create user variable to put into session
            HttpSession session = request.getSession();
            User test = new User();
            test.setUserId(0);
            session.setAttribute("userObj", test);
            
            
            User user = (User) session.getAttribute("userObj");
            //Use session variable to query database for users orders
            //Querying for the cake items as well is most likely necessary.
            int id = user.getUserId();
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstonedb","root", "password");
            String preparedQuery = "Select * from Orders Where user_id = ?";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ArrayList orderList = new ArrayList();
            int orders = 0;
            int i = 0;
            rs.last();
            int rows = rs.getRow();
            orders = rows / 7;
            while(i < orders)
            {
                //Create object from resultset data
                Date orderDate = rs.getDate("order_datetime");
                Date dueDate = rs.getDate("due_datetime");
                Orders order = new Orders();
                order.setOrderNo(rs.getInt("order_no"));
                order.setUserId(test);
                order.setOrderDatetime(orderDate);
                order.setDueDatetime(dueDate);
                order.setOrderItems("order_items");
                order.setTotalPrice(rs.getDouble("total_price"));
                
                //Grab delivery object from database
                
                //order.setDeliveryNo(rs.getInt("delivery_no"));
                orderList.add(order);
                i++;
            }
            
            
            
            getServletContext().getRequestDispatcher("/WEB-INF/orders.jsp").forward(request, response);
            //Display orders in a decent way
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ViewOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    // </editor-fold>

}
