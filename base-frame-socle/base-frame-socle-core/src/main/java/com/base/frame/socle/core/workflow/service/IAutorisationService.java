/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;


import com.base.frame.socle.core.dto.IdLabelObject;
import com.base.frame.socle.core.workflow.entity.Etat;
import java.util.ArrayList;
import java.util.List;

/**
 * core-codification
 *
 * @author Pierre NGS creée le 20 août 2018 13:04:20
 */
public interface IAutorisationService {

     public default void addAutorisation(String idObjet, String idObjetAutorisant) {

    }

    public default  String getTypeAutorisationEtendu() {
        return null;
    }

    public default  String getTypeAutorisationLimite() {
        return null;
    }

    public default  List<IdLabelObject> getProfilAutoriseForAction(String idObjet) {

        return new ArrayList<>();
    }

    public default  void deleteAutoriseForAction(String codeConceptMetierObjectAutorise, String codeTypeAutorisation, String codeConceptObjectAutorisant, String idObjectAutorisant) {

    }

    public default  boolean isUserProfileIsAuthorizeByAtleastOneActionOfTheInitialStateOfTheWorkflow(Etat etatInitialWorkflow, String codeConceptMetierObjectAutorise, String codeTypeAutorisation) {
        return false;
    }
}
