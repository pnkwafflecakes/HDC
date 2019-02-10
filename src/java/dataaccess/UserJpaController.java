/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Account;
import Entities.Order;
import Entities.User;
import dataaccess.exceptions.IllegalOrphanException;
import dataaccess.exceptions.NonexistentEntityException;
import dataaccess.exceptions.PreexistingEntityException;
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

    public UserJpaController() {
        this.emf = DBUtil.getEmFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) throws PreexistingEntityException, Exception {
        if (user.getOrder1Collection() == null) {
            user.setOrder1Collection(new ArrayList<Order>());
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
            Collection<Order> attachedOrder1Collection = new ArrayList<Order>();
            for (Order order1CollectionOrderToAttach : user.getOrder1Collection()) {
                order1CollectionOrderToAttach = em.getReference(order1CollectionOrderToAttach.getClass(), order1CollectionOrderToAttach.getOrderNo());
                attachedOrder1Collection.add(order1CollectionOrderToAttach);
            }
            user.setOrder1Collection(attachedOrder1Collection);
            em.persist(user);
            if (accountNo != null) {
                accountNo.getUserCollection().add(user);
                accountNo = em.merge(accountNo);
            }
            for (Order order1CollectionOrder : user.getOrder1Collection()) {
                User oldUserIdOfOrder1CollectionOrder = order1CollectionOrder.getUserId();
                order1CollectionOrder.setUserId(user);
                order1CollectionOrder = em.merge(order1CollectionOrder);
                if (oldUserIdOfOrder1CollectionOrder != null) {
                    oldUserIdOfOrder1CollectionOrder.getOrder1Collection().remove(order1CollectionOrder);
                    oldUserIdOfOrder1CollectionOrder = em.merge(oldUserIdOfOrder1CollectionOrder);
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
            Collection<Order> order1CollectionOld = persistentUser.getOrder1Collection();
            Collection<Order> order1CollectionNew = user.getOrder1Collection();
            List<String> illegalOrphanMessages = null;
            for (Order order1CollectionOldOrder : order1CollectionOld) {
                if (!order1CollectionNew.contains(order1CollectionOldOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Order " + order1CollectionOldOrder + " since its userId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (accountNoNew != null) {
                accountNoNew = em.getReference(accountNoNew.getClass(), accountNoNew.getAccountNo());
                user.setAccountNo(accountNoNew);
            }
            Collection<Order> attachedOrder1CollectionNew = new ArrayList<Order>();
            for (Order order1CollectionNewOrderToAttach : order1CollectionNew) {
                order1CollectionNewOrderToAttach = em.getReference(order1CollectionNewOrderToAttach.getClass(), order1CollectionNewOrderToAttach.getOrderNo());
                attachedOrder1CollectionNew.add(order1CollectionNewOrderToAttach);
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
            for (Order order1CollectionNewOrder : order1CollectionNew) {
                if (!order1CollectionOld.contains(order1CollectionNewOrder)) {
                    User oldUserIdOfOrder1CollectionNewOrder = order1CollectionNewOrder.getUserId();
                    order1CollectionNewOrder.setUserId(user);
                    order1CollectionNewOrder = em.merge(order1CollectionNewOrder);
                    if (oldUserIdOfOrder1CollectionNewOrder != null && !oldUserIdOfOrder1CollectionNewOrder.equals(user)) {
                        oldUserIdOfOrder1CollectionNewOrder.getOrder1Collection().remove(order1CollectionNewOrder);
                        oldUserIdOfOrder1CollectionNewOrder = em.merge(oldUserIdOfOrder1CollectionNewOrder);
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
            Collection<Order> order1CollectionOrphanCheck = user.getOrder1Collection();
            for (Order order1CollectionOrphanCheckOrder : order1CollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Order " + order1CollectionOrphanCheckOrder + " in its order1Collection field has a non-nullable userId field.");
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
