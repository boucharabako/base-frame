///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//
//import com.base.frame.socle.core.codification.annotation.dao.IGenericWorkflowDAO;
//import com.base.frame.socle.core.codification.utils.CodificationConstant;
//import com.base.frame.socle.core.dto.IdLabelObject;
//import com.base.frame.socle.core.entity.ParamList;
//import com.base.frame.socle.core.repository.ParamListRepository;
//import com.base.frame.socle.core.workflow.dto.ActionDTO;
//import com.base.frame.socle.core.workflow.dto.CycleDTO;
//import com.base.frame.socle.core.workflow.dto.UtilisateurDTO;
//import com.base.frame.socle.core.workflow.dto.WorkFlowCycleEtatSimpleObject;
//import com.base.frame.socle.core.workflow.entity.ActionEffectuee;
//import com.base.frame.socle.core.workflow.entity.ActionPermise;
//import com.base.frame.socle.core.workflow.entity.Etat;
//import com.base.frame.socle.core.workflow.entity.EtatUtilisateurAutorise;
//import com.base.frame.socle.core.workflow.entity.EvenementGenere;
//import com.base.frame.socle.core.workflow.entity.EvenementPossible;
//import com.base.frame.socle.core.workflow.entity.Tracking;
//import com.base.frame.socle.core.workflow.entity.WorkFlowCycle;
//import com.base.frame.socle.core.workflow.entity.WorkflowCycleObjet;
//import com.base.frame.socle.core.workflow.repository.ActionEffectueeRepository;
//import com.base.frame.socle.core.workflow.repository.ActionPermiseExecutorRepository;
//import com.base.frame.socle.core.workflow.repository.ActionPermiseRepository;
//import com.base.frame.socle.core.workflow.repository.EtatRepository;
//import com.base.frame.socle.core.workflow.repository.EtatUtilisateurAutoriseRepository;
//import com.base.frame.socle.core.workflow.repository.EvenementGenereRepository;
//import com.base.frame.socle.core.workflow.repository.EvenementProgrammeRepository;
//import com.base.frame.socle.core.workflow.repository.TrackingRepository;
//import com.base.frame.socle.core.workflow.repository.WorkFlowCycleRepository;
//import com.base.frame.socle.core.workflow.repository.WorkflowCycleObjetRepository;
//import com.base.frame.socle.utils.exceptions.InternalServerException;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//import javax.servlet.http.HttpServletRequest;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Les services des gestion des des workflow
// *
// * @author W.KOUWONOU
// * @since 30/08/2018
// * @version 1.0.0
// */
//@Transactional
//@Service
//public class WorkflowCycleService implements IWorkflowCycleService {
//
//    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(WorkflowCycleService.class);
//
//    @Autowired
//    private IGenericWorkflowDAO genericWorkflowDAO;
//    @Autowired
//    private TrackingRepository trackingRepository;
//    @Autowired
//    private ActionPermiseRepository actionPermiseRepository;
//    @Autowired
//    private ActionEffectueeRepository actionEffectueeRepository;
//    @Autowired
//    private ParamListRepository paramListRepository;
//    @Autowired
//    private EvenementProgrammeRepository evenementProgrammeRepository;
//    @Autowired
//    private EvenementGenereRepository evenementGenereRepository;
//    @Autowired
//    private HttpServletRequest request;
//    
//
//    @Autowired(required = false)
//    private IAutorisationService autorisationService;
//    @Autowired(required = false)
//    private ICustomUtilService customUtilService;
//    @Autowired
//    private EtatUtilisateurAutoriseRepository etatUtilisateurAutoriseRepository;
//
//    @Autowired
//    private WorkFlowCycleRepository workFlowCycleRepository;
//
//    @Autowired
//    private EtatRepository etatRepository;
//
////    @Autowired
////    private CodificationService codificationService;
//
//    @Autowired(required = false)
//    private Map<String, IExecutor> executors;
//    @Autowired
//    private WorkflowCycleObjetRepository workflowCycleObjetRepository;
//
//    //@Autowired
////    private RefConfigFonctionRepository configFonctionRepository;
////    @Autowired
////    private ValeurExtensionRepository valeurExtensionRepository;
////    @Autowired
////    private ExtensionRepository extensionRepository;
//    
//    @Autowired
//    private ActionPermiseExecutorRepository actionPermiseExecutorRepository;
//
//    /**
//     * Execute une action
//     *
//     * @param cpt concept metier
//     * @param idObjet Identifiand el'objet
//     * @param idAcion identifiant de l'action
//     * @param commentaire commentaire
//     * @param users Les utilisateur à notifier
//     * @return
//     */
//    @Override
//    public Integer executerAction(String codeFonction, String cpt, String idObjet, String idAcion, String commentaire, String[] users, HashMap<String, Object> params) {
//        System.out.println("  executerAction 0001");
//        Etat etatCourant = genericWorkflowDAO.findEtatById(cpt, idObjet);
//        List<String> idProfiles = customUtilService.getCurrentUserProfil();
//        System.out.println("  executerAction 00011 " + idProfiles.size());
//        String createdUser = genericWorkflowDAO.findAuthorBy(cpt, idObjet);
//        System.out.println("  executerAction 00012");
//        String currentUser = customUtilService.getCurrentUserLogin().get();
//        System.out.println("  executerAction 00013");
////        if (codeFonction == null || codeFonction.isEmpty() || configFonctionRepository.getOne(codeFonction) == null) {
////            throw new InternalServerException(this.codificationService.findMessage(CodificationConstant.WKFL_FONCTION_INVALIDE), null);
////
////        }
//        if (etatCourant == null) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.ETAT_NULL)", null);
//        }
//        ActionPermise actionPermise = actionPermiseRepository.getOne(idAcion);
//        if (actionPermise == null) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.ACTION_NULL)", null);
//        }
//        if (!etatCourant.getId().equals(actionPermise.getEtat().getId())) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.ETAT_ACTION_DIFFERENT)", null);
//        }
//        List<IdLabelObject> listProfileAutorise = this.autorisationService.getProfilAutoriseForAction(
//                this.customUtilService.getConcepteMetierPFU(),
//                this.autorisationService.getTypeAutorisationEtendu(), CodificationConstant.ACTIONS_WORKFLOW, idAcion);
//        boolean flag = false;
//        for (IdLabelObject i : listProfileAutorise) {
//
//            if (idProfiles.contains(i.getId())) {
//                flag = true;
//                break;
//
//            }
//        }
//        boolean flg = (currentUser.equals(createdUser) && this.currentSequence(etatCourant) == 0);
//        if ((params == null || !params.containsKey(CodificationConstant.WKF_NOTSECURE)) && !flag && !flg) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.USER_NOT_AUTHORISED)", null);
//        }
//        String codeHabilitation = this.customUtilService.findNiveauHabilitationOfUserByFunction(codeFonction);
//        if ((params == null || !params.containsKey(CodificationConstant.WKF_NOTSECURE)) && (codeHabilitation == null || codeHabilitation.isEmpty())) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.USER_NOT_AUTHORISED)", null);
//
//        }
//
//        if ((params == null || !params.containsKey(CodificationConstant.WKF_NOTSECURE)) && actionPermise.getHabilitation() != null
//                && Integer.valueOf(actionPermise.getHabilitation().getCode()) > Integer.valueOf(codeHabilitation) && !flg) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.USER_NOT_AUTHORISED)", null);
//
//        }
//        {
//            //this.executeOneCallBack(actionPermise, params);
//
//            this.executeAllCalback(actionPermise, params);
//        }
//
//        flag = false;
//        Tracking t = new Tracking();
//
//        t.setComment(commentaire);
//        if (params != null && params.get(CodificationConstant.ANONYME) != null) {
//            t.setCommentaireAnonyme(params.get(CodificationConstant.ANONYME).toString());
//        }
//
//        List<EvenementPossible> evenementPossibles = evenementProgrammeRepository.findByAction(idAcion);
//        try {
//
//            /**
//             * Enregistrement d'une ligne d'action effectué
//             */
//            ActionEffectuee ev = new ActionEffectuee();
//            ev.setAction(actionPermise);
//
//            String remoteAddr = "";
//             try {
//                if (request != null) {
//                remoteAddr = request.getHeader("X-FORWARDED-FOR");
//                if (remoteAddr == null || "".equals(remoteAddr)) {
//                    remoteAddr = request.getRemoteAddr();
//                }
//            }
//            } catch (Exception e) {
//            }
//            
//            ev.setIp(remoteAddr);
//            actionEffectueeRepository.save(ev);
//
//            /**
//             * Execution des evenement associés à l'objet
//             */
//            for (EvenementPossible e : evenementPossibles) {
//                if (e.getEvent().getCode().equals(CodificationConstant.WF_REFERENTIEL_EVENEMENTS_CHANGEMENT_BLOCAGE_ETAT)) {
//                    flag = true;
//                }
//                EvenementGenere evg = new EvenementGenere();
//                evg.setActionPermise(actionPermise);
//                evg.setEvenement(e);
//                evg.setConceptMeter(paramListRepository.findParamListByCode(cpt).get());
//                evg.setIdObjet(idObjet);
//                //evg.setStatut(paramListRepository.findByCodificationAndCode(CodificationConstant.EVENT_STATUS, CodificationConstant.EVENT_STATUS_NOUVEL));
//
//                evenementGenereRepository.save(evg);
//
//            }
//            if (!flag) {
//                if (actionPermise.getEtatSuivant() != null) {
//                    genericWorkflowDAO.saveEtat(cpt, idObjet, actionPermise.getEtatSuivant().getId());
//
//                    t.setEtatFinal(actionPermise.getEtatSuivant());
//
//                    List<UtilisateurDTO> udtos = customUtilService.getUserByUsernames(users);
//
//                    List<ActionPermise> actionEtatSuivants = actionPermiseRepository.findByEtatWorkFlow(actionPermise.getEtatSuivant().getId());
//                    HashSet<IdLabelObject> labelObjects = new HashSet<>();
//                    HashSet<String> profillIds = new HashSet<>();
//                    /**
//                     * Recherche des profiles liés aux actions de l'etats
//                     * suivant
//                     */
//                    actionEtatSuivants.stream().forEach(item -> {
//                        labelObjects.addAll(this.autorisationService.getProfilAutoriseForAction(
//                                this.customUtilService.getConcepteMetierPFU(),
//                                this.autorisationService.getTypeAutorisationEtendu(), CodificationConstant.ACTIONS_WORKFLOW, item.getId()));
//                    });
//
//                    labelObjects.forEach(lb -> {
//                        profillIds.add(lb.getId());
//                    });
//                    /**
//                     * Enregistrement des utilisateur autorié pour recevoir des
//                     * notifications pour l'etat suivant
//                     */
//                    udtos.forEach(u -> {
//
//                        for (String e : u.getProfil()) {
//                            if (profillIds.contains(e)) {
//                                EtatUtilisateurAutorise etu = etatUtilisateurAutoriseRepository.findOneByUsernameEtatConcetpMetierIbjet(u.getUsername(), actionPermise.getEtatSuivant().getId(), cpt, idObjet);
//
//                                if (etu == null) {
//                                    etu = new EtatUtilisateurAutorise();
//                                    etu.setConceptMetier(cpt);
//                                    etu.setUsername(u.getUsername());
//                                    etu.setEtat(actionPermise.getEtatSuivant());
//                                    etu.setIdObjet(idObjet);
//
//                                    etatUtilisateurAutoriseRepository.save(etu);
//
//                                }
//                                break;
//                            }
//                        }
//
//                    });
//
//                }
//                if (t.getEtatFinal() == null) {
//                    t.setEtatFinal(etatCourant);
//                }
//                t.setConceptMetier(paramListRepository.findParamListByCode(cpt).get());
//                t.setIdObject(idObjet);
//                t.setEtatInitial(etatCourant);
//                t.setAction(actionPermise);
//                trackingRepository.save(t);
//            }
//
//            return 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.UNKNOWN_EXCEPTION)", null);
//        }
//
//    }
//
////    /**
////     * Ajouter par Assima le 19/03/2019 pour l'execution du workflow avec les
////     * commentaire .
////     *
////     * @param codeFonction
////     * @param cpt
////     * @param idObjet
////     * @param idAcion
////     * @param commentaire
////     * @param users
////     * @param params
////     * @param extensionList
////     * @return
////     */
////    @Override
////    @Transactional
////    public Integer executerActionWithGestionCommentaire(String codeFonction, String cpt, String idObjet, String idAcion, String commentaire, String[] users, HashMap<String, Object> params, List<ExtensionSimpleObject> extensionList) {
////        if (extensionList != null && !extensionList.isEmpty() && this.checkIfActionModeIsEtendu(idAcion)) {
////            this.executerActionSaveComment(idObjet, extensionList);
////        }
////        return this.executerAction(codeFonction, cpt, idObjet, idAcion, commentaire, users, params);
////    }
//
////    @Override
////    public void executerActionSaveComment(String idObjet, List<ExtensionSimpleObject> extensionList) {
////        //Enregistrement des extensions
////        if (extensionList != null && !extensionList.isEmpty()) {
////            extensionList.stream().forEach(t -> {
////                if (t.getRequired() && (t.getValeur() == null || t.getValeur() == "")
////                        && (t.getListDeControle() == null
////                        || this.getSelectedValue(t.getListDeControle()).isEmpty())
////                        && (t.getValeurLDC() == null || t.getValeurLDC().equals(new IdLabelObject()))) {
////                    throw new InternalServerException(CodificationConstant.F_REQUIERD + "#" + t.getLibelle(), null);
////                }
////
////                List<ValeurExtension> listValeurExtensions = valeurExtensionRepository.findByIdExtensionIdObject(t.getId(), idObjet);
////                if (listValeurExtensions.isEmpty()) {
////
////                    if (t.getTypeDonneListDeControle() && t.getValeurLDC() != null && t.getValeurLDC().getId() != null) {
////
////                        t.setValeur(t.getValeurLDC().getId());
////                    }
////                    if (t.getTypeDonneListDeControle() && !t.getTypeDonneListDeControleMultiple() && t.getValeurLDC() == null && t.getValeur() != null) {
////                        if (t.getCodeListDeControleIsOUI_NON()) {
////
////                            if (t.getValeur().equalsIgnoreCase("true")) {
////                                t.setValeur(CodificationConstant.OUI_NON_OUI_IDE_CODE);
////                                valeurExtensionRepository.save(new ValeurExtension(extensionRepository.getOne(t.getId()), idObjet, CodificationConstant.OUI_NON_OUI_IDE_CODE));
////                            } else {
////                                t.setValeur(CodificationConstant.OUI_NON_NON_IDE_CODE);
////                                valeurExtensionRepository.save(new ValeurExtension(extensionRepository.getOne(t.getId()), idObjet, CodificationConstant.OUI_NON_NON_IDE_CODE));
////                            }
////                        }
////                    }
////                    if (!t.getTypeDonneListDeControleMultiple() && t.getValeur() != null && !t.getValeur().isEmpty() && !t.getCodeListDeControleIsOUI_NON()) {
////                        valeurExtensionRepository.save(new ValeurExtension(extensionRepository.getOne(t.getId()), idObjet, t.getValeur()));
////                    }
////                    //Gestion des listes de controle multiples
////                    if (t.getTypeDonneListDeControle() && t.getTypeDonneListDeControleMultiple()) {
////                        Set<String> valeurSave = this.getSelectedValue(t.getListDeControle());
////                        if (!valeurSave.isEmpty()) {
////                            ValeurExtension valeurExtension = new ValeurExtension();
////                            valeurExtension.setExtension(extensionRepository.getOne(t.getId()));
////                            valeurExtension.setIdObjet(idObjet);
////                            valeurExtension.setValeurMultiple(valeurSave);
////                            valeurExtensionRepository.save(valeurExtension);
////                        }
////
////                    }
////                } else {
////                    listValeurExtensions.forEach(lv -> {
////                        if ((t.getValeur() != null && !t.getValeur().isEmpty()) || t.getValeurLDC() != null || t.getValeurCLA() != null || (t.getTypeDonneListDeControleMultiple() && !this.getSelectedValue(t.getListDeControle()).isEmpty())) {
////                            if (t.getTypeDonneListDeControle() && t.getValeurLDC() != null && t.getValeurLDC().getId() != null) {
////                                t.setValeur(t.getValeurLDC().getId());
////                            }
////                            if (t.getTypeDonneListDeControle() && !t.getTypeDonneListDeControleMultiple() && t.getValeurLDC() == null && t.getValeur() != null) {
////                                if (t.getCodeListDeControleIsOUI_NON()) {
////                                    if (t.getValeur().equalsIgnoreCase("true")) {
////                                        t.setValeur(CodificationConstant.OUI_NON_OUI_IDE_CODE);
////                                        lv.setValeur(CodificationConstant.OUI_NON_OUI_IDE_CODE);
////                                    } else {
////                                        t.setValeur(CodificationConstant.OUI_NON_NON_IDE_CODE);
////                                        lv.setValeur(CodificationConstant.OUI_NON_NON_IDE_CODE);
////                                    }
////                                }
////                            }
////                            if (!t.getTypeDonneListDeControleMultiple() && t.getValeur() != null && !t.getValeur().isEmpty() && !t.getCodeListDeControleIsOUI_NON()) {
////                                lv.setValeur(t.getValeur());
////                            }
////                            if (t.getTypeDonneListDeControle() && t.getTypeDonneListDeControleMultiple()) {// Modification Gestion des liste de controles multiples
////                                Set<String> valeurSave = this.getSelectedValue(t.getListDeControle());
////                                if (!valeurSave.isEmpty()) {
////                                    lv.setExtension(extensionRepository.getOne(t.getId()));
////                                    lv.setIdObjet(idObjet);
////                                    lv.setValeurMultiple(valeurSave);
////                                }
////                            }
////
////                        } else { // Suppression des valeurs extensions non renseignée mais déjà existente en base de données
////                            if (!t.getTypeDonneListDeControle()) {
////                                if (valeurExtensionRepository.exists(lv.getId())) {
////                                    valeurExtensionRepository.delete(lv);
////                                }
////                            }
////                            if (!t.getTypeDonneListDeControleMultiple()) {
////                                if (valeurExtensionRepository.exists(lv.getId())) {
////                                    valeurExtensionRepository.delete(lv);
////                                }
////
////                            }
////                            if (t.getTypeDonneListDeControle() && t.getTypeDonneListDeControleMultiple()) {
////                                lv.getValeurMultiple().clear();
////                                valeurExtensionRepository.delete(lv);
////
////                            }
////                        }
////                    });
////                }
////
////            });
////        }
////    }
//
//    public Set<String> getSelectedValue(List<IdLabelObject> listMapped) {
//        HashSet<String> model = new HashSet<>();
//        listMapped.stream().forEach(element -> {
//            if (element.isCheckValue()) {
//                model.add(element.getId());
//            }
//        });
//        return model;
//    }
//
////    @Transactional
////    public void removeExtension(String idObject) {
////        List<ValeurExtension> listExtension = this.valeurExtensionRepository.findByIdObject(idObject);
////        this.valeurExtensionRepository.deleteInBatch(listExtension);
////    }
//
//    public boolean checkIfActionModeIsEtendu(String idAction) {
//        boolean value = false;
//        if (idAction != null) {
//            ActionPermise act = this.actionPermiseRepository.getOne(idAction);
//            if (act != null && act.getId() != null && act.getIndicateurCommentaire() != null) {
//                if (act.getIndicateurCommentaire().getId().equalsIgnoreCase(CodificationConstant.ID_TRANSITION_ETENDU)) {
//                    value = true;
//                }
//            }
//        }
//        return value;
//    }
//
//    /**
//     * Execute une action
//     *
//     * @param cpt concept metier
//     * @param idObjet Identifiand el'objet
//     * @param idAcion identifiant de l'action
//     * @param commentaire commentaire
//     * @param users Les utilisateur à notifier
//     * @return
//     */
//    @Override
//    public Integer executerActionForChildren(String cpt, String idObjet,
//            String idAcion, String commentaire,
//            String[] users
//    ) {
//        Etat etatCourant = genericWorkflowDAO.findEtatById(cpt, idObjet);
//        List<String> idProfiles = customUtilService.getCurrentUserProfil();
//
//        if (etatCourant == null) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.ETAT_NULL)", null);
//        }
//        ActionPermise actionPermise = actionPermiseRepository.getOne(idAcion);
//        if (actionPermise == null) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.ACTION_NULL)", null);
//        }
//
//        List<IdLabelObject> listProfileAutorise = this.autorisationService.getProfilAutoriseForAction(
//                this.customUtilService.getConcepteMetierPFU(),
//                this.autorisationService.getTypeAutorisationEtendu(), CodificationConstant.ACTIONS_WORKFLOW, idAcion);
//
//        boolean flag;
//        for (IdLabelObject i : listProfileAutorise) {
//            if (idProfiles.contains(i.getId())) {
//
//                break;
//            }
//        }
//        flag = false;
//        Tracking t = new Tracking();
//
//        t.setComment(commentaire);
//
//        List<EvenementPossible> evenementPossibles = evenementProgrammeRepository.findByAction(idAcion);
//        try {
//
//            /**
//             * Enregistrement d'une ligne d'action effectué
//             */
//            ActionEffectuee ev = new ActionEffectuee();
//            ev.setAction(actionPermise);
//
//            String remoteAddr = "";
//
//            if (request != null) {
//                remoteAddr = request.getHeader("X-FORWARDED-FOR");
//                if (remoteAddr == null || "".equals(remoteAddr)) {
//                    remoteAddr = request.getRemoteAddr();
//                }
//            }
//            ev.setIp(remoteAddr);
//
//            actionEffectueeRepository.save(ev);
//
//            /**
//             * Execution des evenement associés à l'objet
//             */
//            for (EvenementPossible e : evenementPossibles) {
//                if (e.getEvent().getCode().equals(CodificationConstant.WF_REFERENTIEL_EVENEMENTS_CHANGEMENT_BLOCAGE_ETAT)) {
//                    flag = true;
//                }
//                EvenementGenere evg = new EvenementGenere();
//                evg.setActionPermise(actionPermise);
//                evg.setEvenement(e);
//                //evg.setStatut(paramListRepository.findByCodificationAndCode(CodificationConstant.EVENT_STATUS, CodificationConstant.EVENT_STATUS_NOUVEL));
//                evenementGenereRepository.save(evg);
//
//            }
//
//            if (!flag && (actionPermise.getEtatSuivant() != null)) {
//                //Fusion des 2 structures conditionnelles
//                genericWorkflowDAO.saveEtat(cpt, idObjet, actionPermise.getEtatSuivant().getId());
//
//                t.setEtatFinal(actionPermise.getEtatSuivant());
//
//                List<UtilisateurDTO> udtos = customUtilService.getUserByUsernames(users);
//
//                List<ActionPermise> actionEtatSuivants = actionPermiseRepository.findByEtatWorkFlow(actionPermise.getEtatSuivant().getId());
//                HashSet<IdLabelObject> labelObjects = new HashSet<>();
//                HashSet<String> profillIds = new HashSet<>();
//                /**
//                 * Recherche des profiles liés aux actions de l'etats suivant
//                 */
//                actionEtatSuivants.stream().forEach(item -> {
//                    labelObjects.addAll(this.autorisationService.getProfilAutoriseForAction(
//                            this.customUtilService.getConcepteMetierPFU(),
//                            this.autorisationService.getTypeAutorisationEtendu(), CodificationConstant.ACTIONS_WORKFLOW, idAcion));
//                });
//
//                labelObjects.forEach(lb -> {
//                    profillIds.add(lb.getId());
//                });
//
//                /**
//                 * Enregistrement des utilisateur autorié pour recevoir des
//                 * notifications pour l'etat suivant
//                 */
//                udtos.forEach(u -> {
//                    if (profillIds.containsAll(u.getProfil())) {
//                        EtatUtilisateurAutorise etu = etatUtilisateurAutoriseRepository.findOneByUsernameEtatConcetpMetierIbjet(u.getUsername(), actionPermise.getEtatSuivant().getId(), cpt, idObjet);
//
//                        if (etu == null) {
//                            etu = new EtatUtilisateurAutorise();
//                            etu.setConceptMetier(cpt);
//                            etu.setUsername(u.getUsername());
//                            etu.setEtat(actionPermise.getEtatSuivant());
//                            etu.setIdObjet(idObjet);
//
//                            etatUtilisateurAutoriseRepository.save(etu);
//                        }
//
//                    }
//                });
//
//                t.setConceptMetier(paramListRepository.findParamListByCode(cpt).get());
//                t.setIdObject(idObjet);
//                t.setEtatInitial(etatCourant);
//
//                trackingRepository.save(t);
//
//            }
//
//            return 0;
//        } catch (Exception e) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.UNKNOWN_EXCEPTION)", null);
//        }
//
//    }
//
//    @Override
//    public List<ActionDTO> findActionByObectEtat(String cpt, String idObjet
//    ) {
//        Etat etatCourant = genericWorkflowDAO.findEtatById(cpt, idObjet);
//
//        if (etatCourant == null) {
//            return new ArrayList<>();
//        }
//        return actionPermiseRepository.findByEtat(etatCourant.getId());
//    }
//
//    @Override
//    public List<ActionDTO> findActionByObectAndEtatAndCurrentUser(String cpt, String idObjet,
//            String codeFonction
//    ) {
//        List<String> idProfiles = customUtilService.getCurrentUserProfil();
//
//        Etat etatCourant = genericWorkflowDAO.findEtatById(cpt, idObjet);
//        String createdUser = genericWorkflowDAO.findAuthorBy(cpt, idObjet);
//        String currentUser = customUtilService.getCurrentUserLogin().get();
//
//        if (etatCourant == null) {
//            return new ArrayList<>();
//        }
//
//        List<ActionDTO> l;
//        if (codeFonction != null) {
//            String codeHabilitation = this.customUtilService.findNiveauHabilitationOfUserByFunction(codeFonction);
//
//            l = actionPermiseRepository.findByEtatByHabilitation(etatCourant.getId(),codeHabilitation);
//        } else {
//            l = actionPermiseRepository.findByEtat(etatCourant.getId());
//        }
//        if (currentUser.equals(createdUser)) {
//            return l;
//        }
//
//        List<ActionDTO> l2 = new ArrayList<>();
//        for (ActionDTO a : l) {
//            List<IdLabelObject> listProfileAutorise = this.autorisationService.getProfilAutoriseForAction(
//                    this.customUtilService.getConcepteMetierPFU(),
//                    this.autorisationService.getTypeAutorisationEtendu(), CodificationConstant.ACTIONS_WORKFLOW, a.getId());
//
//            for (IdLabelObject i : listProfileAutorise) {
//                if (idProfiles.contains(i.getId())) {
//                    l2.add(a);
//                    break;
//                }
//            }
//        }
//        return l2;
//    }
//
//    @Override
//    public List<ActionDTO> findActionByObectAndEtatAndCurrentUserAndFunction(String cpt, String idObjet,
//            String codeFonction
//    ) {
//        List<String> idProfiles = customUtilService.getCurrentUserProfil();
//
//        Etat etatCourant = genericWorkflowDAO.findEtatById(cpt, idObjet);
//
//        if (etatCourant == null) {
//            return new ArrayList<>();
//        }
//
//        List<ActionDTO> l;
//        if (codeFonction != null) {
//            String codeHabilitation = this.customUtilService.findNiveauHabilitationOfUserByFunction(codeFonction);
//
//            l = actionPermiseRepository.findByEtatByHabilitation(etatCourant.getId(),
//                    codeHabilitation);
//        } else {
//            l = actionPermiseRepository.findByEtat(etatCourant.getId());
//        }
//
//        List<ActionDTO> l2 = new ArrayList<>();
//        for (ActionDTO a : l) {
//            List<IdLabelObject> listProfileAutorise = this.autorisationService.getProfilAutoriseForAction(
//                    this.customUtilService.getConcepteMetierPFU(),
//                    this.autorisationService.getTypeAutorisationEtendu(), CodificationConstant.ACTIONS_WORKFLOW, a.getId());
//
//            for (IdLabelObject i : listProfileAutorise) {
//                if (idProfiles.contains(i.getId())) {
//                    l2.add(a);
//                    break;
//                }
//            }
//        }
//        return l2.stream().map(x -> {
//            Optional<String> cm = actionPermiseRepository.findCommentaireBydeAction(x.getId());
//            if (cm.isPresent()) {
//                x.setCommentaire(cm.get());
//            }
//            if (x.getIdAction() != null) {
//                ParamList c = paramListRepository.getOne(x.getIdAction());
//                if (c != null) {
//                    x.setCode(c.getCode());
//                }
//            }
//
//            return x;
//        }).collect(Collectors.toList());
//
//    }
//
//    @Override
//    public List<UtilisateurDTO> findUserByAction(String idAcion
//    ) {
//        ActionPermise a = actionPermiseRepository.getOne(idAcion);
//
//        if (a == null || a.getEtatSuivant() == null) {
//            return new ArrayList<>();
//        }
//
//        List<ActionPermise> l = actionPermiseRepository.findByEtatWorkFlow(a.getEtatSuivant().getId());
//
//        List<IdLabelObject> labelObjects = new ArrayList<>();
//
//        l.forEach(e -> {
//            labelObjects.addAll(this.autorisationService.getProfilAutoriseForAction(
//                    this.customUtilService.getConcepteMetierPFU(),
//                    this.autorisationService.getTypeAutorisationEtendu(), CodificationConstant.ACTIONS_WORKFLOW, e.getId()));
//
//        });
//
//        HashSet<String> profillIds = new HashSet<>();
//        labelObjects.forEach(lb -> {
//            profillIds.add(lb.getId());
//        });
//
//        return customUtilService.getUserByProfils(profillIds.toArray()).stream().distinct().collect(Collectors.toList());
//
//    }
//
//    @Override
//    public List<UtilisateurDTO> findAllowedUserOnAction(String idAcion) {
//        List<IdLabelObject> labelObjects = this.autorisationService.getProfilAutoriseForAction(
//                this.customUtilService.getConcepteMetierPFU(),
//                this.autorisationService.getTypeAutorisationEtendu(), CodificationConstant.ACTIONS_WORKFLOW, idAcion);
//
//        HashSet<String> profillIds = new HashSet<>();
//        labelObjects.forEach(lb -> {
//            profillIds.add(lb.getId());
//        });
//
//        return customUtilService.getUserByProfils(profillIds.toArray()).stream().distinct().collect(Collectors.toList());
//
//    }
//
//    @Override
//    public List<UtilisateurDTO> findUserAuthorizeByAction(String cpt, String idObjet,
//            String idAaction
//    ) {
//        ActionPermise a = actionPermiseRepository.getOne(idAaction);
//        if (a == null || a.getEtatSuivant() == null) {
//            return new ArrayList<>();
//        }
//
//        List<EtatUtilisateurAutorise> autorises = etatUtilisateurAutoriseRepository.findOneByEtatConcetpMetierIObjet(a.getEtatSuivant().getId(), cpt, idObjet);
//
//        List<String> usernames = new ArrayList<>();
//
//        autorises.stream().forEach(e -> {
//            usernames.add(e.getUsername());
//        });
//
//        return customUtilService.getUsersByUsernames(usernames.toArray());
//
//    }
//
//    @Override
//    public void initWorkflowId(String cpt, String idObjet,
//            String idWorkflow
//    ) {
//        if (cpt == null || cpt.isEmpty()) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_CONCEPT_METIER_NOT_NULL)", null);
//        }
//        if (idObjet == null || idObjet.isEmpty()) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_IDENTIFIANT_OBJECT_NOT_NULL)", null);
//        }
//        if (idWorkflow == null || idWorkflow.isEmpty()) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_IDENTIFIANT_WORKFLOW_NOT_NULL)", null);
//        }
//
//        Optional<Etat> e = etatRepository.findFirtEtatByWorkfow(idWorkflow);
//
//        if (!e.isPresent()) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_WORKFLOW_NOT_EXISTE)", null);
//
//        }
//        Etat etat = e.get();
//
//        if (!etat.getWorkflow().getStatut().getCode().equals(CodificationConstant.STATUT_PARAM_ACTIF)) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_STATUT_WORKFLOW_NOT_ACTIF)", null);
//        }
//        try {
//            this.genericWorkflowDAO.saveEtat(cpt, idObjet, etat);
//            Tracking t = new Tracking();
//            t.setComment("Création d'objet");
//            t.setConceptMetier(paramListRepository.findParamListByCode(cpt).get());
//            t.setIdObject(idObjet);
//            t.setEtatInitial(etat);
//            t.setEtatFinal(etat);
//            trackingRepository.save(t);
//        } catch (Exception er) {
//            LOG.info(": " + er.toString());
//        }
//
//    }
//
//    @Override
//    public void initWorkflowByEtat(String cpt, String idObjet,
//            Etat etat
//    ) {
//        if (cpt == null || cpt.isEmpty()) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_CONCEPT_METIER_NOT_NULL)", null);
//        }
//        if (idObjet == null || idObjet.isEmpty()) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_CONCEPT_METIER_NOT_NULL)", null);
//        }
//
//        if (!etat.getWorkflow().getStatut().getCode().equals(CodificationConstant.STATUT_PARAM_ACTIF)) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_STATUT_WORKFLOW_NOT_ACTIF)", null);
//
//        }
//        Etat etatCourant = genericWorkflowDAO.findEtatById(cpt, idObjet);
//        genericWorkflowDAO.saveEtat(cpt, idObjet, etat);
//
//        Tracking t = new Tracking();
//        t.setComment("Création d'objet");
//        t.setConceptMetier(paramListRepository.findParamListByCode(cpt).get());
//        t.setIdObject(idObjet);
//        t.setEtatInitial(etat);
//        t.setEtatFinal(etat);
//        if (etat != etatCourant) {
//            trackingRepository.save(t);
//        }
//
//    }
//
//    @Override
//    public void initWorkflowByEtatParametrer(String cpt, String idObjet,
//            Etat etat
//    ) {
//        if (cpt == null || cpt.isEmpty()) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_CONCEPT_METIER_NOT_NULL)", null);
//        }
//        if (idObjet == null || idObjet.isEmpty()) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_CONCEPT_METIER_NOT_NULL)", null);
//        }
//
//        if (!etat.getWorkflow().getStatut().getCode().equals(CodificationConstant.STATUT_PARAM_ACTIF)) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_STATUT_WORKFLOW_NOT_ACTIF)", null);
//
//        }
//        try {
//           Etat etatCourant=  this.genericWorkflowDAO.findEtatById(cpt, idObjet);
//         String commentaire  ="Création d'objet";
//           if(etatCourant!=null){
//               commentaire=etatCourant.getLibelleEtat()+"==>"+etat.getLibelleEtat();
//           }
//            this.genericWorkflowDAO.saveEtat(cpt, idObjet, etat);
//            Tracking t = new Tracking();
//            t.setComment(commentaire);
//            t.setConceptMetier(paramListRepository.findParamListByCode(cpt).get());
//            t.setIdObject(idObjet);
//            t.setEtatInitial(etatCourant==null?etat:etatCourant);
//            t.setEtatFinal(etat);
//            trackingRepository.save(t);
//        } catch (Exception er) {
//            LOG.info(": " + er.toString());
//        }
//    }
//
//    @Override
//    public void initWorkflowByEtatAndSetTrackingComment(String cpt, String idObjet,
//            Etat etat, String commentaire
//    ) {
//
//        if (cpt == null || cpt.isEmpty()) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_CONCEPT_METIER_NOT_NULL)", null);
//        }
//        if (idObjet == null || idObjet.isEmpty()) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_CONCEPT_METIER_NOT_NULL)", null);
//        }
//
//        if (!etat.getWorkflow().getStatut().getCode().equals(CodificationConstant.STATUT_PARAM_ACTIF)) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_STATUT_WORKFLOW_NOT_ACTIF)", null);
//
//        }
//        Etat etatCourant = genericWorkflowDAO.findEtatById(cpt, idObjet);
//        genericWorkflowDAO.saveEtat(cpt, idObjet, etat);
//
//        Tracking t = new Tracking();
//        if (commentaire != null) {
//            t.setComment(commentaire);
//        } else {
//            t.setComment("Création d'objet");
//        }
//        t.setConceptMetier(paramListRepository.findParamListByCode(cpt).get());
//        t.setIdObject(idObjet);
//        t.setEtatInitial(etat);
//        t.setEtatFinal(etat);
//        if (etat != etatCourant) {
//            trackingRepository.save(t);
//        }
//
//    }
//
//    @Override
//    public boolean isProfilAllowONAction(String actionPermiseId, String profilId
//    ) {
//        throw new UnsupportedOperationException("this.codificationService.findMessage(CodificationConstant.WKFL_NOT_SUPPORTED_YET)"); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean isInitalEtatOfWorkFlow(WorkFlowCycle workFlowCycle, Etat etat
//    ) {
//        boolean result = false;
//        if (workFlowCycle != null && workFlowCycle.getId() != null && etat != null && etat.getId() != null) {
//            Optional<Etat> e = this.etatRepository.checkIsFirtEtatByWorkfow(workFlowCycle.getId(), etat.getId());
//
//            if (e.isPresent()) {
//                result = true;
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public boolean etatIsTerminaisonOfWorkFlow(Etat etat
//    ) {
//        boolean result = true;
//        if (etat != null && etat.getId() != null) {
//            Integer i = this.actionPermiseRepository.countActionPermises(etat.getId());
//            if (i > 0) {
//                result = false;
//            }
//        }
//        return result;
//    }
//    @Override
//    public boolean etatIsTerminaisonOfWorkFlow(String etat
//    ) {
//        boolean result = true;
//        if (etat != null) {
//            Integer i = this.actionPermiseRepository.countActionPermises(etat);
//            if (i > 0) {
//                result = false;
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public boolean isEditAuthorisedForEtat(Etat etat
//    ) {
//        boolean result = false;
//        if (etat != null && etat.getModif() != null) {
//
//            if (etat.getModif().equals(true)) {
//                result = true;
//            }
//        }
//        return result;
//    }
//
//    /**
//     * Retourne l'etat initial d'un objet geré de concept métier passé en
//     * parametre
//     *
//     * @return
//     */
//    @Override
//    public Etat getEtatInitial(String conceptMetier
//    ) {
//        WorkflowCycleObjet workflowCycleObjet = this.workflowCycleObjetRepository.findByCodeConcept(conceptMetier, CodificationConstant.STATUT_PARAM_ACTIF);
//        Etat etatInitial = new Etat();
////        System.out.println("WORKFLOW CYCLE +++ " + workflowCycleObjet.toString() + " CONCEPT METIER +++ " + conceptMetier);
//        if (workflowCycleObjet != null && workflowCycleObjet.getWorkflow() != null
//                && workflowCycleObjet.getWorkflow().getStatut().getCode().equals(CodificationConstant.STATUT_PARAM_ACTIF)
//                && workflowCycleObjet.getWorkflow().getId() != null) {
//
//            Optional<Etat> optionalEtatInitial = etatRepository.findFirtEtatByWorkfow(workflowCycleObjet.getWorkflow().getId());
//            if (optionalEtatInitial.isPresent()) {
//                etatInitial = optionalEtatInitial.get();
//            }
//        } else {
//            throw new InternalServerException("this.codificationService.findMessage( CodificationConstant.WKFL_STATUT_WORKFLOW_NOT_ACTIF)", null); // Erreur workflow non configuré
//        }
//        return etatInitial;
//    }
//
//    /**
//     * Verifie si le workflow dont le concepte metié de lobjet géré passé en
//     * parametre est actif
//     *
//     * @param conceptMetier
//     * @return
//     */
//    @Override
//    public boolean workFlowIsActif(String conceptMetier
//    ) {
//        WorkflowCycleObjet workflowCycleObjet = this.workflowCycleObjetRepository.findByCodeConcept(conceptMetier, CodificationConstant.STATUT_PARAM_ACTIF);
//        return workflowCycleObjet != null;
//    }
//
//    @Override
//    public WorkFlowCycle workFlowActif(String conceptMetier) {
//        Optional<WorkFlowCycle> workFlowCycle = this.workflowCycleObjetRepository.findWorkflowByCodeConcept(
//                conceptMetier, CodificationConstant.STATUT_PARAM_ACTIF);
//        if (!workFlowCycle.isPresent()) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_STATUT_WORKFLOW_NOT_ACTIF)", null); // Erreur workflow non configuré
//        }
//
//        return workFlowCycle.get();
//    }
//
//    /**
//     * Recupere tous les Etats du workflow géré par l'object géré dont le
//     * concept metier est passé en parametre
//     *
//     * @param conceptMetier
//     * @return
//     */
//    @Override
//    public List<WorkFlowCycleEtatSimpleObject> getAllEtat(String conceptMetier
//    ) {
//        WorkflowCycleObjet workflowCycleObjet = this.workflowCycleObjetRepository.findByCodeConcept(conceptMetier, CodificationConstant.STATUT_PARAM_ACTIF);
//        List<WorkFlowCycleEtatSimpleObject> listEtat = new ArrayList<>();
//        if (workflowCycleObjet != null && workflowCycleObjet.getWorkflow() != null && workflowCycleObjet.getWorkflow().getId() != null) {
//            List<Etat> etats = this.etatRepository.findAllEtatByWorkflow(workflowCycleObjet.getWorkflow().getId());
//            if (etats != null && !etats.isEmpty()) {
//                etats.stream().forEach(t -> {
//                    listEtat.add(new WorkFlowCycleEtatSimpleObject(t.getId(), t.getLibelleEtat(), t.getCodeCouleur(), t.getCodeCouleurText(), t.getWorkflow().getLibelle()));
//                });
//            }
//        }
//        return listEtat;
//    }
//
//    /**
//     * Cette méthode permet de verifier si le workflow d'une fonction utilisant
//     * les objets géré est bien configuré
//     *
//     * @param conceptMetier Le concept metier de l'objet
//     * @param errorConstantWkfNotConfigured Constante d'erreur pour un workflow
//     * non configuré
//     * @param errorConstantWkfNotValide Constant d'erreur pour un workflow mal
//     * configuré
//     * @return
//     */
//    @Override
//    public WorkflowCycleObjet findWorkFlowCycleObjectOrThrowError(String conceptMetier, String errorConstantWkfNotConfigured,
//            String errorConstantWkfNotValide
//    ) {
//
//        List<WorkflowCycleObjet> workflowCycleObjet = this.workflowCycleObjetRepository.findByCodeConceptMetier(conceptMetier, CodificationConstant.STATUT_PARAM_ACTIF);
//
//        /**
//         * Workflow des composants programmatiques non configuré
//         */
//        if (workflowCycleObjet == null || workflowCycleObjet.isEmpty()) {
//            throw new InternalServerException("this.codificationService.findMessage(errorConstantWkfNotConfigured)", null); // Erreur workflow non configuré
//        }
//
//        /**
//         * Workflow mal configuré. L'objet géré du workflow des composants
//         * programmatique ne doit être utilisé qu'une seule fois
//         */
//        if (workflowCycleObjet.size() > 1) {
//            throw new InternalServerException("this.codificationService.findMessage(errorConstantWkfNotValide)", null); // Workflow mal configuré
//        }
//        return workflowCycleObjet.get(0);
//    }
//
//    @Override
//    public Etat getEtatInitialOrThrowError(String conceptMetier, String errorConstantWkfNotConfigured,
//            String errorConstantWkfNotValide
//    ) {
//
//        List<WorkflowCycleObjet> workflowCycleObjet = this.workflowCycleObjetRepository.findByCodeConceptMetier(conceptMetier, CodificationConstant.STATUT_PARAM_ACTIF);
//
//        /**
//         * Workflow des composants programmatiques non configuré
//         */
//        if (workflowCycleObjet == null || workflowCycleObjet.isEmpty()) {
//            throw new InternalServerException("this.codificationService.findMessage(errorConstantWkfNotConfigured)", null); // Erreur workflow non configuré
//        }
//
//        /**
//         * Workflow mal configuré. L'objet géré du workflow des composants
//         * programmatique ne doit être utilisé qu'une seule fois
//         */
//        if (workflowCycleObjet.size() > 1) {
//            throw new InternalServerException("this.codificationService.findMessage(errorConstantWkfNotValide)", null); // Workflow mal configuré
//        }
//        Etat etatInitial = new Etat();
//        Optional<Etat> optionalEtatInitial = etatRepository.findFirtEtatByWorkfow(workflowCycleObjet.get(0).getWorkflow().getId());
//        if (optionalEtatInitial.isPresent()) {
//            etatInitial = optionalEtatInitial.get();
//        }
//
//        return etatInitial;
//    }
//
//    /**
//     * Permet de vérifier le rang de la sequence de l'etat courant
//     *
//     * @param etat (Etat courant du workflow)
//     * @return retourne le rang de la sequence de l'état courant du workflow
//     * @Pierre 09-12-2018
//     */
//    @Override
//    public Integer currentSequence(Etat etat
//    ) {
//        List<Integer> listSequence = this.etatRepository.findAllSequenceAsc(etat.getWorkflow().getId());
//        return listSequence.indexOf(etat.getIdeSequence());
//    }
//
//    @Override
//    public Integer currentSequenceDistinct(Etat etat
//    ) {
//        List<Integer> listSequence = this.etatRepository.findAllSequenceAscDistinct(etat.getWorkflow().getId());
//        return listSequence.indexOf(etat.getIdeSequence());
//    }
//
//    /**
//     * Permet de vérifier le rang de la sequence suivante de l'etat courant
//     *
//     * @param actionPermise Action a executer
//     * @return retourne le rang de la prochaine sequence de l'etat suivant du
//     * workflow
//     * @Pierre 09-12-2018
//     */
//    @Override
//    public Integer nextSequence(ActionPermise actionPermise
//    ) {
//        if (actionPermise.getEtatSuivant() == null) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_ETAT_PAS_ACTION_SUIVANTE)", null);
//        }
//        List<Integer> listSequence = this.etatRepository.findAllSequenceAsc(actionPermise.getEtat().getWorkflow().getId());
//        return listSequence.indexOf(actionPermise.getEtatSuivant().getIdeSequence());
//    }
//
//    /**
//     * Permet de vérifier le rang de l'étape de la séquence de l'etat courant
//     *
//     * @param etat (Etat courant du workflow)
//     * @return retourne le rang de l'étape de la sequence de l'état courant
//     * @Pierre 09-12-2018
//     */
//    @Override
//    public Integer currentEtape(Etat etat
//    ) {
//        List<Integer> listEtape = this.etatRepository.findAllEtapeOfSequence(etat.getWorkflow().getId(), etat.getIdeSequence());
//        return listEtape.indexOf(etat.getIdeEtape());
//    }
//
//    /**
//     * Permet de vérifier le rang de l'étape de la séquence de l'etat suivant
//     *
//     * @param actionPermise (action à exécuter)
//     * @return retourne le rang de l'étape de la sequence de l'état suivant
//     * @Pierre 09-12-2018
//     */
//    @Override
//    public Integer nextEtape(ActionPermise actionPermise
//    ) {
//        if (actionPermise.getEtatSuivant() == null) {
//            throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.WKFL_ETAT_PAS_ACTION_SUIVANTE)", null);
//        }
//        List<Integer> listEtape = this.etatRepository.findAllEtapeOfSequence(actionPermise.getEtat().getWorkflow().getId(), actionPermise.getEtatSuivant().getIdeSequence());
//        return listEtape.indexOf(actionPermise.getEtat().getIdeEtape());
//    }
//
//    public Map<String, IExecutor> getExecutors() {
//        return executors;
//    }
//
//    public void setExecutors(Map<String, IExecutor> executors) {
//        this.executors = executors;
//    }
//
//    @Override
//    public boolean isTerminaisonWorkflow(Etat etat) {
//        boolean b = false;
//        List<ActionDTO> actionDTOs = actionPermiseRepository.findByEtat(etat.getId());
//        if (actionDTOs.isEmpty()) {
//            b = true;
//        }
//        return b;
//    }
//
//    @Override
//    public List<Etat> terminaisonEtats(String wkf) {
//        List<Etat> etats = this.etatRepository.findAllEtatByWorkflow(wkf);
//        List<Etat> terminaisons = new ArrayList<>();
//        etats.forEach((etat) -> {
//            List<ActionDTO> actionDTOs = actionPermiseRepository.findByEtat(
//                    etat.getId());
//            if (actionDTOs.isEmpty()) {
//                terminaisons.add(etat);
//            }
//        });
//
//        return terminaisons;
//    }
//
//    /**
//     * Retourne le derniere etat du workflow
//     *
//     * @param idw
//     * @return
//     */
//    @Override
//    public Etat getLastSequenceLastEtat(String idw) {
//        Etat lastEtat = new Etat();
//        Optional<Etat> optionalLastEtat = etatRepository.getLastSequenceLastEtat(idw);
//        if (optionalLastEtat.isPresent()) {
//            lastEtat = optionalLastEtat.get();
//        }
//        return lastEtat;
//    }
//
////    @Override
////    public Etat getEtatByRangSequence(String idw, Integer sequenceIndex, Integer sequenceEtat) {
////        Page<Integer> pageSequence = this.etatRepository.findSequenceCodeByIndexAndWorkflow(idw, new PageRequest(sequenceIndex, 1, Sort));
////        if (!pageSequence.getContent().isEmpty()) {
////            Page<Etat> pageEtat = this.etatRepository.findSequenceEtatByIndexAndWorkflow(idw, pageSequence.getContent().get(0), new PageRequest(sequenceEtat, 1));
////            if (!pageEtat.getContent().isEmpty()) {
////                return pageEtat.getContent().get(0);
////            }
////        }
////        return null;
////    }
//
//    @Override
//    public List<ActionDTO> findActionByObectAndEtatAndCurrentUserAuthorize(String cpt, String idObjet, String codeFonction) {
//
//        Etat etatCourant = genericWorkflowDAO.findEtatById(cpt, idObjet);
//
//        String createdUser = genericWorkflowDAO.findAuthorBy(cpt, idObjet);
//
//        String currentUser = customUtilService.getCurrentUserLogin().get();
//
//        if (etatCourant == null) {
//            return new ArrayList<>();
//        }
//
//        EtatUtilisateurAutorise autorise = etatUtilisateurAutoriseRepository
//                .findOneByUsernameEtatConcetpMetierIbjet(
//                        customUtilService.getCurrentUserLogin().get(),
//                        etatCourant.getId(), cpt, idObjet);
//        Etat currentEtat = this.getEtatByRangSequence(etatCourant.getWorkflow().getId(), 0, 0);
//        Etat firstSequenceSecondEtat = this.getEtatByRangSequence(etatCourant.getWorkflow().getId(), 0, 1);
//        if (autorise == null && !(currentUser.equals(createdUser)
//                && (currentEtat.getId().equals(etatCourant.getId()) || (firstSequenceSecondEtat != null && firstSequenceSecondEtat.getId().equals(etatCourant.getId()))))) {
//            return new ArrayList<>();
//        }
////        if (autorise == null && !(currentUser.equals(createdUser) && currentEtat.getId().equals(etatCourant.getId()))) {
////            return new ArrayList<>();
////        }
//
//        return this.findActionByObectAndEtatAndCurrentUser(cpt, idObjet, codeFonction);
//    }
//
//    public List<CycleDTO> getWorkflows(String statutCode) {
//        List<CycleDTO> listeWkf = new ArrayList<>();
//        List<WorkFlowCycle> wkf = workFlowCycleRepository.findByStatut(statutCode);
//        wkf.stream().forEach(p -> {
//            CycleDTO wDTO = new CycleDTO();
//            wDTO.setId(p.getId());
//            wDTO.setLibelle(p.getLibelle());
//            wDTO.setNumero(p.getNumero());
//            wDTO.setEtape(this.etatRepository.findByWorkflowCycle(p.getId()));
//            listeWkf.add(wDTO);
//        });
//        return listeWkf;
//    }
//
//    public String getCodeEtatActionSuivante(String idActionPermise) {
//        String result;
//        ActionPermise ap = this.actionPermiseRepository.getOne(idActionPermise);
//
//        if (ap.getEtatSuivant() != null && ap.getEtatSuivant().getCodeEtat() != null) {
//            result = ap.getEtatSuivant().getCodeEtat();
//        } else {
//            result = ap.getEtat().getCodeEtat();
//        }
//        return result;
//    }
//
//    public boolean etatSuivantIsTerminaison(String idActionPermise) {
//        boolean result = true;
//        ActionPermise ap = this.actionPermiseRepository.getOne(idActionPermise);
//
//        if (ap.getEtatSuivant() != null && ap.getEtatSuivant().getCodeEtat() != null) {
//
//            if (!this.etatIsTerminaisonOfWorkFlow(ap.getEtatSuivant())) {
//                result = false;
//            }
//        }
//        return result;
//    }
//
//    /**
//     * Execute tous les callbacks définis sur une action
//     *
//     * @param actionPermise
//     * @param params
//     */
//    public void executeAllCalback(ActionPermise actionPermise, HashMap<String, Object> params) {
//        if (actionPermise != null && actionPermise.getId() != null) {
//
//            List<String> listCallBack = this.actionPermiseExecutorRepository.findExecutorImplementationByActionPermise(actionPermise.getId());
//
//            if (listCallBack != null && !listCallBack.isEmpty()) {
//                listCallBack.stream().forEach(cbImplementation -> {
//                    System.out.println("Implémentation " + cbImplementation);
//                    IExecutor executor = executors.get(cbImplementation);
//                    if (executor == null) {
//                        throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.EXECUTOR_NULL)", null);
//                    }
//                    boolean e = executor.execut(params);
//                    if (!e) {
//                        throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.EXECUTION_ERROR)", null);
//                    }
//                });
//            }
//        }
//    }
//
//    public void executeOneCallBack(ActionPermise actionPermise, HashMap<String, Object> params) {
//        if (actionPermise.getCallBack() != null) {
//            IExecutor executor = executors.get(actionPermise.getCallBack().getImplementation());
//            if (executor == null) {
//                throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.EXECUTOR_NULL)", null);
//            }
//            boolean e = executor.execut(params);
//            if (!e) {
//                throw new InternalServerException("this.codificationService.findMessage(CodificationConstant.EXECUTION_ERROR)", null);
//            }
//        }
//    }
//
//    @Override
//    public boolean etatIsBeforeFirstEtapeValidation(Etat objecEtat) {
//        Integer i = etatRepository.findEtatValidationSeqMinByWorkflow(objecEtat.getWorkflow().getId(),
//                CodificationConstant.STATUT_PARAM_ACTIF, CodificationConstant.OUI_NON_OUI_IDE_CODE);
//        Optional<Etat> etat = this.etatRepository.findEtatByWorkflowAndSequenceAndEtapeValidation(objecEtat.getWorkflow().getId(),
//                i, CodificationConstant.STATUT_PARAM_ACTIF, CodificationConstant.OUI_NON_OUI_IDE_CODE);
//        return ((i != null && etat.isPresent())
//                ? (objecEtat.getIdeSequence() < i) || ((objecEtat.getIdeSequence().equals(i)
//                && etat.isPresent()
//                && objecEtat.getIdeEtape() < etat.get().getIdeEtape())) : false);
//
//    }
//
//    public boolean etatIsFirstEtapeValidation(Etat objecEtat) {
//        Integer i = etatRepository.findEtatValidationSeqMinByWorkflow(objecEtat.getWorkflow().getId(),
//                CodificationConstant.STATUT_PARAM_ACTIF, CodificationConstant.OUI_NON_OUI_IDE_CODE);
//        Optional<Etat> etat = this.etatRepository.findEtatByWorkflowAndSequenceAndEtapeValidation(objecEtat.getWorkflow().getId(),
//                i, CodificationConstant.STATUT_PARAM_ACTIF, CodificationConstant.OUI_NON_OUI_IDE_CODE);
//        return ((i != null && etat.isPresent())
//                ? (objecEtat.getIdeSequence() > i) || ((objecEtat.getIdeSequence().equals(i)
//                && etat.isPresent()
//                && objecEtat.getIdeEtape() > etat.get().getIdeEtape())) : false);
//
//    }
//
////    @Override
////    public Integer executerActionForBatchWorkflowPaiement(String codeFonction, String cpt, String idObjet, String idAcion, String commentaire, String[] users, HashMap<String, Object> params, String userLogin) {
////
////        System.out.println("  executerActionForBatchWorkflowPaiement 0001");
////        Etat etatCourant = genericWorkflowDAO.findEtatById(cpt, idObjet);
////        List<String> idProfiles = customUtilService.getProfilByUserLogin(userLogin);
////        System.out.println("  executerActionForBatchWorkflowPaiement 00011");
////        String createdUser = genericWorkflowDAO.findAuthorBy(cpt, idObjet);
////        System.out.println("  executerActionForBatchWorkflowPaiement 00012");
////        String currentUser = userLogin;
////        System.out.println("  executerActionForBatchWorkflowPaiement 00013");
////        if (codeFonction == null || codeFonction.isEmpty() || configFonctionRepository.getOne(codeFonction) == null) {
////            throw new InternalServerException(this.codificationService.findMessage(CodificationConstant.WKFL_FONCTION_INVALIDE), null);
////
////        }
////        if (etatCourant == null) {
////            throw new InternalServerException(this.codificationService.findMessage(CodificationConstant.ETAT_NULL), null);
////        }
////        ActionPermise actionPermise = actionPermiseRepository.getOne(idAcion);
////        if (actionPermise == null) {
////            throw new InternalServerException(this.codificationService.findMessage(CodificationConstant.ACTION_NULL), null);
////        }
////        if (!etatCourant.getId().equals(actionPermise.getEtat().getId())) {
////            throw new InternalServerException(this.codificationService.findMessage(CodificationConstant.ETAT_ACTION_DIFFERENT), null);
////        }
////        List<IdLabelObject> listProfileAutorise = this.autorisationService.getProfilAutoriseForAction(
////                this.customUtilService.getConcepteMetierPFU(),
////                this.autorisationService.getTypeAutorisationEtendu(), CodificationConstant.ACTIONS_WORKFLOW, idAcion);
////        boolean flag = false;
////        for (IdLabelObject i : listProfileAutorise) {
////
////            if (idProfiles.contains(i.getId())) {
////                flag = true;
////                break;
////
////            }
////        }
////        boolean flg = (currentUser.equals(createdUser) && this.currentSequence(etatCourant) == 0);
////        if ((params == null || !params.containsKey(CodificationConstant.WKF_NOTSECURE)) && !flag && !flg) {
////            throw new InternalServerException(this.codificationService.findMessage(CodificationConstant.USER_NOT_AUTHORISED), null);
////        }
////        String codeHabilitation = this.customUtilService.findNiveauHabilitationByUserLoginAndFunction(codeFonction, userLogin);
////        if ((params == null || !params.containsKey(CodificationConstant.WKF_NOTSECURE)) && (codeHabilitation == null || codeHabilitation.isEmpty())) {
////            throw new InternalServerException(this.codificationService.findMessage(CodificationConstant.USER_NOT_AUTHORISED), null);
////
////        }
////
////        if ((params == null || !params.containsKey(CodificationConstant.WKF_NOTSECURE)) && actionPermise.getHabilitation() != null
////                && Integer.valueOf(actionPermise.getHabilitation().getCode()) > Integer.valueOf(codeHabilitation) && !flg) {
////            throw new InternalServerException(this.codificationService.findMessage(CodificationConstant.USER_NOT_AUTHORISED), null);
////
////        }
////        {
////            //this.executeOneCallBack(actionPermise, params);
////
////            this.executeAllCalback(actionPermise, params);
////        }
////        System.out.println("  executerActionForBatchWorkflowPaiement 00014");
////        flag = false;
////        Tracking t = new Tracking();
////
////        t.setComment(commentaire);
////        if (params != null && params.get(CodificationConstant.ANONYME) != null) {
////            t.setCommentaireAnonyme(params.get(CodificationConstant.ANONYME).toString());
////        }
////        System.out.println("  executerActionForBatchWorkflowPaiement 00015");
////        List<EvenementPossible> evenementPossibles = evenementProgrammeRepository.findByAction(idAcion);
////        try {
////            System.out.println("  executerActionForBatchWorkflowPaiement 00016");
////            /**
////             * Enregistrement d'une ligne d'action effectué
////             */
//////            ActionEffectuee ev = new ActionEffectuee();
//////            ev.setAction(actionPermise);
//////
//////            String remoteAddr = "";
//////
//////            if (request != null) {
//////                remoteAddr = request.getHeader("X-FORWARDED-FOR");
//////                if (remoteAddr == null || "".equals(remoteAddr)) {
//////                    remoteAddr = request.getRemoteAddr();
//////                }
//////            }
//////            ev.setIp(remoteAddr);
//////            actionEffectueeRepository.save(ev);
////
////            /**
////             * Execution des evenement associés à l'objet
////             */
////            for (EvenementPossible e : evenementPossibles) {
////                if (e.getEvent().getCode().equals(CodificationConstant.WF_REFERENTIEL_EVENEMENTS_CHANGEMENT_BLOCAGE_ETAT)) {
////                    flag = true;
////                }
////                EvenementGenere evg = new EvenementGenere();
////                evg.setActionPermise(actionPermise);
////                evg.setEvenement(e);
////                evg.setConceptMeter(paramListRepository.findParamListByCode(cpt).get());
////                evg.setIdObjet(idObjet);
////                //evg.setStatut(paramListRepository.findByCodificationAndCode(CodificationConstant.EVENT_STATUS, CodificationConstant.EVENT_STATUS_NOUVEL));
////
////                evenementGenereRepository.save(evg);
////
////            }
////            if (!flag) {
////                if (actionPermise.getEtatSuivant() != null) {
////                    genericWorkflowDAO.saveEtat(cpt, idObjet, actionPermise.getEtatSuivant().getId());
////
////                    t.setEtatFinal(actionPermise.getEtatSuivant());
////
////                    List<UtilisateurDTO> udtos = customUtilService.getUserByUsernames(users);
////
////                    List<ActionPermise> actionEtatSuivants = actionPermiseRepository.findByEtatWorkFlow(actionPermise.getEtatSuivant().getId());
////                    HashSet<IdLabelObject> labelObjects = new HashSet<>();
////                    HashSet<String> profillIds = new HashSet<>();
////                    /**
////                     * Recherche des profiles liés aux actions de l'etats
////                     * suivant
////                     */
////                    actionEtatSuivants.stream().forEach(item -> {
////                        labelObjects.addAll(this.autorisationService.getProfilAutoriseForAction(
////                                this.customUtilService.getConcepteMetierPFU(),
////                                this.autorisationService.getTypeAutorisationEtendu(), CodificationConstant.ACTIONS_WORKFLOW, item.getId()));
////                    });
////
////                    labelObjects.forEach(lb -> {
////                        profillIds.add(lb.getId());
////                    });
////                    /**
////                     * Enregistrement des utilisateur autorié pour recevoir des
////                     * notifications pour l'etat suivant
////                     */
////                    udtos.forEach(u -> {
////
////                        for (String e : u.getProfil()) {
////                            if (profillIds.contains(e)) {
////                                EtatUtilisateurAutorise etu = etatUtilisateurAutoriseRepository.findOneByUsernameEtatConcetpMetierIbjet(u.getUsername(), actionPermise.getEtatSuivant().getId(), cpt, idObjet);
////
////                                if (etu == null) {
////                                    etu = new EtatUtilisateurAutorise();
////                                    etu.setConceptMetier(cpt);
////                                    etu.setUsername(u.getUsername());
////                                    etu.setEtat(actionPermise.getEtatSuivant());
////                                    etu.setIdObjet(idObjet);
////
////                                    etatUtilisateurAutoriseRepository.save(etu);
////
////                                }
////                                break;
////                            }
////                        }
////
////                    });
////
////                }
////                if (t.getEtatFinal() == null) {
////                    t.setEtatFinal(etatCourant);
////                }
////                t.setConceptMetier(paramListRepository.findParamListByCode(cpt).get());
////                t.setIdObject(idObjet);
////                t.setEtatInitial(etatCourant);
////                t.setAction(actionPermise);
////                trackingRepository.save(t);
////            }
////
////            return 0;
////        } catch (Exception e) {
////            throw new InternalServerException(this.codificationService.findMessage(CodificationConstant.UNKNOWN_EXCEPTION), null);
////        }
////    }
//
//    public boolean getCommentRequired(String idActionPermise) {
//        boolean comment = false;
//        ActionPermise actionPermise = this.actionPermiseRepository.getOne(idActionPermise);
//        if (actionPermise != null && actionPermise.getIndicateurCommentaire() != null && (actionPermise.getIndicateurCommentaire().getCode().equalsIgnoreCase(CodificationConstant.INFO_TRANSITION_BASIQUE)
//                || actionPermise.getIndicateurCommentaire().getCode().equalsIgnoreCase(CodificationConstant.INFO_TRANSITION_ETENDU))) {
//            comment = true;
//        }
//        return comment;
//    }
//
//    @Override
//    public List<ActionDTO> findActionByObectAndEtatByUserAuthorize(String cpt, String idObjet, String codeFonction, String currentUser) {
//
//        Etat etatCourant = genericWorkflowDAO.findEtatById(cpt, idObjet);
//
//        String createdUser = genericWorkflowDAO.findAuthorBy(cpt, idObjet);
//
////        String currentUser = customUtilService.getCurrentUserLogin().get();
//
//        if (etatCourant == null) {
//            return new ArrayList<>();
//        }
//
//        EtatUtilisateurAutorise autorise = etatUtilisateurAutoriseRepository
//                .findOneByUsernameEtatConcetpMetierIbjet(
//                       currentUser,
//                        etatCourant.getId(), cpt, idObjet);
//        Etat currentEtat = this.getEtatByRangSequence(etatCourant.getWorkflow().getId(), 0, 0);
//        Etat firstSequenceSecondEtat = this.getEtatByRangSequence(etatCourant.getWorkflow().getId(), 0, 1);
//        if (autorise == null && !(currentUser.equals(createdUser)
//                && (currentEtat.getId().equals(etatCourant.getId()) || (firstSequenceSecondEtat != null && firstSequenceSecondEtat.getId().equals(etatCourant.getId()))))) {
//            return new ArrayList<>();
//        }
////        if (autorise == null && !(currentUser.equals(createdUser) && currentEtat.getId().equals(etatCourant.getId()))) {
////            return new ArrayList<>();
////        }
//
//        return this.findActionByObectAndEtatByUser(cpt, idObjet, codeFonction,currentUser);
//
//    }
//
//    @Override
//    public List<ActionDTO> findActionByObectAndEtatByUser(String cpt, String idObjet, String codeFonction,String currentUser) {
//    
//     List<String> idProfiles = customUtilService.getProfilByUserLogin(currentUser);
//
//        Etat etatCourant = genericWorkflowDAO.findEtatById(cpt, idObjet);
//        String createdUser = genericWorkflowDAO.findAuthorBy(cpt, idObjet);
////        String currentUser = customUtilService.getCurrentUserLogin().get();
//
//        if (etatCourant == null) {
//            return new ArrayList<>();
//        }
//
//        List<ActionDTO> l;
//        if (codeFonction != null) {
//            String codeHabilitation = this.customUtilService.findNiveauHabilitationByUserLoginAndFunction(codeFonction,currentUser);
//
//            l = actionPermiseRepository.findByEtatByHabilitation(etatCourant.getId(), 
//                    codeHabilitation);
//        } else {
//            l = actionPermiseRepository.findByEtat(etatCourant.getId());
//        }
//        if (currentUser.equals(createdUser)) {
//            return l;
//        }
//
//        List<ActionDTO> l2 = new ArrayList<>();
//        for (ActionDTO a : l) {
//            List<IdLabelObject> listProfileAutorise = this.autorisationService.getProfilAutoriseForAction(
//                    this.customUtilService.getConcepteMetierPFU(),
//                    this.autorisationService.getTypeAutorisationEtendu(), CodificationConstant.ACTIONS_WORKFLOW, a.getId());
//
//            for (IdLabelObject i : listProfileAutorise) {
//                if (idProfiles.contains(i.getId())) {
//                    l2.add(a);
//                    break;
//                }
//            }
//        }
//        return l2;
//    
//    }
//}
