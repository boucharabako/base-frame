/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * core-codification
 *
 * @author Pierre NGS creée le 29 juil. 2018 00:01:05
 */
public class SimpleSousZoneObject {

    private String id;
    private Integer numeroSousZone;
    private String intitule;
    private String typeDeDonneeString;
    private Integer tailleSousZone;
    private String listDeControle;
    private boolean fieldIsClassification = false;
    //private List<LibelleDTO> libellesDTO;
    private String field = "SZ";
    private List<IdLabelObject> listClasseur = new ArrayList<>();

    public SimpleSousZoneObject() {
    }

    public SimpleSousZoneObject(String id, Integer numeroSousZone, String intitule, String typeDeDonneeString, Integer tailleSousZone, String listDeControle) {
        this.id = id;
        this.numeroSousZone = numeroSousZone;
        this.intitule = intitule;
        this.typeDeDonneeString = typeDeDonneeString;
        this.tailleSousZone = tailleSousZone;
        this.listDeControle = listDeControle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumeroSousZone() {
        return numeroSousZone;
    }

    public void setNumeroSousZone(Integer numeroSousZone) {
        this.numeroSousZone = numeroSousZone;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getTypeDeDonneeString() {
        return typeDeDonneeString;
    }

    public void setTypeDeDonneeString(String typeDeDonneeString) {
        this.typeDeDonneeString = typeDeDonneeString;
    }

    public Integer getTailleSousZone() {
        return tailleSousZone;
    }

    public void setTailleSousZone(Integer tailleSousZone) {
        this.tailleSousZone = tailleSousZone;
    }

    public String getListDeControle() {
        return listDeControle;
    }

    public void setListDeControle(String listDeControle) {
        this.listDeControle = listDeControle;
    }

//    public List<LibelleDTO> getLibellesDTO() {
//        return libellesDTO;
//    }
//
//    public void setLibellesDTO(List<LibelleDTO> libellesDTO) {
//        this.libellesDTO = libellesDTO;
//    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isFieldIsClassification() {
        return fieldIsClassification;
    }

    public void setFieldIsClassification(boolean fieldIsClassification) {
        this.fieldIsClassification = fieldIsClassification;
    }

    public List<IdLabelObject> getListClasseur() {
        return listClasseur;
    }

    public void setListClasseur(List<IdLabelObject> listClasseur) {
        this.listClasseur = listClasseur;
    }

}
