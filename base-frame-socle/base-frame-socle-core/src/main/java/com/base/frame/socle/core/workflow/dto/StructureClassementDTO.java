/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

/**
 * abs-bud-job
 *
 * @author Pierre NGS creée le 17 avr. 2019 13:19:55
 */
public class StructureClassementDTO {

    private String type;
    private Integer taille;

    public StructureClassementDTO() {
    }

    public StructureClassementDTO(String type, Integer taille) {
        this.type = type;
        this.taille = taille;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTaille() {
        return taille;
    }

    public void setTaille(Integer taille) {
        this.taille = taille;
    }

}
