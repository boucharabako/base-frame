/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.entity;


import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.utils.audit.AbstractAuditingEntity;
import java.io.Serializable;
import java.util.Objects;
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
 * @author kouwonou
 */
@Entity
@Table(name = "spr_w_etats_utilisateurs_autorises", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code_etat", "username","id_objet","concept_metier"})}, schema = SocleConstant.SCHEMA)
public class EtatUtilisateurAutorise extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;

    @JoinColumn(name = "code_etat")
    @ManyToOne
    private Etat etat;

    @Column(name = "username")
    private String username;
    
    @Column(name = "id_objet")
    private String idObjet;
     
    @Column(name = "concept_metier")
    private String conceptMetier;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(String idObjet) {
        this.idObjet = idObjet;
    }

    public String getConceptMetier() {
        return conceptMetier;
    }

    public void setConceptMetier(String conceptMetier) {
        this.conceptMetier = conceptMetier;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.etat);
        hash = 37 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EtatUtilisateurAutorise other = (EtatUtilisateurAutorise) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.etat, other.etat);
    }

    @Override
    public String toString() {
        return "EtatUtilisateurAutorise{" + "username=" + username + ", idObjet=" + idObjet + ", conceptMetier=" + conceptMetier + '}';
    }

}
