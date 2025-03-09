/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.annotation.dao;

import com.base.frame.socle.core.codification.utils.UtilsRequestParameters;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author WKOUWONOU
 * @version 1.0.0
 * @param <E>
 * @param <ID>
 * @since 25-10-2017
 * @description Parametre de sauvegarde de la base de données
 */
@Transactional
public abstract class GenericDAO<E extends Serializable, ID> implements IGenericDAO<E, ID> {

    @PersistenceContext
    protected EntityManager em;
    protected static final String SUB_ENTITY = "subEntity";
    private final Class<E> entityClass;
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GenericDAO.class);
    private static final String WHERE = " WHERE t.";
    private static final String AS_T = " AS t ";
    private static final String AND_T = " AND t.";
    public GenericDAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<E> filterByParameters(Map<String, UtilsRequestParameters> parameters) {
        StringBuilder req = new StringBuilder("SELECT t FROM " + entityClass.getSimpleName() + AS_T);
        Set<String> ls = parameters.keySet();

        if (ls.contains(SUB_ENTITY)) {
            req = req.append("SELECT t.").append(parameters.get(SUB_ENTITY)).append(" FROM ").append(entityClass.getSimpleName()).append(AS_T);
            parameters.remove(SUB_ENTITY);
        }
        Boolean f = Boolean.TRUE;

        for (String s : ls) {
            String s1 = s;
            if (s.contains("-")) {

                s1 = s.split("-")[0];
            }

            if (f) {

                req = req.append(WHERE).append(s1).append("").append(parameters.get(s).getComparaison()).append(":").append(s.replaceAll("\\.", "").replaceAll("-", "")).append(" ").append(parameters.get(s).getFinRequette());
                f = Boolean.FALSE;
                continue;
            }

            req = req.append(AND_T).append(s1).append("").append(parameters.get(s).getComparaison()).append(":").append(s.replaceAll("\\.", "").replaceAll("-", "")).append(" ").append(parameters.get(s).getFinRequette());

        }
        LOG.info("-------------       " + req);
        Query q = em.createQuery(req.toString());

        ls.forEach(s -> {
            q.setParameter(s.replaceAll("\\.", "").replaceAll("-", ""), parameters.get(s).getValeurParametre());
        });

        return (List<E>) q.getResultList();
    }

    @Override
    public Page<E> filterByParameters(Map<String, UtilsRequestParameters> parameters, Pageable pageable) {
        StringBuilder req = new StringBuilder("SELECT t FROM " + entityClass.getSimpleName() + AS_T);
        StringBuilder total = new StringBuilder("SELECT COUNT(t) FROM " + entityClass.getSimpleName() + AS_T);
        Set<String> ls = parameters.keySet();

        if (ls.contains(SUB_ENTITY)) {
            req = req.append("SELECT t.").append(parameters.get(SUB_ENTITY)).append(" FROM ").append(entityClass.getSimpleName()).append(AS_T);
            parameters.remove(SUB_ENTITY);
        }
        Boolean f = Boolean.TRUE;
        for (String s : ls) {
            String s1 = s;
            if (s.contains("-")) {

                s1 = s.split("-")[0];
            }
            if (f) {

                req = req.append(WHERE).append(s1).append("").append(parameters.get(s).getComparaison()).append(":").append(s.replaceAll("\\.", "").replaceAll("-", "")).append(" ").append(parameters.get(s).getFinRequette());
                total = total.append(WHERE).append(s1).append("").append(parameters.get(s).getComparaison()).append(":").append(s.replaceAll("\\.", "").replaceAll("-", "")).append(" ").append(parameters.get(s).getFinRequette());
                f = Boolean.FALSE;
                continue;
            }
            req = req.append(AND_T).append(s1).append("").append(parameters.get(s).getComparaison()).append(":").append(s.replaceAll("\\.", "").replaceAll("-", "")).append(" ").append(parameters.get(s).getFinRequette());
            total = total.append(AND_T).append(s1).append("").append(parameters.get(s).getComparaison()).append(":").append(s.replaceAll("\\.", "").replaceAll("-", "")).append(" ").append(parameters.get(s).getFinRequette());

        }

        Query q = em.createQuery(req.toString());
        Query t = em.createQuery(total.toString());
        ls.stream().map(s -> {
            q.setParameter(s.replaceAll("\\.", "").replaceAll("-", ""), parameters.get(s).getValeurParametre());
            return s;
        }).forEachOrdered(s -> {
            t.setParameter(s.replaceAll("\\.", "").replaceAll("-", ""), parameters.get(s).getValeurParametre());
        });
        int size = pageable.getPageSize();
        int page = pageable.getPageNumber();

        Long taille = (long) t.getSingleResult();
        int totalPage = (int) (taille / size);
        if (taille.intValue() % size != 0) {
            totalPage++;
        }

        if (taille < size) {
            size = taille.intValue();
            page = 0;
        }
        if (totalPage - 1 < page) {

            return new PageImpl<>(new ArrayList<>(), pageable, taille);
        }

        q.setFirstResult(page * size);
        q.setMaxResults(size);

        return new PageImpl<>((List<E>) q.getResultList(), pageable, taille);
    }

    @Override
    public E getOne(ID id) {
        //To change body of generated methods, choose Tools | Templates.
        return (E) em.find(entityClass, id);
    }

    @Override
    public List<E> getAll() {
        Query q = em.createQuery("select t from " + entityClass.getSimpleName() + " t");
        return (List<E>) q.getResultList();
    }

    @Override
    @Transactional
    public E saveOne(E t) {
        em.persist(t);
        em.flush();
        em.refresh(t);
        return t;
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public E updateOne(E t) {
        //To change body of generated methods, choose Tools | Templates.

        em.merge(t);
        return t;
    }

    @Override
    public ID getId(final E t) {
        return (ID) em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(updateOne(t));
    }

    @Override
    public boolean deleteOne(ID id) {
        //To change body of generated methods, choose Tools | Templates.
        E t = (E) em.find(entityClass, id);
        if (t == null) {
            return false;
        }
        em.remove(t);
        em.flush();
        return true;
    }

    @Override
    public void deleteAll() {
        //To change body of generated methods, choose Tools | Templates.
        em.createQuery("delete from " + entityClass.getSimpleName()).executeUpdate();
    }

    public boolean deleteOne(E t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearAllCache(int cacheLevel) {
        if (cacheLevel == 1) {
            em.clear();
        } else if (cacheLevel == 2) {
            em.getEntityManagerFactory().getCache().evictAll();
        }
    }

    @Override
    public void clearClassCache() {
        em.getEntityManagerFactory().getCache().evict(entityClass);
    }

    @Override
    public void clearEntityCache(final E t) {
        em.getEntityManagerFactory().getCache().evict(entityClass, getId(t));
    }

    @Override
    public E refreshEntity(final E t) {
        em.flush();
        em.refresh(t);
        return t;
    }

}
