package servlets;

import Entities.Cake;
import businesslogic.CakeService;
import java.io.IOException;
import java.util.ArrayList;
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
 * This Java Servlet is responsible for handling the requests and responses of cakeinfo.jsp. It also
 * serves as the connection between cakeinfo.jsp and the database.
 */
public class CakeInfoServlet extends HttpServlet
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
        int cakeId = 1;

        try
        {
            cakeId = Integer.valueOf(request.getParameter("cakeid"));
        }
        catch (NumberFormatException e)
        {
        }

        CakeService service = new CakeService();
        Cake currCake = service.get(cakeId);

        request.setAttribute("currCake", currCake);
        getServletContext().getRequestDispatcher("/WEB-INF/cakeinfo.jsp").forward(request, response);
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

        ArrayList<Cake> cakes = (ArrayList<Cake>) session.getAttribute("cakes");

        if (cakes == null)
        {
            cakes = new ArrayList<Cake>();
        }

        int cakeId = Integer.valueOf(request.getParameter("cakeId"));

        CakeService cs = new CakeService();
        Cake currCake = cs.get(cakeId);
        int quantity = Integer.valueOf(request.getParameter("quantity"));
        for (int i = 0; i <= quantity; i++) {
            System.out.println("adding at instance: "+i);
            cakes.add(currCake);
        }

        session.setAttribute("cakes", cakes);
        response.sendRedirect("cart");
    }
}
