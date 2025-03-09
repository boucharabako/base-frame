///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.core.codification.dto.StatutResponce;
//import com.ngs.core.codification.entities.CodifList;
//import com.ngs.core.codification.entities.MessageApplicatif;
//import com.ngs.core.codification.repositories.ClasseurRepository;
//import com.ngs.core.codification.repositories.CodifListRepository;
//import com.ngs.core.codification.repositories.CodificationRepository;
//import com.ngs.core.codification.repositories.EtiquetteRepository;
//import com.ngs.core.codification.repositories.LibelleRepository;
//import com.ngs.core.codification.repositories.MessageApplicatifRepository;
//import com.ngs.core.codification.repositories.ParameterRepository;
//import com.ngs.core.codification.utils.ApiError;
//import com.ngs.core.codification.utils.CodificationConstant;
//import com.ngs.core.codification.utils.MessageParam;
//import com.ngs.core.codification.utils.RestConstants;
//import com.ngs.core.codification.utils.TypeError;
//import java.util.HashMap;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.Validator;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
///**
// *
// * @author W.KOUWONOU
// * @since 04/05/2018
// * @version 1.0.0 Declare des constantes et methodes génériques liés à la
// * gestion des workflow
// */
//public class WorkFlowConstants {
//
//    protected static final String ATT_STATUT_ACTIF = "statutActif";
//    protected static final String ATT_STATUT_SAISIE = "statutSaisie";
//    protected static final String ATT_STATUT_OBSOLETE = "statutObsolete";
//    protected static final String ATT_ACTION_ACTIVER = "actionActiver";
//    protected static final String ATT_ACTION_ABANDONNER = "actionAbandonner";
//    protected static final String ATT_CHANGER_STATUT = "changerStatut";
//    protected static final String OPERATION_NON_AUTORISEE = "opp_non_aut";
//    protected static final String PARAMETRE_INVALIDE = "param_inv";
//     @Autowired
//    protected TrackingParamObjetService trackingParamObjetService;
//    @Autowired
//    protected CodifListRepository codifListRepository;
//    @Autowired
//    protected Validator validator;
//    @Autowired
//    protected ErrorMessageService errorMessage;
//    @Autowired
//    protected MessageApplicatifRepository messageRepository;
//    @Autowired
//    protected LibelleRepository libelleRepository;
//    @Autowired
//    protected CodificationRepository codificationRepository;
//    @Autowired
//    protected NProperties properties;
//    @Autowired protected ParameterRepository parameterRepository;
//    @Autowired protected EtiquetteRepository etiquetteRepository;
//    @Autowired protected ClasseurRepository classeurRepository;
//    
//    
//
//    @ModelAttribute(ATT_STATUT_ACTIF)
//    public CodifList statutActif() {
//        return properties.findStatutByCode(CodificationConstant.STATUT_PARAM_ACTIF);
//
//    }
//
//    @ModelAttribute(ATT_STATUT_SAISIE)
//    public CodifList statutSaisie() {
//
//        return properties.findStatutByCode(CodificationConstant.STATUT_PARAM_EN_SAISIE);
//
//    }
//
//    @ModelAttribute(ATT_STATUT_ACTIF)
//    public CodifList statutObsolete() {
//        return properties.findStatutByCode(CodificationConstant.STATUT_PARAM_OBSOLETE);
//
//    }
//
//    @ModelAttribute(ATT_ACTION_ACTIVER)
//    public CodifList actionActiver() {
//        return properties.findActionByCode(CodificationConstant.ACTION_PARAM_ACTIVER);
//
//    }
//
//    @ModelAttribute(ATT_ACTION_ABANDONNER)
//    public CodifList actionAbandonner() {
//        return properties.findActionByCode(CodificationConstant.ACTION_PARAM_ABANDONNER);
//
//    }
//
//    /**
//     * Validation du changement de statut sur un objet
//     *
//     * @param idecourant code du statut courant de l'objet
//     * @param ideSuivant code su statut suivant de l'objet
//     * @return Les erreurs de validation
//     */
//    public StatutResponce processStatut(String idecourant, String ideSuivant) {
//        HashMap<String, MessageParam> errors = new HashMap<>();
//        HashMap<String, Object> model = new HashMap<>();
//        StatutResponce s = new StatutResponce();
//        /**
//         * Les parametres son vides
//         */
//        if (ideSuivant == null || ideSuivant.isEmpty()
//                || idecourant == null || idecourant.isEmpty()) {
//            MessageApplicatif p = messageRepository.findByCodeAndCodeLangue(PARAMETRE_INVALIDE, properties.getLangue().getId());
//
//            ApiError apiError = this.errorMessage.getApiError("400", TypeError.DANGER,
//                    p == null ? "" : p.getLibelle(), errors);
//            model.put(RestConstants.MSG_KEY, apiError);
//            s.setModel(model);
//            return s;
//
//        }
//
//        /**
//         * Objet deja obsolete ou pération invalide
//         */
//        if (idecourant.equals(CodificationConstant.STATUT_PARAM_OBSOLETE)
//                || ideSuivant.equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)
//                || ideSuivant.equals(CodificationConstant.STATUT_PARAM_ACTIF)
//                && !idecourant.equals(CodificationConstant.STATUT_PARAM_EN_SAISIE)
//                || ideSuivant.equals(CodificationConstant.STATUT_PARAM_OBSOLETE)
//                && !idecourant.equals(CodificationConstant.STATUT_PARAM_ACTIF)) {
//
//            MessageApplicatif p = messageRepository.findByCodeAndCodeLangue(OPERATION_NON_AUTORISEE, properties.getLangue().getId());
//
//            ApiError apiError = this.errorMessage.getApiError("400", TypeError.DANGER,
//                    p == null ? "" : p.getLibelle(), errors);
//            model.put(RestConstants.MSG_KEY, apiError);
//            s.setModel(model);
//            return s;
//
//        }
//
//        ApiError apiError = this.errorMessage.getApiError("400", TypeError.INFO,
//                properties.getInfoPrase(), errors);
//        model.put(RestConstants.MSG_KEY, apiError);
//                    s.setModel(model);
//
//        s.setError(false);
//        return s;
//
//    }
//}
