/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Alassani
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdLabelObject implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String libelle;
    private String autre;
    private BigDecimal mt = BigDecimal.ZERO;
    private boolean booleanValue = true;
    private boolean checkValue = false;
    private boolean mobValue = false;
    private String description;

    public IdLabelObject() {
    }

    public IdLabelObject(String id) {
        this.id = id;
    }

    public IdLabelObject(String id, String libelle, String autre) {
        this.id = id;
        this.libelle = libelle;
        this.autre = autre;
    }

    public IdLabelObject(String id, String libelle, String autre, BigDecimal mt) {
        this.id = id;
        this.libelle = libelle;
        this.autre = autre;
        this.mt = mt;
    }

    public IdLabelObject(String id, String libelle, String autre, String description) {
        this.id = id;
        this.libelle = libelle;
        this.autre = autre;
        this.description = description;
    }

    public IdLabelObject(String id, boolean booleanValue) {
        this.id = id;
        this.booleanValue = booleanValue;
    }

    public IdLabelObject(String id, String libelle) {
        this.id = id;
        this.libelle = libelle;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final IdLabelObject other = (IdLabelObject) obj;
        return Objects.equals(this.id, other.id);
    }

   

    public String getAutre() {
        return autre;
    }

    public void setAutre(String autre) {
        this.autre = autre;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public boolean isCheckValue() {
        return checkValue;
    }

    public void setCheckValue(boolean checkValue) {
        this.checkValue = checkValue;
    }

    public BigDecimal getMt() {
        return mt;
    }

    public void setMt(BigDecimal mt) {
        this.mt = mt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMobValue() {
        return mobValue;
    }

    public void setMobValue(boolean mobValue) {
        this.mobValue = mobValue;
    }

    @Override
    public String toString() {
        return "IdLabelObject{" + "id=" + id + ", libelle=" + libelle + ", autre=" + autre + ", mt=" + mt + ", booleanValue=" + booleanValue + ", checkValue=" + checkValue + ", mobValue=" + mobValue + ", description=" + description + '}';
    }

    
}

