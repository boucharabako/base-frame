package com.base.frame.socle.core.workflow.dto;

import com.base.frame.socle.core.utils.SocleConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.validation.constraints.NotNull;

public class ActionSimpleObject {

    private String id;
    @NotNull(message = SocleConstant.F_REQUIERD)

    private String idWorkFlow;
    @NotNull(message = SocleConstant.F_REQUIERD)

    private String action;

    private String fond;

    private String text;
    private String idCallback;

    @NotNull(message = SocleConstant.F_REQUIERD)
    private String etatSelected;

    private String etatSuivant;

    private List<IdLabelObject> listEvenementProgramme = new ArrayList<>();
    private List<IdLabelObject> listeParamEvenement = new ArrayList<>();
    private List<IdLabelObject> listProfileAutorise = new ArrayList<>();
    private List<ActionPermiseExecutorDTO> listExecutorSelected = new ArrayList<>();

    private boolean displaySaveButton;
    private boolean displayEditButton;
    private boolean displayCancelButton;
    private boolean displayDeleteButton;

    private boolean enabledButton = false;

    private String codeHabilitation;
    private String codeInfoTransition;

    public ActionSimpleObject() {
    }

    public ActionSimpleObject(String id, String idWorkFlow, String action, String etatSelected) {
        this.id = id;
        this.idWorkFlow = idWorkFlow;
        this.action = action;
        this.etatSelected = etatSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdWorkFlow() {
        return idWorkFlow;
    }

    public void setIdWorkFlow(String idWorkFlow) {
        this.idWorkFlow = idWorkFlow;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFond() {
        return fond;
    }

    public void setFond(String fond) {
        this.fond = fond;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEtatSelected() {
        return etatSelected;
    }

    public void setEtatSelected(String etatSelected) {
        this.etatSelected = etatSelected;
    }

    public String getEtatSuivant() {
        return etatSuivant;
    }

    public void setEtatSuivant(String etatSuivant) {
        this.etatSuivant = etatSuivant;
    }

    public List<IdLabelObject> getListEvenementProgramme() {
        return listEvenementProgramme;
    }

    public void setListEvenementProgramme(List<IdLabelObject> listEvenementProgramme) {
        this.listEvenementProgramme = listEvenementProgramme;
    }

    public List<IdLabelObject> getListProfileAutorise() {
        return listProfileAutorise;
    }

    public void setListProfileAutorise(List<IdLabelObject> listProfileAutorise) {
        this.listProfileAutorise = listProfileAutorise;
    }

    public boolean isDisplaySaveButton() {
        return displaySaveButton;
    }

    public void setDisplaySaveButton(boolean displaySaveButton) {
        this.displaySaveButton = displaySaveButton;
    }

    public boolean isDisplayCancelButton() {
        return displayCancelButton;
    }

    public void setDisplayCancelButton(boolean displayCancelButton) {
        this.displayCancelButton = displayCancelButton;
    }

    public boolean isDisplayDeleteButton() {
        return displayDeleteButton;
    }

    public void setDisplayDeleteButton(boolean displayDeleteButton) {
        this.displayDeleteButton = displayDeleteButton;
    }

    public boolean isDisplayEditButton() {
        return displayEditButton;
    }

    public void setDisplayEditButton(boolean displayEditButton) {
        this.displayEditButton = displayEditButton;
    }

    public boolean isEnabledButton() {
        return enabledButton;
    }

    public void setEnabledButton(boolean enabledButton) {
        this.enabledButton = enabledButton;
    }

    public String getIdCallback() {
        return idCallback;
    }

    public void setIdCallback(String idCallback) {
        this.idCallback = idCallback;
    }

    public String getCodeHabilitation() {
        return codeHabilitation;
    }

    public void setCodeHabilitation(String codeHabilitation) {
        this.codeHabilitation = codeHabilitation;
    }

    public List<IdLabelObject> getListeParamEvenement() {
        return listeParamEvenement;
    }

    public void setListeParamEvenement(List<IdLabelObject> listeParamEvenement) {
        this.listeParamEvenement = listeParamEvenement;
    }

    public HashMap<String, String> findParamByEvent(String idEvent) {
        if (listeParamEvenement == null || listeParamEvenement.isEmpty()) {
            return new HashMap<>();
        }
        HashMap<String, String> l = new HashMap<>();
        listeParamEvenement.stream().filter(a -> a.getAutre().equals(idEvent)).forEach(a -> {
            l.put(a.getId(), a.getLibelle());

        });
        return l;
    }

    public String getCodeInfoTransition() {
        return codeInfoTransition;
    }

    public void setCodeInfoTransition(String codeInfoTransition) {
        this.codeInfoTransition = codeInfoTransition;
    }

    public List<ActionPermiseExecutorDTO> getListExecutorSelected() {
        return listExecutorSelected;
    }

    public void setListExecutorSelected(List<ActionPermiseExecutorDTO> listExecutorSelected) {
        this.listExecutorSelected = listExecutorSelected;
    }
}
