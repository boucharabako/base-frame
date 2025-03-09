///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * core-codification
// *
// * @author Pierre NGS creée le 29 juil. 2018 00:01:05
// */
//public class SimpleZoneObject {
//
//    private String id;
//    private Integer numeroZone;
//    private String intitule;
//    private String typeDeDonneeString;
//    private Integer tailleZone;
//    private String separateur;
//    private String listDeControle;
//    List<LibelleDTO> libellesDTO;
//    private String field = "Z";
//    private boolean fieldIsClassification = false;
//    private List<SimpleSousZoneObject> listSousZone;
//    private List<IdLabelObject> listClasseur = new ArrayList<>();
//
//    public SimpleZoneObject() {
//    }
//
//    public SimpleZoneObject(String id, Integer numeroZone, String intitule, String typeDeDonneeString, Integer tailleZone, String separateur, String listDeControle) {
//        this.id = id;
//        this.numeroZone = numeroZone;
//        this.intitule = intitule;
//        this.typeDeDonneeString = typeDeDonneeString;
//        this.tailleZone = tailleZone;
//        this.separateur = separateur;
//        this.listDeControle = listDeControle;
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
//    public Integer getNumeroZone() {
//        return numeroZone;
//    }
//
//    public void setNumeroZone(Integer numeroZone) {
//        this.numeroZone = numeroZone;
//    }
//
//    public String getIntitule() {
//        return intitule;
//    }
//
//    public void setIntitule(String intitule) {
//        this.intitule = intitule;
//    }
//
//    public String getTypeDeDonneeString() {
//        return typeDeDonneeString;
//    }
//
//    public void setTypeDeDonneeString(String typeDeDonneeString) {
//        this.typeDeDonneeString = typeDeDonneeString;
//    }
//
//    public Integer getTailleZone() {
//        return tailleZone;
//    }
//
//    public void setTailleZone(Integer tailleZone) {
//        this.tailleZone = tailleZone;
//    }
//
//    public String getSeparateur() {
//        return separateur;
//    }
//
//    public void setSeparateur(String separateur) {
//        this.separateur = separateur;
//    }
//
//    public String getListDeControle() {
//        return listDeControle;
//    }
//
//    public void setListDeControle(String listDeControle) {
//        this.listDeControle = listDeControle;
//    }
//
//    public List<LibelleDTO> getLibellesDTO() {
//        return libellesDTO;
//    }
//
//    public void setLibellesDTO(List<LibelleDTO> libellesDTO) {
//        this.libellesDTO = libellesDTO;
//    }
//
//    public String getField() {
//        return field;
//    }
//
//    public void setField(String field) {
//        this.field = field;
//    }
//
//    public List<SimpleSousZoneObject> getListSousZone() {
//        return listSousZone;
//    }
//
//    public void setListSousZone(List<SimpleSousZoneObject> listSousZone) {
//        this.listSousZone = listSousZone;
//    }
//
//    public boolean isFieldIsClassification() {
//        return fieldIsClassification;
//    }
//
//    public void setFieldIsClassification(boolean fieldIsClassification) {
//        this.fieldIsClassification = fieldIsClassification;
//    }
//
//    public List<IdLabelObject> getListClasseur() {
//        return listClasseur;
//    }
//
//    public void setListClasseur(List<IdLabelObject> listClasseur) {
//        this.listClasseur = listClasseur;
//    }
//
//}
