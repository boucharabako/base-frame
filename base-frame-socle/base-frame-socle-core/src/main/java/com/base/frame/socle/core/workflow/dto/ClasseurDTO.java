///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.entities.Classeur;
//
///**
// *
// * @author kouwonou
// */
//public class ClasseurDTO {
//   private String id;
//   private String code;
//   private String libelle;
//   private String parent;
//   private String codification;
//
//    public ClasseurDTO() {
//    }
//
//    public ClasseurDTO(String id, String code, String libelle) {
//        this.id = id;
//        this.code = code;
//        this.libelle = libelle;
//    }
//
//    public ClasseurDTO(String id, String code, String libelle, Classeur  parent) {
//        this.id = id;
//        this.code = code;
//        this.libelle = libelle;
//        this.parent = parent!=null?parent.getId():null;
//    }
//
//    public String getCodification() {
//        return codification;
//    }
//
//    public void setCodification(String codification) {
//        this.codification = codification;
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
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
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
//    public String getParent() {
//        return parent;
//    }
//
//    public void setParent(String parent) {
//        this.parent = parent;
//    }
//
//    @Override
//    public String toString() {
//        return "ClasseurDTO{" + "id=" + id + ", code=" + code + ", libelle=" + libelle + ", parent=" + parent + ", codification=" + codification + '}';
//    }
//
//
//   
//}
