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
import dataaccess.CakeJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author 744916
 */
@MultipartConfig
public class EditCakeServlet1 extends HttpServlet
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
        //request.setAttribute("input", inputType);
        session.setAttribute("input", inputType);
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
        CakeCategoryService ccs = new CakeCategoryService();
        Cake cake = new Cake();
        boolean featured = false;
        boolean special = false;
        
        //image
        
        
        //Description
        String description = request.getParameter("description");
        //Featured
        String[] featuredCheck = request.getParameterValues("featured");
        if (featuredCheck[0].equals("checked")) featured = true;
        //Price
        double price = Double.valueOf(request.getParameter("price"));
        //Size
        int size = Integer.valueOf(request.getParameter("size"));
        //Special
        String[] specialCheck = request.getParameterValues("special");
        if (specialCheck[0].equals("checked")) special = true;
        
        int categoryId = Integer.valueOf(request.getParameter("categorySelect"));           
        
        cake.setCategoryId(ccs.get(categoryId));
        cake.setDescription(description);
        cake.setFeatured(featured);
        cake.setPrice(price);
        cake.setSize(size);
        cake.setSpecial(special);
        
        try {
            System.out.println("EditCake: 1: " + action);
            if (action.equals("edit")) 
            {
                int cakeId = Integer.valueOf(request.getParameter("selectedCakeId"));
                System.out.println("CakeId "+cakeId);
                cake.setCakeId(cakeId);
                //try {
                cs.update(cake);
                //}
                /**
                catch(Exception e) {
                    System.out.println("3-1");
                    cs.delete(cakeId);
                    System.out.println("3-2");
                    CakeJpaController cjc = new CakeJpaController();
                    System.out.println("3-3");
                    cjc.create(cake);
                    System.out.println("3-4");
                }
                * */
                System.out.println("EditCake: 2-4");
                
            }
            else if (action.equals("add")) 
            {
                System.out.println("EditCake: 2-5");
                String imagePath = processFile(request, response);
                if (imagePath == null) {
                    imagePath = cs.get(1).getImage();
                }
                cake.setImage(imagePath);
                
                cs.insert(cake);
                
            }
            response.sendRedirect("managecakes");
        } catch(Exception e) {
            HttpSession session = request.getSession(true);
            String inputType = session.getAttribute("input")+"";
            System.out.println("Error!!!: " + e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
            response.sendRedirect("editcake?input=" + inputType);
        }
    }
    
    private String processFile(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException
    {
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        System.out.println("fileName: " + fileName);
        return null;
    }
}
