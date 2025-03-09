package com.base.frame.socle.core.codification.utils;

import java.io.Serializable;

/**
 *
 * @author william
 * @since 06/03/2018
 */
public class CodificationConstant implements Serializable {

    public static final String SCHEMA = "socle_progiciel";

    public static final int COLUMN_ID_COLUMN_LENTH = 50;

    public static final int COLUMN_GENERATED_ID_COLUMN_LENTH = 40;
    public static final int COLUMN_LABEL_COLUMN_LENTH = 200;
    public static final int COLUMN_DESCRIPTION_COLUMN_PATH_LENTH = 250;
    public static final int COLUMN_DESCRIPTION_COLUMN_EXTENSION_LENTH = 15;
    public static final int COLUMN_DESCRIPTION_COLUMN_LENTH = 500;
    public static final int COLUMN_CONTENT_COLUMN_LENTH = 1024;

    public static final String PARAM_LINGUSTIQUE = "CfgCodeLang";
    public static final String PARAM_SEPARATEUR_MILLER = "cfgCodeMilSep";
    public static final String PARAM_DECIMAL_CODE = "cfgCodeDecSep";
    public static final String PAYS = "cfgCodePays";

    /**
     * codification porté
     */
    public static final String PORTEE = "PORTEE";
    public static final String PORTEE_SYSTEME = "S";
    public static final String PORTEE_LOCALE = "L";
    public static final String PORTEE_NATIONAL = "N";
    /**
     * Liste correspondant à OUI et à NON
     */
    public static final String OUI_NON = "OUI_NON";
    public static final String OUI_NON_OUI = "O";
    public static final String OUI_NON_NON = "N";
    public static final String OUI_NON_OUI_IDE_CODE = "4";
    public static final String OUI_NON_NON_IDE_CODE = "5";

    /**
     * Liste correspondant aux types de codifications
     */
    public static final String TYPE_CODIF = "TYPE_CODIF";
    public static final String TYPE_CODIF_LISTE = "L";
    public static final String TYPE_CODIF_PARAMETRE = "P";
    public static final String TYPE_CODIF_ETIQUETTE = "E";
    public static final String TYPE_CODIF_CLASSEUR = "C";
    /**
     * Liste correspondant aux objets
     */
    public static final String CONCEPT_METIER = "CONCEPT_METIER";
    public static final String CONCEPT_METIER_LIGNE_BUDGETAIRE = "LB";
    public static final String CONCEPT_METIER_COMPTE_PLAN_COMPTABLE_GENERAL = "CPT";
    public static final String CONCEPT_METIER_TIERS = "TR";
    public static final String CONCEPT_METIER_CODIFICATION = "CDF";
    public static final String CONCEPT_METIER_STRUCTURE_DE_CONTROLE = "SDC";
    public static final String CONCEPT_METIER_WORFLOW = "WKL";
    public static final String CONCEPT_METIER_PROPRIETE_ETENDU = "PET";
    public static final String CONCEPT_METIER_EXTENSION = "EXT";

    public static final String CONCEPT_METIER_MODEL_ATTRIBUTE = "conceptMetier";
    public static final String CODE_FONCTION_MODEL_ATTRIBUTE = "codeFonction";
    public static final String CONCEPT_METIER_SITE_EXPLOITATION = "SITE_EXPLOITATION";

    /**
     * Liste des domaine fonctionnels
     */
    public static final String DOMAINES = "DOMAINES";
    public static final String DOMAINES_COMPTABILITE_GENERALE = "CGE";
    public static final String DOMAINES_CIRCUIT_DEPENSE = "CDE";
    public static final String DOMAINES_CIRCUIT_RECETTE = "CRE";
    public static final String DOMAINES_PREVISION_BUDGETAIRE = "PBU";
    public static final String DOMAINES_EXECUTION_BUDGETAIRE = "EBU";
    public static final String DOMAINES_SOCLE_PROGICIEL = "SPR";
    public static final String DOMAINES_MESSAGERIE_ECHANGE_DONNEE = "MED";
    public static final String DOMAINES_WORKFLOW = "WKF";
    public static final String DOMAINES_BUDGET = "BUD";
    public static final String DOMAINES_TIERS = "TRS";
    public static final String DOMAINES_REFERENTIEL_TRANSVERSE = "RET";
    /**
     * Liste corespondant aux statuts des parametres
     */
   // public static final CodifList STATUT_PARAM_EN_SAISIE = "";
    public static final String STATUT_PARAM = "STATUT_PARAM";
    public static final String STATUT_PARAM_EN_SAISIE = "1";
    public static final String STATUT_PARAM_ACTIF = "2";
    public static final String STATUT_PARAM_OBSOLETE = "3";
    
