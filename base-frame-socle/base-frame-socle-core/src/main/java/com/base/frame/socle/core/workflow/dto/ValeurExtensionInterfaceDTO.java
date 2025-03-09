///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.entities.ExtensionInterface;
//import java.util.List;
//import java.util.Set;
//
///**
// *
// * @author kouwonou
// */
//public class ValeurExtensionInterfaceDTO {
//
//    private String id;
//    private String idObjet;
//    private String valeur;
//
//    private String extensionId;
//    private String libelle;
//    private Set<String> valeurs;
//    private ExtensionInterface extension;
//    private List<IdLabelObject> list;
//
//    public ValeurExtensionInterfaceDTO() {
//    }
//
//    public ValeurExtensionInterfaceDTO(String id, String idObjet, String valeur, String extensionId, Set<String> valeurs) {
//        this.id = id;
//        this.idObjet = idObjet;
//        this.valeur = valeur;
//        this.extensionId = extensionId;
//        this.valeurs = valeurs;
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
//    
//    
//    public String getIdObjet() {
//        return idObjet;
//    }
//
//    public void setIdObjet(String idObjet) {
//        this.idObjet = idObjet;
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
//    public String getExtensionId() {
//        return extensionId;
//    }
//
//    public void setExtensionId(String extensionId) {
//        this.extensionId = extensionId;
//    }
//
//    public String getLibelle() {
//        if (extension != null && (extension.getImplentation() == null
//                || extension.getImplentation().isEmpty())) {
//            libelle = valeur;
//        }
//        return libelle;
//    }
//
//    public void setLibelle(String libelle) {
//        this.libelle = libelle;
//    }
//
//    public Set<String> getValeurs() {
//        return valeurs;
//    }
//
//    public void setValeurs(Set<String> valeurs) {
//        this.valeurs = valeurs;
//    }
//
//    public ExtensionInterface getExtension() {
//        return extension;
//    }
//
//    public void setExtension(ExtensionInterface extension) {
//        this.extension = extension;
//    }
//
//    public List<IdLabelObject> getList() {
//        return list;
//    }
//
//    public void setList(List<IdLabelObject> list) {
//        this.list = list;
//    }
//
//    @Override
//    public String toString() {
//        return "ValeurExtensionInterfaceDTO{" + "id=" + id + ", idObjet=" + idObjet + ", valeur=" + valeur + ", extensionId=" + extensionId + ", libelle=" + libelle + ", valeurs=" + valeurs + ", extension=" + extension + ", list=" + list + '}';
//    }
//    
//    
//    
//}
