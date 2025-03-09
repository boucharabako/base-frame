/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

/**
 *
 * @author Sewa
 */
public class ActionPermiseExecutorDTO {

    private String id;
    private String libelle;
    private Integer numeroOrdre;

    public ActionPermiseExecutorDTO() {
    }

    public ActionPermiseExecutorDTO(String id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public ActionPermiseExecutorDTO(String id, String libelle, Integer numeroOrdre) {
        this.id = id;
        this.libelle = libelle;
        this.numeroOrdre = numeroOrdre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

}
