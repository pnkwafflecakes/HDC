/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 775224
 */
public class DBUtil {
    private static final EntityManagerFactory EMF = 
            Persistence.createEntityManagerFactory("persistence");
    
    public static EntityManagerFactory getEmFactory() {
        return EMF;
    }
}
