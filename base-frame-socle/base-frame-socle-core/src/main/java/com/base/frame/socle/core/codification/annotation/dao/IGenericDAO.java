/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.annotation.dao;

import com.base.frame.socle.core.codification.utils.UtilsRequestParameters;
import java.io.Serializable;
import java.util.List;

import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @param <E> L'entité qui implemente
 * @param <ID> La clé primaire de l'entité
 * @author W.KOUWONOU
 * @version 1.0.0
 * @since 25-10-2017
 * @description Recherche generique
 */
public interface IGenericDAO<E extends Serializable, ID> {

    public Page<E> filterByParameters(Map<String, UtilsRequestParameters> parameters, Pageable pageable);

    public List<E> filterByParameters(Map<String, UtilsRequestParameters> parameters);

    public E getOne(ID id);

    public List<E> getAll();

    public E saveOne(E e);

    public E updateOne(E e);

    public boolean deleteOne(ID id);

    public void deleteAll();

    public void clearAllCache(int cacheLevel);

    ID getId(final E e);

    public void clearClassCache();

    public void clearEntityCache(final E e);

    public E refreshEntity(final E e);
}
