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
import Entities.Delivery;
import Entities.User;
import Entities.Cake;
import Entities.Order;
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
public class OrderJpaController implements Serializable {

    public OrderJpaController() {
        this.emf = DBUtil.getEmFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Order order) throws PreexistingEntityException, Exception {
        if (order.getCakeCollection() == null) {
            order.setCakeCollection(new ArrayList<Cake>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Delivery deliveryNo = order.getDeliveryNo();
            if (deliveryNo != null) {
                deliveryNo = em.getReference(deliveryNo.getClass(), deliveryNo.getDeliveryNo());
                order.setDeliveryNo(deliveryNo);
            }
            User userId = order.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                order.setUserId(userId);
            }
            Collection<Cake> attachedCakeCollection = new ArrayList<Cake>();
            for (Cake cakeCollectionCakeToAttach : order.getCakeCollection()) {
                cakeCollectionCakeToAttach = em.getReference(cakeCollectionCakeToAttach.getClass(), cakeCollectionCakeToAttach.getCakeId());
                attachedCakeCollection.add(cakeCollectionCakeToAttach);
            }
            order.setCakeCollection(attachedCakeCollection);
            em.persist(order);
            if (deliveryNo != null) {
                deliveryNo.getOrder1Collection().add(order);
                deliveryNo = em.merge(deliveryNo);
            }
            if (userId != null) {
                userId.getOrder1Collection().add(order);
                userId = em.merge(userId);
            }
            for (Cake cakeCollectionCake : order.getCakeCollection()) {
                cakeCollectionCake.getOrder1Collection().add(order);
                cakeCollectionCake = em.merge(cakeCollectionCake);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOrder(order.getOrderNo()) != null) {
                throw new PreexistingEntityException("Order " + order + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Order order) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Order persistentOrder = em.find(Order.class, order.getOrderNo());
            Delivery deliveryNoOld = persistentOrder.getDeliveryNo();
            Delivery deliveryNoNew = order.getDeliveryNo();
            User userIdOld = persistentOrder.getUserId();
            User userIdNew = order.getUserId();
            Collection<Cake> cakeCollectionOld = persistentOrder.getCakeCollection();
            Collection<Cake> cakeCollectionNew = order.getCakeCollection();
            if (deliveryNoNew != null) {
                deliveryNoNew = em.getReference(deliveryNoNew.getClass(), deliveryNoNew.getDeliveryNo());
                order.setDeliveryNo(deliveryNoNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                order.setUserId(userIdNew);
            }
            Collection<Cake> attachedCakeCollectionNew = new ArrayList<Cake>();
            for (Cake cakeCollectionNewCakeToAttach : cakeCollectionNew) {
                cakeCollectionNewCakeToAttach = em.getReference(cakeCollectionNewCakeToAttach.getClass(), cakeCollectionNewCakeToAttach.getCakeId());
                attachedCakeCollectionNew.add(cakeCollectionNewCakeToAttach);
            }
            cakeCollectionNew = attachedCakeCollectionNew;
            order.setCakeCollection(cakeCollectionNew);
            order = em.merge(order);
            if (deliveryNoOld != null && !deliveryNoOld.equals(deliveryNoNew)) {
                deliveryNoOld.getOrder1Collection().remove(order);
                deliveryNoOld = em.merge(deliveryNoOld);
            }
            if (deliveryNoNew != null && !deliveryNoNew.equals(deliveryNoOld)) {
                deliveryNoNew.getOrder1Collection().add(order);
                deliveryNoNew = em.merge(deliveryNoNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getOrder1Collection().remove(order);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getOrder1Collection().add(order);
                userIdNew = em.merge(userIdNew);
            }
            for (Cake cakeCollectionOldCake : cakeCollectionOld) {
                if (!cakeCollectionNew.contains(cakeCollectionOldCake)) {
                    cakeCollectionOldCake.getOrder1Collection().remove(order);
                    cakeCollectionOldCake = em.merge(cakeCollectionOldCake);
                }
            }
            for (Cake cakeCollectionNewCake : cakeCollectionNew) {
                if (!cakeCollectionOld.contains(cakeCollectionNewCake)) {
                    cakeCollectionNewCake.getOrder1Collection().add(order);
                    cakeCollectionNewCake = em.merge(cakeCollectionNewCake);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = order.getOrderNo();
                if (findOrder(id) == null) {
                    throw new NonexistentEntityException("The order with id " + id + " no longer exists.");
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
            Order order;
            try {
                order = em.getReference(Order.class, id);
                order.getOrderNo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The order with id " + id + " no longer exists.", enfe);
            }
            Delivery deliveryNo = order.getDeliveryNo();
            if (deliveryNo != null) {
                deliveryNo.getOrder1Collection().remove(order);
                deliveryNo = em.merge(deliveryNo);
            }
            User userId = order.getUserId();
            if (userId != null) {
                userId.getOrder1Collection().remove(order);
                userId = em.merge(userId);
            }
            Collection<Cake> cakeCollection = order.getCakeCollection();
            for (Cake cakeCollectionCake : cakeCollection) {
                cakeCollectionCake.getOrder1Collection().remove(order);
                cakeCollectionCake = em.merge(cakeCollectionCake);
            }
            em.remove(order);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Order> findOrderEntities() {
        return findOrderEntities(true, -1, -1);
    }

    public List<Order> findOrderEntities(int maxResults, int firstResult) {
        return findOrderEntities(false, maxResults, firstResult);
    }

    private List<Order> findOrderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Order.class));
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

    public Order findOrder(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Order.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Order> rt = cq.from(Order.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
