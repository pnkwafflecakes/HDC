/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin;

import Entities.Cake;
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
    private String changed = null;
    boolean exists = false;
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
        exists = false;
        String inputType = request.getParameter("input");
        name = request.getParameter("imagename");
        changed = request.getParameter("changed");

        HttpSession session = request.getSession(true);
        //request.setAttribute("input", inputType);
        session.setAttribute("input", inputType);
        
        
        if (changed != null) {
                String newPath = path+name;
                request.setAttribute("imagePath", newPath);
                request.setAttribute("changed", "1");
        }
        
        if (inputType.equals("edit")) {
            CakeService cs = new CakeService();
            int cakeId = Integer.valueOf(session.getAttribute("cakeId")+"");
            Cake cake = cs.get(cakeId);
            request.setAttribute("cake", cake);
            if (cs.get(cakeId).getOrdersCollection().size() != 0) {
                request.setAttribute("notification", "This cake is in a current order. Will become new cake with changes");
                exists = true;
            }
        }
        
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
        
        //name
        String name = request.getParameter("name");
        
        //Description
        String description = request.getParameter("description");
        //Featured
        String[] featuredCheck = request.getParameterValues("featured");
        if (featuredCheck==null) featured = false;
        else if (featuredCheck[0].equals("on")) featured = true;
        //Price
        double price = Double.valueOf(request.getParameter("price"));
        //Size
        int size = Integer.valueOf(request.getParameter("size"));
        //Special
        String[] specialCheck = request.getParameterValues("special");
        if (specialCheck==null) featured = false;
        else if (specialCheck[0].equals("on")) special = true;
        
        int categoryId = Integer.valueOf(request.getParameter("categorySelect"));      
        
        //String namecn = request.getParameter("namecn");
        String descriptioncn = new String(request.getParameter("descriptioncn").getBytes("ISO-8859-1"),"utf-8");
        String namecn = new String(request.getParameter("namecn").getBytes("ISO-8859-1"),"utf-8");
        
        
        

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
            if (action.equals("edit")) 
            {
                int cakeId = Integer.valueOf(request.getParameter("selectedCakeId"));
                cake.setCakeId(cakeId);
                
                String imagePath = path+this.name;
                if (imagePath == null) {
                    imagePath = cs.get(cakeId).getImage();
                }
                cake.setImage(imagePath);
                
                if (exists==false) cs.update(cake);
                else if (exists==true) {
                    cake.setActive(false);
                    cs.update(cake);
                    cs.insert(cake);
                }
                

                
            }
            else if (action.equals("add")) 
            {
                String imagePath = path+this.name;
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
            response.sendRedirect("editcake?input=" + inputType);
        }
    }

}
