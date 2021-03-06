package servlets;

import Entities.User;
import dataaccess.UserJpaController;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 744916
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String act = request.getParameter("act");

        if (act != null) {
            if (act.equals("logout")) {
                session.setAttribute("userObj", null);
                getServletContext().getRequestDispatcher("/mainmenu").forward(request, response);
            }
        }

        String language = (String) session.getAttribute("language");

        if (language == null) {
            language = "en";
        }
        if (language.equals("cn")) {
            getServletContext().getRequestDispatcher("/WEB-INF/cn/login.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/en/login.jsp").forward(request, response);
        }

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
        HttpSession session = request.getSession();

        String userIn = request.getParameter("user");
        String passIn = request.getParameter("pass");
        String username;
        String password;

        // sets values so the information stays after refresh
        request.setAttribute("user", userIn);

        // validation for empty fields
        if (userIn == null || userIn.isEmpty()
                || passIn == null || passIn.isEmpty()) {
            request.setAttribute("errorMessage", "Please enter all values.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

        UserJpaController ujc = new UserJpaController();
        List<User> userList = null;

        try {
            userList = ujc.findUserEntities();
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Could not load account list. Please contact administration.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        boolean valid = false;
        for (int i = 0; i < userList.size(); i++) {

            username = userList.get(i).getUsername();
            password = userList.get(i).getPassword();

            if (username.equals(userIn) && password.equals(passIn)) {
                User user = userList.get(i);
                if (user.getAccountStatus() == true) {
                    valid = true;
                    String redir = "login";
                    user.getAccountType();
                    if (user.getAccountType().getAccountType() == 2) {
                        session.setAttribute("admin", user);
                        redir = "manageorders";
                    } else if (user.getAccountType().getAccountType() == 1) {
                        session.setAttribute("userObj", user);
                        redir = "mainmenu";
                    }
                    response.sendRedirect(redir);
                } else {
                    request.setAttribute("errorMessage", "Account not active. Please contact administrator.");
                    response.sendRedirect("login");
                }
            }
            if (valid == true) {
                i = userList.size();
            }
        }
        if (valid == false) {
            request.setAttribute("errorMessage", "Invalid Username/Password");
            String language = (String) session.getAttribute("language");

            if (language == null) {
                language = "en";
            }
            if (language.equals("cn")) {
                getServletContext().getRequestDispatcher("/WEB-INF/cn/login.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/en/login.jsp").forward(request, response);
            }
        }
    }
}
