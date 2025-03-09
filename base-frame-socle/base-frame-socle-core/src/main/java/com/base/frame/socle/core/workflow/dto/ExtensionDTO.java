///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.entities.Extension;
//import java.util.List;
//
///**
// * abs-bud-nomenclature
// *
// * @author Pierre NGS creée le 26 juil. 2018 23:02:55
// */
//public class ExtensionDTO {
//
//    private String id;
//    private String libelle;
//    private String valeur;
//    private String typeDonnee;
//    private boolean required;
//    private boolean typeDonneListDeControle = false;
//    List<LibelleDTO> listDeControle;
//    private LibelleDTO valeurLDC;
//
//    public ExtensionDTO() {
//    }
//
//    public ExtensionDTO(String id, String libelle, String typeDonnee) {
//        this.id = id;
//        this.libelle = libelle;
//        this.typeDonnee = typeDonnee;
//    }
//
//    public ExtensionDTO(Extension extension) {
//        this(extension.getId(), extension.getPropriete().getCaption(), extension.getTypeDonnee().getIdeCode());
//        
//    }
//
//    public ExtensionDTO(String libelle, String valeur) {
//        this.libelle = libelle;
//        this.valeur = valeur;
//    }
//
//    public ExtensionDTO(String id) {
//        this.id = id;
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
//    public List<LibelleDTO> getListDeControle() {
//        return listDeControle;
//    }
//
//    public void setListDeControle(List<LibelleDTO> listDeControle) {
//        this.listDeControle = listDeControle;
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
//    public LibelleDTO getValeurLDC() {
//        return valeurLDC;
//    }
//
//    public void setValeurLDC(LibelleDTO valeurLDC) {
//        this.valeurLDC = valeurLDC;
//    }
//
//}
