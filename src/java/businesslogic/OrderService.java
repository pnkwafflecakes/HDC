package businesslogic;

import Entities.Order;
import dataaccess.OrderJpaController;
import dataaccess.exceptions.IllegalOrphanException;
import dataaccess.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Botan
 */
public class OrderService {

    private final OrderJpaController ojc;

    public OrderService() {
        ojc = new OrderJpaController();
    }

    public Order get(Integer order_no) {
        return ojc.findOrder(order_no);
    }
    
    /**
     * Add new order
     */
    public void create(int user_id, Date order_date, Date due_date, String order_items, double total_price, int delivery_no) throws Exception{
        Order order = new Order(user_id, order_date, due_date, order_items, total_price);
        ojc.create(order);
            
        
        
    }
    /**
     * Edit the order details
     */
    public void edit(int user_id, Date order_date, Date due_date, String order_items, double total_price, int delivery_no) throws Exception{
       
        Order order = new Order(user_id, order_date, due_date, order_items, total_price);
        ojc.edit(order);
    }
    /**
     * Delete the order details
     */
    public void destroy(Integer order_no) throws IllegalOrphanException, NonexistentEntityException{

        ojc.destroy(order_no);
    }
    
    public List<Order> getAll()
    {
        return ojc.findOrderEntities();
    }
}