    public static final String ID_STATUT_PARAM_EN_SAISIE = "19";
    public static final String ID_STATUT_PARAM_ACTIF = "20";
    public static final String ID_STATUT_PARAM_OBSOLETE = "21";

    /**
     * Liste corespondant aux statuts des parametres
     */
    public static final String ACTION_PARAM = "ACTIONS_PARAM";

    public static final String ACTION_PARAM_ACTIVER = "1";
    public static final String ACTION_PARAM_ABANDONNER = "2";

    /**
     * COdification corespondant aux actionss sur un ecran
     */
    public static final String ACTIONS_ECRAN = "ACTIONS_ECRAN";
    public static final String ACTIONS_ECRAN_LECTURE = "0";
    public static final String ACTIONS_ECRAN_SAISIR = "1";
    public static final String ACTIONS_ECRAN_APPROBATION = "2";
    public static final String ACTIONS_ECRAN_VALIDATION = "3";
    public static final String ACTIONS_ECRAN_ADMINISTRATION = "99";
    /**
     * codification context minimum
     */
    public static final String CTX_MINIMUM = "CTX_MINIMUM";
    public static final String CTX_MINIMUM_AUCUN = "A";
    public static final String CTX_MINIMUM_POST_IDENTIFIE = "P";
    public static final String CTX_MINIMUM_GESTION = "G";
    public static final String CTX_MINIMUM_JOURNEE_COMPTABLE = "J";
    public static final String CTX_MINIMUM_ROLE_METIER = "R";
    public static final String CTX_MINIMUM_ENTITE_JURIDIQUE = "E";

    /**
     * Mode de modification d'une liste
     */
    public static final String MODIF_CODIF = "MODIF_CODIF";
    public static final String MODIF_CODIF_ALTERATION = "A";
    public static final String MODIF_CODIF_EXTENSION = "E";
    public static final String MODIF_CODIF_NON_MODIFIABLE = "N";

    /**
     * Etat des parametres
     */
    public static final String ETAT_PARAM = "ETAT_PARAM";
    public static final String ETAT_PARAM_ACTIVER = "1";
    public static final String ETAT_PARAM_DESACTIVER = "2";

    /**
     * Liste des langues
     */
    public static final String LANGUE = "LANGUE";
    public static final String LANGUE_FR = "FR";
    /**
     * Liste des langues
     */
    public static final String ERROR_API_MODEL_ATTRIBUTE = "apiError";

    /**
     * Errors types
     */
    public static final String SEVERITY_INFO = "INFO";
    public static final String SEVERITY_WARN = "WARN";
    public static final String SEVERITY_ERROR = "ERROR";

    /**
     * Type de donnée
     */
    public static final String TYPE_DONNEE = "TYPE_DONNEE";
    public static final String TYPE_DONNEE_NOMBRE = "N";
    public static final String TYPE_DONNEE_DATE = "D";
    public static final String TYPE_DONNEE_ALPHA_NUMERIQUE = "X";
    public static final String TYPE_DONNEE_ALPHABETIQUE = "A";
    public static final String TYPE_DONNEE_LISTE_CONTROLE = "L";
    public static final String TYPE_DONNEE_LISTE_MULTIPLE = "M";
    public static final String TYPE_DONNEE_BIGDECIMAL = "B";
    public static final String TYPE_DONNEE_ENTITE = "Z";
    /**
     * Type de donnée
     */
    public static final String DONNEE_APLHANUM = "DONNEE_APLHANUM";
    public static final String DONNEE_APLHANUM_NOMBRE = "N";
    public static final String DONNEE_APLHANUM_ALPHA_NUMERIQUE = "X";
    public static final String DONNEE_APLHANUM_ALPHABETIQUE = "A";
    public static final String DONNEE_APLHANUM_LISTE_DE_CONTROLE = "L";
    public static final String DONNEE_APLHANUM_LISTE_CIBLEE = "C";
    public static final String DONNEE_APLHANUM_CLASSIFICATION = "CL";

