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
    public void create(Orders order) throws Exception{
        int newId = 0;
        List<Orders> currOrders = (List<Orders>) ojc.findOrdersEntities();

        if (currOrders == null)
        {
            newId = 1;
        }
        else
        {
            Orders selectedOrder;
            int openId = 2;
            for (int i=0; i < currOrders.size(); i++) {
                selectedOrder = currOrders.get(i);
                if (openId != selectedOrder.getOrderNo()+1) {
                    newId = openId-1; //Issue here :/
                    i = currOrders.size();
                }
                else {
                    openId++;
                }
            }
            if(newId == 0) newId = currOrders.size()+1;
        }

        order.setOrderNo(newId);
        ojc.create(order);
            
        
        
    }
    /**
     * Edit the order details
     */
    public void edit(Orders order) throws Exception{
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
