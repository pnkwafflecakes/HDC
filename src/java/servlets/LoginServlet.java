/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
//        processRequest(request, response);
        UserDB ud = new UserDB();
        String action = request.getParameter("action");
        String url="/login.jsp";
        HttpSession session = request.getSession();
         if(action.equals("login")){//login
            //get username and password from login.jsp
            String username= request.getParameter("username");
            String password= request.getParameter("password");

           if(username != null && password !=null && !username.equals("") && !password.equals("")){
               try {
                        //when username/password is not empty
                        //find user which match the username
                        User user = ud.getUser(username);
         //                     User user = new User("aaa","aaa","aaa@aaa.com",true,"first","last");
         //                     Role role1 = new Role(1,"admin");
         //                     Role role2 = new Role(2,"active user");
         //                     Role role3 = new Role(3,"inactive user");
         //                     user.setRole(role2);
                         if(user != null){//when username can find in userList

                             if(password.equals(user.getPassword())){ //when password is match
                                 //read user from txt file, set in session
                         //                                session.setAttribute("userList", loadUserList(path));
                                session.setAttribute("currentUser", user);
                                session.setAttribute("username", user.getUsername());
                                if(user.getActive()){
                                     if(user.getRole().getRoleID()==1){//when system admin, show viewaccount,edit account,sysadmin,company
                                        url="/notes";

                                    }else if(user.getRole().getRoleID()==2){//when regular user, show viewaccount,edit account
                                        url="/notes";

                                     }else if(user.getRole().getRoleID()==3){//when company admin, show viewaccount,edit account,company admin
                                        url="/notes";
                                     }
                                }else{
                                    url="/login.jsp";
                                    request.setAttribute("loginMessage", "Inactive User");
                                }
                             }else{ //when password is not match
                                 url="/login.jsp";
                                 request.setAttribute("loginMessage", "Invalid Password");
                             }
                         }else{//when username can not find in userList
                             url="/login.jsp";
                             request.setAttribute("loginMessage", "Invalid Username");
                         }
                } catch (NotesDBException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{//when username/password is empty
               url="/login.jsp";
               request.setAttribute("loginMessage", "Both values are required");
           } 
           // forward to the view
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    }

}
