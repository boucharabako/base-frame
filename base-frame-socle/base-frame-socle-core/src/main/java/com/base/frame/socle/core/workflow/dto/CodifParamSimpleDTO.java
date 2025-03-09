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
public class CodifParamSimpleDTO extends CodifItemSimpleObjectDTO {

    private String valeur;
    private String dateDebutValidite;
    private String dateFinValidite;

    public CodifParamSimpleDTO() {
    }

    public CodifParamSimpleDTO(String valeur, String dateDebutValidite, String dateFinValidite) {
        this.valeur = valeur;
        this.dateDebutValidite = dateDebutValidite;
        this.dateFinValidite = dateFinValidite;
    }

    public CodifParamSimpleDTO(String valeur, String dateDebutValidite, String dateFinValidite, String id, String code, String codification, CodListDTO type) {
        super(id, code, codification, type);
        this.valeur = valeur;
        this.dateDebutValidite = dateDebutValidite;
        this.dateFinValidite = dateFinValidite;
    }

    @Override
    public void setCode(String c) {
        super.setCode("PARAM");
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
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

    @Override
    public String toString() {
        return "CodifParamSimpleDTO{" + "valeur=" + valeur + ", dateDebutValidite=" + dateDebutValidite + ", dateFinValidite=" + dateFinValidite + '}' + super.toString();
    }

}
