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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author 744916
 */
@MultipartConfig
public class EditCakeServlet extends HttpServlet
{
    
    private String path = "/images/";
    private String name = null;
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
        name = request.getParameter("imagename");
        System.out.println("Image name: " + name);

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
        else {
            if (name != null) {
                System.out.println("Path: " + path+name);
                String newPath = path+name;
                request.setAttribute("imagePath", newPath);
            }
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
        
        //name
        String name = request.getParameter("name");
        
        //Description
        String description = request.getParameter("description");
        //Featured
        String[] featuredCheck = request.getParameterValues("featured");
        if (featuredCheck==null) featured = false;
        else if (featuredCheck[0].equals("on")) featured = true;
        else System.out.println("Featured Check value " + featuredCheck[0]);
        //Price
        double price = Double.valueOf(request.getParameter("price"));
        //Size
        int size = Integer.valueOf(request.getParameter("size"));
        //Special
        String[] specialCheck = request.getParameterValues("special");
        if (specialCheck==null) featured = false;
        else if (specialCheck[0].equals("on")) special = true;
        else System.out.println("Special Check value " + specialCheck[0]);
        
        int categoryId = Integer.valueOf(request.getParameter("categorySelect"));      
        
        //String namecn = request.getParameter("namecn");
        String descriptioncn = new String(request.getParameter("descriptioncn").getBytes("ISO-8859-1"),"utf-8");
        String namecn = new String(request.getParameter("namecn").getBytes("ISO-8859-1"),"utf-8");
        
        
        System.out.println("可可脂蛋糕");
        System.out.println("CN Name: " + namecn + ", CN Desc: " + descriptioncn);
        
        cake.setCategoryId(ccs.get(categoryId));
        cake.setName(name);
        cake.setNamecn(namecn);
        cake.setDescription(description);
        cake.setDescriptioncn(descriptioncn);
        cake.setFeatured(featured);
        cake.setPrice(price);
        cake.setSize(size);
        cake.setSpecial(special);
        
        CakeJpaController cjc = new CakeJpaController();
        
        try {
            System.out.println("EditCake: 1: " + action);
            if (action.equals("edit")) 
            {
                int cakeId = Integer.valueOf(request.getParameter("selectedCakeId"));
                System.out.println("CakeId "+cakeId);
                cake.setCakeId(cakeId);
                
                String imagePath = path+this.name;
                if (imagePath == null) {
                    imagePath = cs.get(cakeId).getImage();
                }
                cake.setImage(imagePath);
                
                cs.update(cake);
                

                System.out.println("EditCake: 2-4");
                
            }
            else if (action.equals("add")) 
            {

                System.out.println("EditCake: 2-5");
                String imagePath = path+this.name;
                if (imagePath == null) {
                    imagePath = cs.get(1).getImage();
                }
                cake.setImage(imagePath);
                System.out.println("cake values: ");
                System.out.println("id: "+cake.getCakeId());
                System.out.println("Desc: "+cake.getDescription());
                System.out.println("cn desc: " + cake.getDescriptioncn());
                System.out.println("image: "+ cake.getImage());
                System.out.println("Name: "+cake.getName());
                System.out.println("Name cn: "+cake.getNamecn());
                System.out.println("Size: " + cake.getSize());
                System.out.println("Price: " + cake.getPrice());
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

}
