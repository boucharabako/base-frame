///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author herve
// */
//@Service
//public class CodifListService {
//
//    @Autowired
//    private CodifListRepository codifListRepository;
//    @Autowired
//    private LibelleRepository libelleRepository;
//    static final Logger LOGGER = Logger.getAnonymousLogger();
//
//    /**
//     *
//     * @param codifList
//     * @return
//     */
//    @Transactional
//    public CodifList save(CodifList codifList) {
//        try {
//            return this.codifListRepository.save(codifList);
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "Exception", e);
//            return null;
//        }
//
//    }
//
//    /**
//     * Suupression d'une codifiList
//     *
//     * @param codifList
//     * @return
//     */
//    @Transactional
//    public boolean delete(CodifList codifList) {
//        try {
//            List<Libelle> libelles = this.codifListRepository.findByCodifListId(codifList.getId());
//            for (Libelle libelle : libelles) {
//                this.libelleRepository.delete(libelle.getId());
//            }
//            this.codifListRepository.delete(codifList.getId());
//            return true;
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "Exception", e);
//            return false;
//        }
//
//    }
//}
