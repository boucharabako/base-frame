/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

import com.base.frame.socle.core.codification.utils.CodificationConstant;
import com.base.frame.socle.core.workflow.entity.TypeWorkFlow;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author kouwonou
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CycleDTO {

    private String id;

    private String description;

    @NotBlank(message = CodificationConstant.F_REQUIERD)
    private String libelle;

    @NotBlank(message = CodificationConstant.F_REQUIERD)
    private String listeAction;

    private String listeActionLibelle;

    @NotBlank(message = CodificationConstant.F_REQUIERD)
    private String codeTypeWorkflow;

    private TypeWorkFlow typeWorkflow;
    private String idObject;

    private Integer numero;

    private StatutDto statutDto;

    private String[] listeObjets;
    private String num;

    // pour type bien 
    private List<EtatDTO> etape = new ArrayList<>();
    
    private BigDecimal delaiExecution;

    public BigDecimal getDelaiExecution() {
        return delaiExecution;
    }

    public void setDelaiExecution(BigDecimal delaiExecution) {
        this.delaiExecution = delaiExecution;
    }

   

    public List<EtatDTO> getEtape() {
        return etape;
    }

    public void setEtape(List<EtatDTO> etape) {
        this.etape = etape;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getListeAction() {
        return listeAction;
    }

    public void setListeAction(String listeAction) {
        this.listeAction = listeAction;
    }

    public String getCodeTypeWorkflow() {
        return codeTypeWorkflow;
    }

    public void setCodeTypeWorkflow(String codeTypeWorkflow) {
        this.codeTypeWorkflow = codeTypeWorkflow;
    }

    public TypeWorkFlow getTypeWorkflow() {
        return typeWorkflow;
    }

    public void setTypeWorkflow(TypeWorkFlow typeWorkflow) {
        this.typeWorkflow = typeWorkflow;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public StatutDto getStatutDto() {
        return statutDto;
    }

    public void setStatutDto(StatutDto statutDto) {
        this.statutDto = statutDto;
    }

    public String getListeActionLibelle() {
        return listeActionLibelle;
    }

    public void setListeActionLibelle(String listeActionLibelle) {
        this.listeActionLibelle = listeActionLibelle;
    }

    public String[] getListeObjets() {
        return listeObjets;
    }

    public void setListeObjets(String[] listeObjets) {
        this.listeObjets = listeObjets;
    }

    public String getIdObject() {
        return idObject;
    }

    public void setIdObject(String idObject) {
        this.idObject = idObject;
    }

    public String getNum() {
        num = numero.toString();
        if (numero != null && numero.toString().length() < 7) {
            for (int i = 0; i < 7 - numero.toString().length(); i++) {
                num = "0" + num;
            }
        }
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


    @Override
    public String toString() {
        return "CycleDTO{" + "id=" + id + ", description=" + description + ", libelle=" + libelle + ", listeAction=" + listeAction + ", listeActionLibelle=" + listeActionLibelle + ", codeTypeWorkflow=" + codeTypeWorkflow + ", typeWorkflow=" + typeWorkflow + ", idObject=" + idObject + ", numero=" + numero + ", statutDto=" + statutDto + ", listeObjets=" + listeObjets + ", num=" + num + ", etape=" + etape + ", delaiExecution=" + delaiExecution + '}';
    }

}
