/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

/**
 *
 * @author kouwonou
 */
public class CodifEtiquetteSimpleObjectDTO extends CodifItemSimpleObjectDTO {

    public CodifEtiquetteSimpleObjectDTO() {
    }

    private String libelle;
    private String dateDebutValidite;
    private String dateFinValidite;

    public CodifEtiquetteSimpleObjectDTO(String libelle, String dateDebutValidite, String dateFinValidite) {
        this.libelle = libelle;
        this.dateDebutValidite = dateDebutValidite;
        this.dateFinValidite = dateFinValidite;
    }

    public CodifEtiquetteSimpleObjectDTO(String libelle, String dateDebutValidite, String dateFinValidite, String id, String code, String codification, CodListDTO type) {
        super(id, code, codification, type);
        this.libelle = libelle;
        this.dateDebutValidite = dateDebutValidite;
        this.dateFinValidite = dateFinValidite;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDateDebutValidite() {
        return dateDebutValidite;
    }

    public void setDateDebutValidite(String dateDebutValidite) {
        this.dateDebutValidite = dateDebutValidite;
    }

    public String getDateFinValidite() {
        return dateFinValidite;
    }

    public void setDateFinValidite(String dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

}
