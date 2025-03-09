/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alassani
 */
public class UserProfilDTO {

    private String idUtilisateur;
    private List<ProfilDTO> listProfil = new ArrayList<>();
    private boolean disabled = false;

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public List<ProfilDTO> getListProfil() {
        return listProfil;
    }

    public void setListProfil(List<ProfilDTO> listProfil) {
        this.listProfil = listProfil;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    
}
