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

public class ManageOrdersServletBackup extends HttpServlet
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
        System.out.println("manageorders");
//        getServletContext().getRequestDispatcher("/WEB-INF/adminportal/manageorders.jsp").forward(request, response);
     OrderService os = new OrderService();
     DeliveryService ds = new DeliveryService();
     CakeorderJpaController co = new CakeorderJpaController();
        
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
            List<Cakeorder> cakeOrders = co.findCakeorderByOrderNo(selectedOrderId);
            
            request.setAttribute("selectedOrder", selectedOrder);
            request.setAttribute("delivery", delivery);
            request.setAttribute("user", user);
            request.setAttribute("cakeOrders", cakeOrders);
            
            url = "/WEB-INF/adminportal/manageorders.jsp";
         } catch (Exception ex) {
                        Logger.getLogger(ManageOrdersServletBackup.class.getName()).log(Level.SEVERE, null, ex);
                    }
     }
     
     //get all orders
     List<Orders> orders;
     try{
         orders = os.getAll();
         for(Orders order:orders){
             System.out.println(order.getOrderNo());
         }
             
         request.setAttribute("orders", orders);
     }catch (Exception ex) {
                    Logger.getLogger(ManageOrdersServletBackup.class.getName()).log(Level.SEVERE, null, ex);
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
        
//      System.out.println("manageorders");
//      getServletContext().getRequestDispatcher("/WEB-INF/adminportal/manageorders.jsp").forward(request, response);
        String url = "/WEB-INF/adminportal/manageorders.jsp";
        OrderService os = new OrderService();
        DeliveryService ds = new DeliveryService();
        OrdersJpaController ojc = new OrdersJpaController();
        DeliveryJpaController djc = new DeliveryJpaController();
        CakeorderJpaController cos = new CakeorderJpaController();
        HttpSession session = request.getSession();
        
        try{
            
            String action = request.getParameter("action");
            
            if(action != null && action.equals("delete")){
              int selectedOrderId = Integer.parseInt(request.getParameter("selectedOrderId"));
                ojc.destroy(selectedOrderId);
            }else if(action != null && action.equals("edit")){
                //save edit order
                int selectedOrderId = Integer.parseInt(request.getParameter("selectedOrderId"));//not change
//                System.out.println("manageorder servlet post edit");
//                System.out.println(selectedOrderId);
                Orders orderOld    = os.get(selectedOrderId);
//                Date order_date    = orderOld.getOrderDatetime();//not change
//                Date due_date      = orderOld.getDueDatetime();//not change
//                String order_items = request.getParameter("orderItems");
//                double total_price = Double.parseDouble(request.getParameter("totalPrice"));
                int delivery_no    = orderOld.getDeliveryNo().getDeliveryNo();//not change
//                System.out.println("os" + os);
////                os.edit(selectedOrderId, order_date, due_date, order_items, total_price, delivery_no);
//                Orders orderNew = new Orders(selectedOrderId, order_date, due_date, order_items, total_price);
//                ojc.edit(orderNew);
                
                //save edit delivery
                String method  = request.getParameter("method");
                String address = request.getParameter("address");
                String phoneNo = request.getParameter("phoneNo");
                String notes   = request.getParameter("notes");
//                ds.edit(delivery_no, method, address, phoneNo, notes);
                Delivery delivery = new Delivery(delivery_no, method, address, phoneNo, notes);
                djc.edit(delivery);
                
            }
        }catch (Exception ex) {
                    Logger.getLogger(ManageOrdersServletBackup.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
        
        //get all orders
     List<Orders> orders;
     try{
         orders = os.getAll();
         for(Orders order:orders){
             System.out.println(order.getOrderNo());
         }
             
         request.setAttribute("orders", orders);
     }catch (Exception ex) {
                    Logger.getLogger(ManageOrdersServletBackup.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
     
     // forward to the view
        getServletContext()
            .getRequestDispatcher(url)
                .forward(request, response);
    }
}
