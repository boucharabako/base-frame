/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.dto;


import com.base.frame.socle.core.entity.ParamList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ALASSANI
 */
public class GroupesDTO {
   private String id;
    private String code;
    private String libelle;
    private String description;
    private boolean disable = false;
    private boolean isEnSaisie = false;
    private boolean isActif = false;
    private boolean isObsolete = false;
    private ParamList statut;
    private List<String> utilisateur;
    private List<GroupesDTO> listUtilisateur;
    private boolean communityManager = false;

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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
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

    public ParamList getStatut() {
        return statut;
    }

    public void setStatut(ParamList statut) {
        this.statut = statut;
    }

    public List<GroupesDTO> getListUtilisateur() {
        return listUtilisateur;
    }

    public void setListUtilisateur(List<GroupesDTO> listUtilisateur) {
        this.listUtilisateur = listUtilisateur;
    }

    public boolean isCommunityManager() {
        return communityManager;
    }

    public void setCommunityManager(boolean communityManager) {
        this.communityManager = communityManager;
    }
    
    
}
