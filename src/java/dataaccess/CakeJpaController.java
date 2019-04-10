/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import BusinessClasses.exceptions.IllegalOrphanException;
import BusinessClasses.exceptions.NonexistentEntityException;
import BusinessClasses.exceptions.PreexistingEntityException;
import Entities.Cake;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Orders;
import java.util.ArrayList;
import java.util.Collection;
import Entities.Cakeorder;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author 775224
 */
public class CakeJpaController implements Serializable {

    public void create(Cake cake) throws PreexistingEntityException, Exception {
        if (cake.getOrdersCollection() == null) {
            cake.setOrdersCollection(new ArrayList<Orders>());
        }
        if (cake.getCakeorderCollection() == null) {
            cake.setCakeorderCollection(new ArrayList<Cakeorder>());
        }
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Collection<Orders> attachedOrdersCollection = new ArrayList<Orders>();
            for (Orders ordersCollectionOrdersToAttach : cake.getOrdersCollection()) {
                ordersCollectionOrdersToAttach = em.getReference(ordersCollectionOrdersToAttach.getClass(), ordersCollectionOrdersToAttach.getOrderNo());
                attachedOrdersCollection.add(ordersCollectionOrdersToAttach);
            }
            cake.setOrdersCollection(attachedOrdersCollection);
            Collection<Cakeorder> attachedCakeorderCollection = new ArrayList<Cakeorder>();
            for (Cakeorder cakeorderCollectionCakeorderToAttach : cake.getCakeorderCollection()) {
                cakeorderCollectionCakeorderToAttach = em.getReference(cakeorderCollectionCakeorderToAttach.getClass(), cakeorderCollectionCakeorderToAttach.getCakeorderPK());
                attachedCakeorderCollection.add(cakeorderCollectionCakeorderToAttach);
            }
            cake.setCakeorderCollection(attachedCakeorderCollection);
            em.persist(cake);
            for (Orders ordersCollectionOrders : cake.getOrdersCollection()) {
                ordersCollectionOrders.getCakeCollection().add(cake);
                ordersCollectionOrders = em.merge(ordersCollectionOrders);
            }
            for (Cakeorder cakeorderCollectionCakeorder : cake.getCakeorderCollection()) {
                Cake oldCakeOfCakeorderCollectionCakeorder = cakeorderCollectionCakeorder.getCake();
                cakeorderCollectionCakeorder.setCake(cake);
                cakeorderCollectionCakeorder = em.merge(cakeorderCollectionCakeorder);
                if (oldCakeOfCakeorderCollectionCakeorder != null) {
                    oldCakeOfCakeorderCollectionCakeorder.getCakeorderCollection().remove(cakeorderCollectionCakeorder);
                    oldCakeOfCakeorderCollectionCakeorder = em.merge(oldCakeOfCakeorderCollectionCakeorder);
                }
            }
            trans.commit();
        } catch (Exception ex) {
            if (findCake(cake.getCakeId()) != null) {
                throw new PreexistingEntityException("Cake " + cake + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cake cake) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Cake persistentCake = em.find(Cake.class, cake.getCakeId());
            Collection<Orders> ordersCollectionOld = persistentCake.getOrdersCollection();
            Collection<Orders> ordersCollectionNew = cake.getOrdersCollection();
            Collection<Cakeorder> cakeorderCollectionOld = persistentCake.getCakeorderCollection();
            Collection<Cakeorder> cakeorderCollectionNew = cake.getCakeorderCollection();
            List<String> illegalOrphanMessages = null;
            for (Cakeorder cakeorderCollectionOldCakeorder : cakeorderCollectionOld) {
                if (!cakeorderCollectionNew.contains(cakeorderCollectionOldCakeorder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cakeorder " + cakeorderCollectionOldCakeorder + " since its cake field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Orders> attachedOrdersCollectionNew = new ArrayList<Orders>();
            try {
                for (Orders ordersCollectionNewOrdersToAttach : ordersCollectionNew) {
                    ordersCollectionNewOrdersToAttach = em.getReference(ordersCollectionNewOrdersToAttach.getClass(), ordersCollectionNewOrdersToAttach.getOrderNo());
                    attachedOrdersCollectionNew.add(ordersCollectionNewOrdersToAttach);
                }
            } catch (NullPointerException e) {
                
            }
            ordersCollectionNew = attachedOrdersCollectionNew;
            cake.setOrdersCollection(ordersCollectionNew);
            Collection<Cakeorder> attachedCakeorderCollectionNew = new ArrayList<Cakeorder>();
            try {
                for (Cakeorder cakeorderCollectionNewCakeorderToAttach : cakeorderCollectionNew) {
                    cakeorderCollectionNewCakeorderToAttach = em.getReference(cakeorderCollectionNewCakeorderToAttach.getClass(), cakeorderCollectionNewCakeorderToAttach.getCakeorderPK());
                    attachedCakeorderCollectionNew.add(cakeorderCollectionNewCakeorderToAttach);
                }
            } catch (NullPointerException e) {
                
            }
            cakeorderCollectionNew = attachedCakeorderCollectionNew;
            cake.setCakeorderCollection(cakeorderCollectionNew);
            cake = em.merge(cake);
            try {
                for (Orders ordersCollectionOldOrders : ordersCollectionOld) {
                    if (!ordersCollectionNew.contains(ordersCollectionOldOrders)) {
                        ordersCollectionOldOrders.getCakeCollection().remove(cake);
                        ordersCollectionOldOrders = em.merge(ordersCollectionOldOrders);
                    }
                }
            } catch (NullPointerException e) {
                
            }
            try {
                for (Orders ordersCollectionNewOrders : ordersCollectionNew) {
                    if (!ordersCollectionOld.contains(ordersCollectionNewOrders)) {
                        ordersCollectionNewOrders.getCakeCollection().add(cake);
                        ordersCollectionNewOrders = em.merge(ordersCollectionNewOrders);
                    }
                }
            } catch (NullPointerException e) {
                
            }
            try {
            for (Cakeorder cakeorderCollectionNewCakeorder : cakeorderCollectionNew) {
                if (!cakeorderCollectionOld.contains(cakeorderCollectionNewCakeorder)) {
                    Cake oldCakeOfCakeorderCollectionNewCakeorder = cakeorderCollectionNewCakeorder.getCake();
                    cakeorderCollectionNewCakeorder.setCake(cake);
                    cakeorderCollectionNewCakeorder = em.merge(cakeorderCollectionNewCakeorder);
                    if (oldCakeOfCakeorderCollectionNewCakeorder != null && !oldCakeOfCakeorderCollectionNewCakeorder.equals(cake)) {
                        oldCakeOfCakeorderCollectionNewCakeorder.getCakeorderCollection().remove(cakeorderCollectionNewCakeorder);
                        oldCakeOfCakeorderCollectionNewCakeorder = em.merge(oldCakeOfCakeorderCollectionNewCakeorder);
                    }
                }
            }
            } catch (NullPointerException e) {
                
            }
            trans.commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cake.getCakeId();
                if (findCake(id) == null) {
                    throw new NonexistentEntityException("The cake with id " + id + " no longer exists.");
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
            Cake cake;
            try {
                cake = em.getReference(Cake.class, id);
                cake.getCakeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cake with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Cakeorder> cakeorderCollectionOrphanCheck = cake.getCakeorderCollection();
            for (Cakeorder cakeorderCollectionOrphanCheckCakeorder : cakeorderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cake (" + cake + ") cannot be destroyed since the Cakeorder " + cakeorderCollectionOrphanCheckCakeorder + " in its cakeorderCollection field has a non-nullable cake field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Orders> ordersCollection = cake.getOrdersCollection();
            for (Orders ordersCollectionOrders : ordersCollection) {
                ordersCollectionOrders.getCakeCollection().remove(cake);
                ordersCollectionOrders = em.merge(ordersCollectionOrders);
            }
            em.remove(cake);
            trans.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cake> findCakeEntities() {
        return findCakeEntities(true, -1, -1);
    }

    public List<Cake> findCakeEntities(int maxResults, int firstResult) {
        return findCakeEntities(false, maxResults, firstResult);
    }

    private List<Cake> findCakeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cake.class));
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

    public Cake findCake(Integer id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.find(Cake.class, id);
        } finally {
            em.close();
        }
    }

    public int getCakeCount() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cake> rt = cq.from(Cake.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //    another solution for search in English, not work on Chinese
    public List<Cake> search(String str) throws NonexistentEntityException{
//      return  DBUtil.getEmFactory().createEntityManager().createQuery("select n from Note n", Note.class).getResultList();
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Cake c where c.descriptioncn LIKE :pattern";
        TypedQuery<Cake> q = em.createQuery(qString,Cake.class);
        q.setParameter("pattern", "%" +str+"%");
        List<Cake> cakes;
        try{
            cakes = q.getResultList();
            System.out.println("CakeJpaController.search"+cakes);
            if(cakes == null || cakes.isEmpty()){
                cakes = null;
            }
            }finally{
                    em.close();
            }
        return cakes;
        }
    
}
