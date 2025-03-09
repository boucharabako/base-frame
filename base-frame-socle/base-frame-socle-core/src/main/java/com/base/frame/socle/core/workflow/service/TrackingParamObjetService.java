///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.core.codification.entities.CodifList;
//import com.ngs.core.codification.entities.TrackingParamObjet;
//import com.ngs.core.codification.repositories.CodifListRepository;
//import com.ngs.core.codification.repositories.MessageApplicatifRepository;
//import com.ngs.core.codification.repositories.TrackingParamObjetRepository;
//import com.ngs.core.codification.utils.CodificationConstant;
//import java.io.Serializable;
//import java.time.Instant;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Adnaane
// * @version 1.0.0
// * @since 27-02-2018
// * @description Service concernant le tracking
// */
//@Service
//public class TrackingParamObjetService implements Serializable {
//
//    @Autowired
//    NProperties properties;
//    @Autowired
//    private TrackingParamObjetRepository trackingParamObjetRepository;
//    @Autowired
//    private MessageApplicatifRepository messageRepository;
//    @Autowired
//    private CodifListRepository codifListRepository;
//
//    /**
//     * Cette méthode permet de faire le tracking
//     *
//     * @param idObject l'id de l'objet
//     * @param comment le commentaire
//     * @param conceptMetier le concept métier
//     * @param statut le nouveau statut mise à jout ou ajouté
//     * @param natureEvent la nature de l'evenement
//     * @return
//     */
//    @Transactional
//    public TrackingParamObjet saveTracking(String idObject, String comment, CodifList conceptMetier, CodifList statut, CodifList natureEvent) {
//        TrackingParamObjet trackingParamObjet = new TrackingParamObjet();
//        trackingParamObjet.setIdObject(idObject);
//        trackingParamObjet.setConceptMetier(conceptMetier);
//        trackingParamObjet.setDateEvent(Instant.now());
//        trackingParamObjet.setComment(this.messageRepository.findLibelleByCodeAndCodeLangue(comment, properties.getLangue().getId()));
//        trackingParamObjet.setCodeColeur(
//                statut.getIdeCode().equals(CodificationConstant.STATUT_PARAM_ACTIF) ? CodificationConstant.COLOR_ACTIVER
//                : statut.getIdeCode().equals(CodificationConstant.STATUT_PARAM_OBSOLETE) ? CodificationConstant.COLOR_ABANDONNER
//                : statut.getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE) ? CodificationConstant.COLOR_EN_SAISIE : ""
//        );
//        trackingParamObjet.setStatut(statut);
//        trackingParamObjet.setNatureEvent(natureEvent);
//        return trackingParamObjetRepository.save(trackingParamObjet);
//    }
//
//    /**
//     * Cette méthode permet de faire le tracking concernant le changement de
//     * statut
//     *
//     * @param idObject l'id de l'objet
//     * @param conceptMetier le concept métier
//     * @param statut le nouveau statut mise à jout ou ajouté
//     * @return
//     */
//    @Transactional
//    public TrackingParamObjet saveStatusTracking(String idObject, String conceptMetier, CodifList statut) {
//        return this.saveStatusTracking(idObject, conceptMetier, !statut.getIdeCode().equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)?CodificationConstant.CHANGEMENT_STATUT_PARAM:CodificationConstant.CREATION_OBJET, statut);
//    }
//
//    /**
//     * Cette méthode permet de faire le tracking concernant le changement de
//     * statut
//     *
//     * @param idObject l'id de l'objet
//     * @param conceptMetier le concept métier
//     * @param message le message en question
//     * @param statut le nouveau statut mise à jout ou ajouté
//     * @return
//     */
//    @Transactional
//    public TrackingParamObjet saveStatusTracking(String idObject, String conceptMetier, String message, CodifList statut) {
//        return this.saveTracking(idObject, message,
//                codifListRepository.findByCodificationAndCode(CodificationConstant.CONCEPT_METIER, conceptMetier),
//                statut, codifListRepository.findByCodificationAndCode(CodificationConstant.NATURE_EVENT, CodificationConstant.NATURE_EVENT_ACTION));
//    }
//}
