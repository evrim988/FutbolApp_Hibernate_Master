package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;
import java.util.Optional;

public class RepositoryManager<T, ID> implements ICRUD<T, ID> {

    private final EntityManagerFactory emf;
    private final Class<T> entityClass;

    public RepositoryManager(Class<T> entityClass) {
        this.emf = Persistence.createEntityManagerFactory("pu_hibernate");
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public T save(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(entity);
            tx.commit();
        }
        catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("Save metodunda hata..."+e.getMessage());
        }
        finally {
            em.close();
        }
        return entity;
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entities) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            for (T entity : entities) {
                em.persist(entity);
            }
            tx.commit();
        }
        catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("SaveAll metodunda hata..."+e.getMessage());
        }
        finally {
            em.close();
        }
        return entities;
    }

    @Override
    public Boolean deleteById(ID id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            T entityToRemove = em.find(entityClass, id);
            if (entityToRemove != null) {
                em.remove(entityToRemove);
                tx.commit();
                return true;
            }
            else {
                tx.commit();
                return false;
            }
        }
        catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.out.println("DeleteById metodunda hata..."+e.getMessage());
            return false;
        }
        finally {
            em.close();
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        EntityManager em = getEntityManager();
        try {
            T entity = em.find(entityClass, id);
            return Optional.ofNullable(entity);
        }
        finally {
            em.close();
        }
    }

    @Override
    public Boolean existById(ID id) {
        return findById(id).isPresent();
    }

    @Override
    public List<T> findAll() {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            cq.select(cq.from(entityClass));
            return em.createQuery(cq).getResultList();
        }
        finally {
            em.close();
        }
    }
}
