/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.dto;

/**
 *
 * @author Alassani
 */
public class HabilitationDTO {

    private String id;
    private String idProfil;
    private String libelleProfil;
    private String codeFonction;
    private String libelleFonction;
    private String idNiveauHabilitation;
    private String libelleNiveauHabilitation;
    private boolean disabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(String idProfil) {
        this.idProfil = idProfil;
    }

    public String getLibelleProfil() {
        return libelleProfil;
    }

    public void setLibelleProfil(String libelleProfil) {
        this.libelleProfil = libelleProfil;
    }

    public String getCodeFonction() {
        return codeFonction;
    }

    public void setCodeFonction(String codeFonction) {
        this.codeFonction = codeFonction;
    }

    public String getLibelleFonction() {
        return libelleFonction;
    }

    public void setLibelleFonction(String libelleFonction) {
        this.libelleFonction = libelleFonction;
    }

    public String getIdNiveauHabilitation() {
        return idNiveauHabilitation;
    }

    public void setIdNiveauHabilitation(String idNiveauHabilitation) {
        this.idNiveauHabilitation = idNiveauHabilitation;
    }

    public String getLibelleNiveauHabilitation() {
        return libelleNiveauHabilitation;
    }

    public void setLibelleNiveauHabilitation(String libelleNiveauHabilitation) {
        this.libelleNiveauHabilitation = libelleNiveauHabilitation;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "HabilitationDTO{" + "id=" + id + ", idProfil=" + idProfil + ", libelleProfil=" + libelleProfil + ", codeFonction=" + codeFonction + ", libelleFonction=" + libelleFonction + ", idNiveauHabilitation=" + idNiveauHabilitation + ", libelleNiveauHabilitation=" + libelleNiveauHabilitation + ", disabled=" + disabled + '}';
    }
    
    
}
