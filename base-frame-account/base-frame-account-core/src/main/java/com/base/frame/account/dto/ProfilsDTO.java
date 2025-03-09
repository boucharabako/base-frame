/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.dto;
//import com.ngs.core.codification.dto.StatutDto;
//import com.ngs.core.codification.entities.CodifList;

import com.base.frame.socle.core.entity.ParamList;


/**
 *
 * @author Alassani
 */
public class ProfilsDTO {

    private String id;
    private String code;
    private String intitule;
    private ParamList statut; 
    private String concepteMetier;
   private String flgProfil;
    private StatutDTO statutDto;
    private boolean isEnSaisie = true;
    private boolean isActif = false;
    private boolean isObsolete = false;
    

    public ProfilsDTO(String id, String code, String intitule, ParamList statut) {
        this.id = id;
        this.code = code;
        this.intitule = intitule;
        this.statut = statut;
    }

    public ProfilsDTO() {
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

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public ParamList getStatut() {
        return statut;
    }

    public void setStatut(ParamList statut) {
        this.statut = statut;
    }
    
       public String getConcepteMetier() {
        return concepteMetier;
    }

    public void setConcepteMetier(String concepteMetier) {
        this.concepteMetier = concepteMetier;
    }

    public StatutDTO getStatutDto() {
        return statutDto;
    }

    public void setStatutDto(StatutDTO statutDto) {
        this.statutDto = statutDto;
    }

   

    public boolean isIsEnSaisie() {
        return isEnSaisie;
    }

    public void setIsEnSaisie(boolean isEnSaisie) {
        this.isEnSaisie = isEnSaisie;
    }

    public boolean isIsActif() {
        return isActif;
    }

    public void setIsActif(boolean isActif) {
        this.isActif = isActif;
    }

    public boolean isIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public String getFlgProfil() {
        return flgProfil;
    }

    public void setFlgProfil(String flgProfil) {
        this.flgProfil = flgProfil;
    }
    
    

    @Override
    public String toString() {
        return "ProfilsDTO{" + "id=" + id + ", code=" + code + ", intitule=" + intitule + ", statut=" + statut + '}';
    }
    
    
    
}
