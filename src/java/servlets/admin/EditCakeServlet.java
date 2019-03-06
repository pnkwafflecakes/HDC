/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin;

import Entities.Cake;
import Entities.Cakecategory;
import businesslogic.CakeCategoryService;
import businesslogic.CakeService;
import java.io.IOException;
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
public class EditCakeServlet extends HttpServlet
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
        String inputType = request.getParameter("input");
        HttpSession session = request.getSession(true);
        request.setAttribute("input", inputType);
        CakeCategoryService ccs = new CakeCategoryService();
        List<Cakecategory> categoryList = ccs.getAll();
        
        System.out.println("Input: " + inputType);
        if (inputType.equals("edit")) {
            System.out.println("In edits");
            CakeService cs = new CakeService();
            int cakeId = Integer.valueOf(session.getAttribute("cakeId")+"");
            Cake cake = cs.get(cakeId);
            request.setAttribute("cake", cake);
            Cakecategory excludeCategory = cake.getCategoryId();
            request.setAttribute("selectedCategory", excludeCategory);
            categoryList.remove(excludeCategory);
            System.out.println("Selected Category: " + excludeCategory);
        }
        
        Cakecategory[] categories = categoryList.toArray(new Cakecategory[categoryList.size()]);
        request.setAttribute("categories", categories);
        getServletContext().getRequestDispatcher("/WEB-INF/adminportal/editcake.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        CakeService cs = new CakeService();
        String action = request.getParameter("action");
        Cake cake = new Cake();
        boolean featured = false;
        boolean special = false;
            
        int cakeId = Integer.valueOf(request.getParameter("cakeId"));
        String description = request.getParameter("description");
            
        String[] featuredCheck = request.getParameterValues("featured");
        if (featuredCheck[0].equals("checked")) featured = true;
            
        double price = Double.valueOf(request.getParameter("price"));
        int size = Integer.valueOf(request.getParameter("size"));
            
        String[] specialCheck = request.getParameterValues("special");
        if (specialCheck[0].equals("checked")) special = true;
           
        cake.setCakeId(cakeId);
        //cake.setCategoryId(categoryId);
        cake.setDescription(description);
        cake.setFeatured(featured);
        //cake.setImage();
        cake.setPrice(price);
        cake.setSize(size);
        cake.setSpecial(special);
        
        try {
            if (action.equals("edit")) 
            {
                cs.update(cake);
            }
            else if (action.equals("add")) {
                cs.insert(cake);
            }
        } catch(Exception e) {
            
        }
    }
}
