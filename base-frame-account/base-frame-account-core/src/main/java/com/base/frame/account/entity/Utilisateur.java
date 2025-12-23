/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.entity;

import com.base.frame.account.utils.AccountConstant;
import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.workflow.entity.Etat;
import com.base.frame.socle.utils.audit.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Alassani
 */
@Entity
@Table(name = "sec_utilisateurs", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}), schema = AccountConstant.SCHEMA)
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;
    @Column(name = "titre")
    private String titre;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "date_naissance")
    private Instant dateNaissance;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "picture")
    private Fichier picture = new Fichier();

    @ManyToOne
    @JoinColumn(name = "etat", referencedColumnName = "id", nullable = false)
    private Etat etat;
    @Transient
    Set<String> authorities = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Fichier getPicture() {
        return picture;
    }

    public void setPicture(Fichier picture) {
        this.picture = picture;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Instant getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Instant dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", tel=" + tel + ", titre=" + titre + ", sexe=" + sexe + ", dateNaissance=" + dateNaissance + ", etat=" + etat + ", authorities=" + authorities + '}';
    }

}
