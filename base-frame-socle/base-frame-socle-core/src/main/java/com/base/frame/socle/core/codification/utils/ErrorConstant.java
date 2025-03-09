/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.utils;

import java.io.Serializable;

/**
 *
 * @author rkoufionou
 * @since 16/30/2018
 * @version 1.0.0
 */
public class ErrorConstant implements Serializable {

    /**
     * Operation execution validation type
     */
    public static final String OPERATION_VALIDATION_ERROR = "OPERATION_VALIDATION_ERROR";
    public static final String OPERATION_SUCCESS = "OPERATION_SUCCESS";
    public static final String OPERATION_SUCCESS_LABEL = "OPERATION_SUCCESS_LABEL";
    public static final String OPERATION_ERROR_LABEL = "OPERATION_ERROR_LABEL";
    public static final String STATUS_BAD_REQUEST = "400";
    public static final String STATUS_SUCCESS = "200";

    /**
     * Debut constantes Hervé Mofiala LOKOSSOU
     */
    public static final String STATUS_INTERNAL_SERVER_ERROR = "500";
    public static final String OPERATION_ERROR = "MSG_CODIFICATION_GROUPE_EXTENSION_GLOBAL_ECHEC";
    public static final String OPERATION_MSG_NO_EXTENSION = "MSG_CODIFICATION_GROUPE_EXTENSION_PAS_DEXTENSION";
    public static final String KEY_DOUBLE = "MSG_CODIFICATION_GROUPE_CLE_DOUBLE";
    public static final String KEY_DOUBLE_WITH_SURCIS = "MSG_CODIFICATION_GROUPE_CLES_DOUBLES_WITH_SURCIS";

    //Fin des constantes Hervé Mofiala LOKOSSOU
    public static final String EXTENSION_ID_CONSTRAINT = "EXTENSION_ID_CONSTRAINT";

    public static final String FIELD_INVALID = "F_INVALID";

    public static final String CHANGEMENT_STATUT_PARAM = "CHANGEMENT_DE_STATUT";
    public static final String STRUCTURE_REQUIS = "STRUCTURE_REQUIS";
    public static final String LIBELLE_REQUIS = "LIBELLE_REQUIS";
    public static final String TYPE_LIGNE_REQUIS = "TYPE_LIGNE_REQUIS";
    public static final String CATEGORIE_REQUIS = "CATEGORIE_REQUIS";
    public static final String ENCODE_KEY_REQUIS = "ENCODE_KEY_REQUIS";
    public static final String TYPE_INDICATEUR_ASSOCIE = "TYPE_INDICATEUR_ASSOCIE";
    public static final String TYPE_ACTEUR_REQUISE = "TYPE_ACTEUR_REQUISE";

    public static final String FIRST_ZONE_REQUISE = "FIRST_ZONE_REQUISE";
    public static final String FIRST_SOUS_ZONE_REQUISE = "FIRST_SOUS_ZONE_REQUISE";
    public static final String ZONE_PRECEDENT_ZONE_SAISE_REQUISE = "ZONE_PRECEDENT_ZONE_SAISE_REQUISE";
    public static final String SOUS_ZONE_PRECEDENT_SOUS_ZONE_SAISE_REQUISE = "SOUS_ZONE_PRECEDENT_SOUS_ZONE_SAISE_REQUISE";
    public static final String PR_LINE_MUST_BE_ASSOCIETED = "PR_LINE_MUST_BE_ASSOCIETED";
    public static final String CFG_CODE_PAYS = "CFG_CODE_PAYS";
    public static final String ACTEUR_REQUIS = "ACTEUR_REQUIS";
    public static final String ENCODE_KEY_EXIST = "ENCODE_KEY_EXIST";
    public static final String ZONE_ERROR_MESSAGE = "ZONE_ERROR_MESSAGE";
    public static final String SOUS_ZONE_ERROR_MESSAGE = "SOUS_ZONE_ERROR_MESSAGE";
    public static final String ZONE_REQUIRED_MESSAGE = "ZONE_REQUIRED_MESSAGE";
    public static final String SOUS_ZONE_REQUIRED_MESSAGE = "SOUS_ZONE_REQUIRED_MESSAGE";
    public static final String PREVIOUS_ZONE_MUST_BE_FILLED = "PREVIOUS_ZONE_MUST_BE_FILLED";
    public static final String CAN_NOT_ASSOCIETE_SAME_LIGNE_TO_ITSELF = "CAN_NOT_ASSOCIETE_SAME_LIGNE_TO_ITSELF";
    public static final String LIGNE_ALLREADY_ASSIGN = "LIGNE_ALLREADY_ASSIGN";

