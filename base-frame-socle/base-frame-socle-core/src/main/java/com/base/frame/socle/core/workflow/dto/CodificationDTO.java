///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.entities.Codification;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// *
// * @author Herve
// */
//public class CodificationDTO {
//
//    private String code;
//    private String libelle;
//    private String domaine;
//    private String porte;
//    
//    private String modify;
//    private Boolean editable;
//    private String statut;
//    private Date dateActivation;
//    private String versionCodif;
//    private List<ClasseurDTO> classeurs;
//    private List<CodifListDTO> codifLists;
//    private List<Codification> codifications;
//    private StatutDto statutDto;
//    public CodificationDTO() {
//        this.codifLists = new ArrayList();
//        this.codifications = new ArrayList();
//    }
//
//    public CodificationDTO(Codification codification) {
//        this.code = codification.getCode();
//        this.libelle = codification.getLibelle();
//        this.dateActivation = codification.getDateActivation();
//        this.domaine = codification.getDomaine().getId();
//        this.porte = codification.getPorte().getId();
//        this.statut = codification.getStatut().getId();
//        this.codifLists = new ArrayList();
//        this.codifications = new ArrayList();
//    }
//
//    public CodificationDTO(String libelle) {
//        this.codifLists = new ArrayList();
//        this.codifications = new ArrayList();
//        this.libelle = libelle;
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
//    public List<CodifListDTO> getCodifLists() {
//        return codifLists;
//    }
//
//    public void setCodifLists(List<CodifListDTO> codifLists) {
//        this.codifLists = codifLists;
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
//    public Date getDateActivation() {
//        return dateActivation;
//    }
//
//    public void setDateActivation(Date dateActivation) {
//        this.dateActivation = dateActivation;
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
//    public String getVersionCodif() {
//        return versionCodif;
//    }
//
//    public void setVersionCodif(String versionCodif) {
//        this.versionCodif = versionCodif;
//    }
//
//    public List<Codification> getCodifications() {
//        return codifications;
//    }
//
//    public void setCodifications(List<Codification> codifications) {
//        this.codifications = codifications;
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
//    public List<ClasseurDTO> getClasseurs() {
//        return classeurs;
//    }
//
//    public void setClasseurs(List<ClasseurDTO> classeurs) {
//        this.classeurs = classeurs;
//    }
//
//    public StatutDto getStatutDto() {
//        return statutDto;
//    }
//
//    public void setStatutDto(StatutDto statutDto) {
//        this.statutDto = statutDto;
//    }
//
//    public Boolean getEditable() {
//        return editable;
//    }
//
//    public void setEditable(Boolean editable) {
//        this.editable = editable;
//    }
//
//    @Override
//    public String toString() {
//        return "CodificationDTO{" + "code=" + code + ", libelle=" + libelle + ", domaine=" + domaine + ", porte=" + porte + ", modify=" + modify + ", statut=" + statut + ", dateActivation=" + dateActivation + ", versionCodif=" + versionCodif + ", codifLists=" + codifLists + ", codifications=" + codifications + '}';
//    }
//
//    public Codification from() throws ParseException {
//        Codification cod = new Codification();
//        cod.setCode(this.getCode());
//        cod.setLibelle(this.getLibelle());
//        cod.setDateActivation(this.getDateActivation());
//        return cod;
//    }
//
//    public Codification castCodification(Codification codification) {
//        codification.setCode(this.code);
//        codification.setDateActivation(this.dateActivation);
//        codification.setLibelle(this.libelle);
//        return codification;
//    }
//
//}
