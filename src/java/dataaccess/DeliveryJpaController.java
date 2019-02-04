/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import Database.exceptions.IllegalOrphanException;
import Database.exceptions.NonexistentEntityException;
import Database.exceptions.PreexistingEntityException;
import Entities.Delivery;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class DeliveryJpaController implements Serializable {

    public DeliveryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Delivery delivery) throws PreexistingEntityException, Exception {
        if (delivery.getOrder1Collection() == null) {
            delivery.setOrder1Collection(new ArrayList<Order1>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Order1> attachedOrder1Collection = new ArrayList<Order1>();
            for (Order1 order1CollectionOrder1ToAttach : delivery.getOrder1Collection()) {
                order1CollectionOrder1ToAttach = em.getReference(order1CollectionOrder1ToAttach.getClass(), order1CollectionOrder1ToAttach.getOrderNo());
                attachedOrder1Collection.add(order1CollectionOrder1ToAttach);
            }
            delivery.setOrder1Collection(attachedOrder1Collection);
            em.persist(delivery);
            for (Order1 order1CollectionOrder1 : delivery.getOrder1Collection()) {
                Delivery oldDeliveryNoOfOrder1CollectionOrder1 = order1CollectionOrder1.getDeliveryNo();
                order1CollectionOrder1.setDeliveryNo(delivery);
                order1CollectionOrder1 = em.merge(order1CollectionOrder1);
                if (oldDeliveryNoOfOrder1CollectionOrder1 != null) {
                    oldDeliveryNoOfOrder1CollectionOrder1.getOrder1Collection().remove(order1CollectionOrder1);
                    oldDeliveryNoOfOrder1CollectionOrder1 = em.merge(oldDeliveryNoOfOrder1CollectionOrder1);
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
            Collection<Order1> order1CollectionOld = persistentDelivery.getOrder1Collection();
            Collection<Order1> order1CollectionNew = delivery.getOrder1Collection();
            List<String> illegalOrphanMessages = null;
            for (Order1 order1CollectionOldOrder1 : order1CollectionOld) {
                if (!order1CollectionNew.contains(order1CollectionOldOrder1)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Order1 " + order1CollectionOldOrder1 + " since its deliveryNo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Order1> attachedOrder1CollectionNew = new ArrayList<Order1>();
            for (Order1 order1CollectionNewOrder1ToAttach : order1CollectionNew) {
                order1CollectionNewOrder1ToAttach = em.getReference(order1CollectionNewOrder1ToAttach.getClass(), order1CollectionNewOrder1ToAttach.getOrderNo());
                attachedOrder1CollectionNew.add(order1CollectionNewOrder1ToAttach);
            }
            order1CollectionNew = attachedOrder1CollectionNew;
            delivery.setOrder1Collection(order1CollectionNew);
            delivery = em.merge(delivery);
            for (Order1 order1CollectionNewOrder1 : order1CollectionNew) {
                if (!order1CollectionOld.contains(order1CollectionNewOrder1)) {
                    Delivery oldDeliveryNoOfOrder1CollectionNewOrder1 = order1CollectionNewOrder1.getDeliveryNo();
                    order1CollectionNewOrder1.setDeliveryNo(delivery);
                    order1CollectionNewOrder1 = em.merge(order1CollectionNewOrder1);
                    if (oldDeliveryNoOfOrder1CollectionNewOrder1 != null && !oldDeliveryNoOfOrder1CollectionNewOrder1.equals(delivery)) {
                        oldDeliveryNoOfOrder1CollectionNewOrder1.getOrder1Collection().remove(order1CollectionNewOrder1);
                        oldDeliveryNoOfOrder1CollectionNewOrder1 = em.merge(oldDeliveryNoOfOrder1CollectionNewOrder1);
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
            Collection<Order1> order1CollectionOrphanCheck = delivery.getOrder1Collection();
            for (Order1 order1CollectionOrphanCheckOrder1 : order1CollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Delivery (" + delivery + ") cannot be destroyed since the Order1 " + order1CollectionOrphanCheckOrder1 + " in its order1Collection field has a non-nullable deliveryNo field.");
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
