///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.entities.CodifList;
//import com.ngs.core.codification.entities.Codification;
//import com.ngs.core.codification.entities.Parameter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.springframework.format.annotation.DateTimeFormat;
//
///**
// *
// * @author Adnaane
// */
//public class CodificationParameterDTO {
//
//    private String code;
//
//    private String libelle;
//    
//    private CodifList statut;
//
//    private String versionCodif;
//
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    private Date dateDebutValidite;
//
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    private Date dateFinValidite;
//
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    private Date dateActivation;
//
//    private CodifList porte;
//
//    private CodifList typeCodif;
//
//    private CodifList domaine;
//
//    private List<Parameter> params = new ArrayList<>();
//
//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 79 * hash + (this.code != null ? this.code.hashCode() : 0);
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
//        final CodificationParameterDTO other = (CodificationParameterDTO) obj;
//        return !((this.code == null) ? (other.code != null) : !this.code.equals(other.code));
//    }
//
//    public Codification castCodification(Codification codification) {
//        codification.setCode(this.code);
//        codification.setDateActivation(this.dateActivation);
//        codification.setDomaine(this.domaine);
//        codification.setLibelle(this.libelle);
//        codification.setPorte(this.porte);
//        return codification;
//    }
//
//    public void parseCodification1(Codification codification, List<Parameter> params) {
//        this.code = codification.getCode();
//        this.libelle = codification.getLibelle();
//        this.dateActivation = codification.getDateActivation();
//        this.porte = codification.getPorte();
//        this.typeCodif = codification.getTypeCodif();
//        this.domaine = codification.getDomaine();
//        this.params.addAll(params);
//    }
//
//    public void parseCodification(Codification codification , List<Parameter> params) {
//        this.code = codification.getCode();
//        this.libelle = codification.getLibelle();
//        this.typeCodif = codification.getTypeCodif();
//        this.porte = codification.getPorte();
//        this.domaine = codification.getDomaine();
//        this.params.addAll(params);
//    }
//
//    @Override
//    public String toString() {
//        return "CodificationParameterDTO{" + "code=" + code + ", libelle=" + libelle + ", versionCodif=" + versionCodif + ", dateDebutValidite=" + dateDebutValidite + ", dateFinValidite=" + dateFinValidite + ", dateActivation=" + dateActivation + ", porte=" + porte +  ", typeCodif=" + typeCodif + ", domaine=" + domaine + '}';
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
//    public String getVersionCodif() {
//        return versionCodif;
//    }
//
//    public void setVersionCodif(String versionCodif) {
//        this.versionCodif = versionCodif;
//    }
//
//    public Date getDateDebutValidite() {
//        return dateDebutValidite;
//    }
//
//    public void setDateDebutValidite(Date dateDebutValidite) {
//        this.dateDebutValidite = dateDebutValidite;
//    }
//
//    public Date getDateFinValidite() {
//        return dateFinValidite;
//    }
//
//    public void setDateFinValidite(Date dateFinValidite) {
//        this.dateFinValidite = dateFinValidite;
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
//    public CodifList getPorte() {
//        return porte;
//    }
//
//    public void setPorte(CodifList porte) {
//        this.porte = porte;
//    }
//
//    public CodifList getTypeCodif() {
//        return typeCodif;
//    }
//
//    public void setTypeCodif(CodifList typeCodif) {
//        this.typeCodif = typeCodif;
//    }
//
//    public CodifList getDomaine() {
//        return domaine;
//    }
//
//    public void setDomaine(CodifList domaine) {
//        this.domaine = domaine;
//    }
//
//    public List<Parameter> getParams() {
//        return params;
//    }
//
//    public void setParams(List<Parameter> params) {
//        this.params = params;
//    }
//
//    public CodifList getStatut() {
//        return statut;
//    }
//
//    public void setStatut(CodifList statut) {
//        this.statut = statut;
//    }
//
//}