    public static final String PROGRAMME_INDICATEUR_EMPTY = "PROGRAMME_INDICATEUR_EMPTY";

    public static final String DATE_REFERENCE_PROGRAMME_INDICATEUR_REQUIRED = "DATE_REFERENCE_PROGRAMME_INDICATEUR_REQUIRED";
    public static final String VALEUR_REFERENCE_PROGRAMME_INDICATEUR_REQUIRED = "VALEUR_REFERENCE_PROGRAMME_INDICATEUR_REQUIRED";
    public static final String VALEUR_CIBLE_PROGRAMME_INDICATEUR_REQUIRED = "VALEUR_CIBLE_PROGRAMME_INDICATEUR_REQUIRED";
    public static final String FREQUENCE_MESSAGE_PROGRAMME_INDICATEUR_REQUIRED = "FREQUENCE_MESSAGE_PROGRAMME_INDICATEUR_REQUIRED";
    public static final String EXIST_ALREADY = "EXIST_ALREADY";
    public static final String VALUE_EXISTE_FOR_DATE = "VALUE_EXISTE_FOR_DATE";

    public static final String INDICATEUR_ALLREADY_ASSOCIATED = "INDICATEUR_ALLREADY_ASSOCIATED";

    public static final String PARENT_HAS_CHILDREN_SO_CANNOT_BE_DELETE = "PARENT_HAS_CHILDREN_SO_CANNOT_BE_DELETE";

