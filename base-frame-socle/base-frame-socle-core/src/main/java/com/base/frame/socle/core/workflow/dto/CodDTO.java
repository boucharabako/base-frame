///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.entities.CodifList;
//import com.ngs.core.codification.entities.Codification;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
///**
// *
// * @author herve
// */
//public class CodDTO {
//
//    private String code;
//    private String libelle;
//    private String domaine;
//    private String porte;
//    private String modify;
//    private String statut;
//    private List<CodifList> codifLists;
//
//    public CodDTO() {
//
//        this.codifLists = new ArrayList();
//    }
//
//    public CodDTO(String code, String libelle, String domaine, String porte, String modify, String statut) {
//        this.code = code;
//        this.libelle = libelle;
//        this.domaine = domaine;
//        this.porte = porte;
//        this.modify = modify;
//        this.statut = statut;
//        this.codifLists = new ArrayList();
//    }
//
//    public CodDTO(Codification codification) {
//        this.code = codification.getCode();
//        this.libelle = codification.getLibelle();
//        this.domaine = codification.getDomaine().getId();
//        this.porte = codification.getPorte().getId();
//        this.statut = codification.getStatut().getId();
//        this.codifLists = new ArrayList();
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
//    public String getDomaine() {
//        return domaine;
//    }
//
//    public void setDomaine(String domaine) {
//        this.domaine = domaine;
//    }
//
//    public String getPorte() {
//        return porte;
//    }
//
//    public void setPorte(String porte) {
//        this.porte = porte;
//    }
//
//    public String getModify() {
//        return modify;
//    }
//
//    public void setModify(String modify) {
//        this.modify = modify;
//    }
//
//    public String getStatut() {
//        return statut;
//    }
//
//    public void setStatut(String statut) {
//        this.statut = statut;
//    }
//
//    public List<CodifList> getCodifLists() {
//        return codifLists;
//    }
//
//    public void setCodifLists(List<CodifList> codifLists) {
//        this.codifLists = codifLists;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 83 * hash + Objects.hashCode(this.code);
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
//        final CodDTO other = (CodDTO) obj;
//        if (!Objects.equals(this.code, other.code)) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "CodDTO{" + "code=" + code + ", libelle=" + libelle + ", domaine=" + domaine + ", porte=" + porte + ", modify=" + modify + ", statut=" + statut + ", codifLists=" + codifLists + '}';
//    }
//
//}
