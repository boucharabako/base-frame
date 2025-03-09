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
@Table(name = "sec_utilisateur_profil",  uniqueConstraints = @UniqueConstraint(columnNames = {"profil", "utilisateur"}), schema = AccountConstant.SCHEMA)
public class UtilisateurProfil extends AbstractAuditingEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "utilisateur", referencedColumnName = "id", nullable = false)
    private Utilisateur utilisateur;
    
    @ManyToOne
    @JoinColumn(name = "profil", nullable = false)
    private Profil profil;

    public UtilisateurProfil(Utilisateur utilisateur, Profil profil) {
        this.utilisateur = utilisateur;
        this.profil = profil;
    }

    public UtilisateurProfil() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    @Override
    public String toString() {
        return "UtilisateurProfil{" + "id=" + id + ", utilisateur=" + utilisateur + ", profil=" + profil + '}';
    }
     
     
}
