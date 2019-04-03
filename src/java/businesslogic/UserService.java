package businesslogic;

import Entities.User;
import dataaccess.UserJpaController;
import dataaccess.exceptions.IllegalOrphanException;
import dataaccess.exceptions.NonexistentEntityException;
import java.util.List;

public class UserService
{
    private final UserJpaController ujc;

    public UserService()
    {
        ujc = new UserJpaController();
    }

    public User get(Integer user_id)
    {
        return ujc.findUser(user_id);
    }

    /**
     * Add new user
     *
     * @param user
     * @throws java.lang.Exception
     */
    public void create(User user) throws Exception
    {
        int newId = 0;
        List<User> currUsers = (List<User>) ujc.findUserEntities();

        if (currUsers == null)
        {
            newId = 1;
        }
        else
        {
            User selectedUser;
            int openId = 2;
            for (int i=0; i < currUsers.size(); i++) {
                selectedUser = currUsers.get(i);
                if (openId != selectedUser.getUserId()+1) {
                    newId = openId-1; //Issue here :/
                    i = currUsers.size();
                }
                else {
                    openId++;
                }
            }
            if(newId == 0) newId = currUsers.size()+1;
        }

        user.setUserId(newId);
        
        ujc.create(user);
    }

    /**
     * Edit the user details
     *
     * @param user
     * @throws java.lang.Exception
     */
    public void edit(User user) throws Exception
    {
        ujc.edit(user);
    }

    /**
     * Delete the user details
     *
     * @param user_id
     * @throws dataaccess.exceptions.NonexistentEntityException
     * @throws dataaccess.exceptions.IllegalOrphanException
     * @throws BusinessClasses.exceptions.IllegalOrphanException
     * @throws BusinessClasses.exceptions.NonexistentEntityException
     */
    public void destroy(Integer user_id) throws NonexistentEntityException, IllegalOrphanException, BusinessClasses.exceptions.IllegalOrphanException, BusinessClasses.exceptions.NonexistentEntityException
    {
        ujc.destroy(user_id);
    }

    public List<User> getAll()
    {
        return ujc.findUserEntities();
    }
}
