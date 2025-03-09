/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.entity;

import com.base.frame.account.utils.AccountConstant;
import com.base.frame.socle.core.entity.Fonction;
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
@Table(name = "sec_habilitations",  uniqueConstraints = @UniqueConstraint(columnNames = {"profil", "fonction"}), schema = AccountConstant.SCHEMA)
public class Habilitation extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "profil", nullable = false)
    private Profil profil;
    
    @ManyToOne
    @JoinColumn(name = "fonction", nullable = false)
    private Fonction fonction;
    
     @ManyToOne
    @JoinColumn(name = "niveau_habilitation")
    private ParamList niveauHabilitation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public ParamList getNiveauHabilitation() {
        return niveauHabilitation;
    }

    public void setNiveauHabilitation(ParamList niveauHabilitation) {
        this.niveauHabilitation = niveauHabilitation;
    }
     
     
}
