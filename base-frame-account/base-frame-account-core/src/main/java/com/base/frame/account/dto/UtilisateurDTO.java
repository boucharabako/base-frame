/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.dto;

import com.base.frame.account.entity.Fichier;
import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.workflow.dto.WorkFlowCycleActionSimpleObject;
import com.base.frame.socle.core.workflow.dto.WorkFlowCycleEtatSimpleObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alassani
 */
public class UtilisateurDTO {

    private String id;
    private String username;
    private String photo;
    private String firstName;
    private String lastName;
    private String firstAndLastName;
    private String email;
    private String titre;
    private String profil;
    private List<ProfilDTO> profils;
    private List<GroupesDTO> groupes;
    private String tel;
    private boolean estAgent;
    private boolean disabledEdit = false;
    private String groupe;
    private ParamList statut;
    private StatutDTO statutDto;
    private String concepteMetier;
    private boolean isEnSaisie = true;
    private boolean isActif = false;
    private boolean isObsolete = false;
    private Fichier picture;
    private String password;
    private String confirmPassword;

    private String etat;
    boolean sameGpe = false;
    boolean button = true;

    private boolean creerUser = false;
    private boolean modifierUser = false;
    private boolean detail = false;
    private boolean suprimer = false;
    private boolean reinitialiserMDP = false;
    private boolean changerMDP = false;
    private boolean genererCS = false;
    private boolean modifierMDP = false;
    private String dateNaissance;
    
    private String libelleEtat;
    private boolean disabled =false;
    private boolean canEdit = true;
    private WorkFlowCycleEtatSimpleObject workFlowCycleEtatSimpleObject;
    private WorkFlowCycleActionSimpleObject actionObject;
    private List<WorkFlowCycleActionSimpleObject> listActions = new ArrayList<>();

    public UtilisateurDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstAndLastName() {
        return firstAndLastName;
    }

    public void setFirstAndLastName(String firstAndLastName) {
        this.firstAndLastName = firstAndLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public List<ProfilDTO> getProfils() {
        return profils;
    }

    public void setProfils(List<ProfilDTO> profils) {
        this.profils = profils;
    }

    public List<GroupesDTO> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<GroupesDTO> groupes) {
        this.groupes = groupes;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public ParamList getStatut() {
        return statut;
    }

    public void setStatut(ParamList statut) {
        this.statut = statut;
    }

    public StatutDTO getStatutDto() {
        return statutDto;
    }

    public void setStatutDto(StatutDTO statutDto) {
        this.statutDto = statutDto;
    }

    public String getConcepteMetier() {
        return concepteMetier;
    }

    public void setConcepteMetier(String concepteMetier) {
        this.concepteMetier = concepteMetier;
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

    public Fichier getPicture() {
        return picture;
    }

    public void setPicture(Fichier picture) {
        this.picture = picture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String isEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public boolean isSameGpe() {
        return sameGpe;
    }

    public void setSameGpe(boolean sameGpe) {
        this.sameGpe = sameGpe;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public boolean isCreerUser() {
        return creerUser;
    }

    public void setCreerUser(boolean creerUser) {
        this.creerUser = creerUser;
    }

    public boolean isModifierUser() {
        return modifierUser;
    }

    public void setModifierUser(boolean modifierUser) {
        this.modifierUser = modifierUser;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public boolean isSuprimer() {
        return suprimer;
    }

    public void setSuprimer(boolean suprimer) {
        this.suprimer = suprimer;
    }

    public boolean isReinitialiserMDP() {
        return reinitialiserMDP;
    }

    public void setReinitialiserMDP(boolean reinitialiserMDP) {
        this.reinitialiserMDP = reinitialiserMDP;
    }

    public boolean isChangerMDP() {
        return changerMDP;
    }

    public void setChangerMDP(boolean changerMDP) {
        this.changerMDP = changerMDP;
    }

    public boolean isGenererCS() {
        return genererCS;
    }

    public void setGenererCS(boolean genererCS) {
        this.genererCS = genererCS;
    }

    public boolean isModifierMDP() {
        return modifierMDP;
    }

    public void setModifierMDP(boolean modifierMDP) {
        this.modifierMDP = modifierMDP;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public boolean estAgent() {
        return estAgent;
    }

    public void setEstAgent(boolean estAgent) {
        this.estAgent = estAgent;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean setDisabledEdit() {
        return disabledEdit;
    }

    public void setDisabledEdit(boolean disabledEdit) {
        this.disabledEdit = disabledEdit;
    }

    public WorkFlowCycleEtatSimpleObject getEtatObject() {
        return workFlowCycleEtatSimpleObject;
    }

    public void setEtatObject(WorkFlowCycleEtatSimpleObject workFlowCycleEtatSimpleObject) {
        this.workFlowCycleEtatSimpleObject = workFlowCycleEtatSimpleObject;
    }

    public WorkFlowCycleEtatSimpleObject getWorkFlowCycleEtatSimpleObject() {
        return workFlowCycleEtatSimpleObject;
    }

    public void setWorkFlowCycleEtatSimpleObject(WorkFlowCycleEtatSimpleObject workFlowCycleEtatSimpleObject) {
        this.workFlowCycleEtatSimpleObject = workFlowCycleEtatSimpleObject;
    }
    
     public WorkFlowCycleActionSimpleObject getActionObject() {
        return actionObject;
    }

    public void setActionObject(WorkFlowCycleActionSimpleObject actionObject) {
        this.actionObject = actionObject;
    }

    public List<WorkFlowCycleActionSimpleObject> getListActions() {
        return listActions;
    }

    public void setListActions(List<WorkFlowCycleActionSimpleObject> listActions) {
        this.listActions = listActions;
    }

    public String getLibelleEtat() {
        return libelleEtat;
    }

    public void setLibelleEtat(String libelleEtat) {
        this.libelleEtat = libelleEtat;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }
    
    

    @Override
    public String toString() {
        return "UtilisateurDTO{" + "id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", firstAndLastName=" + firstAndLastName + ", email=" + email + ", titre=" + titre + ", profil=" + profil + ", profils=" + profils + ", groupes=" + groupes + ", groupe=" + groupe + ", statut=" + statut + ", statutDto=" + statutDto + ", concepteMetier=" + concepteMetier + ", isEnSaisie=" + isEnSaisie + ", isActif=" + isActif + ", isObsolete=" + isObsolete + ", picture=" + picture + ", password=" + password + ", confirmPassword=" + confirmPassword + ", etat=" + etat + ", sameGpe=" + sameGpe + ", button=" + button + ", creerUser=" + creerUser + ", modifierUser=" + modifierUser + ", detail=" + detail + ", suprimer=" + suprimer + ", reinitialiserMDP=" + reinitialiserMDP + ", changerMDP=" + changerMDP + ", genererCS=" + genererCS + ", modifierMDP=" + modifierMDP + '}';
    }

}
