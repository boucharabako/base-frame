///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import java.util.Objects;
//
///**
// *
// * @author herve
// */
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class LibelleDTO {
//
//    /* Code Codification Liste*/
//    private String id;
//
//    private String idCodeListe;
//
//    private String libelle;
//
//    private String action;
//
//    private String color;
//
//    private String textColor;
//
//    public LibelleDTO() {
//    }
//
//    public LibelleDTO(String id, String libelle, String color, String textColor) {
//        this.id = id;
//        this.libelle = libelle;
//        this.color = color;
//        this.textColor = textColor;
//    }
//
//    public LibelleDTO(String ideCode, String libelle) {
//        this.id = ideCode;
//        this.idCodeListe = ideCode;
//        this.libelle = libelle;
//    }
//
//    public LibelleDTO(String id, String idCodeListe, String libelle) {
//        this.id = id;
//        this.idCodeListe = idCodeListe;
//        this.libelle = libelle;
//    }
//
//    public LibelleDTO(Libelle libelle) {
//        this(libelle.getCodifList().getId(), libelle.getCodifList().getIdeCode(), libelle.getLibelle());
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
//    public String getIdCodeListe() {
//        return idCodeListe;
//    }
//
//    public void setIdCodeListe(String idCodeListe) {
//        this.idCodeListe = idCodeListe;
//    }
//
//    public String getAction() {
//        return action;
//    }
//
//    public void setAction(String action) {
//        this.action = action;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public String getTextColor() {
//        return textColor;
//    }
//
//    public void setTextColor(String textColor) {
//        this.textColor = textColor;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 11 * hash + Objects.hashCode(this.id);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final LibelleDTO other = (LibelleDTO) obj;
//        if (!Objects.equals(this.id, other.id)) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "LibelleDTO{" + "id=" + id + ", idCodeListe=" + idCodeListe + ", libelle=" + libelle + '}';
//    }
//
//}
