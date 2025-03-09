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
public class CodificationSimpleDTO {

    private String code;
    private String libelle;
    private CodListDTO porte;
    private CodListDTO domaine;
    private CodListDTO type;
    private StatutDto statutDto;

    public CodificationSimpleDTO() {
    }

    public CodificationSimpleDTO(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public CodListDTO getPorte() {
        return porte;
    }

    public void setPorte(CodListDTO porte) {
        this.porte = porte;
    }

    public CodListDTO getDomaine() {
        return domaine;
    }

    public void setDomaine(CodListDTO domaine) {
        this.domaine = domaine;
    }

    public CodListDTO getType() {
        return type;
    }

    public void setType(CodListDTO type) {
        this.type = type;
    }

    public StatutDto getStatutDto() {
        return statutDto;
    }

    public void setStatutDto(StatutDto statutDto) {
        this.statutDto = statutDto;
    }

}
