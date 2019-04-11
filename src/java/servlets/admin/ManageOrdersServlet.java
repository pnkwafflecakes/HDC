package servlets.admin;

import Entities.Cakeorder;
import Entities.CakeorderPK;
import Entities.Delivery;
import Entities.Orders;
import Entities.User;
import businesslogic.DeliveryService;
import businesslogic.OrderService;
import dataaccess.CakeorderJpaController;
import dataaccess.DeliveryJpaController;
import dataaccess.OrdersJpaController;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManageOrdersServlet extends HttpServlet
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
//        getServletContext().getRequestDispatcher("/WEB-INF/adminportal/manageorders.jsp").forward(request, response);
     OrderService os = new OrderService();
     DeliveryService ds = new DeliveryService();
     CakeorderJpaController cojc = new CakeorderJpaController();
        
     String action = request.getParameter("action");
     String url = "/WEB-INF/adminportal/manageorders.jsp";
     HttpSession session = request.getSession();
     if(action != null && action.equals("logout")){
         url = "/mainmenu";
         session.invalidate();
          getServletContext()
            .getRequestDispatcher(url)
                .forward(request, response);
          return;
     }else if(action != null && action.equals("view")){
         int selectedOrderId = Integer.parseInt(request.getParameter("selectedOrderId"));
         try{
            //get order
            Orders selectedOrder = os.get(selectedOrderId);
            //get order related delivery
            Delivery delivery = selectedOrder.getDeliveryNo();
            //get user
            User user = selectedOrder.getUserId();
            //get cakeOrder
            List<Cakeorder> cakeOrders = cojc.findCakeorderByOrderNo(selectedOrderId);
            
            request.setAttribute("selectedOrder", selectedOrder);
            request.setAttribute("delivery", delivery);
            request.setAttribute("user", user);
            request.setAttribute("cakeOrders", cakeOrders);
            //put cakeOrders in session for doPost change qty
            session.setAttribute("cakeOrdersSession", cakeOrders);
            
            
            url = "/WEB-INF/adminportal/manageorders.jsp";
         } catch (Exception ex) {
                        Logger.getLogger(ManageOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
     }
     
     //get all orders
     List<Orders> orders;
     try{
         orders = os.getAll();
             
         request.setAttribute("orders", orders);
     }catch (Exception ex) {
                    Logger.getLogger(ManageOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
     
     // forward to the view
        getServletContext()
            .getRequestDispatcher(url)
                .forward(request, response);
    
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

        String url = "/WEB-INF/adminportal/manageorders.jsp";
        OrderService os = new OrderService();

        OrdersJpaController ojc = new OrdersJpaController();
        DeliveryJpaController djc = new DeliveryJpaController();
        CakeorderJpaController cojc = new CakeorderJpaController();
        HttpSession session = request.getSession();
        
        boolean active = false;
        boolean confirmed = false;
        boolean paid = false;
        
        try{
            
            String action = request.getParameter("action");
            
            if(action != null && action.equals("delete")){
              int selectedOrderId = Integer.parseInt(request.getParameter("selectedOrderId"));
              Orders undoOrder = ojc.findOrders(selectedOrderId);
              session.setAttribute("undoOrder", undoOrder);
              ojc.destroy(selectedOrderId);
            }
            else if (action != null && action.equals("undo")) {
                Orders undoOrder = (Orders) session.getAttribute("undoOrder");
                if (undoOrder != null) {
                    ojc.create(undoOrder);
                    request.setAttribute("errorMessage", "Undo Delete was successful");
                }
            }
            
            else if(action != null && action.equals("edit")){
                //save edit order
                int selectedOrderId = Integer.parseInt(request.getParameter("selectedOrderId"));//not change
                Orders orderOld = ojc.findOrders(selectedOrderId);
                Date order_date    = orderOld.getOrderDatetime();//not change
                Date due_date      = orderOld.getDueDatetime();//not change
                String order_items = request.getParameter("orderItems");
                double total_price = Double.parseDouble(request.getParameter("totalPrice"));
                int delivery_no    = orderOld.getDeliveryNo().getDeliveryNo();//not change
//                get active checkbox
                String[] activeCheck = request.getParameterValues("active");
                if (activeCheck==null) active = false;
                else if (activeCheck[0].equals("on")) active = true;
    
//                get confirmed checkbox
                String[] confirmedCheck = request.getParameterValues("confirmed");
                if (confirmedCheck==null) confirmed = false;
                else if (confirmedCheck[0].equals("on")) confirmed = true;
               
//                get paid checkbox
                String[] paidCheck = request.getParameterValues("paid");
                if (paidCheck==null) paid = false;
                else if (paidCheck[0].equals("on")) paid = true;
                
                orderOld.setTotalPrice(total_price);
                orderOld.setActive(active);
                orderOld.setConfirmed(confirmed);
                orderOld.setPaid(paid);
                
                ojc.edit(orderOld);
                
                //save edit delivery
                String method  = request.getParameter("method");
                String address = request.getParameter("address");
                String phoneNo = request.getParameter("phoneNo");
                String notes   = request.getParameter("notes");

                Delivery deliveryOld = djc.findDelivery(delivery_no);
                deliveryOld.setMethod(method);
                deliveryOld.setAddress(address);
                deliveryOld.setPhoneNo(phoneNo);
                deliveryOld.setNotes(notes);
                   
                djc.edit(deliveryOld);
                
            }else if(action != null && action.equals("changeQuantity")){
                int selectedCakeOrder = Integer.parseInt(request.getParameter("selectedCakeOrder"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                List<Cakeorder> cakeOrdersOld = (List<Cakeorder>)session.getAttribute("cakeOrdersSession");
                List<Cakeorder> cakeOrdersNew = cojc.findCakeorderByOrderNo(cakeOrdersOld.get(0).getOrders().getOrderNo());
                
                Cakeorder cakeOrderCurrent = cakeOrdersNew.get(selectedCakeOrder);
                
                     cakeOrderCurrent.setQuantity(quantity);
                     cojc.edit(cakeOrderCurrent);
                
              
//              calculate subtotal
//                cakeOrders = (List<Cakeorder>)session.getAttribute("cakeOrders");
                cakeOrdersNew = cojc.findCakeorderByOrderNo(cakeOrdersOld.get(0).getOrders().getOrderNo());
                double total = 0;
                for (Cakeorder co : cakeOrdersNew){
                    total = total + co.getQuantity()*co.getCake().getPrice();
                }
                //change total price for this order
                int selectedOrderId = Integer.parseInt(request.getParameter("selectedOrderId"));
                Orders orderOld = ojc.findOrders(selectedOrderId);
                orderOld.setTotalPrice(total);
                ojc.edit(orderOld);
                
                 
                //if qty =0 , remove this cake order
                if(quantity == 0){
                     cojc.destroy(cakeOrderCurrent.getCakeorderPK());
                }
                
                //update cake item  
                cakeOrdersNew = cojc.findCakeorderByOrderNo(cakeOrdersOld.get(0).getOrders().getOrderNo());
                
                String orderItems = "";
                if(cakeOrdersNew == null){
                    orderItems = "";
                }else{
                    orderItems = "";
                    for (Cakeorder co : cakeOrdersNew){
                        orderItems = orderItems + co.getCake().getName()+",";
                        }
                }
                orderOld.setOrderItems(orderItems);
                ojc.edit(orderOld);
            }
        }catch (Exception ex) {
                    Logger.getLogger(ManageOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
        
        //get all orders
     List<Orders> orders;
     try{
         orders = os.getAll();
             
         request.setAttribute("orders", orders);
//         sent selectedOrder back to jsp, so edit part can show
         int selectedOrderId = Integer.parseInt(request.getParameter("selectedOrderId"));
         //get order
            Orders selectedOrder = ojc.findOrders(selectedOrderId);
            //get order related delivery
            Delivery delivery = selectedOrder.getDeliveryNo();
            //get user
            User user = selectedOrder.getUserId();
            //get cakeOrder
            List<Cakeorder> cakeOrders = cojc.findCakeorderByOrderNo(selectedOrderId);
            
            request.setAttribute("selectedOrder", selectedOrder);
            request.setAttribute("delivery", delivery);
            request.setAttribute("user", user);
            request.setAttribute("cakeOrders", cakeOrders);
     }catch (Exception ex) {
                    Logger.getLogger(ManageOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
     
     // forward to the view
        request.setAttribute("selectedOrder", null);
        
        getServletContext()
            .getRequestDispatcher(url)
                .forward(request, response);
    }
}
