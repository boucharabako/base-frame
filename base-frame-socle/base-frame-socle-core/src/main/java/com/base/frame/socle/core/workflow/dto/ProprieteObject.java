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
// * @author KOMLA
// */
//public class ProprieteObject {
//
//    private String id;
//    @NotNull(message = CodificationConstant.F_REQUIERD)
//    private String ide;
//    @NotNull(message = CodificationConstant.F_REQUIERD)
//    private String libelle;
//    @NotNull(message = CodificationConstant.F_REQUIERD)
//    private String caption;
//    private String codeLangue;
//    private StatutDto statut;
// private LibelleDTO etat;
//    private ArrayList<LibelleDTO> tracking;
//    private boolean disabledEdit = true;
//    private boolean isEnSaisie = false;
//    private boolean isActif = false;
//    private boolean isObsolete =false;
//
//    public ProprieteObject() {
//    }
//
//    public ProprieteObject(String id, String ide, String libelle, String caption) {
//        this.id = id;
//        this.ide = ide;
//        this.libelle = libelle;
//        this.caption = caption;
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
//    public String getIde() {
//        return ide;
//    }
//
//    public void setIde(String ide) {
//        this.ide = ide;
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
//    public String getCaption() {
//        return caption;
//    }
//
//    public void setCaption(String caption) {
//        this.caption = caption;
//    }
//
//    public String getCodeLangue() {
//        return codeLangue;
//    }
//
//    public void setCodeLangue(String codeLangue) {
//        this.codeLangue = codeLangue;
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
//
//
//
//    public LibelleDTO getEtat() {
//        return etat;
//    }
//
//    public void setEtat(LibelleDTO etat) {
//        this.etat = etat;
//    }
//
//
//
//    public boolean isDisabledEdit() {
//        return disabledEdit;
//    }
//
//    public void setDisabledEdit(boolean disabledEdit) {
//        this.disabledEdit = disabledEdit;
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
//    @Override
//    public String toString() {
//        return "ProprieteObject{" + "id=" + id + ", ide=" + ide + ", libelle=" + libelle + ", caption=" + caption + ", codeLangue=" + codeLangue + ", statut=" + statut + ", etat=" + etat + ", tracking=" + tracking + ", disabledEdit=" + disabledEdit + ", isEnSaisie=" + isEnSaisie + ", isActif=" + isActif + ", isObsolete=" + isObsolete + '}';
//    }
//
//   
//
// 
//
//    
//
//    
//
//    
//    
//
//}
//
//
//
//
