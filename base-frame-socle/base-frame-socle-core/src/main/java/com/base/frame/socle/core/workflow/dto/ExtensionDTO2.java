///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.utils.CodificationConstant;
//import java.util.ArrayList;
//import javax.validation.constraints.NotNull;
//
///**
// *
// * @author Bouchara
// */
//public class ExtensionDTO2 {
//
//    @NotNull(message = CodificationConstant.F_REQUIERD)
//    private String id;
//    @NotNull(message = CodificationConstant.F_REQUIERD)
//    private String propriete;
//    private String proprieteLibelle;
//    @NotNull(message = CodificationConstant.F_REQUIERD)
//    private String typeDonnee;
//    private String typeDonneeLibelle;
//    @NotNull(message = CodificationConstant.F_REQUIERD)
//    private String codeConcept;
//    private String codeConceptLibelle;
//    //@NotNull(message = CodificationConstant.F_REQUIERD)
//    private String classification;
//    private String classificationLibelle;
//    //@NotNull(message = CodificationConstant.F_REQUIERD)
//    private String listeControle;
//    private String listeControleLibelle;
//    private String codeObligation;
//    private Boolean requis = false;
//    private LibelleDTO etat;
//    private ArrayList<LibelleDTO> tracking;
//    private boolean disable = false;
//      private boolean isEnSaisie = false;
//    private boolean isActif = false;
//    private boolean isObsolete = false;
//        private StatutDto statut;
//
//    public ExtensionDTO2() {
//    }
//    
//
//
//    public ExtensionDTO2(String id, String typeDonnee , String propriete,  String listeControle, String classification, String idEtat) {
//        this.id = id;
//        this.propriete = propriete;
//        this.typeDonnee = typeDonnee;
//        this.classification = classification;
//        this.listeControle = listeControle;
//        this.etat = new LibelleDTO(idEtat, idEtat, "");
//        this.tracking = new ArrayList<>();
//    }
//
//    public ExtensionDTO2(String id, String typeDonnee,String typeDonneeLibelle, String propriete,String proprieteLibelle,  String listeControle, String listeControleLibelle,String classification,String classificationLibelle, String idEtat) {
//        this.id = id;
//        this.propriete = propriete;
//        this.typeDonnee = typeDonnee;
//        this.classification = classification;
//        this.listeControle = listeControle;
//        this.proprieteLibelle = proprieteLibelle;
//        this.typeDonneeLibelle = typeDonneeLibelle;
//        this.classificationLibelle = classificationLibelle;
//        this.listeControleLibelle = listeControleLibelle;
//        this.etat = new LibelleDTO(idEtat, idEtat, "");
//        this.tracking = new ArrayList<>();
//    }
//
//    public ExtensionDTO2(String id, String typeDonnee, String propriete,String proprieteLibelle, String listeControle,String classification,String codeConcept, String codeObligation,StatutDto statut) {
//        this.id = id;
//        this.propriete = propriete;
//        this.typeDonnee = typeDonnee;
//        this.classification = classification;
//        this.listeControle = listeControle;
//        this.codeConcept = codeConcept;
//        this.proprieteLibelle = proprieteLibelle;
//        this.codeObligation = codeObligation;
//        this.statut = statut;
//    }
//
//
//    public String getCodeObligation() {
//        return codeObligation;
//    }
//
//    public void setCodeObligation(String codeObligation) {
//        this.codeObligation = codeObligation;
//    }
//
//    public boolean isIsEnSaisie() {
//        return isEnSaisie;
//    }
//
//    public void setIsEnSaisie(boolean isEnSaisie) {
//        this.isEnSaisie = isEnSaisie;
//    }
//
//    public boolean isIsActif() {
//        return isActif;
//    }
//
//    public void setIsActif(boolean isActif) {
//        this.isActif = isActif;
//    }
//
//    public boolean isIsObsolete() {
//        return isObsolete;
//    }
//
//    public void setIsObsolete(boolean isObsolete) {
//        this.isObsolete = isObsolete;
//    }
//
//    public StatutDto getStatut() {
//        return statut;
//    }
//
//    public void setStatut(StatutDto statut) {
//        this.statut = statut;
//    }
//    
//    public String getPropriete() {
//        return propriete;
//    }
//
//    public boolean isDisable() {
//        return disable;
//    }
//
//    public String getTypeDonneeLibelle() {
//        return typeDonneeLibelle;
//    }
//
//    public void setTypeDonneeLibelle(String typeDonneeLibelle) {
//        this.typeDonneeLibelle = typeDonneeLibelle;
//    }
//
//    public void setDisable(boolean disable) {
//        this.disable = disable;
//    }
//
//    public void setPropriete(String propriete) {
//        this.propriete = propriete;
//    }
//
//    public String getProprieteLibelle() {
//        return proprieteLibelle;
//    }
//
//    public void setProprieteLibelle(String proprieteLibelle) {
//        this.proprieteLibelle = proprieteLibelle;
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
//
//    public String getClassification() {
//        return classification;
//    }
//
//    public void setClassification(String classification) {
//        this.classification = classification;
//    }
//
//    public String getClassificationLibelle() {
//        return classificationLibelle;
//    }
//
//    public void setClassificationLibelle(String classificationLibelle) {
//        this.classificationLibelle = classificationLibelle;
//    }
//
//    public String getListeControle() {
//        return listeControle;
//    }
//
//    public void setListeControle(String listeControle) {
//        this.listeControle = listeControle;
//    }
//
//    public String getListeControleLibelle() {
//        return listeControleLibelle;
//    }
//
//    public void setListeControleLibelle(String listeControleLibelle) {
//        this.listeControleLibelle = listeControleLibelle;
//    }
//
//    public Boolean getRequis() {
//        return requis;
//    }
//
//    public void setRequis(Boolean requis) {
//        this.requis = requis;
//    }
//   
//
//    public String getId() {
//        return id;
//    }
//
//    public String getCodeConcept() {
//        return codeConcept;
//    }
//
//    public void setCodeConcept(String codeConcept) {
//        this.codeConcept = codeConcept;
//    }
//
//    public String getCodeConceptLibelle() {
//        return codeConceptLibelle;
//    }
//
//    public void setCodeConceptLibelle(String codeConceptLibelle) {
//        this.codeConceptLibelle = codeConceptLibelle;
//    }
//    
//    
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public LibelleDTO getEtat() {
//        return etat;
//    }
//
//    public void setEtat(LibelleDTO etat) {
//        this.etat = etat;
//    }
//
//    public ArrayList<LibelleDTO> getTracking() {
//        return tracking;
//    }
//
//    public void setTracking(ArrayList<LibelleDTO> tracking) {
//        this.tracking = tracking;
//    }
//
//    @Override
//    public String toString() {
//        return "ExtensionDTO2{" + "id=" + id + ", propriete=" + propriete + ", proprieteLibelle=" + proprieteLibelle + ", typeDonnee=" + typeDonnee + ", typeDonneeLibelle=" + typeDonneeLibelle + ", codeConcept=" + codeConcept + ", codeConceptLibelle=" + codeConceptLibelle + ", classification=" + classification + ", classificationLibelle=" + classificationLibelle + ", listeControle=" + listeControle + ", listeControleLibelle=" + listeControleLibelle + ", codeObligation=" + codeObligation + ", requis=" + requis + ", etat=" + etat + ", tracking=" + tracking + ", disable=" + disable + '}';
//    }
//
//}
