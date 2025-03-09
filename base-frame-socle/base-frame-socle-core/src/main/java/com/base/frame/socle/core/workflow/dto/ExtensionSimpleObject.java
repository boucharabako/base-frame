///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.utils.CodificationConstant;
//import java.util.List;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import javax.persistence.Transient;
//
///**
// * abs-bud-nomenclature
// *
// * @author Pierre NGS creée le 26 juil. 2018 23:02:55
// */
//public class ExtensionSimpleObject {
//
//    private String id;
//    private String libelle;
//    private String valeur;
//    private String libelleValeur;
//    private String typeDonnee;
//    private boolean required;
//    private boolean typeDonneListDeControle = false;
//    private boolean typeDonneClassification = false;
//    List<IdLabelObject> listDeControle;
//    private IdLabelObject valeurLDC;
//    private boolean codeListDeControleIsOUI_NON = false;
//
//    List<IdLabelObject> classification;
//    private IdLabelObject valeurCLA;
//    private String libelleClassification;
//    private boolean typeDonneListDeControleMultiple = false;
//    private String idClassification;
//
//    @Transient
//    @JsonProperty
//    private boolean hidethis = true;
//    @Transient
//    @JsonProperty
//    private boolean hisSelectize = false;
//    @Transient
//    @JsonProperty
//    private boolean hide = true;
//    private int hauteur = 1;
//    private boolean cochet = false;
//    
//    private boolean inputDisabled = false;
//
//    public ExtensionSimpleObject() {
//    }
//
//    public ExtensionSimpleObject(String libelle, String valeur) {
//        this.libelle = libelle;
//        this.valeur = valeur;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getLibelle() {
//        return libelle;
//    }
//
//    public void setLibelle(String libelle) {
//        this.libelle = libelle;
//    }
//
//    public String getValeur() {
//        return valeur;
//    }
//
//    public void setValeur(String valeur) {
//        this.valeur = valeur;
//    }
//
//    public String getTypeDonnee() {
//        return typeDonnee;
//    }
//
//    public void setTypeDonnee(String typeDonnee) {
//        this.typeDonnee = typeDonnee;
//    }
//
//    public boolean getRequired() {
//        return required;
//    }
//
//    public void setRequired(boolean required) {
//        this.required = required;
//    }
//
//    public boolean getTypeDonneListDeControle() {
//        return typeDonneListDeControle;
//    }
//
//    public void setTypeDonneListDeControle(boolean typeDonneListDeControle) {
//        this.typeDonneListDeControle = typeDonneListDeControle;
//    }
//
//    public List<IdLabelObject> getListDeControle() {
//        return listDeControle;
//    }
//
//    public void setListDeControle(List<IdLabelObject> listDeControle) {
//        this.listDeControle = listDeControle;
//    }
//
//    public IdLabelObject getValeurLDC() {
//        return valeurLDC;
//    }
//
//    public void setValeurLDC(IdLabelObject valeurLDC) {
//        this.valeurLDC = valeurLDC;
//    }
//
//    public List<IdLabelObject> getClassification() {
//        return classification;
//    }
//
//    public void setClassification(List<IdLabelObject> classification) {
//        this.classification = classification;
//    }
//
//    public IdLabelObject getValeurCLA() {
//        return valeurCLA;
//    }
//
//    public void setValeurCLA(IdLabelObject valeurCLA) {
//        this.valeurCLA = valeurCLA;
//    }
//
//    public boolean isTypeDonneClassification() {
//        return typeDonneClassification;
//    }
//
//    public void setTypeDonneClassification(boolean typeDonneClassification) {
//        this.typeDonneClassification = typeDonneClassification;
//    }
//
//    public String getLibelleClassification() {
//        return libelleClassification;
//    }
//
//    public void setLibelleClassification(String libelleClassification) {
//        this.libelleClassification = libelleClassification;
//    }
//
//    public boolean isHidethis() {
//        return hidethis;
//    }
//
//    public boolean getCodeListDeControleIsOUI_NON() {
//        return codeListDeControleIsOUI_NON;
//    }
//
//    public void setCodeListDeControleIsOUI_NON(boolean codeListDeControleIsOUI_NON) {
//        this.codeListDeControleIsOUI_NON = codeListDeControleIsOUI_NON;
//    }
//
//    public void setHidethis(boolean hidethis) {
//        this.hidethis = hidethis;
//    }
//
//    public boolean getTypeDonneListDeControleMultiple() {
//        return typeDonneListDeControleMultiple;
//    }
//
//    public void setTypeDonneListDeControleMultiple(boolean typeDonneListDeControleMultiple) {
//        this.typeDonneListDeControleMultiple = typeDonneListDeControleMultiple;
//    }
//
//    public int getHauteur() {
//
//        if (getTypeDonnee() != null && getTypeDonnee().equals(CodificationConstant.TYPE_DONNEE_LISTE_MULTIPLE)) {
//            setHauteur(getListDeControle().size());
//        }
//        return hauteur;
//    }
//
//    public void setHauteur(int hauteur) {
//        this.hauteur = hauteur;
//    }
//
//    public boolean isHide() {
//        return hide;
//    }
//
//    public void setHide(boolean hide) {
//        this.hide = hide;
//    }
//
//    public String getLibelleValeur() {
//        return libelleValeur;
//    }
//
//    public void setLibelleValeur(String libelleValeur) {
//        this.libelleValeur = libelleValeur;
//    }
//
//    public boolean isHisSelectize() {
//        return hisSelectize;
//    }
//
//    public void setHisSelectize(boolean hisSelectize) {
//        this.hisSelectize = hisSelectize;
//    }
//
//    public boolean isCochet() {
//        return cochet;
//    }
//
//    public void setCochet(boolean cochet) {
//        this.cochet = cochet;
//    }
//
//    public boolean isInputDisabled() {
//        return inputDisabled;
//    }
//
//    public void setInputDisabled(boolean inputDisabled) {
//        this.inputDisabled = inputDisabled;
//    }
//
//    public String getIdClassification() {
//        return idClassification;
//    }
//
//    public void setIdClassification(String idClassification) {
//        this.idClassification = idClassification;
//    }
//
//    @Override
//    public String toString() {
//        return "ExtensionSimpleObject{" + "id=" + id + ", libelle=" + libelle + ", valeur=" + valeur + ", listDeControle=" + listDeControle + ", valeurLDC=" + valeurLDC + '}';
//    }
//
//}
