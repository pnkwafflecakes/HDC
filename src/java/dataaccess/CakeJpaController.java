/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import Database.exceptions.NonexistentEntityException;
import Database.exceptions.PreexistingEntityException;
import Entities.Cake;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Cakecategory;
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
public class CakeJpaController implements Serializable {

    public CakeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = DBUtil.getEmFactory();

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cake cake) throws PreexistingEntityException, Exception {
        if (cake.getOrder1Collection() == null) {
            cake.setOrder1Collection(new ArrayList<Order1>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cakecategory categoryId = cake.getCategoryId();
            if (categoryId != null) {
                categoryId = em.getReference(categoryId.getClass(), categoryId.getCategoryId());
                cake.setCategoryId(categoryId);
            }
            Collection<Order1> attachedOrder1Collection = new ArrayList<Order1>();
            for (Order1 order1CollectionOrder1ToAttach : cake.getOrder1Collection()) {
                order1CollectionOrder1ToAttach = em.getReference(order1CollectionOrder1ToAttach.getClass(), order1CollectionOrder1ToAttach.getOrderNo());
                attachedOrder1Collection.add(order1CollectionOrder1ToAttach);
            }
            cake.setOrder1Collection(attachedOrder1Collection);
            em.persist(cake);
            if (categoryId != null) {
                categoryId.getCakeCollection().add(cake);
                categoryId = em.merge(categoryId);
            }
            for (Order1 order1CollectionOrder1 : cake.getOrder1Collection()) {
                order1CollectionOrder1.getCakeCollection().add(cake);
                order1CollectionOrder1 = em.merge(order1CollectionOrder1);
            }
            em.getTransaction().commit();
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

    public void edit(Cake cake) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cake persistentCake = em.find(Cake.class, cake.getCakeId());
            Cakecategory categoryIdOld = persistentCake.getCategoryId();
            Cakecategory categoryIdNew = cake.getCategoryId();
            Collection<Order1> order1CollectionOld = persistentCake.getOrder1Collection();
            Collection<Order1> order1CollectionNew = cake.getOrder1Collection();
            if (categoryIdNew != null) {
                categoryIdNew = em.getReference(categoryIdNew.getClass(), categoryIdNew.getCategoryId());
                cake.setCategoryId(categoryIdNew);
            }
            Collection<Order1> attachedOrder1CollectionNew = new ArrayList<Order1>();
            for (Order1 order1CollectionNewOrder1ToAttach : order1CollectionNew) {
                order1CollectionNewOrder1ToAttach = em.getReference(order1CollectionNewOrder1ToAttach.getClass(), order1CollectionNewOrder1ToAttach.getOrderNo());
                attachedOrder1CollectionNew.add(order1CollectionNewOrder1ToAttach);
            }
            order1CollectionNew = attachedOrder1CollectionNew;
            cake.setOrder1Collection(order1CollectionNew);
            cake = em.merge(cake);
            if (categoryIdOld != null && !categoryIdOld.equals(categoryIdNew)) {
                categoryIdOld.getCakeCollection().remove(cake);
                categoryIdOld = em.merge(categoryIdOld);
            }
            if (categoryIdNew != null && !categoryIdNew.equals(categoryIdOld)) {
                categoryIdNew.getCakeCollection().add(cake);
                categoryIdNew = em.merge(categoryIdNew);
            }
            for (Order1 order1CollectionOldOrder1 : order1CollectionOld) {
                if (!order1CollectionNew.contains(order1CollectionOldOrder1)) {
                    order1CollectionOldOrder1.getCakeCollection().remove(cake);
                    order1CollectionOldOrder1 = em.merge(order1CollectionOldOrder1);
                }
            }
            for (Order1 order1CollectionNewOrder1 : order1CollectionNew) {
                if (!order1CollectionOld.contains(order1CollectionNewOrder1)) {
                    order1CollectionNewOrder1.getCakeCollection().add(cake);
                    order1CollectionNewOrder1 = em.merge(order1CollectionNewOrder1);
                }
            }
            em.getTransaction().commit();
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cake cake;
            try {
                cake = em.getReference(Cake.class, id);
                cake.getCakeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cake with id " + id + " no longer exists.", enfe);
            }
            Cakecategory categoryId = cake.getCategoryId();
            if (categoryId != null) {
                categoryId.getCakeCollection().remove(cake);
                categoryId = em.merge(categoryId);
            }
            Collection<Order1> order1Collection = cake.getOrder1Collection();
            for (Order1 order1CollectionOrder1 : order1Collection) {
                order1CollectionOrder1.getCakeCollection().remove(cake);
                order1CollectionOrder1 = em.merge(order1CollectionOrder1);
            }
            em.remove(cake);
            em.getTransaction().commit();
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
        EntityManager em = getEntityManager();
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
        EntityManager em = getEntityManager();
        try {
            return em.find(Cake.class, id);
        } finally {
            em.close();
        }
    }

    public int getCakeCount() {
        EntityManager em = getEntityManager();
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
    
}
