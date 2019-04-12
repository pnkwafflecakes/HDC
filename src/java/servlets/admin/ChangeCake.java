/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin;

import BusinessClasses.exceptions.IllegalOrphanException;
import Entities.Cake;
import businesslogic.CakeService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mypc
 */
public class ChangeCake extends HttpServlet {

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
        String temp = request.getParameter("selectedCakeId");
        int cakeid = Integer.valueOf(temp);

        CakeService cs = new CakeService();
        Cake curcake = cs.get(cakeid);

        if (curcake != null) {
            request.setAttribute("cake", curcake);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/adminportal/changecake.jsp").forward(request, response);

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
        CakeService cs = new CakeService();

        String temp = request.getParameter("selectedCakeId");
        int cakeid = Integer.valueOf(temp);
        Cake curcake = cs.get(cakeid);

        String name = (String) request.getParameter("name");

        double price = Double.valueOf(request.getParameter("price"));
        int size = Integer.valueOf(request.getParameter("size"));

        String description = (String) request.getParameter("description");

        String descriptioncn = new String(request.getParameter("descriptioncn").getBytes("ISO-8859-1"), "utf-8");
        String namecn = new String(request.getParameter("namecn").getBytes("ISO-8859-1"), "utf-8");

        boolean featured = false;
        boolean special = false;

        String[] featuredCheck = request.getParameterValues("featured");
        if (featuredCheck == null) {
            featured = false;
        } else if (featuredCheck[0].equals("on")) {
            featured = true;
        }
        String[] specialCheck = request.getParameterValues("special");
        if (specialCheck == null) {
            featured = false;
        } else if (specialCheck[0].equals("on")) {
            special = true;
        }

        curcake.setName(name);
        curcake.setNamecn(namecn);
        curcake.setDescription(description);
        curcake.setDescriptioncn(descriptioncn);
        curcake.setFeatured(featured);
        curcake.setPrice(price);
        curcake.setSize(size);
        curcake.setSpecial(special);

        try {
            cs.update(curcake);
        } catch (IllegalOrphanException ex) {
            request.setAttribute("notification", "Database error");
            Logger.getLogger(ChangeCake.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            request.setAttribute("notification", "Database error");
            Logger.getLogger(ChangeCake.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("notification", "Cake edited");

        response.sendRedirect("managecakes");

    }

}
