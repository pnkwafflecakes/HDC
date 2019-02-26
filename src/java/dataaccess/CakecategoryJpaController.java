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
import Entities.Cake;
import Entities.Cakecategory;
import dataaccess.DBUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author 775224
 */
public class CakecategoryJpaController implements Serializable {

    public void create(Cakecategory cakecategory) throws PreexistingEntityException, Exception {
        if (cakecategory.getCakeCollection() == null) {
            cakecategory.setCakeCollection(new ArrayList<Cake>());
        }
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Collection<Cake> attachedCakeCollection = new ArrayList<Cake>();
            for (Cake cakeCollectionCakeToAttach : cakecategory.getCakeCollection()) {
                cakeCollectionCakeToAttach = em.getReference(cakeCollectionCakeToAttach.getClass(), cakeCollectionCakeToAttach.getCakeId());
                attachedCakeCollection.add(cakeCollectionCakeToAttach);
            }
            cakecategory.setCakeCollection(attachedCakeCollection);
            em.persist(cakecategory);
            for (Cake cakeCollectionCake : cakecategory.getCakeCollection()) {
                Cakecategory oldCategoryIdOfCakeCollectionCake = cakeCollectionCake.getCategoryId();
                cakeCollectionCake.setCategoryId(cakecategory);
                cakeCollectionCake = em.merge(cakeCollectionCake);
                if (oldCategoryIdOfCakeCollectionCake != null) {
                    oldCategoryIdOfCakeCollectionCake.getCakeCollection().remove(cakeCollectionCake);
                    oldCategoryIdOfCakeCollectionCake = em.merge(oldCategoryIdOfCakeCollectionCake);
                }
            }
            trans.commit();
        } catch (Exception ex) {
            if (findCakecategory(cakecategory.getCategoryId()) != null) {
                throw new PreexistingEntityException("Cakecategory " + cakecategory + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cakecategory cakecategory) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            Cakecategory persistentCakecategory = em.find(Cakecategory.class, cakecategory.getCategoryId());
            Collection<Cake> cakeCollectionOld = persistentCakecategory.getCakeCollection();
            Collection<Cake> cakeCollectionNew = cakecategory.getCakeCollection();
            List<String> illegalOrphanMessages = null;
            for (Cake cakeCollectionOldCake : cakeCollectionOld) {
                if (!cakeCollectionNew.contains(cakeCollectionOldCake)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cake " + cakeCollectionOldCake + " since its categoryId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Cake> attachedCakeCollectionNew = new ArrayList<Cake>();
            for (Cake cakeCollectionNewCakeToAttach : cakeCollectionNew) {
                cakeCollectionNewCakeToAttach = em.getReference(cakeCollectionNewCakeToAttach.getClass(), cakeCollectionNewCakeToAttach.getCakeId());
                attachedCakeCollectionNew.add(cakeCollectionNewCakeToAttach);
            }
            cakeCollectionNew = attachedCakeCollectionNew;
            cakecategory.setCakeCollection(cakeCollectionNew);
            cakecategory = em.merge(cakecategory);
            for (Cake cakeCollectionNewCake : cakeCollectionNew) {
                if (!cakeCollectionOld.contains(cakeCollectionNewCake)) {
                    Cakecategory oldCategoryIdOfCakeCollectionNewCake = cakeCollectionNewCake.getCategoryId();
                    cakeCollectionNewCake.setCategoryId(cakecategory);
                    cakeCollectionNewCake = em.merge(cakeCollectionNewCake);
                    if (oldCategoryIdOfCakeCollectionNewCake != null && !oldCategoryIdOfCakeCollectionNewCake.equals(cakecategory)) {
                        oldCategoryIdOfCakeCollectionNewCake.getCakeCollection().remove(cakeCollectionNewCake);
                        oldCategoryIdOfCakeCollectionNewCake = em.merge(oldCategoryIdOfCakeCollectionNewCake);
                    }
                }
            }
            trans.commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cakecategory.getCategoryId();
                if (findCakecategory(id) == null) {
                    throw new NonexistentEntityException("The cakecategory with id " + id + " no longer exists.");
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
            Cakecategory cakecategory;
            try {
                cakecategory = em.getReference(Cakecategory.class, id);
                cakecategory.getCategoryId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cakecategory with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Cake> cakeCollectionOrphanCheck = cakecategory.getCakeCollection();
            for (Cake cakeCollectionOrphanCheckCake : cakeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cakecategory (" + cakecategory + ") cannot be destroyed since the Cake " + cakeCollectionOrphanCheckCake + " in its cakeCollection field has a non-nullable categoryId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cakecategory);
            trans.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cakecategory> findCakecategoryEntities() {
        return findCakecategoryEntities(true, -1, -1);
    }

    public List<Cakecategory> findCakecategoryEntities(int maxResults, int firstResult) {
        return findCakecategoryEntities(false, maxResults, firstResult);
    }

    private List<Cakecategory> findCakecategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cakecategory.class));
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

    public Cakecategory findCakecategory(Integer id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.find(Cakecategory.class, id);
        } finally {
            em.close();
        }
    }

    public int getCakecategoryCount() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cakecategory> rt = cq.from(Cakecategory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
