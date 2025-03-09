/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.entity;

import com.base.frame.account.utils.AccountConstant;
import com.base.frame.socle.core.entity.ParamList;
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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Alassani
 */
@Entity
@Table(name = "sec_groupes", uniqueConstraints = @UniqueConstraint(columnNames = {"code"}), schema = AccountConstant.SCHEMA)
public class Groupe extends AbstractAuditingEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "intitule", nullable = false)
    private String intitule;

    @Column(name = "description")
    private String description;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "etat", nullable = false)
//    private Etat etat = Etat.INACTIF;
    @ManyToOne
    @JoinColumn(name = "etat", referencedColumnName = "id", nullable = false)
    private ParamList etat;

    public Groupe(String code, String intitule, ParamList etat) {
        this.code = code;
        this.intitule = intitule;
        this.etat = etat;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParamList getEtat() {
        return etat;
    }

    public void setEtat(ParamList etat) {
        this.etat = etat;
    }
    
    
    
}
