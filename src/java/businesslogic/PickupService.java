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
            newId = currPickups.get(currPickups.size()-1).getPickupId()+1;
        }

        pickup.setPickupId(newId);
        pickupController.create(pickup);
    }
    
}
