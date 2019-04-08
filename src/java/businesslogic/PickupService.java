package businesslogic;

import BusinessClasses.exceptions.IllegalOrphanException;
import Entities.Cake;
import Entities.Pickup;
import dataaccess.PickupJpaController;
import dataaccess.exceptions.NonexistentEntityException;
import java.util.List;

public class PickupService
{
    private final PickupJpaController pickupController;

    public PickupService()
    {
        pickupController = new PickupJpaController();
    }

    public Pickup get(int pickupId)
    {
        return pickupController.findPickup(pickupId);
    }

    public List<Pickup> getAll()
    {
        return pickupController.findPickupEntities();
    }

    public void update(Pickup pickup) throws BusinessClasses.exceptions.NonexistentEntityException, IllegalOrphanException, java.lang.Exception
    {
        pickupController.edit(pickup);
    }

    public void delete(int pickupId) throws NonexistentEntityException, IllegalOrphanException, BusinessClasses.exceptions.NonexistentEntityException
    {
        pickupController.destroy(pickupId);
    }

    public void insert(Pickup pickup) throws Exception
    {
        int newId = 0;
        List<Pickup> currPickups = (List<Pickup>) pickupController.findPickupEntities();

        if (currPickups == null)
        {
            newId = 1;
        }
        else
        {
            Pickup selectedPickup;
            int openId = 2;
            for (int i=0; i < currPickups.size(); i++) {
                selectedPickup = currPickups.get(i);
                if (openId != selectedPickup.getPickupId()+1) {
                    newId = openId-1; //Issue here :/
                    i = currPickups.size();
                }
                else {
                    openId++;
                }
            }
            if(newId == 0) newId = currPickups.size()+1;
        }

        pickup.setPickupId(newId);
        pickupController.create(pickup);
    }
    
}
