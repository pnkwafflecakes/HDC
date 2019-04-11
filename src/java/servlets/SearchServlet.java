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
        ArrayList<Cake> searchList = new ArrayList();
        CakeService cs = new CakeService();
        boolean validCake;
        
        HttpSession session = request.getSession(true);
        String searchWord = session.getAttribute("searchWord")+"";
        
        if (searchWord != null) {
            List<Cake> cakeListDB = cs.getAll();
            for (int i=0; i < cakeListDB.size(); i++) {
                validCake = false;
                Cake cakeProc = cakeListDB.get(i);
                
                if (cakeProc.getName().toLowerCase().contains(searchWord.toLowerCase())) validCake = true;
                else if (cakeProc.getDescription().toLowerCase().contains(searchWord.toLowerCase())) validCake = true;
                    
                if (validCake) searchList.add(cakeProc);
            }
            
        }
        request.setAttribute("cakes", searchList);
        if (searchList.isEmpty()) request.setAttribute("message", "No cakes containing that description or name");
        
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
            throws ServletException, IOException
    {
        String searchWord = request.getParameter("searchWord");
        HttpSession session = request.getSession(true);
        session.setAttribute("searchWord", searchWord);
        System.out.println("SearchWord: " +searchWord);
        System.out.println("Search Word in Session: " + session.getAttribute("searchWord"));
        doGet(request, response);
    }
}
