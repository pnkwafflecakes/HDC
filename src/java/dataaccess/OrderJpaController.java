/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import Database.exceptions.NonexistentEntityException;
import Database.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Delivery;
import Entities.User;
import Entities.Cake;
import Entities.Order1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author 775224
 */
public class OrderJpaController implements Serializable {

    public OrderJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Order1 order1) throws PreexistingEntityException, Exception {
        if (order1.getCakeCollection() == null) {
            order1.setCakeCollection(new ArrayList<Cake>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Delivery deliveryNo = order1.getDeliveryNo();
            if (deliveryNo != null) {
                deliveryNo = em.getReference(deliveryNo.getClass(), deliveryNo.getDeliveryNo());
                order1.setDeliveryNo(deliveryNo);
            }
            User userId = order1.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                order1.setUserId(userId);
            }
            Collection<Cake> attachedCakeCollection = new ArrayList<Cake>();
            for (Cake cakeCollectionCakeToAttach : order1.getCakeCollection()) {
                cakeCollectionCakeToAttach = em.getReference(cakeCollectionCakeToAttach.getClass(), cakeCollectionCakeToAttach.getCakeId());
                attachedCakeCollection.add(cakeCollectionCakeToAttach);
            }
            order1.setCakeCollection(attachedCakeCollection);
            em.persist(order1);
            if (deliveryNo != null) {
                deliveryNo.getOrder1Collection().add(order1);
                deliveryNo = em.merge(deliveryNo);
            }
            if (userId != null) {
                userId.getOrder1Collection().add(order1);
                userId = em.merge(userId);
            }
            for (Cake cakeCollectionCake : order1.getCakeCollection()) {
                cakeCollectionCake.getOrder1Collection().add(order1);
                cakeCollectionCake = em.merge(cakeCollectionCake);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrder1(order1.getOrderNo()) != null) {
                throw new PreexistingEntityException("Order1 " + order1 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Order1 order1) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Order1 persistentOrder1 = em.find(Order1.class, order1.getOrderNo());
            Delivery deliveryNoOld = persistentOrder1.getDeliveryNo();
            Delivery deliveryNoNew = order1.getDeliveryNo();
            User userIdOld = persistentOrder1.getUserId();
            User userIdNew = order1.getUserId();
            Collection<Cake> cakeCollectionOld = persistentOrder1.getCakeCollection();
            Collection<Cake> cakeCollectionNew = order1.getCakeCollection();
            if (deliveryNoNew != null) {
                deliveryNoNew = em.getReference(deliveryNoNew.getClass(), deliveryNoNew.getDeliveryNo());
                order1.setDeliveryNo(deliveryNoNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                order1.setUserId(userIdNew);
            }
            Collection<Cake> attachedCakeCollectionNew = new ArrayList<Cake>();
            for (Cake cakeCollectionNewCakeToAttach : cakeCollectionNew) {
                cakeCollectionNewCakeToAttach = em.getReference(cakeCollectionNewCakeToAttach.getClass(), cakeCollectionNewCakeToAttach.getCakeId());
                attachedCakeCollectionNew.add(cakeCollectionNewCakeToAttach);
            }
            cakeCollectionNew = attachedCakeCollectionNew;
            order1.setCakeCollection(cakeCollectionNew);
            order1 = em.merge(order1);
            if (deliveryNoOld != null && !deliveryNoOld.equals(deliveryNoNew)) {
                deliveryNoOld.getOrder1Collection().remove(order1);
                deliveryNoOld = em.merge(deliveryNoOld);
            }
            if (deliveryNoNew != null && !deliveryNoNew.equals(deliveryNoOld)) {
                deliveryNoNew.getOrder1Collection().add(order1);
                deliveryNoNew = em.merge(deliveryNoNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getOrder1Collection().remove(order1);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getOrder1Collection().add(order1);
                userIdNew = em.merge(userIdNew);
            }
            for (Cake cakeCollectionOldCake : cakeCollectionOld) {
                if (!cakeCollectionNew.contains(cakeCollectionOldCake)) {
                    cakeCollectionOldCake.getOrder1Collection().remove(order1);
                    cakeCollectionOldCake = em.merge(cakeCollectionOldCake);
                }
            }
            for (Cake cakeCollectionNewCake : cakeCollectionNew) {
                if (!cakeCollectionOld.contains(cakeCollectionNewCake)) {
                    cakeCollectionNewCake.getOrder1Collection().add(order1);
                    cakeCollectionNewCake = em.merge(cakeCollectionNewCake);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = order1.getOrderNo();
                if (findOrder1(id) == null) {
                    throw new NonexistentEntityException("The order1 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Order1 order1;
            try {
                order1 = em.getReference(Order1.class, id);
                order1.getOrderNo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The order1 with id " + id + " no longer exists.", enfe);
            }
            Delivery deliveryNo = order1.getDeliveryNo();
            if (deliveryNo != null) {
                deliveryNo.getOrder1Collection().remove(order1);
                deliveryNo = em.merge(deliveryNo);
            }
            User userId = order1.getUserId();
            if (userId != null) {
                userId.getOrder1Collection().remove(order1);
                userId = em.merge(userId);
            }
            Collection<Cake> cakeCollection = order1.getCakeCollection();
            for (Cake cakeCollectionCake : cakeCollection) {
                cakeCollectionCake.getOrder1Collection().remove(order1);
                cakeCollectionCake = em.merge(cakeCollectionCake);
            }
            em.remove(order1);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Order1> findOrder1Entities() {
        return findOrder1Entities(true, -1, -1);
    }

    public List<Order1> findOrder1Entities(int maxResults, int firstResult) {
        return findOrder1Entities(false, maxResults, firstResult);
    }

    private List<Order1> findOrder1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Order1.class));
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

    public Order1 findOrder1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Order1.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrder1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Order1> rt = cq.from(Order1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
