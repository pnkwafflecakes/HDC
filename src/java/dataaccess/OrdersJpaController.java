/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import BusinessClasses.exceptions.IllegalOrphanException;
import BusinessClasses.exceptions.NonexistentEntityException;
import BusinessClasses.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Delivery;
import Entities.User;
import Entities.Cakeorder;
import java.util.ArrayList;
import java.util.Collection;
import Entities.Cake;
import Entities.Orders;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author 775224
 */
public class OrdersJpaController implements Serializable {

    public void create(Orders orders) throws PreexistingEntityException, Exception {
        if (orders.getCakeorderCollection() == null) {
            orders.setCakeorderCollection(new ArrayList<Cakeorder>());
        }
        if (orders.getCakeCollection() == null) {
            orders.setCakeCollection(new ArrayList<Cake>());
        }
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Delivery deliveryNo = orders.getDeliveryNo();
            if (deliveryNo != null) {
                deliveryNo = em.getReference(deliveryNo.getClass(), deliveryNo.getDeliveryNo());
                orders.setDeliveryNo(deliveryNo);
            }
            User userId = orders.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getUserId());
                orders.setUserId(userId);
            }
            Collection<Cakeorder> attachedCakeorderCollection = new ArrayList<Cakeorder>();
            for (Cakeorder cakeorderCollectionCakeorderToAttach : orders.getCakeorderCollection()) {
                cakeorderCollectionCakeorderToAttach = em.getReference(cakeorderCollectionCakeorderToAttach.getClass(), cakeorderCollectionCakeorderToAttach.getCakeorderPK());
                attachedCakeorderCollection.add(cakeorderCollectionCakeorderToAttach);
            }
            orders.setCakeorderCollection(attachedCakeorderCollection);
            Collection<Cake> attachedCakeCollection = new ArrayList<Cake>();
            for (Cake cakeCollectionCakeToAttach : orders.getCakeCollection()) {
                cakeCollectionCakeToAttach = em.getReference(cakeCollectionCakeToAttach.getClass(), cakeCollectionCakeToAttach.getCakeId());
                attachedCakeCollection.add(cakeCollectionCakeToAttach);
            }
            orders.setCakeCollection(attachedCakeCollection);
            em.persist(orders);
            if (deliveryNo != null) {
                deliveryNo.getOrdersCollection().add(orders);
                deliveryNo = em.merge(deliveryNo);
            }
            if (userId != null) {
                userId.getOrdersCollection().add(orders);
                userId = em.merge(userId);
            }
            for (Cakeorder cakeorderCollectionCakeorder : orders.getCakeorderCollection()) {
                Orders oldOrdersOfCakeorderCollectionCakeorder = cakeorderCollectionCakeorder.getOrders();
                cakeorderCollectionCakeorder.setOrders(orders);
                cakeorderCollectionCakeorder = em.merge(cakeorderCollectionCakeorder);
                if (oldOrdersOfCakeorderCollectionCakeorder != null) {
                    oldOrdersOfCakeorderCollectionCakeorder.getCakeorderCollection().remove(cakeorderCollectionCakeorder);
                    oldOrdersOfCakeorderCollectionCakeorder = em.merge(oldOrdersOfCakeorderCollectionCakeorder);
                }
            }
            for (Cake cakeCollectionCake : orders.getCakeCollection()) {
                cakeCollectionCake.getOrdersCollection().add(orders);
                cakeCollectionCake = em.merge(cakeCollectionCake);
            }
            trans.commit();
        } catch (Exception ex) {
            if (findOrders(orders.getOrderNo()) != null) {
                throw new PreexistingEntityException("Orders " + orders + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orders orders) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Orders persistentOrders = em.find(Orders.class, orders.getOrderNo());
            Delivery deliveryNoOld = persistentOrders.getDeliveryNo();
            Delivery deliveryNoNew = orders.getDeliveryNo();
            User userIdOld = persistentOrders.getUserId();
            User userIdNew = orders.getUserId();
            Collection<Cakeorder> cakeorderCollectionOld = persistentOrders.getCakeorderCollection();
            Collection<Cakeorder> cakeorderCollectionNew = orders.getCakeorderCollection();
            Collection<Cake> cakeCollectionOld = persistentOrders.getCakeCollection();
            Collection<Cake> cakeCollectionNew = orders.getCakeCollection();
            List<String> illegalOrphanMessages = null;
            for (Cakeorder cakeorderCollectionOldCakeorder : cakeorderCollectionOld) {
                if (!cakeorderCollectionNew.contains(cakeorderCollectionOldCakeorder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cakeorder " + cakeorderCollectionOldCakeorder + " since its orders field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (deliveryNoNew != null) {
                deliveryNoNew = em.getReference(deliveryNoNew.getClass(), deliveryNoNew.getDeliveryNo());
                orders.setDeliveryNo(deliveryNoNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getUserId());
                orders.setUserId(userIdNew);
            }
            Collection<Cakeorder> attachedCakeorderCollectionNew = new ArrayList<Cakeorder>();
            for (Cakeorder cakeorderCollectionNewCakeorderToAttach : cakeorderCollectionNew) {
                cakeorderCollectionNewCakeorderToAttach = em.getReference(cakeorderCollectionNewCakeorderToAttach.getClass(), cakeorderCollectionNewCakeorderToAttach.getCakeorderPK());
                attachedCakeorderCollectionNew.add(cakeorderCollectionNewCakeorderToAttach);
            }
            cakeorderCollectionNew = attachedCakeorderCollectionNew;
            orders.setCakeorderCollection(cakeorderCollectionNew);
            Collection<Cake> attachedCakeCollectionNew = new ArrayList<Cake>();
            for (Cake cakeCollectionNewCakeToAttach : cakeCollectionNew) {
                cakeCollectionNewCakeToAttach = em.getReference(cakeCollectionNewCakeToAttach.getClass(), cakeCollectionNewCakeToAttach.getCakeId());
                attachedCakeCollectionNew.add(cakeCollectionNewCakeToAttach);
            }
            cakeCollectionNew = attachedCakeCollectionNew;
            orders.setCakeCollection(cakeCollectionNew);
            orders = em.merge(orders);
            if (deliveryNoOld != null && !deliveryNoOld.equals(deliveryNoNew)) {
                deliveryNoOld.getOrdersCollection().remove(orders);
                deliveryNoOld = em.merge(deliveryNoOld);
            }
            if (deliveryNoNew != null && !deliveryNoNew.equals(deliveryNoOld)) {
                deliveryNoNew.getOrdersCollection().add(orders);
                deliveryNoNew = em.merge(deliveryNoNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getOrdersCollection().remove(orders);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getOrdersCollection().add(orders);
                userIdNew = em.merge(userIdNew);
            }
            for (Cakeorder cakeorderCollectionNewCakeorder : cakeorderCollectionNew) {
                if (!cakeorderCollectionOld.contains(cakeorderCollectionNewCakeorder)) {
                    Orders oldOrdersOfCakeorderCollectionNewCakeorder = cakeorderCollectionNewCakeorder.getOrders();
                    cakeorderCollectionNewCakeorder.setOrders(orders);
                    cakeorderCollectionNewCakeorder = em.merge(cakeorderCollectionNewCakeorder);
                    if (oldOrdersOfCakeorderCollectionNewCakeorder != null && !oldOrdersOfCakeorderCollectionNewCakeorder.equals(orders)) {
                        oldOrdersOfCakeorderCollectionNewCakeorder.getCakeorderCollection().remove(cakeorderCollectionNewCakeorder);
                        oldOrdersOfCakeorderCollectionNewCakeorder = em.merge(oldOrdersOfCakeorderCollectionNewCakeorder);
                    }
                }
            }
            for (Cake cakeCollectionOldCake : cakeCollectionOld) {
                if (!cakeCollectionNew.contains(cakeCollectionOldCake)) {
                    cakeCollectionOldCake.getOrdersCollection().remove(orders);
                    cakeCollectionOldCake = em.merge(cakeCollectionOldCake);
                }
            }
            for (Cake cakeCollectionNewCake : cakeCollectionNew) {
                if (!cakeCollectionOld.contains(cakeCollectionNewCake)) {
                    cakeCollectionNewCake.getOrdersCollection().add(orders);
                    cakeCollectionNewCake = em.merge(cakeCollectionNewCake);
                }
            }
            trans.commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orders.getOrderNo();
                if (findOrders(id) == null) {
                    throw new NonexistentEntityException("The orders with id " + id + " no longer exists.");
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
            Orders orders;
            try {
                orders = em.getReference(Orders.class, id);
                orders.getOrderNo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orders with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Cakeorder> cakeorderCollectionOrphanCheck = orders.getCakeorderCollection();
            for (Cakeorder cakeorderCollectionOrphanCheckCakeorder : cakeorderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Orders (" + orders + ") cannot be destroyed since the Cakeorder " + cakeorderCollectionOrphanCheckCakeorder + " in its cakeorderCollection field has a non-nullable orders field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Delivery deliveryNo = orders.getDeliveryNo();
            if (deliveryNo != null) {
                deliveryNo.getOrdersCollection().remove(orders);
                deliveryNo = em.merge(deliveryNo);
            }
            User userId = orders.getUserId();
            if (userId != null) {
                userId.getOrdersCollection().remove(orders);
                userId = em.merge(userId);
            }
            Collection<Cake> cakeCollection = orders.getCakeCollection();
            for (Cake cakeCollectionCake : cakeCollection) {
                cakeCollectionCake.getOrdersCollection().remove(orders);
                cakeCollectionCake = em.merge(cakeCollectionCake);
            }
            em.remove(orders);
            trans.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orders> findOrdersEntities() {
        return findOrdersEntities(true, -1, -1);
    }

    public List<Orders> findOrdersEntities(int maxResults, int firstResult) {
        return findOrdersEntities(false, maxResults, firstResult);
    }

    private List<Orders> findOrdersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orders.class));
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

    public Orders findOrders(Integer id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.find(Orders.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdersCount() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orders> rt = cq.from(Orders.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
