/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

import java.util.Objects;

/**
 *
 * @author herve
 */
public class CodListDTO {

    private String ideCode;

    private String libelle;

    public CodListDTO() {
    }

    public CodListDTO(String ideCode, String libelle) {
        this.ideCode = ideCode;
        this.libelle = libelle;
    }

    public String getIdeCode() {
        return ideCode;
    }

    public void setIdeCode(String ideCode) {
        this.ideCode = ideCode;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.ideCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CodListDTO other = (CodListDTO) obj;
        if (!Objects.equals(this.ideCode, other.ideCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CodListDTO{" + "ideCode=" + ideCode + ", libelle=" + libelle + '}';
    }

}
