/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Entities.User;
import businesslogic.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 775224
 */
public class ManageAccount extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = null;
        UserService us;
        us = new UserService();
        
        getServletContext().getRequestDispatcher("/WEB-INF/manageaccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("userObj");

            UserService us;
            us = new UserService();

            String prompt = null;


            if (action.equals("changePassword")) {
                String password = user.getPassword();
                String currentPassword = request.getParameter("currentPassword");

                if ((!currentPassword.isEmpty()) && password.equals(currentPassword)) {
                    String newPassword = request.getParameter("newPassword");
                    String newPasswordConfirm = request.getParameter("newPasswordConfirm");

                    if (!newPassword.isEmpty() && !newPasswordConfirm.isEmpty()) {
                        if (newPassword.equals(newPasswordConfirm)) {
                            user.setPassword(newPassword);
                            us.edit(user);
                            prompt = "Successfully changed password";
                        }
                        else prompt = "Password and confirm password are different";
                    }
                    else prompt = "Please fill in all password fields";
                }
                else prompt = "Password is incorrect";
            }
            else if(action.equals("change")){
                String address = request.getParameter("address");
                String email = request.getParameter("email");
                String name = request.getParameter("name");
                String phoneNo = request.getParameter("phone");
                String postalCode = request.getParameter("postal");
                String username = request.getParameter("username");

                user.setAddress(address);
                user.setEmail(email);
                user.setName(name);
                user.setPhoneNo(phoneNo);
                user.setPostalCode(postalCode);
                user.setUsername(username);
                
                System.out.println("Address"+address);
                System.out.println("Email: " + email);
                System.out.println("Name: " + name);
                System.out.println("PhoneNo" + phoneNo);
                System.out.println("Postal Code: " + postalCode);
                System.out.println("Username" + username);
                
                us.edit(user);
                prompt = "Data changed successfully!";
            }

            
            request.setAttribute("prompt", prompt);
            
            doGet(request, response);
        } catch (Exception e) {
            request.setAttribute("prompt", "Something went wrong");
            System.out.println("Error in Manage Account: "+e.getMessage());
            doGet(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
