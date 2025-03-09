/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author kouwonou
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CodifItemSimpleObjectDTO {

    private String id;
    private String code;

    private String codification;
    private CodListDTO type;
    private boolean disabled = true;
    private boolean displayGroup = true;

    public CodifItemSimpleObjectDTO() {
    }

    public CodifItemSimpleObjectDTO(String id, String code, String codification, CodListDTO type) {
        this.id = id;
        this.code = code;
        this.codification = codification;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodification() {
        return codification;
    }

    public void setCodification(String codification) {
        this.codification = codification;
    }

    public CodListDTO getType() {
        return type;
    }

    public void setType(CodListDTO type) {
        this.type = type;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisplayGroup() {
        return displayGroup;
    }

    public void setDisplayGroup(boolean displayGroup) {
        this.displayGroup = displayGroup;
    }

    @Override
    public String toString() {
        return "CodifItemSimpleObjectDTO{" + "id=" + id + ", code=" + code + ", codification=" + codification + ", type=" + type + ", disabled=" + disabled + ", displayGroup=" + displayGroup + '}';
    }

}
