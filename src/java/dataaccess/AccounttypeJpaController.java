/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import BusinessClasses.exceptions.IllegalOrphanException;
import BusinessClasses.exceptions.NonexistentEntityException;
import BusinessClasses.exceptions.PreexistingEntityException;
import Entities.Accounttype;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.User;
import dataaccess.DBUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author 775224
 */
public class AccounttypeJpaController implements Serializable {

    public void create(Accounttype accounttype) throws PreexistingEntityException, Exception {
        if (accounttype.getUserCollection() == null) {
            accounttype.setUserCollection(new ArrayList<User>());
        }
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Collection<User> attachedUserCollection = new ArrayList<User>();
            for (User userCollectionUserToAttach : accounttype.getUserCollection()) {
                userCollectionUserToAttach = em.getReference(userCollectionUserToAttach.getClass(), userCollectionUserToAttach.getUserId());
                attachedUserCollection.add(userCollectionUserToAttach);
            }
            accounttype.setUserCollection(attachedUserCollection);
            em.persist(accounttype);
            for (User userCollectionUser : accounttype.getUserCollection()) {
                Accounttype oldAccountTypeOfUserCollectionUser = userCollectionUser.getAccountType();
                userCollectionUser.setAccountType(accounttype);
                userCollectionUser = em.merge(userCollectionUser);
                if (oldAccountTypeOfUserCollectionUser != null) {
                    oldAccountTypeOfUserCollectionUser.getUserCollection().remove(userCollectionUser);
                    oldAccountTypeOfUserCollectionUser = em.merge(oldAccountTypeOfUserCollectionUser);
                }
            }
            trans.commit();
        } catch (Exception ex) {
            if (findAccounttype(accounttype.getAccountType()) != null) {
                throw new PreexistingEntityException("Accounttype " + accounttype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Accounttype accounttype) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Accounttype persistentAccounttype = em.find(Accounttype.class, accounttype.getAccountType());
            Collection<User> userCollectionOld = persistentAccounttype.getUserCollection();
            Collection<User> userCollectionNew = accounttype.getUserCollection();
            List<String> illegalOrphanMessages = null;
            for (User userCollectionOldUser : userCollectionOld) {
                if (!userCollectionNew.contains(userCollectionOldUser)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain User " + userCollectionOldUser + " since its accountType field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<User> attachedUserCollectionNew = new ArrayList<User>();
            for (User userCollectionNewUserToAttach : userCollectionNew) {
                userCollectionNewUserToAttach = em.getReference(userCollectionNewUserToAttach.getClass(), userCollectionNewUserToAttach.getUserId());
                attachedUserCollectionNew.add(userCollectionNewUserToAttach);
            }
            userCollectionNew = attachedUserCollectionNew;
            accounttype.setUserCollection(userCollectionNew);
            accounttype = em.merge(accounttype);
            for (User userCollectionNewUser : userCollectionNew) {
                if (!userCollectionOld.contains(userCollectionNewUser)) {
                    Accounttype oldAccountTypeOfUserCollectionNewUser = userCollectionNewUser.getAccountType();
                    userCollectionNewUser.setAccountType(accounttype);
                    userCollectionNewUser = em.merge(userCollectionNewUser);
                    if (oldAccountTypeOfUserCollectionNewUser != null && !oldAccountTypeOfUserCollectionNewUser.equals(accounttype)) {
                        oldAccountTypeOfUserCollectionNewUser.getUserCollection().remove(userCollectionNewUser);
                        oldAccountTypeOfUserCollectionNewUser = em.merge(oldAccountTypeOfUserCollectionNewUser);
                    }
                }
            }
            trans.commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = accounttype.getAccountType();
                if (findAccounttype(id) == null) {
                    throw new NonexistentEntityException("The accounttype with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Accounttype accounttype;
            try {
                accounttype = em.getReference(Accounttype.class, id);
                accounttype.getAccountType();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accounttype with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<User> userCollectionOrphanCheck = accounttype.getUserCollection();
            for (User userCollectionOrphanCheckUser : userCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Accounttype (" + accounttype + ") cannot be destroyed since the User " + userCollectionOrphanCheckUser + " in its userCollection field has a non-nullable accountType field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(accounttype);
            trans.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Accounttype> findAccounttypeEntities() {
        return findAccounttypeEntities(true, -1, -1);
    }

    public List<Accounttype> findAccounttypeEntities(int maxResults, int firstResult) {
        return findAccounttypeEntities(false, maxResults, firstResult);
    }

    private List<Accounttype> findAccounttypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Accounttype.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Accounttype findAccounttype(Integer id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.find(Accounttype.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccounttypeCount() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Accounttype> rt = cq.from(Accounttype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
