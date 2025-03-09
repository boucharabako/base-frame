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
import java.util.Objects;
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
 * elles définissent pour chaque état, les actions qui peuvent être entreprises
 * pour faire évoluer le workflow. Si aucune action n’est permise à partir d’un
 * état, alors cet état matérialise un terminaison de workflow.
 *
 * @author W.KOUWONOU
 * @since 13/08/2018
 * @version 1.0.0
 */
@Entity
@Table(name = "spr_w_actions_permise", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code_action", "code_etat"})}, schema = SocleConstant.SCHEMA)
public class ActionPermise extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", length = 40)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "code_action")
    private ParamList action;

    @ManyToOne
    @JoinColumn(name = "code_etat")
    private Etat etat;
    @ManyToOne
    @JoinColumn(name = "code_etat_suivant")
    private Etat etatSuivant;

    @Column(name = "code_couleur")
    private String codeCouleur;

    @ManyToOne
    @JoinColumn(name = "callbak_id")
    private WCallBack callBack;

    /**
     * Liste codifiée ACTIONS_ECRAN
     */
    @ManyToOne
    @JoinColumn(name = "niveau_habilitation", referencedColumnName = "id")
    private ParamList habilitation;

    @Transient
    @JsonProperty
    private String codeCouleurText;
    
    @ManyToOne
    @JoinColumn(name = "indic_comment")
    private ParamList indicateurCommentaire;
    
    @ManyToOne
    @JoinColumn(name = "info_transition")
    private ParamList infoTransition;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Code unique d’identification de l’action. Prend ses valeurs dans la liste
     * des actions attachée à la définition du workflow.
     *
     * @return
     */
    public ParamList getAction() {
        return action;
    }

    /**
     * Code unique d’identification de l’action. Prend ses valeurs dans la liste
     * des actions attachée à la définition du workflow.
     *
     * @param action
     */
    public void setAction(ParamList action) {
        this.action = action;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final ActionPermise other = (ActionPermise) obj;
        return Objects.equals(this.id, other.id);
    }

  

    public Etat getEtatSuivant() {
        return etatSuivant;
    }

    public void setEtatSuivant(Etat etatSuivant) {
        this.etatSuivant = etatSuivant;
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

    public WCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(WCallBack callBack) {
        this.callBack = callBack;
    }

    public ParamList getHabilitation() {
        return habilitation;
    }

    public void setHabilitation(ParamList habilitation) {
        this.habilitation = habilitation;
    }

    public ParamList getIndicateurCommentaire() {
        return indicateurCommentaire;
    }

    public void setIndicateurCommentaire(ParamList indicateurCommentaire) {
        this.indicateurCommentaire = indicateurCommentaire;
    }

    public ParamList getInfoTransition() {
        return infoTransition;
    }

    public void setInfoTransition(ParamList infoTransition) {
        this.infoTransition = infoTransition;
    }

    @Override
    public String toString() {
        return "ActionPermise{" + "id=" + id + ", action=" + action + ", etat=" + etat + ", etatSuivant=" + etatSuivant + ", codeCouleur=" + codeCouleur + ", callBack=" + callBack + ", habilitation=" + habilitation + ", codeCouleurText=" + codeCouleurText + ", indicateurCommentaire=" + indicateurCommentaire + ", infoTransition=" + infoTransition + '}';
    }
    
    

}
