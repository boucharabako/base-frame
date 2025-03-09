///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.core.codification.dto.CodifListDTO;
//import com.ngs.core.codification.entities.CodifList;
//import com.ngs.core.codification.entities.Codification;
//import com.ngs.core.codification.entities.Etiquette;
//import com.ngs.core.codification.entities.Libelle;
//import com.ngs.core.codification.entities.Parameter;
//import com.ngs.core.codification.repositories.CodifListRepository;
//import com.ngs.core.codification.repositories.CodificationRepository;
//import com.ngs.core.codification.repositories.EtiquetteRepository;
//import com.ngs.core.codification.repositories.LibelleRepository;
//import com.ngs.core.codification.repositories.ParameterRepository;
//import com.ngs.core.codification.utils.CodificationConstant;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.persistence.RollbackException;
//import org.hibernate.exception.ConstraintViolationException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Gbenou
// */
//@Service
//public class CodifNewService {
//
//    @Autowired
//    EtiquetteRepository etiquetteRepository;
//    @Autowired
//    ParameterRepository parameterRepository;
//    @Autowired
//    private TrackingParamObjetService trackingParamObjetService;
//    @Autowired
//    NProperties properties;
//    @Autowired
//    private CodificationRepository codificationRepository;
//    @Autowired
//    private TrackingParamObjetService trackingParamObjetRepository;
//    @Autowired
//    private CodifListRepository codifListRepository;
//    @Autowired
//    private LibelleRepository libelleRepository;
//    @Autowired
//    private CodifListService codifListService;
//
//    private static final Logger LOGGER = Logger.getLogger(CodificationService.class.getName());
//    private static final String PROPRIETE_RESULT_PUT_ERROR = "ERROR";
//    private static final String PROPRIETE_RESULT_PUT_CODIFICATION = "CODIFICATION";
//    private static final String PROPRIETE_RESULT_PUT_TYPE = "TYPE";
//    private static final String PROPRIETE_LOGGER_EXCPETION = "Exception";
//
//
//
//    /**
//     * Cette méthode permet de sauvegarder la codification
//     *
//     * @param codification
//     * @param codifLists
//     * @param modification
//     * @return
//     */
//    @Transactional
//    public Map<String, Object> save(Codification codification, List<CodifListDTO> codifLists, boolean modification) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            Codification cod = this.codificationRepository.save(codification);
//            for (CodifListDTO cl : codifLists) {
//                CodifList clo;
//                //Si c'est une modification
//                if (modification) {
//                    if ("".equals(cl.getId())) {
//                        clo = cl.from();
//                    } else {
//                        clo = cl.castCodifList(this.codifListRepository.findOne(cl.getId()));
//                    }
//                } else {
//                    clo = cl.from();
//                }
//                //Si la codiflist n'existe pas encore
//                int size = this.codifListRepository.findByIdeCode(clo.getIdeCode()) == null ? 0 : this.codifListRepository.findByIdeCode(clo.getIdeCode()).size();
//                if (0 != size) {
//                    result.put(PROPRIETE_RESULT_PUT_ERROR, true);
//                    result.put(PROPRIETE_RESULT_PUT_TYPE, 1);
//                    result.put(PROPRIETE_RESULT_PUT_CODIFICATION, null);
//                } else {
//                    clo.setCodification(cod);
//                    if (!"".equals(cl.getIdeCode())) {
//                        clo = codifListService.save(clo);
//                        if (clo != null) {
//                            Libelle lbl = new Libelle();
//                            lbl.setCodeLangue(this.codifListRepository.findByCodificationAndCode(CodificationConstant.LANGUE, properties.getLangue().getValeur()));
//                            lbl.setLibelle(cl.getLibelle());
//                            lbl.setCodifList(this.codifListRepository.findOneById(clo.getId()));
//                            //Si c'est une modification
//                            if (modification) {
//                                if ("".equals(cl.getId())) {
//                                    this.libelleRepository.save(lbl);
//                                } else {
//                                    //On va chercher le libellé correspondant à la liste en question
//                                    lbl = this.libelleRepository.findByCodificationAndListAndCodeLangue(cod.getCode(), clo.getIdeCode(), properties.getLangue().getValeur());
//                                    lbl.setLibelle(cl.getLibelle());
//                                    //On enregistre le libellé
//                                    this.libelleRepository.save(lbl);
//                                }
//                            } else {
//                                //On enregistre le libellé
//                                this.libelleRepository.save(lbl);
//                            }
//                        }
//                    }
//                    result.put(PROPRIETE_RESULT_PUT_TYPE, 0);
//                }
//            }
//            result.put(PROPRIETE_RESULT_PUT_CODIFICATION, cod);
//            result.put(PROPRIETE_RESULT_PUT_ERROR, false);
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, PROPRIETE_LOGGER_EXCPETION, e);
//            result.put(PROPRIETE_RESULT_PUT_TYPE, 2);
//            result.put(PROPRIETE_RESULT_PUT_ERROR, true);
//            result.put("CODIFICATION", null);
//        }
//        return result;
//    }
//
//    /**
//     * Enregistrement par une transaction des codifLists liés à une codification
//     *
//     * @param codification
//     * @param codifList
//     * @return
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public boolean saveCodifList(Codification codification, CodifList codifList) {
//        try {
//            Libelle lbl = new Libelle();
//            if (codifList.getId() == null || "".equals(codifList.getId())) {
//                CodifList cl = new CodifList();
//                cl.setCodification(codification);
//                cl.setIdeCode(codifList.getIdeCode());
//                cl.setLibelle(codifList.getLibelle());
//                //Enregistrement
//                if (this.codifListRepository.sizeByCodificationAndIdeCode(codification.getCode(), codifList.getIdeCode()) == 0) {
//                    cl = codifListRepository.save(cl);
//                } else {
//                    return false;
//                }
//                //Enregistrement du libelle
//                lbl.setCodeLangue(this.codifListRepository.findByCodificationAndCode(CodificationConstant.LANGUE, properties.getLangue().getValeur()));
//                lbl.setLibelle(cl.getLibelle());
//                lbl.setCodifList(this.codifListRepository.findOneById(cl.getId()));
//            } else {
//                CodifList cl = this.codifListRepository.findOneById(codifList.getId());
//                cl.setIdeCode(codifList.getIdeCode());
//                cl.setLibelle(codifList.getLibelle());
//                cl = codifListRepository.save(cl);
//                lbl = this.libelleRepository.findByCodificationAndListAndCodeLangue(codification.getCode(), cl.getIdeCode(), properties.getLangue().getValeur());
//                lbl.setLibelle(cl.getLibelle());
//            }
//            this.libelleRepository.save(lbl);
//            return true;
//        } catch (DataIntegrityViolationException | ConstraintViolationException | RollbackException e) {
//            LOGGER.log(Level.SEVERE, PROPRIETE_LOGGER_EXCPETION, e);
//            return false;
//        }
//    }
//
//    /**
//     * Cette méthode permet de sauvegarde le tracking
//     *
//     * @param codification
//     * @return
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public boolean saveTracking(Codification codification) {
//        try {
//            trackingParamObjetRepository.saveStatusTracking(codification.getCode(), CodificationConstant.CONCEPT_METIER_CODIFICATION, this.codifListRepository.findByCodificationAndCode(
//                    CodificationConstant.STATUT_PARAM,
//                    CodificationConstant.STATUT_PARAM_EN_SAISIE));
//            return true;
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, PROPRIETE_LOGGER_EXCPETION, e);
//            return false;
//        }
//    }
//
//    
//
//    /**
//     * Cette méthode permet de faire l'enregistrement d'une codifciation
//     * paramètre
//     *
//     * @param codification la codification
//     * @param parameterList la liste des paramètres
//     * @return
//     */
//    @Transactional
//    public Codification saveParameter(Codification codification, List<Parameter> parameterList) {
//        Codification codification1 = codification;
//        try {
//            codification1 = codificationRepository.save(codification1);
//            for (Parameter param : parameterList) {
//                param.setCodification(codification1);
//                parameterRepository.save(param);
//            }
//            trackingParamObjetService.saveStatusTracking(codification1.getCode(), CodificationConstant.CONCEPT_METIER_CODIFICATION, CodificationConstant.CREATION_OBJET, codification.getStatut());
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, PROPRIETE_LOGGER_EXCPETION, e);
//            return codification;
//        }
//        return codification1;
//    }
//
//    /**
//     * Cette méthode permet d'enregistrer une codification étiquette
//     *
//     * @param codification la codification en question
//     * @param etiquetteList la liste de ses étiquettes
//     * @return
//     */
//    @Transactional
//    public Codification saveEtiquette(Codification codification, List<Etiquette> etiquetteList) {
//        Codification codification1 = codification;
//        try {
//            codification1 = codificationRepository.save(codification1);
//            for (Etiquette etiqt : etiquetteList) {
//                etiqt.setCodification(codification1);
//                etiquetteRepository.save(etiqt);
//            }
//            trackingParamObjetService.saveStatusTracking(codification1.getCode(), CodificationConstant.CONCEPT_METIER_CODIFICATION, CodificationConstant.CREATION_OBJET, codification.getStatut());
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, PROPRIETE_LOGGER_EXCPETION, e);
//            return codification;
//        }
//        return codification1;
//    }
//
//}
