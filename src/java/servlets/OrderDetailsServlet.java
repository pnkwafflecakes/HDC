/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import BusinessClasses.DBEntry;
import Entities.Cake;
import Entities.Delivery;
import Entities.User;
import businesslogic.DeliveryService;
import businesslogic.UserService;
import dataaccess.DeliveryJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class OrderDetailsServlet extends HttpServlet
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
        System.out.println("YES");
        HttpSession session = request.getSession();
        //User user = (User) session.getAttribute("userObj");
        
        //--*-- Simualted part
        UserService us = new UserService();
        User user = us.get(0);
        System.out.println("User in OrderDetails: " + user);
        //--*--
        
        request.setAttribute("address", user.getAddress());
        System.out.println(user.getAddress());
        request.setAttribute("phoneNo", user.getPhoneNo());
        
        getServletContext().getRequestDispatcher("/WEB-INF/orderdetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String address = request.getAttribute("address")+"";
        String method = request.getParameter("method");
        String notes = request.getAttribute("notes")+"";
        String phoneNo = request.getAttribute("phoneNo")+"";
        int deliveryNo = 0;
        
        DeliveryService ds = new DeliveryService();
 
        System.out.println("--*-- Finding good ID");
        boolean notFound = true;
        while (notFound) {
            if (ds.get(deliveryNo) != null) deliveryNo++;
            else notFound = false;
        }
        System.out.println("--*-- ID found: " + deliveryNo);
        
        Delivery delivery = new Delivery();
        delivery.setAddress(address);
        delivery.setDeliveryNo(deliveryNo);
        delivery.setMethod(method);
        delivery.setNotes(notes);
        delivery.setPhoneNo(phoneNo);
        DeliveryJpaController djc = new DeliveryJpaController();
        try {
            djc.create(delivery);
            System.out.println("Delivery creation successful");
        } catch (Exception ex) {
            System.out.println("Creation failed, error message: "+ex.getMessage());
        }
        
        DBEntry dbEntry = new DBEntry();
        
        HttpSession session = request.getSession(true);
        ArrayList<Cake> cakes = (ArrayList<Cake>) session.getAttribute("cakes");
        System.out.println(cakes);
        Cake[] cakeArray = new Cake[cakes.size()];
        for (int i = 0; i < cakes.size(); i++) {
            cakeArray[i] = cakes.get(i);
        }
        
        UserService us = new UserService();
        User user = us.get(0);
        String returnPage = "";
        
        if (dbEntry.inserOrderDB(cakeArray, user, delivery)) returnPage = "mainmenu?result=fail";
        else returnPage = "mainmenu?result=success";
        System.out.println(returnPage);
        response.sendRedirect(returnPage);
    }
}
