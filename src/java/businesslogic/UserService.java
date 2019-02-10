/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import Entities.User;
import dataaccess.UserJpaController;
import dataaccess.exceptions.IllegalOrphanException;
import dataaccess.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Botan
 */
public class UserService {

    private final UserJpaController ujc;

    public UserService() {
        ujc = new UserJpaController();
    }

    public User get(Integer user_id) {
        return ujc.findUser(user_id);
    }
    
    /**
     * Add new user
     */
    public void create(User user) throws Exception{
        ujc.create(user);

    }
    /**
     * Edit the user details
     * 
     */
    public void edit(User user) throws Exception{
        ujc.edit(user);

        }
    /**
     * Delete the user details
     */
    public void destroy(Integer user_id) throws NonexistentEntityException, IllegalOrphanException{
            ujc.destroy(user_id);
        
    }
    
    public List<User> getAll()
    {
        return ujc.findUserEntities();
    }
}
