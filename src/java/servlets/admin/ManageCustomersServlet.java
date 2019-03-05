package servlets.admin;

import Entities.User;
import businesslogic.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManageCustomersServlet extends HttpServlet
{
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
        HttpSession session = request.getSession(true);

        UserService us = new UserService();
        List users = us.getAll();

        session.setAttribute("customers", users);

        getServletContext().getRequestDispatcher("/WEB-INF/adminportal/managecustomers.jsp").forward(request, response);
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
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);
        String action = request.getParameter("usertype");
        List users = (List) session.getAttribute("customers");

        List newList = null;

        if (action.equals("customers"))
        {
            for (int i = 0; i < users.size(); i++)
            {
                User user = (User) users.get(i);

                if (user.getAccountType().equals(1))
                {
                    newList.add(user);
                }
            }
        }
    }
}
