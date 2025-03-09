package com.base.frame.account.entity;

import com.base.frame.account.utils.AccountConstant;
import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.utils.audit.AbstractAuditingEntity;
import java.io.Serializable;
import java.time.Instant;
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
@Table(name = "sec_utilisateur_reinit_password", schema = AccountConstant.SCHEMA)
public class UtilisateurReinitPassword extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "utilisateur", referencedColumnName = "id", nullable = false)
    private Utilisateur utilisateur;

    @Column(name = "reinit_code", nullable = false)
    private String reinitCode;
    
    @ManyToOne
    @JoinColumn(name = "etat", referencedColumnName = "id" , nullable = false)
    private ParamList etat;
    
    @Column(name = "date_expiration", length = 50)
    private Instant dateExpiration;

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

    public String getReinitCode() {
        return reinitCode;
    }

    public void setReinitCode(String reinitCode) {
        this.reinitCode = reinitCode;
    }

    public Instant getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Instant dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public ParamList getEtat() {
        return etat;
    }

    public void setEtat(ParamList etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "UtilisateurReinitPassword{" + "id=" + id + ", utilisateur=" + utilisateur + ", reinitCode=" + reinitCode + ", etat=" + etat + ", dateExpiration=" + dateExpiration + '}';
    }
}
