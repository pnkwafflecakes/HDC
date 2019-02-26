/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import BusinessClasses.exceptions.NonexistentEntityException;
import BusinessClasses.exceptions.PreexistingEntityException;
import Entities.Cakeorder;
import Entities.CakeorderPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Orders;
import dataaccess.DBUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author 775224
 */
public class CakeorderJpaController implements Serializable {

    public void create(Cakeorder cakeorder) throws PreexistingEntityException, Exception {
        if (cakeorder.getCakeorderPK() == null) {
            cakeorder.setCakeorderPK(new CakeorderPK());
        }
        cakeorder.getCakeorderPK().setCakeId(cakeorder.getCake().getCakeId());
        cakeorder.getCakeorderPK().setOrderNo(cakeorder.getOrders().getOrderNo());
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Orders orders = cakeorder.getOrders();
            if (orders != null) {
                orders = em.getReference(orders.getClass(), orders.getOrderNo());
                cakeorder.setOrders(orders);
            }
            em.persist(cakeorder);
            if (orders != null) {
                orders.getCakeorderCollection().add(cakeorder);
                orders = em.merge(orders);
            }
            trans.commit();
        } catch (Exception ex) {
            if (findCakeorder(cakeorder.getCakeorderPK()) != null) {
                throw new PreexistingEntityException("Cakeorder " + cakeorder + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cakeorder cakeorder) throws NonexistentEntityException, Exception {
        cakeorder.getCakeorderPK().setCakeId(cakeorder.getCake().getCakeId());
        cakeorder.getCakeorderPK().setOrderNo(cakeorder.getOrders().getOrderNo());
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Cakeorder persistentCakeorder = em.find(Cakeorder.class, cakeorder.getCakeorderPK());
            Orders ordersOld = persistentCakeorder.getOrders();
            Orders ordersNew = cakeorder.getOrders();
            if (ordersNew != null) {
                ordersNew = em.getReference(ordersNew.getClass(), ordersNew.getOrderNo());
                cakeorder.setOrders(ordersNew);
            }
            cakeorder = em.merge(cakeorder);
            if (ordersOld != null && !ordersOld.equals(ordersNew)) {
                ordersOld.getCakeorderCollection().remove(cakeorder);
                ordersOld = em.merge(ordersOld);
            }
            if (ordersNew != null && !ordersNew.equals(ordersOld)) {
                ordersNew.getCakeorderCollection().add(cakeorder);
                ordersNew = em.merge(ordersNew);
            }
            trans.commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CakeorderPK id = cakeorder.getCakeorderPK();
                if (findCakeorder(id) == null) {
                    throw new NonexistentEntityException("The cakeorder with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CakeorderPK id) throws NonexistentEntityException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Cakeorder cakeorder;
            try {
                cakeorder = em.getReference(Cakeorder.class, id);
                cakeorder.getCakeorderPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cakeorder with id " + id + " no longer exists.", enfe);
            }
            Orders orders = cakeorder.getOrders();
            if (orders != null) {
                orders.getCakeorderCollection().remove(cakeorder);
                orders = em.merge(orders);
            }
            em.remove(cakeorder);
            trans.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cakeorder> findCakeorderEntities() {
        return findCakeorderEntities(true, -1, -1);
    }

    public List<Cakeorder> findCakeorderEntities(int maxResults, int firstResult) {
        return findCakeorderEntities(false, maxResults, firstResult);
    }

    private List<Cakeorder> findCakeorderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cakeorder.class));
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

    public Cakeorder findCakeorder(CakeorderPK id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.find(Cakeorder.class, id);
        } finally {
            em.close();
        }
    }

    public int getCakeorderCount() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cakeorder> rt = cq.from(Cakeorder.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
