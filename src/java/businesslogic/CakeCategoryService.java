package businesslogic;

import Entities.Account;
import Entities.Cakecategory;
import dataaccess.CakecategoryJpaController;
import dataaccess.exceptions.IllegalOrphanException;
import dataaccess.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Botan
 */
public class CakeCategoryService {

    private final CakecategoryJpaController cjc;

    public CakeCategoryService() {
        cjc = new CakecategoryJpaController();
    }

    public Cakecategory get(Integer cakeCategory_no) {
        return cjc.findCakecategory(cakeCategory_no);
    }
    
    /**
     * Add new cakeCategory
     */
    public void create(Integer category_id, String name, String description) throws Exception{
        Cakecategory cakeCategory = new Cakecategory(category_id, name, description);
        cjc.create(cakeCategory);
   
    }
    /**
     * Edit the cakeCategory details
     */
    public void edit(Integer category_id, String name, String description) throws Exception{

        Cakecategory cakeCategory = new Cakecategory(category_id, name, description);
        cjc.edit(cakeCategory);
        }
    /**
     * Delete the cakeCategory details 
     */
    public void destroy(Integer cakeCategory_no) throws IllegalOrphanException, NonexistentEntityException{
       
        cjc.destroy(cakeCategory_no);
    } 
    
    public List<Cakecategory> getAll()
    {
        return cjc.findCakecategoryEntities();
    }
}
