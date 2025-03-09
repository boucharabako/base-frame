/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.dto;

/**
 * 
 *
 * @author ALASSANI
 */
public class StatutDTO {

    private String id;
    private String libelle;
    private String ideCode;
    
    public StatutDTO(String id, String libelle, String ideCode) {
        this.id = id;
        this.libelle = libelle;
        this.ideCode = ideCode;
        
    }
  
    public StatutDTO() {
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

    public String getIdeCode() {
        return ideCode;
    }

    public void setIdeCode(String ideCode) {
        this.ideCode = ideCode;
    }

  

    @Override
    public String toString() {
        return "StatutDTO{" + "id=" + id + ", libelle=" + libelle + ", ideCode=" + ideCode+ '}';
    }

}
