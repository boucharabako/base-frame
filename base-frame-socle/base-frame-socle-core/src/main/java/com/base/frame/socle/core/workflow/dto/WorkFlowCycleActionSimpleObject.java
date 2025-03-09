/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

/**
 * abs-bud-nomenclature
 *
 * @author Pierre NGS creée le 4 sept. 2018 17:56:36
 */
public class WorkFlowCycleActionSimpleObject {

    private String idAction;
    private String idActionPermise;
    private String codeEtatActionSuivante;
    private String libelleAction;
    private String codeCouleur;
    private String codeCouleurText;
    private Integer rangEtatSuivante;
    private String codeCallback;
    private boolean etatSuivantIsTerminaison;
    private boolean etatSuivantIsEtapeValidation =false;
        private boolean signatureRequired=false;


    public WorkFlowCycleActionSimpleObject() {
    }

    public WorkFlowCycleActionSimpleObject(String idAction, String idActionPermise, String libelleAction, String codeCouleur, String codeCouleurText) {
        this.idAction = idAction;
        this.idActionPermise = idActionPermise;
        this.libelleAction = libelleAction;
        this.codeCouleur = codeCouleur;
        this.codeCouleurText = codeCouleurText;
    }

    public WorkFlowCycleActionSimpleObject(String idAction, String idActionPermise, String codeEtatActionSuivante, String libelleAction, String codeCouleur, String codeCouleurText) {
        this.idAction = idAction;
        this.idActionPermise = idActionPermise;
        this.codeEtatActionSuivante = codeEtatActionSuivante;
        this.libelleAction = libelleAction;
        this.codeCouleur = codeCouleur;
        this.codeCouleurText = codeCouleurText;
    }

    public WorkFlowCycleActionSimpleObject(String idAction, String idActionPermise, String codeEtatActionSuivante, String libelleAction, String codeCouleur, String codeCouleurText, Integer rangEtatSuivante) {
        this.idAction = idAction;
        this.idActionPermise = idActionPermise;
        this.codeEtatActionSuivante = codeEtatActionSuivante;
        this.libelleAction = libelleAction;
        this.codeCouleur = codeCouleur;
        this.codeCouleurText = codeCouleurText;
        this.rangEtatSuivante = rangEtatSuivante;
    }
       public WorkFlowCycleActionSimpleObject(String idAction, String idActionPermise, String codeEtatActionSuivante, String libelleAction, String codeCouleur, String codeCouleurText, Integer rangEtatSuivante, boolean etatSuivantIsEtapeValidation) {
        this.idAction = idAction;
        this.idActionPermise = idActionPermise;
        this.codeEtatActionSuivante = codeEtatActionSuivante;
        this.libelleAction = libelleAction;
        this.codeCouleur = codeCouleur;
        this.codeCouleurText = codeCouleurText;
        this.rangEtatSuivante = rangEtatSuivante;
        this.etatSuivantIsEtapeValidation = etatSuivantIsEtapeValidation;
    }

    public String getIdAction() {
        return idAction;
    }

    public void setIdAction(String idAction) {
        this.idAction = idAction;
    }

    public String getLibelleAction() {
        return libelleAction;
    }

    public void setLibelleAction(String libelleAction) {
        this.libelleAction = libelleAction;
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

    public String getIdActionPermise() {
        return idActionPermise;
    }

    public void setIdActionPermise(String idActionPermise) {
        this.idActionPermise = idActionPermise;
    }

    public String getCodeEtatActionSuivante() {
        return codeEtatActionSuivante;
    }

    public void setCodeEtatActionSuivante(String codeEtatActionSuivante) {
        this.codeEtatActionSuivante = codeEtatActionSuivante;
    }

    public boolean isEtatSuivantIsTerminaison() {
        return etatSuivantIsTerminaison;
    }

    public void setEtatSuivantIsTerminaison(boolean etatSuivantIsTerminaison) {
        this.etatSuivantIsTerminaison = etatSuivantIsTerminaison;
    }

    public Integer getRangEtatSuivante() {
        return rangEtatSuivante;
    }

    public void setRangEtatSuivante(Integer rangEtatSuivante) {
        this.rangEtatSuivante = rangEtatSuivante;
    }

    public String getCodeCallback() {
        return codeCallback;
    }

    public void setCodeCallback(String codeCallback) {
        this.codeCallback = codeCallback;
    }

    public boolean getEtatSuivantIsEtapeValidation() {
        return etatSuivantIsEtapeValidation;
    }

    public void setEtatSuivantIsEtapeValidation(boolean etatSuivantIsEtapeValidation) {
        this.etatSuivantIsEtapeValidation = etatSuivantIsEtapeValidation;
    }

    public boolean isSignatureRequired() {
        return signatureRequired;
    }

    public void setSignatureRequired(boolean signatureRequired) {
        this.signatureRequired = signatureRequired;
    }

    @Override
    public String toString() {
        return "WorkFlowCycleActionSimpleObject{" + "idAction=" + idAction + ", idActionPermise=" + idActionPermise + ", codeEtatActionSuivante=" + codeEtatActionSuivante + ", libelleAction=" + libelleAction + ", codeCouleur=" + codeCouleur + ", codeCouleurText=" + codeCouleurText + '}';
    }
}
