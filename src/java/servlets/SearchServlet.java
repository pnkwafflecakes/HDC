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
public class SearchServlet extends HttpServlet
{
    
    private String searchWordGl = null;
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
        //Variable set up
        String searchWord = request.getParameter("searchWord");
        int i =0;
        boolean processing = true;
        ArrayList<Cake> searchList = new ArrayList();
        CakeService cs = new CakeService();
        boolean validCake;
        if (searchWord == null && searchWordGl != null) searchWord = searchWordGl;
        
        if (searchWord != null) {
            while (processing) {
                validCake = false;
                Cake cakeProc = cs.get(i);
                if (cakeProc == null) processing = false;
                else {
                    if (cakeProc.getName().contains(searchWord)) validCake = true;
                    else if (cakeProc.getDescription().contains(searchWord)) validCake = true;
                    
                    if (validCake) searchList.add(cakeProc);
                }
                i++;
            }
            
        }
        request.setAttribute("cakes", searchList);
        if (searchList.size() == 0) request.setAttribute("message", "No cakes containing that description or name");
        getServletContext().getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
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
        String searchWord = request.getParameter("searchbar");
        if (searchWord == null || searchWord.isEmpty()) searchWordGl = null;
        else searchWordGl = searchWord;
        doGet(request, response);
    }
}
