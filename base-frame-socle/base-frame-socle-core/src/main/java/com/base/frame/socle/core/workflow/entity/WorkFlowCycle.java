/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.entity;


import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.utils.audit.AbstractAuditingEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Les workflows : ils matérialisent des flux collaboratifs et se composent
 * d’une ou plusieurs étapes, chaque étape matérialisant à son tour un état
 * instantané des informations échangées ainsi que les possibles échanges entre
 * acteurs.
 *
 * @author W.KOUWONOU
 * @since 19/03/2018
 * @version 1.0.0
 */

@Entity
@Table(name = "spr_w_workflows_cycle", schema = SocleConstant.SCHEMA)
public class WorkFlowCycle extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;
    
    
    @Column(name = "numero")
    private Integer numero;
    
    @Column(name = "nombre_jours")
    private BigDecimal delaiExecution;

    @Column(name = "description", length = 500)
    private String description;
    
    @Column(name = "libelle", length = 100)
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "code_liste_actions", referencedColumnName = "code_codif")
    private Codification codeListeActions;

    @ManyToOne
    @JoinColumn(name = "code_type_workflow")
    private TypeWorkFlow typeWorkflow;


    @ManyToOne
    @JoinColumn(name = "code_statut", referencedColumnName = "id")
    private ParamList statut;

    public BigDecimal getDelaiExecution() {
        return delaiExecution;
    }

    public void setDelaiExecution(BigDecimal delaiExecution) {
        this.delaiExecution = delaiExecution;
    }
    

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumero() {
        
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public TypeWorkFlow getTypeWorkflow() {
        return typeWorkflow;
    }

    public void setTypeWorkflow(TypeWorkFlow typeWorkflow) {
        this.typeWorkflow = typeWorkflow;
    }

 

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Codification getCodeListeActions() {
        return codeListeActions;
    }

    public void setCodeListeActions(Codification codeListeActions) {
        this.codeListeActions = codeListeActions;

    }


    public ParamList getStatut() {
        return statut;
    }

    public void setStatut(ParamList statut) {
        this.statut = statut;
    }

  

    @Override
    public String toString() {
        return "WorkFlow{" + "id=" + id + '}';
    }

  
    

    public boolean getVisible() {
        return statut == null || !statut.getCode().equals(SocleConstant.STATUT_PARAM_OBSOLETE);
    }

    

    public boolean getVisible2() {
        return statut == null || statut.getCode().equals(SocleConstant.STATUT_PARAM_EN_SAISIE);
    }

   

    
}
