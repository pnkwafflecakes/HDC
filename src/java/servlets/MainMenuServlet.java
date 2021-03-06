/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Entities.Cake;
import businesslogic.CakeService;
import dataaccess.CakeJpaController;
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
public class MainMenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("language");

        CakeService cs = new CakeService();
        List<Cake> cakes = cs.getAll();
        ArrayList<Cake> cakearray = new ArrayList<>();
        ArrayList<Cake> ftcakes = new ArrayList<>();

        Cake tmp = null;
        for (int i = cakes.size() - 1; i >= 0 && cakearray.size() <= 6; i--) {
            tmp = cakes.get(i);
            if (tmp.getFeatured()) {
                ftcakes.add(tmp);
            }
            if (tmp.getSpecial()) {
                cakearray.add(tmp);
            }
        }
        request.setAttribute("feature1", ftcakes.get(0));
        request.setAttribute("feature2", ftcakes.get(1));
        request.setAttribute("feature3", ftcakes.get(2));

        request.setAttribute("cake1", cakearray.get(0));
        request.setAttribute("cake2", cakearray.get(1));
        request.setAttribute("cake3", cakearray.get(2));
        request.setAttribute("cake4", cakearray.get(3));
        request.setAttribute("cake5", cakearray.get(4));
        request.setAttribute("cake6", cakearray.get(5));

        if (language == null) {
            language = "en";
        }
        if (language.equals("cn")) {
            getServletContext().getRequestDispatcher("/WEB-INF/cn/mainmenu.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/en/mainmenu.jsp").forward(request, response);
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

}
