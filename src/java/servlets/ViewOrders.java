/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Entities.Delivery;
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
            User user = (User) session.getAttribute("userObj");
            //Use session variable to query database for users orders
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstonedb","root", "password");
            String preparedQuery = "Select * from Orders Where user_id = ?";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();
            ArrayList orderList = new ArrayList();
            int i = 0;
            rs.last();
            int rows = rs.getRow();
            rs.first();
            //Push a message to the page if there are no orders
            if(rows == 0)
            {
                request.setAttribute("error","No orders have been placed yet.");
            }
            while(i < rows)
            {
                //Create object from resultset data
                Date orderDate = rs.getDate("order_datetime");
                Date dueDate = rs.getDate("due_datetime");
                Orders order = new Orders();
                order.setOrderNo(rs.getInt("order_no"));
                order.setUserId(user);
                order.setOrderDatetime(orderDate);
                order.setDueDatetime(dueDate);
                order.setOrderItems(rs.getString("order_items"));
                order.setTotalPrice(rs.getDouble("total_price"));
                int order_no = order.getOrderNo();
                
                //Get the delivery number from the order.
                String prepOrderStatement = "Select delivery_no from Orders where order_no = ? ;";
                PreparedStatement ps2 = connection.prepareStatement(prepOrderStatement);
                ps2.setInt(1,order_no);
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                
                //Grab delivery object from database
                String prepDeliveryStatement = "Select * from Delivery where delivery_no = ? ;";
                PreparedStatement ps3 = connection.prepareStatement(prepDeliveryStatement);
                ps3.setInt(1, order.getOrderNo());
                ResultSet rs3 = ps3.executeQuery();
                rs3.next();
                Delivery del = new Delivery();
                del.setDeliveryNo(rs2.getInt("delivery_no"));
                del.setMethod(rs3.getString("method"));
                del.setAddress(rs3.getString("address"));
                del.setPhoneNo(rs3.getString("phone_no"));
                del.setNotes(rs3.getString("notes"));
                order.setDeliveryNo(del);
                orderList.add(order);
                i++;
                rs.next();
            }          
            //Push orderList to page
            request.setAttribute("orderList", orderList);
            session.setAttribute("orderList", orderList);
            getServletContext().getRequestDispatcher("/WEB-INF/orders.jsp").forward(request, response);
            
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
        //get the order number from request.
        HttpSession session = request.getSession();
        String order_no = request.getParameter("deleteOrder");
        int orderNo = Integer.parseInt(order_no); 
        User user = (User) session.getAttribute("userObj");
        int user_id = user.getUserId();
        //check if the order can still be cancelled
        //try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstonedb","root", "password");
//            String prepStatement = "Select ((((booleans))) from Orders Where user_id = ? And order_no = ?;";
//            PreparedStatement ps = connection.prepareStatement(prepStatement);
//            
//            ps.setInt(1, user_id);
//            ps.setInt(2,orderNo);
//            ResultSet rs = ps.executeQuery();
            
            //check which boolean is true
            //if it can:
            //delete the order from the database
            //if not confirmed or paid yet delete
            if(){
                //Class.forName("com.mysql.jdbc.Driver");
                //Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/capstonedb","root", "password");
                //String prepDeleteStatement = "Update active from Orders Where user_id = ? And order_no = ? Values(?);";
                //PreparedStatement ps2 = connection2.prepareStatement(prepDeleteStatement);
                //ps2.setInt(1, user_id);
                //ps2.setInt(2,Integer.parseInt(order_no));
                //ResultSet rs2 = ps2.executeQuery(prepDeleteStatement);
                //update page and inform user
                ArrayList orderList = (ArrayList) session.getAttribute("orderList");
                int i = 0;   
                orderList.size();
                for(i = 0;i < orderList.size();i++) {
                }
                    Orders order = (Orders) orderList.get(i-1);
                    if(order.getOrderNo() == orderNo)
                    {
                        orderList.remove(i-1);
                    }
                    request.setAttribute("deleted", "Order Deleted!");
                    getServletContext().getRequestDispatcher("/WEB-INF/orders.jsp").forward(request, response);
                }
                
            
            //if it cannot:
            request.setAttribute("notdeleted", "Order Has Already Started and Cannot be Deleted Anymore!");
            request.setAttribute("orderList", session.getAttribute("orderList"));
            getServletContext().getRequestDispatcher("/WEB-INF/orders.jsp").forward(request, response);
                //this does not populate the orders page once again
        //}
        //catch(ClassNotFoundException | SQLException ex) {
        
        //}
        
        
            //inform the user that it cannot be deleted and why.
            
    }
    // </editor-fold>

}
