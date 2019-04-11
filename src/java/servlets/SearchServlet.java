package servlets;

import Entities.Cake;
import businesslogic.CakeService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nickolas Barnes
 * @version 1.0
 */
public class SearchServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response, String searchWord)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        CakeService cs = new CakeService();
        List<Cake> tmparr = cs.getAll();
        ArrayList<Cake> cakearray = new ArrayList<>();

        if (tmparr != null) {
            if (!tmparr.isEmpty()) {
                for (Cake tmp : tmparr) {
                    cakearray.add(tmp);
                }
            }
        }

        request.setAttribute("initword", searchWord);
        request.setAttribute("allcakes", cakearray);

        String language = (String) session.getAttribute("language");
        if (language == null) {
            language = "en";
        }
        if (language.equals("cn")) {
            getServletContext().getRequestDispatcher("/WEB-INF/cn/search.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/en/search.jsp").forward(request, response);
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
        String searchWord = request.getParameter("searchWord");
        doGet(request, response, searchWord);
    }
}
