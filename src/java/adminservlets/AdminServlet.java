package adminservlets;

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

public class AdminServlet extends HttpServlet
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
        String action = request.getParameter("action");
        if (action != null && action.equals("view"))
        {
            String selectedUsername = request.getParameter("selectedUsername");
            try
            {
                User user = us.get(0001);
                request.setAttribute("selectedUser", user);
            }
            catch (Exception ex)
            {
            }
        }

        List<User> users = us.getAll();

        User currUser = (User) session.getAttribute("currUser");
        List<User> companyUsers = new ArrayList<>();

        if (currUser.getRole().getRoleID() == 3)
        {
            for (User toFind : users)
            {
                if (toFind.getCompany().getCompanyID() == currUser.getCompany().getCompanyID())
                {
                    companyUsers.add(toFind);
                    request.setAttribute("users", companyUsers);
                }
            }
        }
        else if (currUser.getRole().getRoleID() == 1)
        {
            request.setAttribute("users", users);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
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

        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        int companyAdd = 0;
        int roleAdd = 0;

        if (action.equals("edit") || action.equals("add"))
        {
            companyAdd = Integer.parseInt(request.getParameter("company"));
            roleAdd = Integer.parseInt(request.getParameter("role"));
        }

        Company company = new Company();
        Role role = new Role();
        User owner = (User) session.getAttribute("currUser");

        if (owner.getRole().getRoleID() == 1)
        {
            if (companyAdd == 1)
            {
                company.setCompanyID(1);
                company.setCompanyName("SAIT");
            }
            else if (companyAdd == 2)
            {
                company.setCompanyID(2);
                company.setCompanyName("Star Trek");
            }
            else if (companyAdd == 3)
            {
                company.setCompanyID(3);
                company.setCompanyName("My Little Pony");
            }
        }
        else
        {
            company.setCompanyID(owner.getCompany().getCompanyID());
            company.setCompanyName(owner.getCompany().getCompanyName());
        }

        if (roleAdd == 1)
        {
            role.setRoleID(1);
            role.setRoleName("system admin");
        }
        else if (roleAdd == 2)
        {
            role.setRoleID(2);
            role.setRoleName("regular user");
        }
        else if (roleAdd == 3)
        {
            role.setRoleID(3);
            role.setRoleName("company admin");
        }

        String selectedUsername = request.getParameter("selectedUsername");

        UserService us = new UserService();

        try
        {
            if (action.equals("delete"))
            {
                us.delete(selectedUsername);
            }
            else if (action.equals("edit"))
            {
                us.update(username, password, email, true, firstname, lastname, company, role);
            }
            else if (action.equals("add"))
            {
                us.insert(username, password, email, true, firstname, lastname, company, role);
            }
        }
        catch (Exception ex)
        {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.\n" + ex.getMessage());
        }

        List<User> users = us.getAll();
        List<User> companyUsers = new ArrayList<>();

        if (owner.getRole().getRoleID() == 3)
        {
            for (User toFind : users)
            {
                if (toFind.getCompany().getCompanyID() == owner.getCompany().getCompanyID())
                {
                    companyUsers.add(toFind);
                    request.setAttribute("users", companyUsers);
                }
            }
        }
        else if (owner.getRole().getRoleID() == 1)
        {
            request.setAttribute("users", users);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }
}
