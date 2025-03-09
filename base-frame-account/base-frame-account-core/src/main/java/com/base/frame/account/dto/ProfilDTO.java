/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.dto;

import com.base.frame.socle.core.workflow.dto.WorkFlowCycleActionSimpleObject;
import com.base.frame.socle.core.workflow.dto.WorkFlowCycleEtatSimpleObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alassani
 */
public class ProfilDTO {

    private String id;
    private String code;
    private String intitule;
    private String description;
    private String etat;
    private String libelleEtat;
    private WorkFlowCycleEtatSimpleObject workFlowCycleEtatSimpleObject;
    private WorkFlowCycleActionSimpleObject actionObject;
    private List<WorkFlowCycleActionSimpleObject> listActions = new ArrayList<>();
    private boolean disabled =false;
    private boolean canEdit = true;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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

    public String getLibelleEtat() {
        return libelleEtat;
    }

    public void setLibelleEtat(String libelleEtat) {
        this.libelleEtat = libelleEtat;
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

    @Override
    public String toString() {
        return "ProfilDTO{" + "id=" + id + ", code=" + code + ", intitule=" + intitule + ", description=" + description + ", etat=" + etat + ", libelleEtat=" + libelleEtat + ", workFlowCycleEtatSimpleObject=" + workFlowCycleEtatSimpleObject + ", actionObject=" + actionObject + ", listActions=" + listActions + ", disabled=" + disabled + ", canEdit=" + canEdit + '}';
    }

    
   

    
}
