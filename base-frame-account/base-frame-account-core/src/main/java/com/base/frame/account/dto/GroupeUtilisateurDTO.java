/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.dto;

//import com.ngs.core.codification.dto.IdLabelObject;
//import com.ngs.core.codification.dto.LibelleDTO;
//import com.ngs.core.codification.dto.StatutDto;
import com.base.frame.socle.core.entity.ParamList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bouchara
 */
public class GroupeUtilisateurDTO {

    private String id;
    private String code;
    private String libelle;
    private String description;
    private boolean disable = false;

    private ParamList etat;
    private ArrayList<ParamList> tracking;
    private boolean isEnSaisie = false;
    private boolean isActif = false;
    private boolean isObsolete = false;
    private StatutDTO statutDTO;
    private List<String> utilisateur;
    private List<UtilisateurGroupeDTO> listUtilisateur;
    private boolean communityManager = false;
    private String parent;

    public GroupeUtilisateurDTO() {
    }

    public GroupeUtilisateurDTO(String id, String code, String libelle, String description, String idEtat) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.description = description;
        //this.etat = new LibelleDTO(idEtat, idEtat, "");
        this.tracking = new ArrayList<>();
    }

    public GroupeUtilisateurDTO(String id, String code, String libelle, String description, StatutDTO statutDTO) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.description = description;
        this.statutDTO = statutDTO;
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

//    public LibelleDTO getEtat() {
//        return etat;
//    }
//
//    public void setEtat(LibelleDTO etat) {
//        this.etat = etat;
//    }
//
//    public ArrayList<LibelleDTO> getTracking() {
//        return tracking;
//    }
//
//    public void setTracking(ArrayList<LibelleDTO> tracking) {
//        this.tracking = tracking;
//    }

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

    public StatutDTO getStatut() {
        return statutDTO;
    }

    public void setStatut(StatutDTO statutDTO) {
        this.statutDTO = statutDTO;
    }

    public List<String> getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(List<String> utilisateur) {
        this.utilisateur = utilisateur;
    }

    public boolean isCommunityManager() {
        return communityManager;
    }

    public void setCommunityManager(boolean communityManager) {
        this.communityManager = communityManager;
    }

    public List<UtilisateurGroupeDTO> getListUtilisateur() {
        return listUtilisateur;
    }

    public void setListUtilisateur(List<UtilisateurGroupeDTO> listUtilisateur) {
        this.listUtilisateur = listUtilisateur;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "GroupeUtilisateurDTO{" + "id=" + id + ", code=" + code + ", libelle=" + libelle + ", description=" + description + ", disable=" + disable + ", etat=" + etat + ", tracking=" + tracking + ", isEnSaisie=" + isEnSaisie + ", isActif=" + isActif + ", isObsolete=" + isObsolete + ", statut=" + statutDTO + '}';
    }

}
