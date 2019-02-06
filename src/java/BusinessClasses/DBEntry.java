/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessClasses;

import Entities.Cake;
import Entities.Delivery;
import Entities.User;
import Entities.Order;
import dataaccess.DeliveryJpaController;
import dataaccess.OrderJpaController;

/**
 *
 * @author 775224
 */
public class DBEntry {
    /**
     * Takes in cakes assigned to an order and creates entries into Delivery, Order
     * This method should only be refered to by OrderDetails after order is finalized
     * @param cakes The cakes being added into the order
     * @param user The user who the order belongs to
     * @return 
     */
    public boolean inserOrderDB(Cake[] cakes, User user, Delivery delivery) {
        DeliveryJpaController djc = new DeliveryJpaController();
        OrderJpaController ojc = new OrderJpaController();
        //Create Delivery then order
        Order order = new Order();
        /*
        `order_no` int(4) NOT NULL,
    `user_id` int(4) NOT NULL, -- user_id instead of customer_id
    `order_datetime` datetime NOT NULL,
    `due_datetime` datetime NOT NULL,
    `order_items` VARCHAR(99) NOT NULL, --Do we need this?
    `total_price` double(9,2) NOT NULL,
    `delivery_no` int(4) NOT NULL,
        */
        return false;
    }
    
}
