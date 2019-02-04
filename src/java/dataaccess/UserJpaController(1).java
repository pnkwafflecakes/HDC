/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import Database.exceptions.IllegalOrphanException;
import Database.exceptions.NonexistentEntityException;
import Database.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Account;
import Entities.Order1;
import Entities.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author 775224
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) throws PreexistingEntityException, Exception {
        if (user.getOrder1Collection() == null) {
            user.setOrder1Collection(new ArrayList<Order1>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Account accountNo = user.getAccountNo();
            if (accountNo != null) {
                accountNo = em.getReference(accountNo.getClass(), accountNo.getAccountNo());
                user.setAccountNo(accountNo);
            }
            Collection<Order1> attachedOrder1Collection = new ArrayList<Order1>();
            for (Order1 order1CollectionOrder1ToAttach : user.getOrder1Collection()) {
                order1CollectionOrder1ToAttach = em.getReference(order1CollectionOrder1ToAttach.getClass(), order1CollectionOrder1ToAttach.getOrderNo());
                attachedOrder1Collection.add(order1CollectionOrder1ToAttach);
            }
            user.setOrder1Collection(attachedOrder1Collection);
            em.persist(user);
            if (accountNo != null) {
                accountNo.getUserCollection().add(user);
                accountNo = em.merge(accountNo);
            }
            for (Order1 order1CollectionOrder1 : user.getOrder1Collection()) {
                User oldUserIdOfOrder1CollectionOrder1 = order1CollectionOrder1.getUserId();
                order1CollectionOrder1.setUserId(user);
                order1CollectionOrder1 = em.merge(order1CollectionOrder1);
                if (oldUserIdOfOrder1CollectionOrder1 != null) {
                    oldUserIdOfOrder1CollectionOrder1.getOrder1Collection().remove(order1CollectionOrder1);
                    oldUserIdOfOrder1CollectionOrder1 = em.merge(oldUserIdOfOrder1CollectionOrder1);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUser(user.getUserId()) != null) {
                throw new PreexistingEntityException("User " + user + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getUserId());
            Account accountNoOld = persistentUser.getAccountNo();
            Account accountNoNew = user.getAccountNo();
            Collection<Order1> order1CollectionOld = persistentUser.getOrder1Collection();
            Collection<Order1> order1CollectionNew = user.getOrder1Collection();
            List<String> illegalOrphanMessages = null;
            for (Order1 order1CollectionOldOrder1 : order1CollectionOld) {
                if (!order1CollectionNew.contains(order1CollectionOldOrder1)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Order1 " + order1CollectionOldOrder1 + " since its userId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (accountNoNew != null) {
                accountNoNew = em.getReference(accountNoNew.getClass(), accountNoNew.getAccountNo());
                user.setAccountNo(accountNoNew);
            }
            Collection<Order1> attachedOrder1CollectionNew = new ArrayList<Order1>();
            for (Order1 order1CollectionNewOrder1ToAttach : order1CollectionNew) {
                order1CollectionNewOrder1ToAttach = em.getReference(order1CollectionNewOrder1ToAttach.getClass(), order1CollectionNewOrder1ToAttach.getOrderNo());
                attachedOrder1CollectionNew.add(order1CollectionNewOrder1ToAttach);
            }
            order1CollectionNew = attachedOrder1CollectionNew;
            user.setOrder1Collection(order1CollectionNew);
            user = em.merge(user);
            if (accountNoOld != null && !accountNoOld.equals(accountNoNew)) {
                accountNoOld.getUserCollection().remove(user);
                accountNoOld = em.merge(accountNoOld);
            }
            if (accountNoNew != null && !accountNoNew.equals(accountNoOld)) {
                accountNoNew.getUserCollection().add(user);
                accountNoNew = em.merge(accountNoNew);
            }
            for (Order1 order1CollectionNewOrder1 : order1CollectionNew) {
                if (!order1CollectionOld.contains(order1CollectionNewOrder1)) {
                    User oldUserIdOfOrder1CollectionNewOrder1 = order1CollectionNewOrder1.getUserId();
                    order1CollectionNewOrder1.setUserId(user);
                    order1CollectionNewOrder1 = em.merge(order1CollectionNewOrder1);
                    if (oldUserIdOfOrder1CollectionNewOrder1 != null && !oldUserIdOfOrder1CollectionNewOrder1.equals(user)) {
                        oldUserIdOfOrder1CollectionNewOrder1.getOrder1Collection().remove(order1CollectionNewOrder1);
                        oldUserIdOfOrder1CollectionNewOrder1 = em.merge(oldUserIdOfOrder1CollectionNewOrder1);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getUserId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Order1> order1CollectionOrphanCheck = user.getOrder1Collection();
            for (Order1 order1CollectionOrphanCheckOrder1 : order1CollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Order1 " + order1CollectionOrphanCheckOrder1 + " in its order1Collection field has a non-nullable userId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Account accountNo = user.getAccountNo();
            if (accountNo != null) {
                accountNo.getUserCollection().remove(user);
                accountNo = em.merge(accountNo);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
