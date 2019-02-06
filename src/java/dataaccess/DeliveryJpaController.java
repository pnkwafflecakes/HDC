/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import Entities.Delivery;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Order;
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
public class DeliveryJpaController implements Serializable {

    public DeliveryJpaController() {
        this.emf = DBUtil.getEmFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Delivery delivery) throws PreexistingEntityException, Exception {
        if (delivery.getOrder1Collection() == null) {
            delivery.setOrder1Collection(new ArrayList<Order>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Order> attachedOrder1Collection = new ArrayList<Order>();
            for (Order order1CollectionOrderToAttach : delivery.getOrder1Collection()) {
                order1CollectionOrderToAttach = em.getReference(order1CollectionOrderToAttach.getClass(), order1CollectionOrderToAttach.getOrderNo());
                attachedOrder1Collection.add(order1CollectionOrderToAttach);
            }
            delivery.setOrder1Collection(attachedOrder1Collection);
            em.persist(delivery);
            for (Order order1CollectionOrder : delivery.getOrder1Collection()) {
                Delivery oldDeliveryNoOfOrder1CollectionOrder = order1CollectionOrder.getDeliveryNo();
                order1CollectionOrder.setDeliveryNo(delivery);
                order1CollectionOrder = em.merge(order1CollectionOrder);
                if (oldDeliveryNoOfOrder1CollectionOrder != null) {
                    oldDeliveryNoOfOrder1CollectionOrder.getOrder1Collection().remove(order1CollectionOrder);
                    oldDeliveryNoOfOrder1CollectionOrder = em.merge(oldDeliveryNoOfOrder1CollectionOrder);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDelivery(delivery.getDeliveryNo()) != null) {
                throw new PreexistingEntityException("Delivery " + delivery + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Delivery delivery) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Delivery persistentDelivery = em.find(Delivery.class, delivery.getDeliveryNo());
            Collection<Order> order1CollectionOld = persistentDelivery.getOrder1Collection();
            Collection<Order> order1CollectionNew = delivery.getOrder1Collection();
            List<String> illegalOrphanMessages = null;
            for (Order order1CollectionOldOrder : order1CollectionOld) {
                if (!order1CollectionNew.contains(order1CollectionOldOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Order " + order1CollectionOldOrder + " since its deliveryNo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Order> attachedOrder1CollectionNew = new ArrayList<Order>();
            for (Order order1CollectionNewOrderToAttach : order1CollectionNew) {
                order1CollectionNewOrderToAttach = em.getReference(order1CollectionNewOrderToAttach.getClass(), order1CollectionNewOrderToAttach.getOrderNo());
                attachedOrder1CollectionNew.add(order1CollectionNewOrderToAttach);
            }
            order1CollectionNew = attachedOrder1CollectionNew;
            delivery.setOrder1Collection(order1CollectionNew);
            delivery = em.merge(delivery);
            for (Order order1CollectionNewOrder : order1CollectionNew) {
                if (!order1CollectionOld.contains(order1CollectionNewOrder)) {
                    Delivery oldDeliveryNoOfOrder1CollectionNewOrder = order1CollectionNewOrder.getDeliveryNo();
                    order1CollectionNewOrder.setDeliveryNo(delivery);
                    order1CollectionNewOrder = em.merge(order1CollectionNewOrder);
                    if (oldDeliveryNoOfOrder1CollectionNewOrder != null && !oldDeliveryNoOfOrder1CollectionNewOrder.equals(delivery)) {
                        oldDeliveryNoOfOrder1CollectionNewOrder.getOrder1Collection().remove(order1CollectionNewOrder);
                        oldDeliveryNoOfOrder1CollectionNewOrder = em.merge(oldDeliveryNoOfOrder1CollectionNewOrder);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = delivery.getDeliveryNo();
                if (findDelivery(id) == null) {
                    throw new NonexistentEntityException("The delivery with id " + id + " no longer exists.");
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
            Delivery delivery;
            try {
                delivery = em.getReference(Delivery.class, id);
                delivery.getDeliveryNo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The delivery with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Order> order1CollectionOrphanCheck = delivery.getOrder1Collection();
            for (Order order1CollectionOrphanCheckOrder : order1CollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Delivery (" + delivery + ") cannot be destroyed since the Order " + order1CollectionOrphanCheckOrder + " in its order1Collection field has a non-nullable deliveryNo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(delivery);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Delivery> findDeliveryEntities() {
        return findDeliveryEntities(true, -1, -1);
    }

    public List<Delivery> findDeliveryEntities(int maxResults, int firstResult) {
        return findDeliveryEntities(false, maxResults, firstResult);
    }

    private List<Delivery> findDeliveryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Delivery.class));
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

    public Delivery findDelivery(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Delivery.class, id);
        } finally {
            em.close();
        }
    }

    public int getDeliveryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Delivery> rt = cq.from(Delivery.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
