package servlets.admin;

import BusinessClasses.exceptions.IllegalOrphanException;
import BusinessClasses.exceptions.NonexistentEntityException;
import Entities.Delivery;
import Entities.Pickup;
import businesslogic.PickupService;
import dataaccess.PickupJpaController;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagePickupsServlet extends HttpServlet
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
        PickupJpaController pjc = new PickupJpaController();
        List<Pickup> pickupList = pjc.findPickupEntities();
        
        Pickup[] pickups = pickupList.toArray(new Pickup[pickupList.size()]);
        request.setAttribute("pickups", pickups);
        
        getServletContext().getRequestDispatcher("/WEB-INF/adminportal/managepickups.jsp").forward(request, response);
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
        try {
            HttpSession session = request.getSession(true);
            PickupService ps = new PickupService();
            String action = (String) request.getParameter("action");
            if (action.equals("add")) {
                String selectedPickupName = (String) request.getParameter("selectedPickupName");
                String selectedPickupAddress = (String) request.getParameter("selectedPickupAddress");
                Pickup pickup = new Pickup();
                pickup.setPickupName(selectedPickupName);
                pickup.setPickupAddress(selectedPickupAddress);
                request.setAttribute("notification", "New Pickup Location was added");
                ps.insert(pickup);

            }
            else if (action.equals("edit")) {
                String pickupName = (String) request.getParameter("pickupName");
                String pickupAddress = (String) request.getParameter("pickupAddress");
                int pickupId = Integer.valueOf(request.getParameter("selectedPickupId")+"");
                Pickup pickup = new Pickup();
                pickup.setPickupAddress(pickupAddress);
                pickup.setPickupName(pickupName);
                pickup.setPickupId(pickupId);
                ps.update(pickup);
                request.setAttribute("notification", "Edit was successful");
            }
            else if (action.equals("undo")) {
                Pickup undoPickup = (Pickup) session.getAttribute("undoPickup");
                if (undoPickup != null) {
                    request.setAttribute("notification", "Undo delete was successful");
                    ps.insert(undoPickup);
                }
                else {
                    request.setAttribute("notification", "No recently deleted pickups exist to undo deletion of");
                }
            }
            else if(action.equals("delete")) {
                int pickupId = Integer.valueOf(request.getParameter("selectedPickupId")+"");
                Pickup deletePickup = ps.get(pickupId);
                session.setAttribute("undoPickup", deletePickup);
                try {
                    ps.delete(pickupId);
                    request.setAttribute("notification", "Pickup Location was successfully deleted");
                } catch (IllegalOrphanException | NonexistentEntityException | dataaccess.exceptions.NonexistentEntityException e) {
                    request.setAttribute("notification", "Cannot delete this location because it is being used for a delivery");
                }
            }
            else if (action.equals("view")) {
                int selectedPickupId = Integer.valueOf(request.getParameter("selectedPickupId"));
                Pickup selectedPickup = ps.get(selectedPickupId);
                request.setAttribute("selectedPickup", selectedPickup);
                request.setAttribute("notification", "Entry can now be edited under the \"Edit Pickup\" section");
            }
            doGet(request, response);
        } catch(Exception e) {
            request.setAttribute("notification", "An error has occured");
            doGet(request, response);
        }
    }
}
