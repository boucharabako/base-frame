/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

import com.base.frame.socle.core.utils.Color;



/**
 *
 * @author kouwonou
 */
public class ActionDTO {

    private String id;
    private String idAction;
    private String libelle;
    private String codeCouleur;
    private String codeCouleurText;
    private String codeCallback;
    private String commentaire;
    private String code;
    public ActionDTO() {
    }

    public ActionDTO(String id, String idAction, String libellle) {
        this.id = id;
        this.idAction = idAction;
        this.libelle = libellle;
    }

    public ActionDTO(String id, String idAction, String libelle, String codeCouleur) {
        this.id = id;
        this.idAction = idAction;
        this.libelle = libelle;
        this.codeCouleur = codeCouleur;
    }
    public ActionDTO(String id, String idAction, String libelle, String codeCouleur,
            String codeCallback) {
        this.id = id;
        this.idAction = idAction;
        this.libelle = libelle;
        this.codeCouleur = codeCouleur;
        this.codeCallback = codeCallback;

    }

    public String getId() {
        return id;
    }

    public String getCodeCallback() {
        return codeCallback;
    }

    public void setCodeCallback(String codeCallback) {
        this.codeCallback = codeCallback;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAction() {
        return idAction;
    }

    public void setIdAction(String idAction) {
        this.idAction = idAction;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCodeCouleur() {
        return codeCouleur;
    }

    public void setCodeCouleur(String codeCouleur) {
        this.codeCouleur = codeCouleur;
    }

    public String getCodeCouleurText() {
        return Color.textColorByBackgroundColor(codeCouleur);
    }

    public void setCodeCouleurText(String codeCouleurText) {
        this.codeCouleurText = codeCouleurText;
    }

    @Override
    public String toString() {
        return "ActionDTO{" + "id=" + id + ", idAction=" + idAction + ", libelle=" + libelle + ", codeCouleur=" + codeCouleur + ", codeCouleurText=" + codeCouleurText + ", codeCallback=" + codeCallback + '}';
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    

}
