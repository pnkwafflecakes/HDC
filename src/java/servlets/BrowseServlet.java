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
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kim Nguyen
 */
public class BrowseServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("language");
        
        CakeService cs = new CakeService();
        List<Cake> tmparr = cs.getAll();
        ArrayList<Cake> cakearray = new ArrayList<>();
        
        if (tmparr != null) {
            if (!tmparr.isEmpty()) {
                for (Cake tmp : tmparr) {
                    cakearray.add(tmp);
                }
            }
        }
        
        request.setAttribute("allcakes", cakearray);

//        if (language == null) {
//            language = "en";
//        }
//        if (language.equals("cn")) {
//            getServletContext().getRequestDispatcher("/WEB-INF/cn/browse.jsp").forward(request, response);
//        } else {
//            getServletContext().getRequestDispatcher("/WEB-INF/en/browse.jsp").forward(request, response);
//        }
        getServletContext().getRequestDispatcher("/WEB-INF/en/browse.jsp").forward(request, response);
        
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
    
}
