package com.base.frame.socle.core.workflow.entity;


import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.utils.audit.AbstractAuditingEntity;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author rkoufionou
 * @since 19/03/2018
 * @version 1.0.0
 */
@Entity
@Table(name = "spr_trackings", schema = SocleConstant.SCHEMA)
public class Tracking extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", length = 40)
    private String id;

    @ManyToOne
    @JoinColumn(name = "code_statut", referencedColumnName = "id")
    private WorkFlowEtape workFlowEtape;

    @ManyToOne
    @JoinColumn(name = "nature_event", referencedColumnName = "id")
    private ParamList natureEvent;

    @Column(name = "id_object", nullable = false, length = SocleConstant.GED_GENERATED_ID_COLUMN_LENTH)
    private String idObject;

    @Column(name = "date_event", nullable = false)
    private Instant dateEvent = Instant.now();

    @Column(name = "comment", length = SocleConstant.GED_DESCRIPTION_COLUMN_LENTH)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "concept_metier", referencedColumnName = "id")
    private ParamList conceptMetier;

    @ManyToOne
    @JoinColumn(name = "code_etat_initial", referencedColumnName = "id")
    private Etat etatInitial;

    @ManyToOne
    @JoinColumn(name = "code_etat_final", referencedColumnName = "id")
    private Etat etatFinal;
    @ManyToOne
    @JoinColumn(name = "code_action_permise", referencedColumnName = "id")
    private ActionPermise action;
    @Transient
    @JsonProperty
    private String libelle;
    @Transient
    @JsonProperty
    private String codeCouleur;
    @Transient
    @JsonProperty
    private String codeCouleurText;

    @Column(name = "commentaire_anonyme")
    private String commentaireAnonyme = "N";

    public Tracking() {
        // Do nothing
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdObject() {
        return idObject;
    }

    public void setIdObject(String idObject) {
        this.idObject = idObject;
    }

    public Instant getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Instant dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getComment() {

        if (comment == null || comment.isEmpty()) {
            if (etatFinal != null && etatInitial != null) {
                comment = etatInitial.getLibelleEtat() + " -> " + etatFinal.getLibelleEtat();
            } else if (etatFinal != null) {
                comment = etatFinal.getLibelleEtat();
            } else if (etatInitial != null) {
                comment = etatInitial.getLibelleEtat();
            }
        }
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ParamList getNatureEvent() {
        return natureEvent;
    }

    public void setNatureEvent(ParamList natureEvent) {
        this.natureEvent = natureEvent;
    }

    public ParamList getConceptMetier() {
        return conceptMetier;
    }

    public void setConceptMetier(ParamList conceptMetier) {
        this.conceptMetier = conceptMetier;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    public WorkFlowEtape getWorkFlowEtape() {
        return workFlowEtape;
    }

    public void setWorkFlowEtape(WorkFlowEtape workFlowEtape) {
        this.workFlowEtape = workFlowEtape;
    }

    public Etat getEtatInitial() {
        return etatInitial;
    }

    public void setEtatInitial(Etat etatInitial) {
        this.etatInitial = etatInitial;
    }

    public Etat getEtatFinal() {
        return etatFinal;
    }

    public void setEtatFinal(Etat etatFinal) {
        this.etatFinal = etatFinal;
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
        final Tracking other = (Tracking) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tracking{" + "id=" + id + '}';
    }

    public ActionPermise getAction() {
        return action;
    }

    public void setAction(ActionPermise action) {
        this.action = action;
    }

    public String getLibelle() {

        if (etatFinal != null) {
            libelle = etatFinal.getLibelleEtat();
        }
        return libelle;
    }

    public String getCodeCouleur() {
        if (action != null && action.getCodeCouleur() != null) {
            codeCouleur = action.getCodeCouleur();
        } else {
            codeCouleur = etatFinal.getCodeCouleur();
        }
        return codeCouleur;
    }

    public void setCodeCouleur(String codeCouleur) {
        this.codeCouleur = codeCouleur;
    }

    public String getCodeCouleurText() {

        if (action != null && action.getCodeCouleurText() != null) {
            codeCouleurText = action.getCodeCouleurText();
        } else {
            codeCouleurText = etatFinal.getCodeCouleurText();
        }
        return codeCouleurText;
    }

    public void setCodeCouleurText(String codeCouleurText) {
        this.codeCouleurText = codeCouleurText;
    }

    public String getCommentaireAnonyme() {
        return commentaireAnonyme;
    }

    public void setCommentaireAnonyme(String commentaireAnonyme) {
        this.commentaireAnonyme = commentaireAnonyme;
    }

}
