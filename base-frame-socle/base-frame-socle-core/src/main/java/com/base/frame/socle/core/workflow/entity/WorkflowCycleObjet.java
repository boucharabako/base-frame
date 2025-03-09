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
import java.util.Objects;
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
 * elles définissent pour chaque état, les actions qui peuvent être entreprises
 * pour faire évoluer le workflow. Si aucune action n’est permise à partir d’un
 * état, alors cet état matérialise un terminaison de workflow.
 *
 * @author W.KOUWONOU
 * @since 13/08/2018
 * @version 1.0.0
 */
@Entity
@Table(name = "spr_w_workflow_cycle_objet", 
     schema = SocleConstant.SCHEMA)
public class WorkflowCycleObjet extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name = "id")
      @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @JoinColumn(name = "domaine_id")
    @ManyToOne
    private ParamList domaine;
    @JoinColumn(name = "workflow_id")
    @ManyToOne
    private WorkFlowCycle workflow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ParamList getDomaine() {
        return domaine;
    }

    public void setDomaine(ParamList domaine) {
        this.domaine = domaine;
    }

    public WorkFlowCycle getWorkflow() {
        return workflow;
    }

    public void setWorkflow(WorkFlowCycle workflow) {
        this.workflow = workflow;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.domaine);
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
        final WorkflowCycleObjet other = (WorkflowCycleObjet) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.domaine, other.domaine);
    }

    @Override
    public String toString() {
        return "WorkflowCycleObjet{" + "id=" + id + ", domaine=" + domaine + ", workflow=" + workflow + '}';
    }

}
