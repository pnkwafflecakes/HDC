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
import businesslogic.CakeService;
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
public class OrderDetailsServlet extends HttpServlet {

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
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("language");

        //--*-- Simualted part
//        System.out.println("YES");
//        HttpSession session = request.getSession();
//        //User user = (User) session.getAttribute("userObj");
//        
//       
//        UserService us = new UserService();
//        User user = us.get(1);
//        System.out.println("User in OrderDetails: " + user);
//        
//        
//        request.setAttribute("address", user.getAddress());
//        System.out.println(user.getAddress());
//        request.setAttribute("phoneNo", user.getPhoneNo());
        //--*--
        
        
//        getServletContext().getRequestDispatcher("/WEB-INF/orderdetails.jsp").forward(request, response);

        if (language == null) {
            language = "en";
        }
        if (language.equals("cn")) {
            getServletContext().getRequestDispatcher("/WEB-INF/cn/orderdetails.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/en/orderdetails.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String address = request.getParameter("address") + "";
        String method = request.getParameter("methodList");
        String notes = request.getParameter("notes") + "";
        String phoneNo = request.getParameter("phoneNo") + "";
        System.out.println("Gathered values:");
        System.out.println("Address: " + address);
        System.out.println("Method: " + method);
        System.out.println("Notes: " + notes);
        System.out.println("PhoneNo " + phoneNo);
        int deliveryNo = 1;

        DeliveryService ds = new DeliveryService();

        System.out.println("--*-- Finding good ID");
        boolean notFound = true;
        while (notFound) {
            if (ds.get(deliveryNo) != null) {
                deliveryNo++;
            } else {
                notFound = false;
            }
        }
        System.out.println("--*-- ID found: " + deliveryNo);

        Delivery delivery = new Delivery();
        delivery.setAddress(address);
        delivery.setDeliveryNo(deliveryNo);
        delivery.setMethod(method);
        delivery.setNotes(notes);
        delivery.setPhoneNo(phoneNo);
        delivery.setMethod(method);
        DeliveryJpaController djc = new DeliveryJpaController();
        try {
            djc.create(delivery);
            System.out.println("Delivery creation successful");
        } catch (Exception ex) {
            System.out.println("Creation failed, error message: " + ex.getMessage());
        }

        DBEntry dbEntry = new DBEntry();

        CakeService cs = new CakeService();
        HttpSession session = request.getSession(true);
        ArrayList<Integer> cakes = (ArrayList<Integer>) session.getAttribute("cakes");
        System.out.println(cakes);
        Cake[] cakeArray = new Cake[cakes.size()];
        for (int i = 0; i < cakes.size(); i++) {
            cakeArray[i] = cs.get(cakes.get(i));
        }

        //--*-- Simualted part
//        UserService us = new UserService();
//        User user = us.get(1);
        //--*--
        User user = (User) session.getAttribute("userObj");

        String returnPage = "";

        if (dbEntry.inserOrderDB(cakeArray, user, delivery)) {
            //clear cakes 
            cakes = new ArrayList<>();
            session.setAttribute("cakes", cakes);
            getServletContext().getRequestDispatcher("/WEB-INF/successorder.jsp").forward(request, response);
        } else {
            returnPage = "mainmenu?result=fail";
            response.sendRedirect(returnPage);
        }

    }
}
