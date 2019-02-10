/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import Entities.Delivery;
import dataaccess.DeliveryJpaController;
import dataaccess.exceptions.IllegalOrphanException;
import dataaccess.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Botan
 */
public class DeliveryService {

    private final DeliveryJpaController djc;

    public DeliveryService() {
        djc = new DeliveryJpaController();
    }

    public Delivery get(Integer delivery_no) {
        return djc.findDelivery(delivery_no);
    }
    
    /**
     * Add new delivery
     */
    public void create(String method, String address, String phoneNo, String notes) throws Exception{
        
        int newNo;
        int check = djc.getDeliveryCount();

        if (check == 0){
            newNo = 1;
        }
        else{
            newNo = check + 1;
        }
        Delivery delivery = new Delivery(newNo, method, address, phoneNo, notes);
        djc.create(delivery);

    }
    /**
     * Edit the delivery details
     * 
     */
    public void edit(Integer delivery_no, String method, String address, String phoneNo, String notes) throws Exception{
        
            Delivery delivery = new Delivery(delivery_no, method, address, phoneNo, notes);
        djc.edit(delivery);

        }
    /**
     * Delete the delivery details
     */
    public void destroy(Integer delivery_no) throws NonexistentEntityException, IllegalOrphanException{
            djc.destroy(delivery_no);
        
    }
    
    public List<Delivery> getAll()
    {
        return djc.findDeliveryEntities();
    }
}
