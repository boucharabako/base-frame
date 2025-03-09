/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Bouchara
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UtilisateurGroupeDTO {

    private String id;
    private String idGroupeUtilisateur;
    private String idUtilisateur;
    private String username;
    private boolean communityManager = false;

    public UtilisateurGroupeDTO() {
    }

    public UtilisateurGroupeDTO(String id, String idGroupeUtilisateur, String idUtilisateur) {
        this.id = id;
        this.idGroupeUtilisateur = idGroupeUtilisateur;
        this.idUtilisateur = idUtilisateur;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdGroupeUtilisateur() {
        return idGroupeUtilisateur;
    }

    public void setIdGroupeUtilisateur(String idGroupeUtilisateur) {
        this.idGroupeUtilisateur = idGroupeUtilisateur;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isCommunityManager() {
        return communityManager;
    }

    public void setCommunityManager(boolean communityManager) {
        this.communityManager = communityManager;
    }

    @Override
    public String toString() {
        return "UtilisateurGroupeDTO{" + "id=" + id + ", idGroupeUtilisateur=" + idGroupeUtilisateur + ", idUtilisateur=" + idUtilisateur + '}';
    }

}
