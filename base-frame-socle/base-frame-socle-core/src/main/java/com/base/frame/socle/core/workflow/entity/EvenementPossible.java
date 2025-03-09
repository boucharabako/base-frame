/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.entity;


import com.base.frame.socle.core.dto.IdLabelObject;
import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.utils.SocleConstant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(name = "spr_w_evenements", uniqueConstraints
        = @UniqueConstraint(columnNames = {"code_action", "code_event", "ide_workflow"}), schema = SocleConstant.SCHEMA)
public class EvenementPossible implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;


    @ManyToOne
    @JoinColumn(name = "code_action")
    private ActionPermise action;

    @ManyToOne
    @JoinColumn(name = "code_event")
    private ParamList event;

    @ManyToOne()
    @JoinColumn(name = "ide_workflow")
    private WorkFlowCycle workFlow;

   @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "event_value")
    @CollectionTable(name = "spr_w_parametres_evenement", joinColumns=@JoinColumn(name="event_id"),schema = SocleConstant.SCHEMA)
    private Map<String, String> parametres;
   
    public EvenementPossible() {
        // Do nothing in this contructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Code d’identification de l’action pour laquelle l’événement est possible
     *
     * @return
     */
    /**
     * Code d’identification de l’action pour laquelle l’événement est possible
     *
     * @return
     */
    public ActionPermise getAction() {
        return action;
    }

    public void setAction(ActionPermise action) {
        this.action = action;
    }

    /**
     * Code unique d’identification de l’événement. Prend ses valeurs dans la
     * liste WF_REFERENTIEL_EVENEMENTS qui matérialise le référentiel
     * d’événements
     *
     * @return
     */
    public ParamList getEvent() {
        return event;
    }

    /**
     * Code unique d’identification de l’événement. Prend ses valeurs dans la
     * liste WF_REFERENTIEL_EVENEMENTS qui matérialise le référentiel
     * d’événements
     *
     * @param event
     */
    public void setEvent(ParamList event) {
        this.event = event;
    }

    /**
     * Identifiant du workflow pour lequel l’action est permise
     *
     * @return
     */
    public WorkFlowCycle getWorkFlow() {
        return workFlow;
    }

    /**
     * Identifiant du workflow pour lequel l’action est permise
     *
     * @param workFlow
     */
    public void setWorkFlow(WorkFlowCycle workFlow) {
        this.workFlow = workFlow;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.action);
        hash = 17 * hash + Objects.hashCode(this.event);
        hash = 17 * hash + Objects.hashCode(this.workFlow);
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
        final EvenementPossible other = (EvenementPossible) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }
        if (!Objects.equals(this.event, other.event)) {
            return false;
        }
        if (!Objects.equals(this.workFlow, other.workFlow)) {
            return false;
        }
        return true;
    }

    public Map<String, String> getParametres() {
        return parametres;
    }

    public void setParametres(Map<String, String> parametres) {
        this.parametres = parametres;
    }
    
    public List<IdLabelObject> parametersToIdLabel(){
        if(parametres!=null && !parametres.isEmpty()){
            List<IdLabelObject> l=new ArrayList<>();
            parametres.forEach((key,value)->{
            l.add(new  IdLabelObject(key, value, event.getId()));
            });
          
            return l;
        }
        return new ArrayList<>();
    }

}
