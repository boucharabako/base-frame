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
@Table(name = "sec_utilisateur_groupe",  uniqueConstraints = @UniqueConstraint(columnNames = {"groupe", "utilisateur"}), schema = AccountConstant.SCHEMA)
public class UtilisateurGroupe extends AbstractAuditingEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "utilisateur", referencedColumnName = "id", nullable = false)
    private Utilisateur utilisateur;
    
    @ManyToOne
    @JoinColumn(name = "groupe", nullable = false)
    private Groupe groupe;

    public UtilisateurGroupe() {
    }
    
    

    public UtilisateurGroupe(Utilisateur utilisateur, Groupe groupe) {
        this.utilisateur = utilisateur;
        this.groupe = groupe;
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

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
    
}
