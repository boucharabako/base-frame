/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.utils;

/**
 *
 * @author NGS_004
 */
public class SprRoleConstantes {

    private SprRoleConstantes() {
        //Do noting
    }

    /**
     * Les roles et les permissions du socle
     */
    public static final String ADMIN = "ADMIN";

    public static final String PARAMETRES_URL = "/parametre/conf/parameter/**";

    /**
     * URL SELLING
     */
    public static final String SELLING_URL = "/errors/conf/selling";
    /**
     * URL du classeur
     */

    public static final String CLASSEUR_URL = "/parametre/conf/classeur";
    public static final String APROPOS_URL = "/apropos";
    public static final String CLASSEUR_API_URL = "/api/parametre/conf/classeur";

    /**
     * codification liste
     */
    public static final String LISTE_URL = "/parametre/conf/listes/**";
    public static final String LISTE_PATH = "/parametre/conf/listes/";

    /**
     * Les etiquettes
     */
    public static final String ETIQUETTE_URL = "/parametre/conf/etiquette";

    /**
     * Les extensions
     */
    public static final String EXTENSION = "CFG_EXTEN_E00";
    public static final String EXTENSION_URL = "/parametre/conf/extension";

    /**
     * Les proprietes etendues
     */
    public static final String PROPRIETE_ETENDUE = "CFG_EXTEN_E01";
    public static final String PROPRIETE_ETENDUE_URL = "/parametre/conf/propriete";

    /**
     * Les workflows
     */
    public static final String WORKFLOW = "CFG_WFL_E00";
    public static final String WORKFLOW_URL = "/parametre/conf/workflow";

    public static final String WORKFLOW_URL2 = "/parametre/conf/workflow";
    public static final String WORKFLOW_ACTION_URL = "/api/parametre/conf/workflow/action";
    public static final String UTILS = "/api/utils";
    public static final String WORKFLOW_API_URL = "/api/parametre/conf/workflow";

    /**
     * Les workflows
     */
    public static final String WORKFLOW_ETAPE = "CFG_WFL_E01";
    public static final String WORKFLOW_ETAPE_API_URL = "parametre/conf/{workflow}/etape";

    /**
     * Habilitation par rapport aux fonctions
     */
    public static final String FC_WORKFLOW = "CFG_WFL_E00";
    public static final String FC_WORKFLOW_LIRE = "CFG_WFL_E00.0";
    public static final String FC_WORKFLOW_SAISIR = "CFG_WFL_E00.1";
    public static final String FC_WORKFLOW_APPROUVER = "CFG_WFL_E00.2";
    public static final String FC_WORKFLOW_VALIDER = "CFG_WFL_E00.3";
    public static final String FC_WORKFLOW_ADMINISTRER = "CFG_WFL_E00.99";

    public static final String FC_WORKFLOW_CYCLE = "CFG_WRKFL_E00_1";
    public static final String FC_WORKFLOW_CYCLE_LIRE = "CFG_WRKFL_E00_1.0";
    public static final String FC_WORKFLOW_CYCLE_SAISIR = "CFG_WRKFL_E00_1.1";
    public static final String FC_WORKFLOW_CYCLE_APPROUVER = "CFG_WRKFL_E00_1.2";
    public static final String FC_WORKFLOW_CYCLE_VALIDER = "CFG_WRKFL_E00_1.3";
    public static final String FC_WORKFLOW_CYCLE_ADMINISTRER = "CFG_WRKFL_E00_1.99";
    public static final String WORKFLOW_CYCLE_URL = "/parametre/conf/workflow-cycle";
    public static final String WORKFLOW_CYCLE_API = "/api/parametre/conf/workflow-cycle";
    public static final String WORKFLOW_CYCLE_VIE_FRAGMENT_API = "/api/parametre/fragment/conf/workflow-cycle";

    public static final String FC_CONFIGURATION_BASE = "CFG_CFGB_E00";
    public static final String FC_CONFIGURATION_BASE_LIRE = "CFG_CFGB_E00.0";
    public static final String FC_CONFIGURATION_BASE_SAISIR = "CFG_CFGB_E00.1";
    public static final String FC_CONFIGURATION_BASE_APPROUVER = "CFG_CFGB_E00.2";
    public static final String FC_CONFIGURATION_BASE_VALIDER = "CFG_CFGB_E00.3";

    public static final String FC_EXTENSION = "CFG_EXTEN_E00";
    public static final String FC_EXTENSION_LIRE = "CFG_EXTEN_E00.0";
    public static final String FC_EXTENSION_SAISIR = "CFG_EXTEN_E00.1";
    public static final String FC_EXTENSION_APPROUVER = "CFG_EXTEN_E00.2";
    public static final String FC_EXTENSION_VALIDER = "CFG_EXTEN_E00.3";
    public static final String FC_EXTENSION_ADMINISTRER = "CFG_EXTEN_E00.99";

    public static final String STRUCTURE_DE_CONTROLE = "CFG_STCTL_E00_1";

    public static final String STRUCTURE_DE_CONTROLE_URL = "/parametre/conf/structurecontrole";
    public static final String FC_STRUCTURE_DE_CONTROLE_LIRE = "CFG_STCTL_E00_1.0";
    public static final String FC_STRUCTURE_DE_CONTROLE_SAISIR = "CFG_STCTL_E00_1.1";
    public static final String FC_STRUCTURE_DE_CONTROLE_APPROUVER = "CFG_STCTL_E00_1.2";
    public static final String FC_STRUCTURE_DE_CONTROLE_VALIDER = "CFG_STCTL_E00_1.3";

    public static final String FC_PARAMETRE = "PARAM";
    public static final String FC_PARAMETRE_CONFIGURATION = "CFG";
    public static final String FC_PARAMETRE_GED = "PARAM_GED";
    public static final String FC_PARAMETRE_RESEAU = "PARAM_RES";
    public static final String FC_PARAMETRE_REFERENTIEL_TRANSVERSE = "PARAM_REFT";
    public static final String FC_PARAMETRE_REFERENTIEL_BUDGETAIRE = "PARAM_REFB";
    public static final String FC_PARAMETRE_REFERENTIEL_COMPTABLE = "PARAM_REFC";
    public static final String FC_PARAMETRE_UTILISATEUR = "PARAM_USER";
    public static final String PROPRIETE_EXTENSION_OBLIGATOIRE = "PROPRIETE_EXTENSION_OBLIGATOIRE";
    public static final String PROPRIETE_EXTENSION_CODE_OBLIGATOIRE = "PROPRIETE_EXTENSION_CODE_OBLIGATOIRE";
    public static final String PROPRIETE_EXTENSION_LIBELLE_OBLIGATOIRE = "PROPRIETE_EXTENSION_LIBELLE_OBLIGATOIRE";
    public static final String PROPRIETE_EXTENSION_LEGENDE_OBLIGATOIRE = "PROPRIETE_EXTENSION_LEGENDE_OBLIGATOIRE";
    public static final String PROPRIETE_EXTENSION_DESCRIPTION_OBLIGATOIRE = "PROPRIETE_EXTENSION_DESCRIPTION_OBLIGATOIRE";
    public static final String PROPRIETE_EXTENSION_TITRE_OBLIGATOIRE = "PROPRIETE_EXTENSION_TITRE_OBLIGATOIRE";
    
    

}
