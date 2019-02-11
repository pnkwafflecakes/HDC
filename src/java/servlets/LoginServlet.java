/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Entities.Account;
import Entities.User;
import businesslogic.AccountService;
import businesslogic.UserService;
import dataaccess.AccountJpaController;
import dataaccess.UserJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
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
public class LoginServlet extends HttpServlet
{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        session.setAttribute("userObj", null);
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
        String userIn = request.getParameter("user");
        String passIn = request.getParameter("pass");
        Account account = null;
        String username = null;
        String password = null;
        User user = null;
        boolean found = false;
        int type = 0;
            
        AccountJpaController ajc = new AccountJpaController();
        List<Account> list = null;
        
        try {
                list = ajc.findAccountEntities();
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Before for");
        for (int i = 0; i < list.size(); i++) {
                username = list.get(i).getUsername();
                password = list.get(i).getPassword();
                if (username.equals(userIn) && password.equals(passIn)) {
                    account = list.get(i);
                    if (account.getAccountStatus() == true) {
                        System.out.println("user found");
                        type = account.getAccountType();
                        account = list.get(i);
                        found = true;
                        i = list.size();
                }
            }
        }
        if (found == true) {
            Collection<User> userList = account.getUserCollection();
            Object[] userObjectArray = userList.toArray();
            String a = ((User)userObjectArray[0]).getName();
            System.out.println(a);
            HttpSession session = request.getSession();
            session.setAttribute("userObj", user);
            getServletContext().getRequestDispatcher("/WEB-INF/mainmenu.jsp").forward(request, response);
        }
        
    }

}
