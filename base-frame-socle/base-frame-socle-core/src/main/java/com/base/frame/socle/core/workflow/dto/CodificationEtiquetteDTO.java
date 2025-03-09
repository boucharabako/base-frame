///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.entities.CodifList;
//import com.ngs.core.codification.entities.Codification;
//import com.ngs.core.codification.entities.Etiquette;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.springframework.format.annotation.DateTimeFormat;
//
///**
// * Cette classe permet au controlleur de gérer les données d'une codification
// * d'étiquette
// *
// * @author Adnaane
// * @version 1.0.0
// * @since 27/02/2018
// *
// */
//public class CodificationEtiquetteDTO {
//
//    private String code;
//
//    private String libelle;
//
//    private String versionCodif;
//
//    private CodifList statut;
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
//    private CodifList modeleModification;
//
//    private CodifList typeCodif;
//
//    private CodifList domaine;
//
//    private List<Etiquette> etiquettes = new ArrayList<>();
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
//        final CodificationEtiquetteDTO other = (CodificationEtiquetteDTO) obj;
//        return !((this.code == null) ? (other.code != null) : !this.code.equals(other.code));
//    }
//
//    /**
//     * Cette méthode permet de caster un dto de codification étiquette en une
//     * codification entité
//     *
//     * @param codification la codification qui doit recevoir les valeurs
//     * @return
//     */
//    public Codification castCodification(Codification codification) {
//        codification.setCode(this.code);
//        codification.setDomaine(this.domaine);
//        codification.setLibelle(this.libelle);
//        codification.setPorte(this.porte);
//        return codification;
//    }
//
//    /**
//     * Cette méthode permet de parser une codification en dto de codification
//     * étiquette
//     *
//     * @param codification la codification en question
//     * @param modeleModification le modèle de modification
//     * @param typeCodif le type de codification (paramètre, étiquette , ou
//     * liste)
//     * @param porte la porté de la codification
//     * @param domaine le domaine
//     * @param etiquettes la liste des étiquettes
//     */
//    public void parseCodification(Codification codification,
//            CodifList modeleModification,
//            CodifList typeCodif,
//            CodifList porte,
//            CodifList domaine,
//            List<Etiquette> etiquettes) {
//        this.code = codification.getCode();
//        this.libelle = codification.getLibelle();
//        this.statut = codification.getStatut();
//        this.dateActivation = codification.getDateActivation();
//        this.modeleModification = modeleModification;
//        this.typeCodif = typeCodif;
//        this.porte = porte;
//        this.domaine = domaine;
//        this.etiquettes.addAll(etiquettes);
//    }
//
//    /**
//     * Cette méthode permet de parser une codification et sa liste d'étiquette
//     * en dto de codification étiquettes
//     *
//     * @param codification la codification concernée
//     * @param etiquettes la liste des étiquettes associées à cette codification
//     */
//    public void parseCodification(Codification codification, List<Etiquette> etiquettes) {
//        this.code = codification.getCode();
//        this.libelle = codification.getLibelle();
//        this.statut = codification.getStatut();
//        this.dateActivation = codification.getDateActivation();
//        this.porte = codification.getPorte();
//        this.typeCodif = codification.getTypeCodif();
//        this.domaine = codification.getDomaine();
//        this.etiquettes.addAll(etiquettes);
//    }
//
//    @Override
//    public String toString() {
//        return "CodificationEtiquetteDTO{" + "code=" + code + ", libelle=" + libelle + ", versionCodif=" + versionCodif + ", dateDebutValidite=" + dateDebutValidite + ", dateFinValidite=" + dateFinValidite + ", dateActivation=" + dateActivation + ", porte=" + porte + ", modeleModification=" + modeleModification + ", typeCodif=" + typeCodif + ", domaine=" + domaine + '}';
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
//    public CodifList getModeleModification() {
//        return modeleModification;
//    }
//
//    public void setModeleModification(CodifList modeleModification) {
//        this.modeleModification = modeleModification;
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
//    public List<Etiquette> getEtiquettes() {
//        return etiquettes;
//    }
//
//    public void setEtiquettes(List<Etiquette> etiquettes) {
//        this.etiquettes = etiquettes;
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
