/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Entities.Cakeorder;
import Entities.Delivery;
import Entities.Orders;
import Entities.User;
import businesslogic.DeliveryService;
import businesslogic.OrderService;
import dataaccess.CakeorderJpaController;
import dataaccess.OrdersJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlets.admin.ManageOrdersServlet;

/**
 *
 * @author 651218
 */
public class SummaryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        get language option cn/en
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("language");
        
     DeliveryService ds = new DeliveryService();
     CakeorderJpaController cojc = new CakeorderJpaController();
     OrdersJpaController ojc = new OrdersJpaController();
     
     String action = request.getParameter("action");
     String url = "/WEB-INF/summary.jsp";

     if(action != null && action.equals("logout")){
         url = "/mainmenu";
         session.invalidate();
          getServletContext()
            .getRequestDispatcher(url)
                .forward(request, response);
          return;
     }
         int selectedOrderId = getOrderNo()-1;
         try{
            //get order
            Orders selectedOrder = ojc.findOrders(selectedOrderId);
            //get order related delivery
            Delivery delivery = selectedOrder.getDeliveryNo();
            //get user
            User user = selectedOrder.getUserId();
            //get cakeOrder
            List<Cakeorder> cakeOrders = cojc.findCakeorderByOrderNo(selectedOrderId);
            System.out.println("cakeOrders.size:"+cakeOrders.size());
            request.setAttribute("selectedOrder", selectedOrder);
            request.setAttribute("delivery", delivery);
            request.setAttribute("user", user);
            request.setAttribute("cakeOrders", cakeOrders);
//            //put cakeOrders in session for doPost change qty
//            session.setAttribute("cakeOrdersSession", cakeOrders);
            
            
            
         } catch (Exception ex) {
                        Logger.getLogger(ManageOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
     
     // forward to the view

        if (language.equals("cn")) {
            getServletContext().getRequestDispatcher("/WEB-INF/cn/summary.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/summary.jsp").forward(request, response);
        }
        
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
      public int getOrderNo() {
        OrderService os = new OrderService();
        int orderNo = 1;
        System.out.println("--*-- Finding good ID");
        boolean notFound = true;
        while (notFound) {
            if (os.get(orderNo) != null) orderNo++;
            else notFound = false;
        }
        return orderNo;
    }

}
