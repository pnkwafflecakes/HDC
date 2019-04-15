/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin;

import BusinessClasses.DBEntry;
import Entities.Cake;
import Entities.Delivery;
import Entities.Orders;
import Entities.User;
import businesslogic.CakeService;
import businesslogic.DeliveryService;
import businesslogic.UserService;
import dataaccess.DeliveryJpaController;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author 744916
 */
public class ManageCakesServlet extends HttpServlet
{
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
            throws ServletException, IOException
    {
        CakeService cs = new CakeService();
        List<Cake> cakes = cs.getAll();
        Cake[] cakeArray = new Cake[cakes.size()];
        
        for (int i = 0; i < cakes.size(); i++) {
            cakeArray[i] = cakes.get(i);
            
        }

        request.setAttribute("cakes", cakeArray);
        getServletContext().getRequestDispatcher("/WEB-INF/adminportal/managecakes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);
        try {
        CakeService cs = new CakeService();
        String action = request.getParameter("action");
        if (action != null) {
            
            if (action.equals("undo")) {
                Cake undoCake = (Cake) session.getAttribute("undoCake");
                if (undoCake != null) {
                    cs.insert(undoCake);
                    session.setAttribute("undoCake", null);
                    request.setAttribute("notification", "Cake deletion was un-done");
                }
                else request.setAttribute("notification", "No recent cake deletions found");
                doGet(request, response);
            }
            
            if (action.equals("delete")) {
                int cakeId = Integer.valueOf(request.getParameter("selectedCakeId"));
                if (cs.get(cakeId).getOrdersCollection().size() != 0) {
                    String orderNums = "";
                    List<Orders> orderN = (List<Orders>) cs.get(cakeId).getOrdersCollection();
                    Orders[] ordArray = orderN.toArray(new Orders[orderN.size()]);
                    for (int i =0; i < ordArray.length; i++) {
                        orderNums = orderNums + ordArray[i].getOrderNo() + ", ";
                    }
                    orderNums.subSequence(0, orderNums.length()-2);
                    request.setAttribute("notification", "Cake is in an orders numbers: " + orderNums +". Will be put to inactive instead (Not visible to customer)");
                    cs.get(cakeId).setActive(false);
                }
                else {
                    request.setAttribute("notification", "Cake deleted. Can undo this with the \"Undo\" button");
                    Cake undoCake = cs.get(cakeId);
                    session.setAttribute("undoCake", undoCake);
                    cs.delete(cakeId);
                }
                    doGet(request, response);
            }
            else if (action.equals("edit")) {
                int cakeId = Integer.valueOf(request.getParameter("selectedCakeId"));
                session.setAttribute("cakeId", cakeId);
                response.sendRedirect("editcake?input=edit");
            }
            else if (action.equals("add")) {
                response.sendRedirect("editcake?input=add");
            }
        }
        } catch (Exception e) {
            response.sendRedirect("login");
        }
    }
}
