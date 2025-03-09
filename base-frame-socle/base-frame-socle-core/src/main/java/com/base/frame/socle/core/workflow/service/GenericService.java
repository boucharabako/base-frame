///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.core.codification.dao.IGenericDAO;
//import com.ngs.core.codification.utils.UtilsRequestParameters;
//import java.io.Serializable;
//import java.util.List;
//
//
//import java.util.Map;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author kouroger
// * @version 1.0.0
// * @param <E>
// * @param <ID>
// * @since 25-10-2017
// * @description Parametre de  sauvegarde de la base de données
// */
//@Transactional
//@Service
//public abstract class GenericService<E extends Serializable, ID>
//        implements IGenericService<E, ID> {
//
//    
//    protected abstract IGenericDAO<E, ID> getDAO();
//
//  
//    @Override
//     public Page<E> filterByParameters(Map<String, UtilsRequestParameters> parameters, Pageable pageable) {
//         return this.getDAO().filterByParameters(parameters, pageable);
//     }
//    @Override
//    public List<E> filterByParameters(Map<String, UtilsRequestParameters> parameters) {
//        return this.getDAO().filterByParameters(parameters);
//    }
//}
