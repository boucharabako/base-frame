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
 * Les événements: un événement constate un fait générateur qui peut être la
 * conséquence d’une action ou d’une échéance. Le référentiel des événements est
 * matérialisé par une liste décrite plus loin. On distingue les événements
 * possibles des événements générés. o Les événements possibles définissent pour
 * chaque action le ou les événements qui peuvent être générés o Les événements
 * générés matérialisent le constat de l’occurrence d’un événement possible
 *
 * @author W.KOUWONOU
 * @since 13/08/2018
 * @version 1.0.0
 */
@Entity
@Table(name = "spr_w_evenement_genere", schema = SocleConstant.SCHEMA)
public class EvenementGenere extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;

    @JoinColumn(name = "ide_action")
    @ManyToOne
    private ActionPermise actionPermise;

    /**
     * LISTE EVENT_STATUT_PARAM
     */
    @JoinColumn(name = "event_statut")
    @ManyToOne
    private ParamList statut;

    @JoinColumn(name = "code_event")
    @ManyToOne
    private EvenementPossible evenement;

    @Column(name = "id_objet", nullable = false)
    private String idObjet;

    @JoinColumn(name = "concept_metier")
    @ManyToOne
    private ParamList conceptMeter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ActionPermise getActionPermise() {
        return actionPermise;
    }

    public void setActionPermise(ActionPermise actionPermise) {
        this.actionPermise = actionPermise;
    }

    public ParamList getStatut() {
        return statut;
    }

    public void setStatut(ParamList statut) {
        this.statut = statut;
    }

    public EvenementPossible getEvenement() {
        return evenement;
    }

    public void setEvenement(EvenementPossible evenement) {
        this.evenement = evenement;
    }

    public String getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(String idObjet) {
        this.idObjet = idObjet;
    }

    public ParamList getConceptMeter() {
        return conceptMeter;
    }

    public void setConceptMeter(ParamList conceptMeter) {
        this.conceptMeter = conceptMeter;
    }

}
