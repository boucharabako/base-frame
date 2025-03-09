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
import javax.persistence.Table;

/**
 * Represente les implémentation des callback
 *
 * @author W.KOUWONOU
 * @since 13/08/2018
 * @version 1.0.0
 */
@Entity
@Table(name = "spr_w_callbacks", schema = SocleConstant.SCHEMA)
public class WCallBack implements Serializable {

    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "libelle")
    private String libelle;
    
     @Column(name = "implementation")
    private String implementation;

    public WCallBack() {
    }

    public WCallBack(String code, String libelle, String implementation) {
        this.code = code;
        this.libelle = libelle;
        this.implementation = implementation;
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

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    @Override
    public String toString() {
        return "WCallBack{" + "code=" + code + ", libelle=" + libelle + ", implementation=" + implementation + '}';
    }

}
