/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author 775224
 */
public class UploadServlet extends HttpServlet {

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
        String inputType = request.getParameter("input");
        String name = null;
        try {
            
        ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> files = sfu.parseRequest(request);
        
        for (FileItem item: files)
        {
            name = item.getName();
            String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\HDCProject\\images" + item.getName();
            item.write(new File(path));
        }
        } catch (Exception ex) {
        }
        response.sendRedirect("editcake?input=" + inputType+"&imagename=" + name+"&changed=1");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
