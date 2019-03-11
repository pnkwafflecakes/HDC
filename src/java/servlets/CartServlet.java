/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Entities.Cake;
import businesslogic.CakeService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
            double totalPrice = 0;
            CakeService cs = new CakeService();
            List<Cake> allCakes = cs.getAll();
            int[] counter = new int[allCakes.size()+1];
            
            //todo: Make add quantity
            
            //For da prices
            for (int i = 0; i < cakes.size(); i++) {
                totalPrice = totalPrice + cakes.get(i).getPrice();
            }
            emptyCart = false;
            
            
            for (int i=0; i < cakes.size(); i++) {
                System.out.println("Set: " + i);
                Cake cake = cakes.get(i);
                System.out.println("Cake at loc: " + cake.getName() + ", id: " + cake.getCakeId());
                System.out.println("Counter value: "+counter[cake.getCakeId()]);
                if (counter[cake.getCakeId()] == 0) counter[cake.getCakeId()] = 1;
                else {
                    System.out.println("Removing duplicate cake: " + cake.getName());
                    counter[cake.getCakeId()]++;
                    cakes.remove(i);
                    i--;
                }
                
            }
            Cake[] cakeArray = cakes.toArray(new Cake[cakes.size()]);
            request.setAttribute("counter", counter);
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
