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
    public void create(Delivery delivery) throws Exception{
        
        int newId = 0;
        List<Delivery> currDelivery = (List<Delivery>) djc.findDeliveryEntities();

        if (currDelivery == null)
        {
            newId = 1;
        }
        else
        {
            newId = currDelivery.get(currDelivery.size()-1).getDeliveryNo()+1;
        }

        delivery.setDeliveryNo(newId);
        djc.create(delivery);

    }
    /**
     * Edit the delivery details
     * 
     */
    public void edit(Delivery delivery) throws Exception{
        
        djc.edit(delivery);

        }
    /**
     * Delete the delivery details
     */
    public void destroy(Integer delivery_no) throws NonexistentEntityException, IllegalOrphanException, BusinessClasses.exceptions.IllegalOrphanException, BusinessClasses.exceptions.NonexistentEntityException{
            djc.destroy(delivery_no);
        
    }
    
    public List<Delivery> getAll()
    {
        return djc.findDeliveryEntities();
    }
}
