/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Entities.Cake;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.time.Clock;
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
        getServletContext().getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
        
        //Retrieve list of cake objects from session attribute.
        HttpSession session = request.getSession();
        ArrayList cart = (ArrayList) session.getAttribute("cart");
        Cake test = (Cake) cart.get(0);
        request.setAttribute("currentCart","hey");
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
        getServletContext().getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
        //Get session variable that cntains the arraylist of cakes.
        //Set attribute on .jsp as the values for cakes using a foreach loop
       
        //request.setAttribute("currentCart", test.getDescription() + "hi");
    }
}
