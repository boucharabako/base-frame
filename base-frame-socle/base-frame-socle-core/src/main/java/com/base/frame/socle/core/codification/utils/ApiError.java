/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.utils;

import java.util.List;

/**
 *
 * @author rkoufionou
 */
public class ApiError {

    public static final String SUCCESS_CODE = "200";
    public static final String ERROR_CODE = "500";
    
    private String statut;

    private TypeError typeError;

    private String principal;

    private List<ErrorMessage> errorMessages;

    public ApiError() {
        //do nothing
    }

    public ApiError(String statut, TypeError typeError, String principal) {
        this.statut = statut;
        this.typeError = typeError;
        this.principal = principal;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public TypeError getTypeError() {
        return typeError;
    }

    public void setTypeError(TypeError typeError) {
        this.typeError = typeError;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<ErrorMessage> errorMessages) {
        this.errorMessages = errorMessages;
    }

}
