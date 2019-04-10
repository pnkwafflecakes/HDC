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
        int newId = 0;
        List<Cake> currCakes = (List<Cake>) cakeController.findCakeEntities();

        if (currCakes == null)
        {
            newId = 1;
        }
        else
        {
            newId = currCakes.get(currCakes.size()-1).getCakeId()+1;
        }

        cake.setCakeId(newId);
        cakeController.create(cake);
    }
    
//    another solution for search in English, not work on Chinese
    public List<Cake> search(String str)throws BusinessClasses.exceptions.NonexistentEntityException{
        return cakeController.search(str);
    }
    
    
}
