/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.CakeJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 744916
 */
public class MainMenuServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/WEB-INF/mainmenu/mainmenu.jsp").forward(request, response);
        
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("select")) {
                HttpSession session = request.getSession();
                //Like we did in the final, selectedCake, get the object then put it in session
                int selectedCakeId = Integer.valueOf(request.getParameter("selectedCakeId"));
                CakeJpaController cjc = new CakeJpaController();
                session.setAttribute("selectedCake", cjc.findCake(selectedCakeId));
            }
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
            throws ServletException, IOException
    {
        
    }


}
