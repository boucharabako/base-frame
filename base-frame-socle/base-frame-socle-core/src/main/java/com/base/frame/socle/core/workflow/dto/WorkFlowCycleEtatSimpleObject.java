/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

import com.base.frame.socle.core.workflow.entity.Etat;


/**
 * abs-bud-nomenclature
 *
 * @author Pierre NGS creée le 4 sept. 2018 17:56:36
 */
public class WorkFlowCycleEtatSimpleObject {

    private String idEtat;
    private String codeEtat;
    private String libelleEtat;
    private String codeCouleur;
    private String codeCouleurText;
    private String libelleWorkFlow;
    private boolean isEtatValidation = false;

    public WorkFlowCycleEtatSimpleObject() {
    }

    public WorkFlowCycleEtatSimpleObject(String idEtat, String libelleEtat, String codeCouleur, String codeCouleurText) {
        this.idEtat = idEtat;
        this.libelleEtat = libelleEtat;
        this.codeCouleur = codeCouleur;
        this.codeCouleurText = codeCouleurText;
    }

    public WorkFlowCycleEtatSimpleObject(String idEtat, String libelleEtat, String codeCouleur, String codeCouleurText, boolean isValidationStep) {
        this.idEtat = idEtat;
        this.libelleEtat = libelleEtat;
        this.codeCouleur = codeCouleur;
        this.codeCouleurText = codeCouleurText;
        this.isEtatValidation = isValidationStep;
    }

    public WorkFlowCycleEtatSimpleObject(Etat etat) {
        this(etat.getId(), etat.getLibelleEtat(),
                etat.getCodeCouleur(), etat.getCodeCouleurText());
        this.codeEtat = etat.getCodeEtat();
    }

    public WorkFlowCycleEtatSimpleObject(String idEtat, String libelleEtat, String codeCouleur, String codeCouleurText, String libelleWorkFlow) {
        this.idEtat = idEtat;
        this.libelleEtat = libelleEtat;
        this.codeCouleur = codeCouleur;
        this.codeCouleurText = codeCouleurText;
        this.libelleWorkFlow = libelleWorkFlow;
    }

    public String getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(String idEtat) {
        this.idEtat = idEtat;
    }

    public String getLibelleEtat() {
        return libelleEtat;
    }

    public void setLibelleEtat(String libelleEtat) {
        this.libelleEtat = libelleEtat;
    }

    public String getCodeCouleur() {
        return codeCouleur;
    }

    public void setCodeCouleur(String codeCouleur) {
        this.codeCouleur = codeCouleur;
    }

    public String getCodeCouleurText() {
        return codeCouleurText;
    }

    public void setCodeCouleurText(String codeCouleurText) {
        this.codeCouleurText = codeCouleurText;
    }

    public String getLibelleWorkFlow() {
        return libelleWorkFlow;
    }

    public void setLibelleWorkFlow(String libelleWorkFlow) {
        this.libelleWorkFlow = libelleWorkFlow;
    }

    public String getCodeEtat() {
        return codeEtat;
    }

    public void setCodeEtat(String codeEtat) {
        this.codeEtat = codeEtat;
    }

    public boolean isIsEtatValidation() {
        return isEtatValidation;
    }

    public void setIsEtatValidation(boolean isEtatValidation) {
        this.isEtatValidation = isEtatValidation;
    }

    @Override
    public String toString() {
        return "WorkFlowCycleEtatSimpleObject{" + "idEtat=" + idEtat + ", codeEtat=" + codeEtat + ", libelleEtat=" + libelleEtat + ", codeCouleur=" + codeCouleur + ", codeCouleurText=" + codeCouleurText + ", libelleWorkFlow=" + libelleWorkFlow + '}';
    }

}
