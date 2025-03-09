///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.utils.CodificationConstant;
//import java.math.BigDecimal;
//import org.hibernate.validator.constraints.NotBlank;
//
///**
// *
// * @author Assima
// */
//public class ClasseurRubriqueDTO {
//
//    @NotBlank(message = CodificationConstant.F_REQUIERD)
//    private String id;
//    private String idVentilation;
//    @NotBlank(message = CodificationConstant.F_REQUIERD)
//    private String code;
//    private String libelle;
//    private String codification;
//    @NotBlank(message = CodificationConstant.F_REQUIERD)
//    private BigDecimal montantRubrique = BigDecimal.ZERO;
//    private BigDecimal oldMontantRubrique = BigDecimal.ZERO;
//    private boolean disabled = false;
//    public ClasseurRubriqueDTO() {
//    }
//
//    public ClasseurRubriqueDTO(String id, String code, String libelle) {
//        this.id = id;
//        this.code = code;
//        this.libelle = libelle;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getLibelle() {
//        return libelle;
//    }
//
//    public void setLibelle(String libelle) {
//        this.libelle = libelle;
//    }
//
//    public String getCodification() {
//        return codification;
//    }
//
//    public void setCodification(String codification) {
//        this.codification = codification;
//    }
//
//    public BigDecimal getMontantRubrique() {
//        return montantRubrique;
//    }
//
//    public void setMontantRubrique(BigDecimal montantRubrique) {
//        this.montantRubrique = montantRubrique;
//    }
//
//    public BigDecimal getOldMontantRubrique() {
//        return oldMontantRubrique;
//    }
//
//    public void setOldMontantRubrique(BigDecimal oldMontantRubrique) {
//        this.oldMontantRubrique = oldMontantRubrique;
//    }
//
//    public String getIdVentilation() {
//        return idVentilation;
//    }
//
//    public void setIdVentilation(String idVentilation) {
//        this.idVentilation = idVentilation;
//    }
//
//    public boolean isDisabled() {
//        return disabled;
//    }
//
//    public void setDisabled(boolean disabled) {
//        this.disabled = disabled;
//    }
//
//    @Override
//    public String toString() {
//        return "ClasseurRubriqueDTO{" + "id=" + id + ", idVentilation=" + idVentilation + ", code=" + code + ", libelle=" + libelle + ", codification=" + codification + ", montantRubrique=" + montantRubrique + ", oldMontantRubrique=" + oldMontantRubrique + ", disabled=" + disabled + '}';
//    }
//
//}
