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

    public void update(Cake cake) throws Exception
    {
        cakeController.edit(cake);
    }

    public void delete(int cakeId) throws NonexistentEntityException, IllegalOrphanException, BusinessClasses.exceptions.NonexistentEntityException
    {
        cakeController.destroy(cakeId);
    }

    public void insert(Cake cake) throws Exception
    {
        int newId;
        List<Cake> currCakes = (List<Cake>) cakeController.findCakeEntities();

        if (currCakes == null)
        {
            newId = 1;
        }
        else
        {
            Cake lastCake = currCakes.get(currCakes.size() - 1);
            newId = lastCake.getCakeId() + 1;
        }

        Cake newCake = new Cake(newId, cake.getName(), cake.getPrice(), cake.getDescription(), cake.getImage());
        cakeController.create(newCake);
    }
}
