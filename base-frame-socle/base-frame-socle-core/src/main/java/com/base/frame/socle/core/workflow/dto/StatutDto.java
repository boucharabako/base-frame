/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

import com.base.frame.socle.core.utils.SocleConstant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Definit les parametres du workflow statut pour les objets de parametrage
 *
 * @author W.KOUWONOU
 * @since 16/06/2018
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class StatutDto {
    
    private String id;
    private String libelle;
    private String ideCode;
    private String libelleStatutSuivant;
    private String ideCodeStatutSuivant;
    private String nextStatutBackColor;
    private String nextStatutTextColor;
    private String statutTextColor;
    private String statutBackColor;
    private boolean editable=true;

    /**
     * Libelle du statut courant
     *
     * @return
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Libelle du statut courant
     *
     * @param libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * code du statut courant
     *
     * @return
     */
    public String getIdeCode() {
        return ideCode;
    }

    /**
     * code du statut courant
     *
     * @param ideCode
     */
    public void setIdeCode(String ideCode) {
        this.ideCode = ideCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * libelle du statut suivant
     *
     * @return
     */
    public String getLibelleStatutSuivant() {
        return libelleStatutSuivant;
    }

    /**
     * libelle du statut suivant
     *
     * @param libelleStatutSuivant
     */
    public void setLibelleStatutSuivant(String libelleStatutSuivant) {
        this.libelleStatutSuivant = libelleStatutSuivant;
    }

    /**
     * ideo code du statut suivant
     *
     * @return
     */
    public String getIdeCodeStatutSuivant() {
        
        return ideCodeStatutSuivant;
    }

    /**
     * ideo code du statut suivant
     *
     * @param ideCodeStatutSuivant
     */
    public void setIdeCodeStatutSuivant(String ideCodeStatutSuivant) {
        this.ideCodeStatutSuivant = ideCodeStatutSuivant;
    }

    public void setNextStatutBackColor(String nextStatutBackColor) {
        this.nextStatutBackColor = nextStatutBackColor;
    }

    public void setNextStatutTextColor(String nextStatutTextColor) {
        this.nextStatutTextColor = nextStatutTextColor;
    }

    public void setStatutTextColor(String statutTextColor) {
        this.statutTextColor = statutTextColor;
    }

    public void setStatutBackColor(String statutBackColor) {
        this.statutBackColor = statutBackColor;
    }

    /**
     * @author W.KOUWONOU
     * @since 16/06/2018
     *
     * Recherche la couleur du fond de l'action courante
     *
     * @return code couleur
     */
    public String getStatutBackColor() {
        switch (this.ideCode) {
            case SocleConstant.STATUT_PARAM_EN_SAISIE:
                return SocleConstant.COLOR_EN_SAISIE;
            case SocleConstant.STATUT_PARAM_ACTIF:
                return SocleConstant.COLOR_ACTIVER;
            case SocleConstant.STATUT_PARAM_OBSOLETE:
                return SocleConstant.COLOR_ABANDONNER;
            default:
                return "";
        }
    }

    /**
     * @author W.KOUWONOU
     * @since 16/06/2018
     *
     * Recherche la couleur du texte de l'action courante
     *
     * @return code couleur
     */
    public String getStatutTextColor() {
        switch (this.ideCode) {
            case SocleConstant.STATUT_PARAM_ACTIF:
                return SocleConstant.COLOR_TEXT_ACTIVER;
            case SocleConstant.STATUT_PARAM_OBSOLETE:
                return SocleConstant.COLOR_TEXT_ABANDONNER;
            default:
                return SocleConstant.COLOR_WHITE;
        }
    }

    /**
     * @author W.KOUWONOU
     * @since 16/06/2018
     *
     * Recherche la couleur du fond de l'action suivante
     *
     * @return code couleur
     */
    public String getNextStatutBackColor() {
        if (this.ideCode != null && !this.ideCode.isEmpty()) {
            if (ideCode.equals(SocleConstant.STATUT_PARAM_EN_SAISIE)) {
                return SocleConstant.COLOR_ACTIVER;
            }
            if (ideCode.equals(SocleConstant.STATUT_PARAM_ACTIF)) {
                return SocleConstant.COLOR_ABANDONNER;
            }

        }
        return "";
    }

    /**
     * @author W.KOUWONOU
     * @since 16/06/2018
     *
     * Recherche la couleur du texte de l'action suivante
     *
     * @return code couleur
     */
    public String getNextStatutTextColor() {
        if (this.ideCode != null && !this.ideCode.isEmpty()) {
            if (ideCode.equals(SocleConstant.STATUT_PARAM_ACTIF)) {
                return SocleConstant.COLOR_TEXT_ABANDONNER;
            }
            if (ideCode.equals(SocleConstant.STATUT_PARAM_EN_SAISIE)) {
                return SocleConstant.COLOR_TEXT_ACTIVER;
            } else {
                return SocleConstant.COLOR_WHITE;
            }
        }
        return "";
    }
    
    public  String getNextStatutIdeCode() {
        if (ideCode.equals(SocleConstant.STATUT_PARAM_EN_SAISIE)) {
            return SocleConstant.STATUT_PARAM_ACTIF;
        }
        if (ideCode.equals(SocleConstant.STATUT_PARAM_ACTIF)) {
            return SocleConstant.STATUT_PARAM_OBSOLETE;
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "StatutDto{" + "id=" + id + ", libelle=" + libelle + ", ideCode=" + ideCode + ", libelleStatutSuivant=" + libelleStatutSuivant + ", ideCodeStatutSuivant=" + ideCodeStatutSuivant + ", nextStatutBackColor=" + nextStatutBackColor + ", nextStatutTextColor=" + nextStatutTextColor + ", statutTextColor=" + statutTextColor + ", statutBackColor=" + statutBackColor + ", editable=" + editable + '}';
    }

   

    public boolean getEditable() {
        
        return  ideCode.equals(SocleConstant.STATUT_PARAM_EN_SAISIE);
       
    }
//    public boolean getEditable() {
//        
//        return  ideCode.equals(SocleConstant.STATUT_PARAM_EN_SAISIE)||
//                ideCode.equals(SocleConstant.STATUT_PARAM_ACTIF);
//       
//    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    
}
