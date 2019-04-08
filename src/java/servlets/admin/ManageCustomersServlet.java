package servlets.admin;

import Entities.User;
import businesslogic.UserService;
import java.io.IOException;
import java.util.ArrayList;
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
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String selectedCustomer = request.getParameter("selectedCustomer");
        int customerId;

        UserService us = new UserService();
        List users = us.getAll();
        ArrayList<User> newList = new ArrayList();

        switch (action)
        {
            case "client":
                for (int i = 0; i < users.size(); i++)
                {
                    User user = (User) users.get(i);

                    if (user.getAccountType().getAccountType() == 1)
                    {
                        newList.add(user);
                    }
                }
                break;

            case "staff":
                for (int i = 0; i < users.size(); i++)
                {
                    User user = (User) users.get(i);

                    if (user.getAccountType().getAccountType() == 2)
                    {
                        newList.add(user);
                    }
                }
                break;

            case "delete":
                try
                {
                    customerId = Integer.parseInt(selectedCustomer);
                    User undoUser = us.get(customerId);
                    session.setAttribute("undoUser", undoUser);
                    us.destroy(customerId);
                    users = us.getAll();

                    request.setAttribute("notification", "User deleted.");
                    session.setAttribute("customers", users);
                    getServletContext().getRequestDispatcher("/WEB-INF/adminportal/managecustomers.jsp").forward(request, response);
                }
                catch (Exception ex)
                {
                    request.setAttribute("notification", "User not deleted.");
                    getServletContext().getRequestDispatcher("/WEB-INF/adminportal/managecustomers.jsp").forward(request, response);
                }
                break;

            case "view":
                customerId = Integer.parseInt(selectedCustomer);
                User viewUser = us.get(customerId);

                session.setAttribute("viewUser", viewUser);
                getServletContext().getRequestDispatcher("/WEB-INF/adminportal/viewuser.jsp").forward(request, response);
                break;

            case "edit":
                customerId = Integer.parseInt(selectedCustomer);
                User editUser = us.get(customerId);

                session.setAttribute("editUser", editUser);
                getServletContext().getRequestDispatcher("/WEB-INF/adminportal/edituser.jsp").forward(request, response);
                break;

            case "undo":
                try
                {
                    User undoUser = (User) session.getAttribute("undoUser");
                    us.create(undoUser);
                    request.setAttribute("notification", "Delete successfully un-done");
                    doGet(request, response);
                }
                catch (Exception e)
                {
                    request.setAttribute("notification", "Undo delete failed");
                    doGet(request, response);
                }
                break;

            default:
                for (int i = 0; i < users.size(); i++)
                {
                    User user = (User) users.get(i);
                    newList.add(user);
                }
                break;
        }

        session.setAttribute("customers", newList);
        getServletContext().getRequestDispatcher("/WEB-INF/adminportal/managecustomers.jsp").forward(request, response);
    }
}
