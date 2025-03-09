///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//
//import com.ngs.core.codification.utils.CodificationConstant;
//import java.util.ArrayList;
//import java.util.List;
//import javax.validation.constraints.NotNull;
//
///**
// *
// * @author KOMLA
// */
//public class GroupeExtensionObject {
//
//    private String id;
//    @NotNull(message = CodificationConstant.F_REQUIERD)
//    private String titre;
//    @NotNull(message = CodificationConstant.F_REQUIERD)
//    private String description;
//    @NotNull(message = CodificationConstant.F_REQUIERD)
//    private List<String> extension;
//    @NotNull(message = CodificationConstant.F_REQUIERD)
//    private String conceptMetier;
//    private StatutDto statut;
//    private LibelleDTO etat;
//    private ArrayList<LibelleDTO> tracking;
//    private boolean disabledEdit = true;
//    private boolean isEnSaisie = false;
//    private boolean isActif = false;
//    private boolean isObsolete = false;
//
//    public GroupeExtensionObject() {
//    }
//
//    public GroupeExtensionObject(String titre, String description) {
//        this.titre = titre;
//        this.description = description;
//    }
//
//    public GroupeExtensionObject(String id, String titre, String description, String idEtat, String idEtatCodeListe) {
//        this.id = id;
//        this.titre = titre;
//        this.description = description;
//        this.titre = titre;
//        this.etat = new LibelleDTO(idEtatCodeListe, idEtatCodeListe, idEtat);
//    }
//
//    public GroupeExtensionObject(String id, String titre, String description, String idEtat,  String idEtatCodeListe, String conceptMetier) {
//        this.id = id;
//        this.titre = titre;
//        this.description = description;
//        this.titre = titre;
//        this.conceptMetier = conceptMetier;
//        this.etat = new LibelleDTO(idEtatCodeListe, idEtatCodeListe, idEtat);
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getTitre() {
//        return titre;
//    }
//
//    public void setTitre(String titre) {
//        this.titre = titre;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public List<String> getExtension() {
//        return extension;
//    }
//
//    public void setExtension(List<String> extension) {
//        this.extension = extension;
//    }
//
//    public StatutDto getStatut() {
//        return statut;
//    }
//
//    public void setStatut(StatutDto statut) {
//        this.statut = statut;
//    }
//
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
//
//    public boolean isDisabledEdit() {
//        return disabledEdit;
//    }
//
//    public void setDisabledEdit(boolean disabledEdit) {
//        this.disabledEdit = disabledEdit;
//    }
//
//    public boolean isIsEnSaisie() {
//        return isEnSaisie;
//    }
//
//    public void setIsEnSaisie(boolean isEnSaisie) {
//        this.isEnSaisie = isEnSaisie;
//    }
//
//    public boolean isIsActif() {
//        return isActif;
//    }
//
//    public void setIsActif(boolean isActif) {
//        this.isActif = isActif;
//    }
//
//    public boolean isIsObsolete() {
//        return isObsolete;
//    }
//
//    public void setIsObsolete(boolean isObsolete) {
//        this.isObsolete = isObsolete;
//    }
//
//    public String getConceptMetier() {
//        return conceptMetier;
//    }
//
//    public void setConceptMetier(String conceptMetier) {
//        this.conceptMetier = conceptMetier;
//    }
//
//    @Override
//    public String toString() {
//        return "GroupeExtensionObject{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", extension=" + extension + ", conceptMetier=" + conceptMetier + ", statut=" + statut + ", etat=" + etat + ", tracking=" + tracking + ", disabledEdit=" + disabledEdit + ", isEnSaisie=" + isEnSaisie + ", isActif=" + isActif + ", isObsolete=" + isObsolete + '}';
//    }
//
//}
