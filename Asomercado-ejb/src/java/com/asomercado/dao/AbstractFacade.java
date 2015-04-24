package com.asomercado.dao;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Abstract class that contains basic methods to interact with the Entities in the database.
 * @author Edgar Santos
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    /**
     * Creates an entity object in the database
     * @param entity 
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }

    /**
     * Updates an entity object in the database
     * @param entity 
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }

    /**
     * Removes an entity object in the database
     * @param entity 
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();
    }

    /**
     * Finds an entity object by its primary key
     * @param pk
     * @return 
     */
    public T find(Object pk) {
        return getEntityManager().find(entityClass, pk);
    }

    /**
     * @return all the entity objects for an specific table.
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    /**
     * 
     * @param range
     * @return a list of entity objects in a range.
     * E.g: range[0] = 20 and range[0] = 30 will return 10 objects starting from object number 20 in a findAll() list.
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        javax.persistence.Query query = getEntityManager().createQuery(criteriaQuery);
        query.setMaxResults(range[1] - range[0] + 1);
        query.setFirstResult(range[0]);
        return query.getResultList();
    }

    /**
     * 
     * @return the amount of entity objects in the database.
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(getEntityManager().getCriteriaBuilder().count(root));
        javax.persistence.Query query = getEntityManager().createQuery(criteriaQuery);
        return ((Long) query.getSingleResult()).intValue();
    }
    
}