    public static final String ALPHA_NUM_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String F_INFO = "F_INFO";
    public static final String F_ERROR = "F_ERROR";
    public static final String F_ERROR_DELETE = "F_ERROR_DELETE";
    public static final String F_REQUIERD = "F_REQUIERD";
    public static final String F_INTERVAL = "F_INTERVAL";
    public static final String F_EXIST = "F_EXIST";
    public static final String F_PLUS_PARAMETRE_LICENCE = "F_PLUS_PARAMETRE_LICENCE";
    public static final String LIVRAISON_NOT_EXIST = "LIVRAISON_INCORRECTE";
    public static final String REF_TRANS_NOT_EXIST = "REF_TRANS_INCORRECTE";
    public static final String F_REGEX = "F_REGEX";
    public static final String F_INVALIDE = "F_INVALIDE";
    public static final String F_SAME_VALUES_NOT_ACCEPTED = "F_SAME_VALUES_NOT_ACCEPTED";

    public static final String COLOR_WHITE = "#ffffff";
    public static final String COLOR_BLACK = "#000000";
    public static final String COLOR_ACTIVER = "#558B2F";
    public static final String COLOR_ABANDONNER = "#757575";
    public static final String COLOR_EN_SAISIE = "#757575";
    public static final String COLOR_TEXT_ACTIVER = "#FFFFFF";
    public static final String COLOR_TEXT_ABANDONNER = "#FFFFFF";

    /*
     * Nature event
     */
    public static final String NATURE_EVENT = "CODE_NATURE_EVENT";
    public static final String NATURE_EVENT_ACTION = "A";
    public static final String NATURE_EVENT_EVENEMENT = "E";

    public static final String CHANGEMENT_STATUT_PARAM = "CHANGEMENT_DE_STATUT";
    public static final String CREATION_OBJET = "CREATION_OBJET";

    public static final String F_ERROR_SIZE_SOUS_ZONE = "F_ERROR_SIZE_SOUS_ZONE";

    /*
    *Type de contribution
     */
    public static final String TYPE_CONTRIBUTEUR = "TYPE_CONTRIBUTEURS";
    public static final String TYPE_CONTRIBUTEUR_SYSTEME = "S";
    public static final String TYPE_CONTRIBUTEUR_TIERS = "A";

    public static final int LONGEUR_MAX_CODE_INPUT = 15;
    public static final int LONGEUR_MAX_CODE_AUTO = 40;
    public static final int LONGEUR_MAX_DESCRIPTION = 500;
    public static final int LONGEUR_MAX_LIBELLE = 100;
    public static final int LONGEUR_MAX_CONTENU_TYPE = 225;

    public static final String WF_REFERENTIEL_EVENEMENTS = "WF_REFERENTIEL_EVENEMENTS";
    public static final String WF_REFERENTIEL_EVENEMENTS_RETOUR_ETAT_PRECEDENT = "RETR";
    public static final String WF_REFERENTIEL_EVENEMENTS_CHANGEMENT_BLOCAGE_ETAT = "BLEC";
    public static final String WF_REFERENTIEL_EVENEMENTS_SIGNATURE_ELECTRONIQUE = "SIEL";
    public static final String WF_REFERENTIEL_EVENEMENTS_COMPTABILISATION = "COMP";

    public static final String EVENT_STATUS = "EVENT_STATUS";
    public static final String EVENT_STATUS_NOUVEL = "NEW";
    public static final String EVENT_STATUS_ENCOURS = "ENT";
    public static final String EVENT_STATUS_TRAITE = "TRT";

    /**
     * DATA KEY
     */
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

