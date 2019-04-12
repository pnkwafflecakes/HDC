package servlets.admin;

import Entities.Accounttype;
import Entities.User;
import businesslogic.UserService;
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
 * @author Adam Schlinker
 */
public class EditUserServlet extends HttpServlet
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
        UserJpaController userJPA = new UserJpaController();

        String userID = request.getParameter("selectedCustomer");
        int intUserID = Integer.parseInt(userID);

        User editedUser = userJPA.findUser(intUserID);

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String postalCode = request.getParameter("postalcode");
        String email = request.getParameter("email");
        String phoneNo = request.getParameter("phoneNumber");
        String accountType = request.getParameter("accountType");
        int intAccountType = Integer.parseInt(accountType);
        Accounttype at = new Accounttype(intAccountType);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String accountStatus = request.getParameter("active");
        boolean accountBoolean = Boolean.parseBoolean(accountStatus);

        editedUser.setUserId(editedUser.getUserId());
        editedUser.setName(name);
        editedUser.setAddress(address);
        editedUser.setPostalCode(postalCode);
        editedUser.setEmail(email);
        editedUser.setPhoneNo(phoneNo);
        editedUser.setAccountType(at);
        editedUser.setUsername(username);
        editedUser.setPassword(password);
        editedUser.setAccountStatus(accountBoolean);

        try
        {
            userJPA = new UserJpaController();
            userJPA.edit(editedUser);
        }
        catch (Exception ex)
        {
            request.setAttribute("notification", "User not saved.");
            response.sendRedirect("managecustomers?action=view");
        }

        HttpSession session = request.getSession(true);
        UserService us = new UserService();
        List users = us.getAll();

        session.setAttribute("customers", users);
        request.setAttribute("notification", "User saved.");
        getServletContext().getRequestDispatcher("/WEB-INF/adminportal/managecustomers.jsp").forward(request, response);
    }
}
