/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

/**
 *
 * @author KOMLA
 */
public class MapSizeDTO {

    private String idExt;
    private String idPropriete;
    private String typeDonne;
    private String value;
    private String text;
    private String conceptMetier;
  

    public MapSizeDTO() {
    }
    

    public MapSizeDTO(String idExt, String idPropriete, String typeDonne, String value, String text, String conceptMetier) {
        this.idExt = idExt;
        this.idPropriete = idPropriete;
        this.typeDonne = typeDonne;
        this.value = value;
        this.text= text;
        this.conceptMetier = conceptMetier;
    }


    public MapSizeDTO(String value, String conceptMetier) {
        this.value = value;
        this.conceptMetier = conceptMetier;
    }

    public MapSizeDTO(String value, String text, String conceptMetier) {
        this.value = value;
        this.text = text;
        this.conceptMetier = conceptMetier;
    }

    public String getIdExt() {
        return idExt;
    }

    public void setIdExt(String idExt) {
        this.idExt = idExt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getConceptMetier() {
        return conceptMetier;
    }

    public void setConceptMetier(String conceptMetier) {
        this.conceptMetier = conceptMetier;
    }

    public String getIdPropriete() {
        return idPropriete;
    }

    public void setIdPropriete(String idPropriete) {
        this.idPropriete = idPropriete;
    }

    public String getTypeDonne() {
        return typeDonne;
    }

    public void setTypeDonne(String typeDonne) {
        this.typeDonne = typeDonne;
    }

    @Override
    public String toString() {
        return "MapSizeDTO{" + "idExt=" + idExt + ", idPropriete=" + idPropriete + ", typeDonne=" + typeDonne + ", value=" + value + ", text=" + text + ", conceptMetier=" + conceptMetier + '}';
    }

  

}