    public static final String ETAT_NULL = "ETAT_NULL";
    public static final String ACTION_NULL = "ACTION_NULL";
    public static final String EXECUTOR_NULL = "EXECUTOR_NULL";
    public static final String EXECUTION_ERROR = "EXECUTION_ERROR";
    public static final String ETAT_ACTION_DIFFERENT = "ETAT_ACTION_DIFFERENT";
    public static final String USER_NOT_AUTHORISED = "USER_NOT_AUTHORISED";
    public static final String USERNAME_EXEISTED = "NOM_UTILISATEUR_EXISTE";
    public static final String USERNAME_REQUIERED = "NOM_UTILISATEUR_OBLIGATOIRE";
    public static final String USERNAME_NOT_SPACE = "NOM_UTILISATEUR_SANS_ESPACE";
    public static final String EMAIL_REQUIRED = "EMAIL_OBLIGATOIRE";
    public static final String EMAIL_INCORRECT = "EMAIL_INCORRECT";
    public static final String NOM_OBLIGATOIRE = "NOM_OBLIGATOIRE";
    public static final String PRENOM_OBLIGATOIRE = "PRENOM_OBLIGATOIRE";
    public static final String MOT_DE_PASSE_OBLIGATOIRE = "MOT_DE_PASSE_OBLIGATOIRE";
    public static final String MOT_DE_PASSE_SEPT_CARACTERES = "MOT_DE_PASSE_SEPT_CARACTERES";
    public static final String MOT_DE_PASSE_CONFIRMATION = "MOT_DE_PASSE_CONFIRMATION";
    public static final String MOT_DE_PASSE_DIFFERENT = "MOT_DE_PASSE_DIFFERENT";
    public static final String PROFIL_PAS_CHOISI = "PROFIL_PAS_CHOISI";
    public static final String ALL_ARG_REQUIRED = "ALL_ARG_REQUIRED";
    public static final String ALPHA_NUMERIQUE_REGEX = "(\\w|-|_)+";

    public static final String WKFL_FONCTION_INVALIDE = "WKFL_FONCTION_INVALIDE";
    public static final String UNKNOWN_EXCEPTION = "UNKNOWN_EXCEPTION";
    public static final String WKFL_CONCEPT_METIER_NOT_NULL = "WKFL_CONCEPT_METIER_NOT_NULL";
    public static final String WKFL_IDENTIFIANT_OBJECT_NOT_NULL = "WKFL_IDENTIFIANT_OBJECT_NOT_NULL";
    public static final String WKFL_IDENTIFIANT_WORKFLOW_NOT_NULL = "WKFL_IDENTIFIANT_WORKFLOW_NOT_NULL";
    public static final String WKFL_WORKFLOW_NOT_EXISTE = "WKFL_WORKFLOW_NOT_EXISTE";
    public static final String WKFL_STATUT_WORKFLOW_NOT_ACTIF = "WKFL_STATUT_WORKFLOW_NOT_ACTIF";
    public static final String WKFL_ETAT_PAS_ACTION_SUIVANTE = "WKFL_ETAT_PAS_ACTION_SUIVANTE";
    public static final String WKFL_NOT_SUPPORTED_YET = "WKFL_NOT_SUPPORTED_YET";
    public static final String WKFL_USER_REQUIRED = "WKFL_USER_REQUIRED";
    public static final String WKFL_ERROR = "WKFL_ERROR";

    /**
     * Information de transition
     */
    public static final String INFO_TRANSITION = "INFO_TRANSITION";
    public static final String INFO_TRANSITION_SANS = "S";
    public static final String INFO_TRANSITION_BASIQUE = "B";
    public static final String INFO_TRANSITION_ETENDU = "E";

    public static final String ID_TRANSITION_ETENDU = "141.1.0.2";
    /**
     * regles fonftion classification
     */
    public static final String REGLE_CLASSIF = "REG";
    public static final String WKF_NOTSECURE = "WKF_NOTSECURE";
    public static final String EXTENSION_OBLIGATOIRE = "Les extensions sont obligatoires";
    public static final String CODE_EXISTE = "Le code exixte";

    public static final String GROUPE_PREFIX = "GROUP.$";
    public static final String USER_PREFIX = "USER.$";
    
    public final static String ANONYME_USER = "anonyme_user";
    public final static String ANONYME = "anonyme";
    public static final String NUMERIC_CONSTANT = "0123456789";
    
    
    public static final String CONCEPT_METIER_PROFIL_USERS = "PROUSER";
    
        /**
     * Les types de fonction
     */
    public static final String TYPE_FONCTION = "TYPE_FONCTION";
    public static final String TYPE_FONCTION_TB = "tf_0001"; //Tratement batch
    public static final String TYPE_FONCTION_TI = "tf_0002"; //Traitement Inter-actif
    public static final String TYPE_FONCTION_SI = "tf_0003";//Sortie imprimable
    public static final String TYPE_FONCTION_EI = "tf_0004";//Echanges instantannees
    public static final String TYPE_FONCTION_ED = "tf_0005"; //Extraction de donnees
}
