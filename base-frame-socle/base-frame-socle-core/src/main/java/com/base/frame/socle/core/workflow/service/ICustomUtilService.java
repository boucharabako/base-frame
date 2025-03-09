/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;


import com.base.frame.socle.core.dto.IdLabelObject;
import com.base.frame.socle.core.workflow.dto.UtilisateurDTOV2;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * core-codification
 *
 * @author Pierre NGS creée le 20 août 2018 13:04:20
 */
public interface ICustomUtilService {

    public default List<IdLabelObject> listProfils() {
        return new ArrayList<>();
    }

    public default Optional<String> getCurrentUserLogin() {
        return Optional.empty();
    }

    public default List<UtilisateurDTOV2> getUsersByUsernames(Object[] usernames) {
        return new ArrayList<>();
    }

    public default List<UtilisateurDTOV2> getUserByUsernames(Object[] username) {
        return new ArrayList<>();
    }

    public default List<UtilisateurDTOV2> getUserByProfils(Object[] profils) {
        return new ArrayList<>();
    }

    public default List<UtilisateurDTOV2> getUserByProfil(String profil) {
        return new ArrayList<>();
    }

    public default boolean isCurrentUserInRole(String authority) {
        return false;
    }

    public default String getConcepteMetierPFU() {
        return null;
    }

    public default List<String> getCurrentUserProfil() {
        return new ArrayList<>();
    }

    public default List<String> getProfilByUserLogin(String userLogin) {
        return new ArrayList<>();
    }

    public default List<String> getAuthorities() {
        return new ArrayList<>();

    }

    public default String findNiveauHabilitationOfUserByFunction(String codeFunction) {
        return null;
    }

    public default String findNiveauHabilitationByUserLoginAndFunction(String codeFunction, String userLogin) {
        return null;
    }

    public default boolean userHasHabilitationConfiguredOnWkfAction(String niveauHabilitationActionWKF, String codeFunction) {
        return false;
    }
}
