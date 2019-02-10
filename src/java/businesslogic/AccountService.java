package businesslogic;

import Entities.Account;
import dataaccess.AccountJpaController;
import dataaccess.exceptions.IllegalOrphanException;
import dataaccess.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Botan
 */
public class AccountService {

    private final AccountJpaController ajc;

    public AccountService() {
        ajc = new AccountJpaController();
    }

    public Account get(Integer account_no) {
        return ajc.findAccount(account_no);
    }
    
    /**
     * Add new account
     */
    public void create(int account_type, String username, String password, boolean account_status) throws Exception{

        int newNo;
        int check = ajc.getAccountCount();

        if (check == 0){
            newNo = 1;
        }
        else{
            newNo = check + 1;
        }
        Account account = new Account(newNo, account_type, username, password, account_status);
        ajc.create(account);
            
        
        
    }
    /**
     * Edit the account details
     */
    public void edit(Integer account_no, int account_type, String username, String password, boolean account_status) throws Exception{
       
        Account account = new Account(account_no, account_type, username, password, account_status);
        ajc.edit(account);
    }
    /**
     * Delete the account details
     */
    public void destroy(Integer account_no) throws IllegalOrphanException, NonexistentEntityException{

        ajc.destroy(account_no);
    }
    
    public List<Account> getAll()
    {
        return ajc.findAccountEntities();
    }
}
