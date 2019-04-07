/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import BusinessClasses.exceptions.NonexistentEntityException;
import BusinessClasses.exceptions.PreexistingEntityException;
import Entities.Pickup;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author 775224
 */
public class PickupJpaController implements Serializable {

    public void create(Pickup pickup) throws PreexistingEntityException, Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            em.persist(pickup);
            trans.commit();
        } catch (Exception ex) {
            if (findPickup(pickup.getPickupId()) != null) {
                throw new PreexistingEntityException("Pickup " + pickup + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pickup pickup) throws NonexistentEntityException, Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            pickup = em.merge(pickup);
            trans.commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pickup.getPickupId();
                if (findPickup(id) == null) {
                    throw new NonexistentEntityException("The pickup with id " + id + " no longer exists.");
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
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Pickup pickup;
            try {
                pickup = em.getReference(Pickup.class, id);
                pickup.getPickupId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pickup with id " + id + " no longer exists.", enfe);
            }
            em.remove(pickup);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pickup> findPickupEntities() {
        return findPickupEntities(true, -1, -1);
    }

    public List<Pickup> findPickupEntities(int maxResults, int firstResult) {
        return findPickupEntities(false, maxResults, firstResult);
    }

    private List<Pickup> findPickupEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pickup.class));
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

    public Pickup findPickup(Integer id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.find(Pickup.class, id);
        } finally {
            em.close();
        }
    }

    public int getPickupCount() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pickup> rt = cq.from(Pickup.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
