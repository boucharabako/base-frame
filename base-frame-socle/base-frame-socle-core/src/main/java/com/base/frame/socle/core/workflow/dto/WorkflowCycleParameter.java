/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author kouwonou
 */
public class WorkflowCycleParameter {

    private String concepMetier;
    private String idObjet;
    private String codeFonction;
    private String idAction;
    private String commentaire;
    private String[] users;
    private String  espaceDoc;
     //private List<ExtensionSimpleObject> extensionsList = new ArrayList<>();
    @JsonDeserialize()
    private HashMap<String, Object> paramsExecutor;

    private String executor;

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getConcepMetier() {
        return concepMetier;
    }

    public void setConcepMetier(String concepMetier) {
        this.concepMetier = concepMetier;
    }

    public String getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(String idObjet) {
        this.idObjet = idObjet;
    }

    public String getIdAction() {
        return idAction;
    }

    public void setIdAction(String idAction) {
        this.idAction = idAction;
    }

    public String getCodeFonction() {
        return codeFonction;
    }

    public void setCodeFonction(String codeFonction) {
        this.codeFonction = codeFonction;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }

    public HashMap<String, Object> getParamsExecutor() {
        return paramsExecutor;
    }

    public void setParamsExecutor(HashMap<String, Object> paramsExecutor) {
        this.paramsExecutor = paramsExecutor;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

//    public List<ExtensionSimpleObject> getExtensionsList() {
//        return extensionsList;
//    }
//
//    public void setExtensionsList(List<ExtensionSimpleObject> extensionsList) {
//        this.extensionsList = extensionsList;
//    }

    @Override
    public String toString() {
        return "WorkflowCycleParameter{" + "concepMetier=" + concepMetier + ", idObjet=" + idObjet + ", codeFonction=" + codeFonction + ", idAction=" + idAction + '}';
    }

    public String getEspaceDoc() {
        return espaceDoc;
    }

    public void setEspaceDoc(String espaceDoc) {
        this.espaceDoc = espaceDoc;
    }

   

}
