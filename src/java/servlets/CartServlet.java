/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Entities.Cake;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 744916
 */
public class CartServlet extends HttpServlet
{
    
    private boolean emptyCart = true;

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
        HttpSession session = request.getSession(true);
        ArrayList<Cake> cakes = (ArrayList<Cake>) session.getAttribute("cakes");
        
        if (cakes!=null && cakes.size()!=0) {
            System.out.println("Cake was valid: " + cakes + " size: " + cakes.size());
            Cake[] cakeArray = new Cake[cakes.size()];
            double totalPrice = 0;
            int[] counter = new int[cakes.size()];
            //todo: Make add quantity
            for (int i = 0; i < cakes.size(); i++) {
                cakeArray[i] = cakes.get(i);
                totalPrice = totalPrice + cakeArray[i].getPrice();
            }
            emptyCart = false;
            request.setAttribute("cakes", cakeArray);
            request.setAttribute("totalPrice", totalPrice);
        }
        else {
            emptyCart = true;
            request.setAttribute("errorMessage", "Your cart is empty");
        }
       
        getServletContext().getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
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
            throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("delete")) {
                int selectedCakeId = Integer.valueOf(request.getParameter("selectedCake"));
                HttpSession session = request.getSession(true);
                ArrayList<Cake> cakes = (ArrayList<Cake>) session.getAttribute("cakes");

                cakes.remove(selectedCakeId);
                doGet(request, response);
            }
        }
        if (emptyCart == false) {
            response.sendRedirect("orderdetails");
        }
        else {
            response.sendRedirect("cart");
        }
    }
}
