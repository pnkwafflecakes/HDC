package businesslogic;

import BusinessClasses.exceptions.IllegalOrphanException;
import dataaccess.CakeJpaController;
import Entities.Cake;
import dataaccess.exceptions.NonexistentEntityException;
import java.util.List;

public class CakeService
{
    private final CakeJpaController cakeController;

    public CakeService()
    {
        cakeController = new CakeJpaController();
    }

    public Cake get(int cakeId)
    {
        return cakeController.findCake(cakeId);
    }

    public List<Cake> getAll()
    {
        return cakeController.findCakeEntities();
    }

    public void update(Cake cake) throws BusinessClasses.exceptions.NonexistentEntityException, IllegalOrphanException, java.lang.Exception
    {
        cakeController.edit(cake);
    }

    public void delete(int cakeId) throws NonexistentEntityException, IllegalOrphanException, BusinessClasses.exceptions.NonexistentEntityException
    {
        cakeController.destroy(cakeId);
    }

    public void insert(Cake cake) throws Exception
    {
        int newId = 1;
        List<Cake> currCakes = (List<Cake>) cakeController.findCakeEntities();

        if (currCakes == null)
        {
            newId = 1;
        }
        else
        {
            Cake selectedCake;
            int openId = 2;
            for (int i=0; i < currCakes.size(); i++) {
                selectedCake = currCakes.get(i);
                if (openId != selectedCake.getCakeId()+1) {
                    newId = openId-1;
                    i = currCakes.size();
                }
                else {
                    openId++;
                }
            }
        }

        cake.setCakeId(newId);
        cakeController.create(cake);
    }
    
    
}
