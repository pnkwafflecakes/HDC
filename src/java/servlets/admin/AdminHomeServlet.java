package servlets.admin;

import Entities.Cake;
import Entities.Orders;
import Entities.User;
import businesslogic.CakeService;
import businesslogic.OrderService;
import businesslogic.UserService;
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
 * @version 1.0
 *
 * This Java Servlet is responsible for handling the requests and responses of adminhome.jsp. It
 * also serves as the connection between adminhome.jsp and the database. This servlet also directs a
 * user to various other administration pages.
 */
public class AdminHomeServlet extends HttpServlet
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
        HttpSession session = request.getSession();

        // Gets lists of all entities needed to populate stats table
        CakeService cs = new CakeService();
        List<Cake> cakes = cs.getAll();
        UserService us = new UserService();
        List<User> users = us.getAll();
        OrderService os = new OrderService();
        List<Orders> orders = os.getAll();

        // Intitialize all variables needed for statistics
        int totalCakes = 0;
        String lastAdded = "";
        int totalCustomers = 0;
        int totalStaff = 0;
        int totalUsers = 0;
        int totalOrders = 0;

        // Populate variables for cake stats
        totalCakes = cakes.size();
        lastAdded = cakes.get(cakes.size() - 1).getName();

        // Populate variables for user stats
        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getAccountType().getAccountType() == 1)
            {
                totalCustomers++;
            }
            else
            {
                totalStaff++;
            }
        }
        totalUsers = users.size();

        // Populate variables for order stats
        totalOrders = orders.size();

        // Set variables to session to display in table
        session.setAttribute("cakeNumber", totalCakes);
        session.setAttribute("lastCake", lastAdded);
        session.setAttribute("custNumber", totalCustomers);
        session.setAttribute("staffNumber", totalStaff);
        session.setAttribute("totalNumber", totalUsers);
        session.setAttribute("totalorders", totalOrders);

        getServletContext().getRequestDispatcher("/WEB-INF/adminportal/adminhome.jsp").forward(request, response);
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
    }
}
