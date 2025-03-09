/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.entity;

import com.base.frame.account.utils.AccountConstant;
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
@Table(name = "sec_groupe_profil",  uniqueConstraints = @UniqueConstraint(columnNames = {"profil", "groupe"}), schema = AccountConstant.SCHEMA)
public class GroupeProfil extends AbstractAuditingEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "groupe", referencedColumnName = "id", nullable = false)
    private Groupe groupe;
    
    @ManyToOne
    @JoinColumn(name = "profil" ,referencedColumnName = "id", nullable = false)
    private Profil profil;

    public GroupeProfil(Groupe groupe, Profil profil) {
        this.groupe = groupe;
        this.profil = profil;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
    
    
    
}
