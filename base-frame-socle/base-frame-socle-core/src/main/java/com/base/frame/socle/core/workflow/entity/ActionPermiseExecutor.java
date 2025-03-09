/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.entity;


import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.utils.audit.AbstractAuditingEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Sewa
 */
@Entity
@Table(name = "spr_w_actions_permise_executor", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"action_id", "callbak_id"})}, schema = SocleConstant.SCHEMA)
public class ActionPermiseExecutor extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", length = 40)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column(name = "action_id")
    private String actionPermise;

    @Column(name = "callbak_id")
    private String callBack;

    @Column(name = "numero_ordre")
    private Integer numeroOrdre;

    public ActionPermiseExecutor() {
    }

    public ActionPermiseExecutor(String id, String actionPermise, String callBack) {
        this.id = id;
        this.actionPermise = actionPermise;
        this.callBack = callBack;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActionPermise() {
        return actionPermise;
    }

    public void setActionPermise(String actionPermise) {
        this.actionPermise = actionPermise;
    }

    public String getCallBack() {
        return callBack;
    }

    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }

    public Integer getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    @Override
    public String toString() {
        return "ActionPermiseExecutor{" + "id=" + id + ", actionPermise=" + actionPermise + ", callBack=" + callBack + ", numeroOrdre=" + numeroOrdre + '}';
    }

}
