/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.entity;


import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.utils.Color;
import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.utils.audit.AbstractAuditingEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * organisent les workflows en étapes successives.
 *
 * @author W.KOUWONOU
 * @since 13/08/2018
 * @version 1.0.0
 */
        @Entity
        @Table(name = "spr_w_etats", uniqueConstraints = {
            @UniqueConstraint(columnNames = {"code_etat", "ide_workflow"})
            ,
            @UniqueConstraint(columnNames = {"ide_etape", "ide_workflow", "ide_sequence"})},
                schema = SocleConstant.SCHEMA)
        public class Etat extends AbstractAuditingEntity implements Serializable {

            @Id
            @Column(name = "id", length = 40)
            @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
            @GenericGenerator(name = "system-uuid", strategy = "uuid2")
            private String id;

            @Column(name = "ide_sequence")
            private Integer ideSequence;
            @Column(name = "ide_etape")
            private Integer ideEtape;

            @Column(name = "libelle_etat")
            private String libelleEtat;

            @Column(name = "code_etat")
            private String codeEtat;

            @JoinColumn(name = "ide_workflow")
            @ManyToOne
            private WorkFlowCycle workflow;

            @Column(name = "code_couleur")
            private String codeCouleur;

            @ManyToOne
            @JoinColumn(name = "indic_modif_authorisee")

            private ParamList indicModifAuthorisee;
            @ManyToOne
            @JoinColumn(name = "ind_etape_validation")

            private ParamList indicEtapeValidation;
            @Transient
            @JsonProperty
            private String codeCouleurText;
            @Transient
            @JsonProperty
            private Boolean modif = false;
            @Transient
            @JsonProperty
            private Boolean etapeValidation = false;

            @Column(name = "delai_execution")
            private BigDecimal delaiExecution;

    public Etat() {
    }

    public Etat(String id, Integer ideSequence, Integer ideEtape, String libelleEtat, String codeEtat, String codeCouleur) {
        this.id = id;
        this.ideSequence = ideSequence;
        this.ideEtape = ideEtape;
        this.libelleEtat = libelleEtat;
        this.codeEtat = codeEtat;
        this.codeCouleur = codeCouleur;
    }
    
    

    /**
     * Identifiant unique
     *
     * @return Identifiant
     */
    public String getId() {
        return id;
    }

    /**
     * Identifiant unique
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdeSequence() {
        return ideSequence;
    }

    public void setIdeSequence(Integer ideSequence) {
        this.ideSequence = ideSequence;
    }

    public Integer getIdeEtape() {
        return ideEtape;
    }

    public void setIdeEtape(Integer ideEtape) {
        this.ideEtape = ideEtape;
    }

    public String getLibelleEtat() {
        return libelleEtat;
    }

    public void setLibelleEtat(String libelleEtat) {
        this.libelleEtat = libelleEtat;
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

    /**
     * code identifiant numerique par rapport au workflow
     *
     * @return
     */
    /**
     * Identifiant du Workflow associé au type de document
     *
     * @return
     */
    public WorkFlowCycle getWorkflow() {
        return workflow;
    }

    /**
     * Identifiant du Workflow associé au type de document
     *
     * @param workflow
     */
    public void setWorkflow(WorkFlowCycle workflow) {
        this.workflow = workflow;
    }

    public String getCodeEtat() {
        return codeEtat;
    }

    public void setCodeEtat(String codeEtat) {
        this.codeEtat = codeEtat;
    }

    public ParamList getIndicModifAuthorisee() {
        return indicModifAuthorisee;
    }

    public void setIndicModifAuthorisee(ParamList indicModifAuthorisee) {
        this.indicModifAuthorisee = indicModifAuthorisee;
    }

    public Boolean getModif() {
        if (indicModifAuthorisee != null && indicModifAuthorisee.getCode().equals(SocleConstant.OUI_NON_OUI)) {
            modif = true;
        }
        if (indicModifAuthorisee != null && indicModifAuthorisee.getCode().equals(SocleConstant.OUI_NON_NON)) {
            modif = false;
        }
        return modif;
    }

    public Boolean getEtapeValidation() {
        if (indicEtapeValidation != null && indicEtapeValidation.getCode().equals(SocleConstant.OUI_NON_OUI)) {
            etapeValidation = true;
        }
        if (indicEtapeValidation != null && indicEtapeValidation.getCode().equals(SocleConstant.OUI_NON_NON)) {
            etapeValidation = false;
        }
        return etapeValidation;
    }

    public void setEtapeValidation(Boolean etapeValidation) {
        this.etapeValidation = etapeValidation;
    }

    public void setModif(Boolean modif) {
        this.modif = modif;
    }

    public ParamList getIndicEtapeValidation() {
        return indicEtapeValidation;
    }

    public void setIndicEtapeValidation(ParamList indicEtapeValidation) {
        this.indicEtapeValidation = indicEtapeValidation;
    }

    public BigDecimal getDelaiExecution() {
        return delaiExecution;
    }

    public void setDelaiExecution(BigDecimal delaiExecution) {
        this.delaiExecution = delaiExecution;
    }

    @Override
    public String toString() {
        return "Etat{" + "id=" + id + ", ideSequence=" + ideSequence + ", ideEtape=" + ideEtape + ", libelleEtat=" + libelleEtat + ", codeEtat=" + codeEtat + ", workflow=" + workflow + ", codeCouleur=" + codeCouleur + ", indicModifAuthorisee=" + indicModifAuthorisee + ", indicEtapeValidation=" + indicEtapeValidation + ", codeCouleurText=" + codeCouleurText + ", modif=" + modif + ", etapeValidation=" + etapeValidation + ", delaiExecution=" + delaiExecution + '}';
    }

}
