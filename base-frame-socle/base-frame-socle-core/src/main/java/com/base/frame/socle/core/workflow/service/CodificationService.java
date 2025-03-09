///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.core.codification.dto.CodifListDTO;
//import com.ngs.core.codification.dto.CodificationDTO;
//import com.ngs.core.codification.dto.UtilisateurDTO;
//import com.ngs.core.codification.entities.CodifList;
//import com.ngs.core.codification.entities.Codification;
//import com.ngs.core.codification.entities.Etiquette;
//import com.ngs.core.codification.entities.Libelle;
//import com.ngs.core.codification.entities.Parameter;
//import com.ngs.core.codification.entities.workflow.ActionPermise;
//import com.ngs.core.codification.repositories.ActionPermiseRepository;
//import com.ngs.core.codification.repositories.CodifListRepository;
//import com.ngs.core.codification.repositories.CodificationRepository;
//import com.ngs.core.codification.repositories.EtiquetteRepository;
//import com.ngs.core.codification.repositories.LibelleRepository;
//import com.ngs.core.codification.repositories.MessageApplicatifRepository;
//import com.ngs.core.codification.repositories.ParameterRepository;
//import com.ngs.core.codification.utils.CodificationConstant;
//import com.ngs.core.codification.utils.ErrorConstant;
//import com.ngs.core.codification.utils.RestConstants;
//import com.ngs.core.codification.utils.TypeError;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.persistence.RollbackException;
//import org.hibernate.exception.ConstraintViolationException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Hervé
// * @version 1.0.0
// * @since 27-02-2018
// * @description Service concernant le la gestion des codifications
// */
//@Service
//public class CodificationService {
//
//    @Autowired
//    EtiquetteRepository etiquetteRepository;
//    @Autowired
//    ParameterRepository parameterRepository;
//    @Autowired
//    private TrackingParamObjetService trackingParamObjetService;
//    @Autowired
//    private ErrorMessageService errorMessage;
//    @Autowired
//    private MessageApplicatifRepository messageRepository;
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
//    @Autowired
//    private MessageApplicatifRepository messageApplicatifRepository;
//    @Autowired
//    private ActionPermiseRepository actionPermiseRepository;
//
//    private static final Logger LOGGER = Logger.getLogger(CodificationService.class.getName());
//    private static final String PROPRIETE_RESULT_PUT_ERROR = "ERROR";
//    private static final String PROPRIETE_RESULT_PUT_CODIFICATION = "CODIFICATION";
//    private static final String PROPRIETE_RESULT_PUT_TYPE = "TYPE";
//    private static final String PROPRIETE_LOGGER_EXCPETION = "Exception";
//
//    /**
//     * Cette méthode permet de faire passer une codification à son étape
//     * suivante
//     *
//     * @param idCodification l'id de la codification
//     * @param trackingComment le commentaire sur le tracking
//     * @return
//     */
//    @Transactional
//    public Map<String, Object> moveToNextStep(String idCodification, String trackingComment) {
//        Map<String, Object> map = new HashMap<>();
//        try {
//            Codification codification = codificationRepository.findOne(idCodification);
//            List<CodifList> listCodif = codifListRepository.findNextStep(CodificationConstant.STATUT_PARAM, codification.getStatut().getIdeCode(), new PageRequest(0, 1));
//            codification.setStatut(listCodif.get(0));
//            codification = codificationRepository.save(codification);
//            trackingParamObjetService.saveTracking(
//                    idCodification,
//                    trackingComment,
//                    codifListRepository.findByCodificationAndCode(
//                            CodificationConstant.CONCEPT_METIER,
//                            CodificationConstant.CONCEPT_METIER_CODIFICATION),
//                    codification.getStatut(),
//                    codifListRepository.findByCodificationAndCode(CodificationConstant.NATURE_EVENT, CodificationConstant.NATURE_EVENT_ACTION));
//
//            List<CodifList> list = codifListRepository.findCurrentStep(CodificationConstant.ACTION_PARAM, codification.getStatut().getIdeCode(), new PageRequest(0, 1));
//            if (list != null && !list.isEmpty()) {
//                CodifList c = codifListRepository.findOneCodifListWithLangue(list.get(0).getId(), properties.getLangue().getValeur());
//                map.put("nextStep", c != null ? c.getLibelle() : null);
//                map.put(RestConstants.BACKGROUND_COLOR_KEY,
//                        (c != null && c.getIdeCode().equals(CodificationConstant.ACTION_PARAM_ACTIVER)) ? CodificationConstant.COLOR_ACTIVER
//                        : ((c != null && c.getIdeCode().equals(CodificationConstant.ACTION_PARAM_ABANDONNER)) ? CodificationConstant.COLOR_ABANDONNER
//                        : CodificationConstant.COLOR_WHITE));
//                map.put(RestConstants.TEXT_COLOR_KEY,
//                        (c != null && c.getIdeCode().equals(CodificationConstant.ACTION_PARAM_ACTIVER)) ? CodificationConstant.COLOR_TEXT_ACTIVER
//                        : ((c != null && c.getIdeCode().equals(CodificationConstant.ACTION_PARAM_ABANDONNER)) ? CodificationConstant.COLOR_TEXT_ABANDONNER
//                        : CodificationConstant.COLOR_WHITE));
//            } else {
//                map.put(RestConstants.DATA_KEY, null);
//            }
//
//            CodifList c2 = codifListRepository.findOneCodifListWithLangue(codification.getStatut().getId(), properties.getLangue().getValeur());
//            map.put("currentStep", c2 != null ? c2.getLibelle() : null);
//            map.put("ideCode", c2 != null ? c2.getIdeCode() : null);
//            map.put(RestConstants.ERROR_KEY, false);
//            map.put(RestConstants.MSG_KEY, this.errorMessage.getApiError(
//                    ErrorConstant.STATUS_SUCCESS,
//                    TypeError.INFO,
//                    this.messageRepository.findLibelleByCodeAndCodeLangue(ErrorConstant.OPERATION_SUCCESS_LABEL, properties.getLangue().getId()),
//                    new HashMap<>()));
//
//        } catch (Exception e) {
//            map.put(RestConstants.ERROR_KEY, false);
//            map.put(RestConstants.MSG_KEY, this.errorMessage.getApiError(
//                    ErrorConstant.STATUS_BAD_REQUEST,
//                    TypeError.DANGER,
//                    this.messageRepository.findLibelleByCodeAndCodeLangue(ErrorConstant.OPERATION_ERROR_LABEL, properties.getLangue().getId()),
//                    new HashMap<>()));
//        }
//        return map;
//    }
//
//    /**
//     * Cette méthode permet de supprimer une codification paramètre
//     *
//     */
////    @Transactional
//    /**
//     * Mis en commentairet et Modifié pour corrigé les erreur de suppression des
//     * paramètres Assima
//     */
//
//    @Transactional
//    public boolean removeParameter(String code, CodificationDTO codificationDTO) {
//        try {
//            Codification cod = this.codificationRepository.findOne(code);
//            if (cod != null) {
//                List<Parameter> parameters = this.parameterRepository.findByCodification(cod.getCode());
//                this.parameterRepository.delete(parameters);
//                codificationRepository.delete(cod.getCode());
//                codificationDTO.getCodifications().remove(cod);
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, e.toString());
//            return false;
//        }
//    }
//
//    /**
//     * Cette méthode permet de faire la suppression d'une codification
//     *
//     * @param code le code de codification
//     * @param codificationDTO le dto codification
//     * @return
//     */
//    @Transactional
//    public boolean deleteCodification(String code, CodificationDTO codificationDTO) {
//        try {
//            Codification cod = this.codificationRepository.findOne(code);
//            List<CodifList> codifs = this.codifListRepository.findByCodification(cod.getCode());
//            codifs.stream().forEach(c -> {
//                this.libelleRepository.delete(this.libelleRepository.findByCodifListId(c.getId()));
//            });
//            this.codifListRepository.delete(codifs);
//            this.codificationRepository.delete(cod);
//            codificationDTO.getCodifications().remove(cod);
//            return true;
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, e.toString());
//            return false;
//        }
//    }
//
//    @Transactional
//    public boolean deleteGroupeEtiquette(String code, CodificationDTO codificationDTO) {
//        try {
//            Codification cod = this.codificationRepository.findOne(code);
//            List<Etiquette> etiquettes = etiquetteRepository.findByCodification(cod.getCode());
//
//            this.etiquetteRepository.delete(etiquettes);
//            this.codificationRepository.delete(cod);
//            codificationDTO.getCodifications().remove(cod);
//            return true;
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, e.toString());
//            return false;
//        }
//    }
//
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
//                        } else {
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
//     * Enregistrement par une transaction des codifList liés à une codification
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
//    /**
//     * Cette méthode permet de faire passer une codification à son étape
//     * suivante
//     *
//     * @param idCodification l'id de la codification
//     * @return
//     */
//    @Transactional
//    public Codification goToNextStep(String idCodification) {
//        Codification u = codificationRepository.findOne(idCodification);
//        if (u.getStatut() != null) {
//            List<CodifList> list = codifListRepository.findNextStep(CodificationConstant.STATUT_PARAM, u.getStatut().getIdeCode(), new PageRequest(0, 1));
//            if (list != null && !list.isEmpty()) {
//                CodifList c = list.get(0);
//                u.setStatut(c);
//                u = codificationRepository.save(u);
//                trackingParamObjetService.saveStatusTracking(idCodification, CodificationConstant.CONCEPT_METIER_CODIFICATION, u.getStatut());
//            }
//        }
//        return u;
//    }
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
//    /**
//     *
//     * @param code
//     * @return
//     */
//    public String findMessage(String code) {
//        return this.messageApplicatifRepository.findLibelleByCodeAndCodeLangue(code, properties.getLangue().getId());
//    }
//
//    public CodifList getCodifListTRUEorFALSE(boolean value) {
//        CodifList oui = this.codifListRepository.findByCodificationAndCode(CodificationConstant.OUI_NON, CodificationConstant.OUI_NON_OUI);
//        CodifList non = this.codifListRepository.findByCodificationAndCode(CodificationConstant.OUI_NON, CodificationConstant.OUI_NON_NON);
//        if (value) {
//            return oui;
//        }
//        return non;
//    }
//
//    public Boolean getTRUEorFALSEByCodifListOUINON(CodifList c) {
//        if (c == null) {
//            return false;
//        }
//        CodifList cd = this.codifListRepository.findByCodificationAndCode(CodificationConstant.OUI_NON, CodificationConstant.OUI_NON_OUI);
//        return c.getIdeCode().equals(cd.getIdeCode());
//    }
//
//    public List<String> getUserName(List<UtilisateurDTO> users) {
//        List<String> usernames = new ArrayList<>();
//        users.stream().forEach(t -> {
//            usernames.add(t.getUsername());
//        });
//        return usernames;
//    }
//
//    public String getCodeEtatActionSuivante(String idActionPermise) {
//        ActionPermise ap = this.actionPermiseRepository.findOne(idActionPermise);
//        return ap.getEtatSuivant().getCodeEtat();
//    }
//
//    public String findTypeDonne(String id) {
//        Libelle l = libelleRepository.findOne(id);
//        return l == null ? "" : l.getCodifList().getIdeCode();
//    }
//
//    
//    public <T> Page<T> genericPage(List<T> listElements, Pageable pageRequest) {
//        List<T> listResultFinal = new ArrayList<>();
//        int index = ((pageRequest.getPageNumber() + 1) * (pageRequest.getPageSize())) - (pageRequest.getPageSize());
//        int indexCible = this.genericGetRealIndex(listElements, (index + pageRequest.getPageSize()));
//        if (!listElements.isEmpty()) {
//            listResultFinal.addAll(listElements.subList(index<=listElements.size() ? index :0, indexCible<=listElements.size() ? indexCible :listElements.size()));
//        }
//        return new PageImpl<>(listResultFinal, pageRequest, listElements.size());
//    }
//
//    public <T> int genericGetRealIndex(List<T> list, int index) {
//        while (index > list.size()) {
//            index--;
//        }
//        return index;
//    }
//}
