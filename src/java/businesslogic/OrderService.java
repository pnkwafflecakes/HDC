package businesslogic;

import Entities.Orders;
import dataaccess.OrdersJpaController;
import dataaccess.exceptions.IllegalOrphanException;
import dataaccess.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Botan
 */
public class OrderService {

    private final OrdersJpaController ojc;

    public OrderService() {
        ojc = new OrdersJpaController();
    }

    public Orders get(Integer order_no) {
        return ojc.findOrders(order_no);
    }
    
    /**
     * Add new orders
     */
    public void create(int user_id, Date order_date, Date due_date, String order_items, double total_price, int delivery_no) throws Exception{
        Orders order = new Orders(user_id, order_date, due_date, order_items, total_price);
        ojc.create(order);
            
        
        
    }
    /**
     * Edit the order details
     */
    public void edit(int user_id, Date order_date, Date due_date, String order_items, double total_price, int delivery_no) throws Exception{
       
        Orders order = new Orders(user_id, order_date, due_date, order_items, total_price);
        ojc.edit(order);
    }
    /**
     * Delete the order details
     */
    public void destroy(Integer order_no) throws IllegalOrphanException, NonexistentEntityException, BusinessClasses.exceptions.NonexistentEntityException, BusinessClasses.exceptions.IllegalOrphanException{

        ojc.destroy(order_no);
    }
    
    public List<Orders> getAll()
    {
        return ojc.findOrdersEntities();
    }
}
