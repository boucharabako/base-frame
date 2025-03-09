/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

/**
 *
 * @author kouwonou
 */
public class LicenceDto {

    private String cleLicence;
    private String codeClient;
    private String denominationClient;
    private String codeProduit;
    private String libelleProduit;
    private String typeLicence;
    private String libelleTypeLicence;
    private String dateLicence;
    private String codeStatut;
    private String libelleStatut;
    private String macAdresse;

    private String typeInstallation;
    private String codeInstallation;
    
    private Integer nbrInstallProd;
    private Integer nbrInstallTest;
    
     private Integer nbrInstallProdParam;
    private Integer nbrInstallTestParam;
    public String getCleLicence() {
        return cleLicence;
    }

    public void setCleLicence(String cleLicence) {
        this.cleLicence = cleLicence;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getDenominationClient() {
        return denominationClient;
    }

    public void setDenominationClient(String denominationClient) {
        this.denominationClient = denominationClient;
    }

    public String getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

    public String getLibelleProduit() {
        return libelleProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    public String getTypeLicence() {
        return typeLicence;
    }

    public void setTypeLicence(String typeLicence) {
        this.typeLicence = typeLicence;
    }

    public String getLibelleTypeLicence() {
        return libelleTypeLicence;
    }

    public void setLibelleTypeLicence(String libelleTypeLicence) {
        this.libelleTypeLicence = libelleTypeLicence;
    }

    public String getDateLicence() {
        return dateLicence;
    }

    public void setDateLicence(String dateLicence) {
        this.dateLicence = dateLicence;
    }

    public String getCodeStatut() {
        return codeStatut;
    }

    public void setCodeStatut(String codeStatut) {
        this.codeStatut = codeStatut;
    }

    public String getLibelleStatut() {
        return libelleStatut;
    }

    public void setLibelleStatut(String libelleStatut) {
        this.libelleStatut = libelleStatut;
    }

    public String getMacAdresse() {
        return macAdresse;
    }

    public void setMacAdresse(String macAdresse) {
        this.macAdresse = macAdresse;
    }

    public String getTypeInstallation() {
        return typeInstallation;
    }

    public void setTypeInstallation(String typeInstallation) {
        this.typeInstallation = typeInstallation;
    }

    public String getCodeInstallation() {
        return codeInstallation;
    }

    public void setCodeInstallation(String codeInstallation) {
        this.codeInstallation = codeInstallation;
    }

    public Integer getNbrInstallProd() {
        return nbrInstallProd;
    }

    public void setNbrInstallProd(Integer nbrInstallProd) {
        this.nbrInstallProd = nbrInstallProd;
    }

    public Integer getNbrInstallTest() {
        return nbrInstallTest;
    }

    public void setNbrInstallTest(Integer nbrInstallTest) {
        this.nbrInstallTest = nbrInstallTest;
    }

    public Integer getNbrInstallProdParam() {
        return nbrInstallProdParam;
    }

    public void setNbrInstallProdParam(Integer nbrInstallProdParam) {
        this.nbrInstallProdParam = nbrInstallProdParam;
    }

    public Integer getNbrInstallTestParam() {
        return nbrInstallTestParam;
    }

    public void setNbrInstallTestParam(Integer nbrInstallTestParam) {
        this.nbrInstallTestParam = nbrInstallTestParam;
    }

    @Override
    public String toString() {
        return "LicenceDto{" + "cleLicence=" + cleLicence + ", codeClient=" + codeClient + ", denominationClient=" + denominationClient + ", codeProduit=" + codeProduit + ", libelleProduit=" + libelleProduit + ", typeLicence=" + typeLicence + ", libelleTypeLicence=" + libelleTypeLicence + ", dateLicence=" + dateLicence + ", codeStatut=" + codeStatut + ", libelleStatut=" + libelleStatut + '}';
    }

}
