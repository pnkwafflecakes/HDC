/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import BusinessClasses.exceptions.NonexistentEntityException;
import Entities.Orders;
import businesslogic.OrderService;
import dataaccess.OrdersJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 651218
 */
public class PaymentServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

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
//        get language option en/ch
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("language");
        
//        get payment status
        String payment = (String) request.getParameter("payment");
        if(payment != null && !"".equals(payment)){
            if(payment.equals("success")){
                 int selectedOrderId = getOrderNo()-1;
                 OrdersJpaController ojc = new OrdersJpaController();
                 Orders orderOld = ojc.findOrders(selectedOrderId);
                 orderOld.setPaid(true);
                try {
                    ojc.edit(orderOld);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                 if (language.equals("cn")) {
                        getServletContext().getRequestDispatcher("/WEB-INF/cn/successorder.jsp").forward(request, response);
                    } else {
                        getServletContext().getRequestDispatcher("/WEB-INF/successorder.jsp").forward(request, response);
                    }
            }else if(payment.equals("fail")){
                 if (language.equals("cn")) {
                        getServletContext().getRequestDispatcher("/WEB-INF/cn/notsuccessorder.jsp").forward(request, response);
                    } else {
                        getServletContext().getRequestDispatcher("/WEB-INF/notsuccessorder.jsp").forward(request, response);
                    }
            }else if(payment.equals("cash")){
                 if (language.equals("cn")) {
                        getServletContext().getRequestDispatcher("/WEB-INF/cn/placedorder.jsp").forward(request, response);
                    } else {
                        getServletContext().getRequestDispatcher("/WEB-INF/placedorder.jsp").forward(request, response);
                    }
            }else if(payment.equals("etrasfer")){
                 if (language.equals("cn")) {
                        getServletContext().getRequestDispatcher("/WEB-INF/cn/placedorder.jsp").forward(request, response);
                    } else {
                        getServletContext().getRequestDispatcher("/WEB-INF/placedorder.jsp").forward(request, response);
                    }
            }
        }
        getServletContext().getRequestDispatcher("/mainmenu").forward(request, response);
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
