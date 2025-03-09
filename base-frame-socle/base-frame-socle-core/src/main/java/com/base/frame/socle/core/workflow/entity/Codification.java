/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.entity;


import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.utils.audit.AbstractAuditingEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Structure des codifications
 *
 * @author Adnaane
 * @modifiedBy William Kouwonou
 * @dateModification 06/03/2018
 * @version 1.0.0
 * @since 27/02/2018
 * @Modification @date 26/04/2018 @Author W.KOUWONOU @Action Ajout du workflow
 * statut; Supression de modeleModification
 */
@Entity
@Table(name = "spr_codifications",schema = SocleConstant.SCHEMA)
public class Codification extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name = "code_codif", nullable = false, length = 40)
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "porte", referencedColumnName = "id")
    private ParamList porte;

    @ManyToOne
    @JoinColumn(name = "type_codif", referencedColumnName = "id")
    private ParamList typeCodif;

    @ManyToOne
    @JoinColumn(name = "domaine", referencedColumnName = "id")
    private ParamList domaine;

    @ManyToOne()
    @JoinColumn(name = "code_statut", referencedColumnName = "id")
    private ParamList statut;

    @Column(name = "date_activation")
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateActivation;

    public Codification() {
    }

    public Codification(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public ParamList getStatut() {
        return statut;
    }

    public void setStatut(ParamList statut) {
        this.statut = statut;
    }

    public ParamList getPorte() {
        return porte;
    }

    public void setPorte(ParamList porte) {
        this.porte = porte;
    }

    public ParamList getTypeCodif() {
        return typeCodif;
    }

    public void setTypeCodif(ParamList typeCodif) {
        this.typeCodif = typeCodif;
    }

    public ParamList getDomaine() {
        return domaine;
    }

    public void setDomaine(ParamList domaine) {
        this.domaine = domaine;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Codification other = (Codification) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return "Codification{" + "code=" + code + ", libelle=" + libelle + '}';
    }

    public Date getDateActivation() {
        return dateActivation;
    }

    public void setDateActivation(Date dateActivation) {
        this.dateActivation = dateActivation;
    }

}