    public static final String MAX_LEVEL_REACHED = "MAX_LEVEL_REACHED";
    public static final String DATE_RANGE_ERROR = "DATE_RANGE_ERROR";
    public static final String DATE_RANGE_ERROR_FOR_VALEUR_INDICATEUR = "DATE_RANGE_ERROR_FOR_VALEUR_INDICATEUR";
    public static final String DATE_RANGE_ERROR_ON_UPDATE = "DATE_RANGE_ERROR_ON_UPDATE";
    public static final String PROGRAMME_DATE_ERROR = "PROGRAMME_DATE_ERROR";
    public static final String PROGRAMME_CASCADE_ABORT_ERROR = "PROGRAMME_CASCADE_ABORT_ERROR";
    public static final String NO_PROFILE_ASSOCIATED_ERROR = "NO_PROFILE_ASSOCIATED_ERROR";
    public static final String ACTION_EXIST_ERROR = "ACTION_EXIST_ERROR";
    public static final String LINE_ALLREADY_USED = "LINE_ALLREADY_USED";
    public static final String RESPONSABLE_REQUIRED_ERROR = "RESPONSABLE_REQUIRED_ERROR";
    public static final String LIGNE_REQUIRED_ERROR = "LIGNE_REQUIRED_ERROR";
    public static final String BUDGET_REQUIRED_ERROR = "BUDGET_REQUIRED_ERROR";
    public static final String FORBIDDEN_ERROR = "FORBIDDEN_ERROR";
    public static final String INDICATEUR_REQUIRED = "INDICATEUR_REQUIRED";
    public static final String EDIT_NOT_ALLOW_FOR_THIS_ETAT = "EDIT_NOT_ALLOW_FOR_THIS_ETAT";
    public static final String USER_PROFIL_NOT_ALLOW_FOR_ATLEAST_ONE_ACTION_OF_THIS_ETAT = "USER_PROFIL_NOT_ALLOW_FOR_ATLEAST_ONE_ACTION_OF_THIS_ETAT";
    public static final String ACTION_NOT_ALLOW_AT_THIS_ETAT = "ACTION_NOT_ALLOW_AT_THIS_ETAT";
    public static final String SEPARATEUR_REQUIRED = "SEPARATEUR_REQUIRED";
    public static final String PROGRAMME_CHANGEMENT_ETAT_ERROR = "PROGRAMME_CHANGEMENT_ETAT_ERROR";
    public static final String ENTITE_GLOBAL_ACTIVATION_FOR_SERVICE_ACTIVATION = "ENTITE_GLOBAL_ACTIVATION_FOR_SERVICE_ACTIVATION";
    public static final String ENTITE_GLOBAL_ACTIVATION_FOR_SERVICE_ABANDON = "ENTITE_GLOBAL_ACTIVATION_FOR_SERVICE_ABANDON";
    public static final String ENTITE_GLOBAL_ACTIVATION_FOR_SERVICE_DELETE = "ENTITE_GLOBAL_ACTIVATION_FOR_SERVICE_DELETE";
    public static final String MSG_CODIFICATION_ENTITE_ORGANISATIONNELLE_GLOBAL_ACTIVATION = "MSG_CODIFICATION_ENTITE_ORGANISATIONNELLE_GLOBAL_ACTIVATION";
    public static final String MSG_CODIFICATION_ENTITE_ORGANISATIONNELLE_GLOBAL_ABANDON = "MSG_CODIFICATION_ENTITE_ORGANISATIONNELLE_GLOBAL_ABANDON";
    public static final String MSG_CODIFICATION_ENTITE_ORGANISATIONNELLE_GLOBAL_DELETE = "MSG_CODIFICATION_ENTITE_ORGANISATIONNELLE_GLOBAL_DELETE";
    public static final String ENTITE_JURIDIQUE_MISSED_LEVEL_ERROR = "ENTITE_JURIDIQUE_MISSED_LEVEL_ERROR";
    public static final String ENTITE_JURIDIQUE_MISSED_LEVEL_BAD_CONFIG = "ENTITE_JURIDIQUE_MISSED_LEVEL_BAD_CONFIG";
    public static final String ENTITE_JURIDIQUE_LEVEL_CONTAINS_DUPLICATE = "ENTITE_JURIDIQUE_LEVEL_CONTAINS_DUPLICATE";
    public static final String ENTITE_ORG_MAX_LEVEL_REACHED = "ENTITE_ORG_MAX_LEVEL_REACHED";
    public static final String MSG_DELETE_ERROR = "MSG_DELETE_ERROR";
    public static final String MSG_CASCADE_DELETE_ERROR = "MSG_CASCADE_DELETE_ERROR";
    public static final String LOI_DATE_ERROR = "LOI_DATE_ERROR";
    public static final String DATE_RANGE_ERROR_FOR_LOI = "DATE_RANGE_ERROR_FOR_LOI";
    public static final String LOI_CHANGEMENT_ETAT_ERROR = "LOI_CHANGEMENT_ETAT_ERROR";
    public static final String LOI_FIN_CYCLE_ERROR = "LOI_FIN_CYCLE_ERROR";

    /*
    * Erreur workflow et cycle de vie 
     */
    public static final String WF_CYCLE_VIE_NO_ACTION = "WORKFLOW_CYCLE_VIE_NO_ACTION_ALLOWED";

//    /**
//     * Constant GESTION : constantes supprimée
//     */
   

    public static final String ETIQUETTE_NOT_EXIST = "ETIQUETTE_NOT_EXIST";
    
    public static final String ENTITE_JURIDIQUE_REQUIRED = "ENTITE_JURIDIQUE_REQUIRED";
    public static final String VAL_GLOBAL_ERROR_CODE = "msg.val";

//    /**
//     * Prevision: constantes supprimée
//     */
}
