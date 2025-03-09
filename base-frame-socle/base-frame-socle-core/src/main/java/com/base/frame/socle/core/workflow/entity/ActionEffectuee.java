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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Les actions effectuées : elles matérialisent, parmi les actions permises,
 * celles qui ont effectivement été effectuées à un moment ou à un autre par un
 * utilisateur. Elles permettent de tracer et de suivre les actions
 * effectivement entreprises par les utilisateurs le long du workflow.
 *
 * @author W.KOUWONOU
 * @since 13/08/2018
 * @version 1.0.0
 */
@Entity
@Table(name = "spr_w_actions_effectuees", schema = SocleConstant.SCHEMA)
public class ActionEffectuee extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name = "id", length = 40)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "code_action")
    private ActionPermise action;

    @Column(name = "lieu")
    private String lieu;

    @Column(name = "ip")
    private String ip;

    @Column(name = "terminal")
    private String terminal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ActionPermise getAction() {
        return action;
    }

    public void setAction(ActionPermise action) {
        this.action = action;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    /**
     * Adresse IP à partir de laquelle l’opération a été effectuée
     *
     * @return
     */
    public String getIp() {
        return ip;
    }

    /**
     * Adresse IP à partir de laquelle l’opération a été effectuée
     *
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Nom du terminal (poste, tablette, téléphone) à partir duquel l’opération
     * a été effectué
     *
     * @return
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * Nom du terminal (poste, tablette, téléphone) à partir duquel l’opération
     * a été effectué
     *
     * @param terminal
     */
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "ActionEffectuee{" + "id=" + id + ", action=" + action + ", lieu=" + lieu + ", ip=" + ip + ", terminal=" + terminal + '}';
    }
    
    

}
