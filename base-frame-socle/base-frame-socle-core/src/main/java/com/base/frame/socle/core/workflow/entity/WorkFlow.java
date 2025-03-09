package com.base.frame.socle.core.workflow.entity;


import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.core.utils.SprUtils;
import com.base.frame.socle.utils.audit.AbstractAuditingEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Les workflows : ils matérialisent des flux collaboratifs et se composent
 * d’une ou plusieurs étapes, chaque étape matérialisant à son tour un état
 * instantané des informations échangées ainsi que les possibles échanges entre
 * acteurs.
 *
 * @author W.KOUWONOU
 * @since 19/03/2018
 * @version 1.0.0
 */
/**
 * 
 * @author Assima
 * @deprecated
 */
@Entity
@Deprecated
@Table(name = "spr_workflows", schema = SocleConstant.SCHEMA)
public class WorkFlow extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workflow_seq")
    @SequenceGenerator(
            name = "workflow_seq",
            sequenceName = "workflow_seq",
            initialValue = 1, schema = SocleConstant.SCHEMA)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description", length = 500)
    @NotBlank(message = SocleConstant.F_REQUIERD)
    @Size(max = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "code_liste_actions", referencedColumnName = "code_codif")
    private Codification codeListeActions;

    @ManyToOne
    @JoinColumn(name = "code_domaine", referencedColumnName = "code_codif")
    private Codification domaine;

    @Transient
    @JsonProperty
    @NotBlank(message = SocleConstant.F_REQUIERD)
    private String idCodif;
    @Transient
    @JsonProperty
    private String idStatut;

    @ManyToOne
    @JoinColumn(name = "code_statut", referencedColumnName = "id")
    private ParamList statut;

    @Transient
    @JsonProperty
    private ParamList action;
    @Transient
    @JsonProperty
    private boolean visible = true;
    @Transient
    @JsonProperty
    private boolean visible2 = true;

    @Transient
    @JsonProperty
    private String ext;

    @Transient
    @JsonProperty
    private String couleur;
    
    @Column(name = "nombre_jours")
    private Integer jours;

    public WorkFlow() {
    }

    public WorkFlow(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getJours() {
        return jours;
    }

    public void setJours(Integer jours) {
        this.jours = jours;
    }
    
/**
 * 
 * @return
 * @deprecated
 */
    @Deprecated
    public Codification getCodeListeActions() {
        return codeListeActions;
    }
/**
 * 
 * @param codeListeActions
 * @deprecated
 */
    @Deprecated
    public void setCodeListeActions(Codification codeListeActions) {
        this.codeListeActions = codeListeActions;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final WorkFlow other = (WorkFlow) obj;
        return !(this.id != other.id && (this.id == null || !this.id.equals(other.id)));
    }

    public String getIdCodif() {

        if (codeListeActions != null) {

            idCodif = codeListeActions.getCode();
        }
        return idCodif;
    }

    public void setIdCodif(String idCodif) {
        this.idCodif = idCodif;
    }

    public ParamList getStatut() {
        return statut;
    }

    public void setStatut(ParamList statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "WorkFlow{" + "id=" + id + '}';
    }

    public String getIdStatut() {
        if (statut != null) {
            idStatut = statut.getId();
        }
        return idStatut;
    }

    public void setIdStatut(String idStatut) {
        this.idStatut = idStatut;
    }

    public boolean getVisible() {
        return statut == null || !statut.getCode().equals(SocleConstant.STATUT_PARAM_OBSOLETE);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ParamList getAction() {
        return action;
    }

    public void setAction(ParamList action) {
        this.action = action;
    }

    public boolean getVisible2() {
        return statut == null || statut.getCode().equals(SocleConstant.STATUT_PARAM_EN_SAISIE);
    }

    public void setVisible2(boolean visible2) {
        this.visible2 = visible2;
    }

    public String getCouleur() {
        return SprUtils.getCouleur(getAction());
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @PreUpdate
    public void PU() {
        idCodif = "1";

    }

    @PostUpdate
    public void POU() {
        //Do nothing
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * Domaine d’appartenance du workflow. Prend ses valeurs dans la liste
     * DOMAINES
     *
     * @return
     */
    public Codification getDomaine() {
        return domaine;
    }

    /**
     * Domaine d’appartenance du workflow. Prend ses valeurs dans la liste
     * DOMAINES
     *
     * @param domaine domaine
     */
    public void setDomaine(Codification domaine) {
        this.domaine = domaine;
    }

}
