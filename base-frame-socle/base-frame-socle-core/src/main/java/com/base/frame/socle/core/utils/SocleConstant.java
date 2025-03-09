/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.utils;

/**
 *
 * @author Alassani
 */
public class SocleConstant {

    public static final String SCHEMA = "socle";
    public static final String CODIFICATION_ETAT = "PARAM_CODE_ETAT";
    public static final String CODIFICATION_ETAT_ACTIF = "PROFIL001";
    public static final String CODIFICATION_ETAT_INACTIF = "PARAM_LIST_ETAT_IN";

    public static final String CODIFICATION_NIVEAU_HABILITATION = "PARAM_CODE_NVX";
    public static final String CODIFICATION_NIVEAU_HABILITATION_LIRE = "PARAM_NVX_LI";
    public static final String CODIFICATION_NIVEAU_HABILITATION_SAISIR = "PARAM_NVX_SA";
    
    public static final String COLOR_WHITE = "#ffffff";
    public static final String COLOR_BLACK = "#000000";
    public static final String COLOR_ACTIVER = "#558B2F";
    public static final String COLOR_ABANDONNER = "#757575";
    public static final String COLOR_EN_SAISIE = "#757575";
    public static final String COLOR_TEXT_ACTIVER = "#FFFFFF";
    public static final String COLOR_TEXT_ABANDONNER = "#FFFFFF";
    
     /**
     * Liste correspondant à OUI et à NON
     */
    public static final String OUI_NON = "OUI_NON";
    public static final String OUI_NON_OUI = "O";
    public static final String OUI_NON_NON = "N";
    public static final String OUI_NON_OUI_IDE_CODE = "4";
    public static final String OUI_NON_NON_IDE_CODE = "5";
    
    public static final String STATUT_PARAM = "STATUT_PARAM";
    public static final String STATUT_PARAM_EN_SAISIE = "1";
    public static final String STATUT_PARAM_ACTIF = "2";
    public static final String STATUT_PARAM_OBSOLETE = "3";
    
    public static final String ID_STATUT_PARAM_EN_SAISIE = "19";
    public static final String ID_STATUT_PARAM_ACTIF = "20";
    public static final String ID_STATUT_PARAM_OBSOLETE = "21";
    
     public static final String F_REQUIERD = "F_REQUIERD";
     public static final String F_REGEX = "F_REGEX";
     public static final String ALPHA_NUM_REGEX = "^[_'.@A-Za-z0-9-]*$";
     
      /**
     * Liste corespondant aux statuts des parametres
     */
    public static final String ACTION_PARAM = "ACTIONS_PARAM";

    public static final String ACTION_PARAM_ACTIVER = "1";
    public static final String ACTION_PARAM_ABANDONNER = "2";
    
    public static final int GED_ID_COLUMN_LENTH = 20;
    public static final int GED_GENERATED_ID_COLUMN_LENTH = 40;
    public static final int GED_LABEL_COLUMN_LENTH = 100;
    public static final int GED_DESCRIPTION_COLUMN_LENTH = 500;

    public static final String DATE_PATTERN = "dd/MM/yyyy";
    
    public static final String DATA_KEY = "value";

    /**
     * COdification corespondant aux actionss de workflow
     */
    public static final String ACTIONS_WORKFLOW = "AWF";
    public static final String CONCEPT_METIER_GROUPE_EXTENSION = "GET";

    public static final String DOMAINE_BUD = "BUD";
    public static final String DAY_PARAM = "DAY_PARAM";
    public static final String MONTH_PARAM = "MONTH_PARAM";
    public static final String YEAR_PARAM = "YEAR_PARAM";
}
