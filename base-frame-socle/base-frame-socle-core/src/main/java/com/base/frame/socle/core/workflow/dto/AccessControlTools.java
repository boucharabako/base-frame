/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

/**
 * abs-bud-nomenclature
 *
 * @author Pierre NGS creée le 8 oct. 2018 13:02:31
 */
public class AccessControlTools {

    private boolean isUserAdminOfTheFunction = false;
    private boolean isEditAuthorised = false;
    private boolean isUserProfilAuthorized = false;
    private boolean isInitialState = false;
    private boolean isTerminusState = false;
    private boolean isUserOrdonnateur = false;
    private String workFlowId;
    private String etatId;
    private WorkFlowCycleEtatSimpleObject workFlowCycleEtatSimpleObject;
    private String conceptMetier;
    private boolean etatHasOnlyOneAction = true;
    private boolean userHabilitationSupOrEqFunctionHabil = false;

    public AccessControlTools() {
        // Do nothing in this method
    }

    public boolean isIsUserAdminOfTheFunction() {
        return isUserAdminOfTheFunction;
    }

    public void setIsUserAdminOfTheFunction(boolean isUserAdminOfTheFunction) {
        this.isUserAdminOfTheFunction = isUserAdminOfTheFunction;
    }

    public boolean isIsEditAuthorised() {
        return isEditAuthorised;
    }

    public void setIsEditAuthorised(boolean isEditAuthorised) {
        this.isEditAuthorised = isEditAuthorised;
    }

    public boolean isIsUserProfilAuthorized() {
        return isUserProfilAuthorized;
    }

    public void setIsUserProfilAuthorized(boolean isUserProfilAuthorized) {
        this.isUserProfilAuthorized = isUserProfilAuthorized;
    }

    public boolean isIsInitialState() {
        return isInitialState;
    }

    public void setIsInitialState(boolean isInitialState) {
        this.isInitialState = isInitialState;
    }

    public boolean isIsTerminusState() {
        return isTerminusState;
    }

    public void setIsTerminusState(boolean isTerminusState) {
        this.isTerminusState = isTerminusState;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getEtatId() {
        return etatId;
    }

    public void setEtatId(String etatId) {
        this.etatId = etatId;
    }

    public String getConceptMetier() {
        return conceptMetier;
    }

    public void setConceptMetier(String conceptMetier) {
        this.conceptMetier = conceptMetier;
    }

    public WorkFlowCycleEtatSimpleObject getWorkFlowCycleEtatSimpleObject() {
        return workFlowCycleEtatSimpleObject;
    }

    public void setWorkFlowCycleEtatSimpleObject(WorkFlowCycleEtatSimpleObject workFlowCycleEtatSimpleObject) {
        this.workFlowCycleEtatSimpleObject = workFlowCycleEtatSimpleObject;
    }

    public boolean isIsUserOrdonnateur() {
        return isUserOrdonnateur;
    }

    public void setIsUserOrdonnateur(boolean isUserOrdonnateur) {
        this.isUserOrdonnateur = isUserOrdonnateur;
    }

    public boolean isEtatHasOnlyOneAction() {
        return etatHasOnlyOneAction;
    }

    public void setEtatHasOnlyOneAction(boolean etatHasOnlyOneAction) {
        this.etatHasOnlyOneAction = etatHasOnlyOneAction;
    }

    public boolean isUserHabilitationSupOrEqFunctionHabil() {
        return userHabilitationSupOrEqFunctionHabil;
    }

    public void setUserHabilitationSupOrEqFunctionHabil(boolean userHabilitationSupOrEqFunctionHabil) {
        this.userHabilitationSupOrEqFunctionHabil = userHabilitationSupOrEqFunctionHabil;
    }

}
