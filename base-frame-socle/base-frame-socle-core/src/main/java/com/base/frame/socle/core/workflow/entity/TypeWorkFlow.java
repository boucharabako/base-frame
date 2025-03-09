/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.entity;


import com.base.frame.socle.core.utils.SocleConstant;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Les types de workflow : les workflows sont organisés par type afin de
 * distinguer les workflows système (compilés avec le logiciel) des workflows
 * créés et maintenus par les utilisateurs.
 *
 * @author W.KOUWONOU
 * @since 13/08/2018
 * @version 1.0.0
 */
@Entity
@Table(name = "spr_w_type_workflow", schema = SocleConstant.SCHEMA)
public class TypeWorkFlow implements Serializable {

    @Id
    @Column(name = "code")
    private String code;
    
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "dernier_numero")
    private Integer dernierNumero;

    
    @ManyToOne
    
    @JoinColumn(name = "etat_id")
    private Etat etat;
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

    public Integer getDernierNumero() {
        return dernierNumero;
    }

    public void setDernierNumero(Integer dernierNumero) {
        this.dernierNumero = dernierNumero;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "TypeWorkFlow{" + "code=" + code + ", libelle=" + libelle + ", dernierNumero=" + dernierNumero + '}';
    }
    
    
}
