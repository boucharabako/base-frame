package com.base.frame.socle.core.workflow.entity;

import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.utils.Color;
import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.utils.audit.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author rkoufionou
 */
/**
 * 
 * @deprecated( value deprecated)
 */
@Deprecated()
@Entity
@Table(name = "spr_workflow_steps", uniqueConstraints
        = {
            @UniqueConstraint(columnNames = {"ordre", "id_worwflow", "etape_sequence"})},schema = SocleConstant.SCHEMA)
public class WorkFlowEtape extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", length = 40)
    private String id;

    @Column(name = "ordre")
    @NotNull(message = SocleConstant.F_REQUIERD)
    private Integer ordre;

    @Column(name = "code_statut", length = 15)
    @Pattern(regexp = SocleConstant.ALPHA_NUM_REGEX, message = SocleConstant.F_REGEX)
    @NotBlank(message = SocleConstant.F_REQUIERD)
    @Size(max = 15)
    private String codeStatut;

    @Column(name = "libelle_statut", length = 100)
    @NotBlank(message = SocleConstant.F_REQUIERD)
    @Size(max = 100)
    private String libelleStatut;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_worwflow", referencedColumnName = "id",
            updatable = false)
    private WorkFlow workFlow;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "previous_action", referencedColumnName = "id")
    private ParamList previousAction;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "next_action", referencedColumnName = "id")
    private ParamList nextAction;

    @Column(name = "code_couleur", length = 30)
    @NotBlank(message = SocleConstant.F_REQUIERD)
    private String codeColeur;
    @Column(name = "terminaison")
    private Boolean terminaison;
    @Transient
    @JsonProperty
    private String codeCouleurText;

    @Transient
    @JsonProperty
    private String idNextAction;

    @Transient
    @JsonProperty
    private String nextActionLibelle;

    @Transient
    @JsonProperty
    private String idPreviousAction;

    @Transient
    @JsonProperty
    private String previousActionLibelle;

    @Transient
    @JsonProperty
    public Boolean checked;

    @NotNull(message = SocleConstant.F_REQUIERD)
    @Column(name = "etape_sequence")
    private Integer sequence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public String getCodeStatut() {
        return codeStatut;
    }

    public void setCodeStatut(String codeStatut) {
        this.codeStatut = codeStatut;
    }

    public String getLibelleStatut() {
        return libelleStatut;
    }

    public void setLibelleStatut(String libelleStatut) {
        this.libelleStatut = libelleStatut;
    }

    public WorkFlow getWorkFlow() {
        return workFlow;
    }

    public void setWorkFlow(WorkFlow workFlow) {
        this.workFlow = workFlow;
    }

    public ParamList getNextAction() {
        return nextAction;
    }

    public void setNextAction(ParamList nextAction) {
        this.nextAction = nextAction;
    }

    public ParamList getPreviousAction() {
        return previousAction;
    }

    public void setPreviousAction(ParamList previousAction) {
        this.previousAction = previousAction;
    }

    public String getCodeColeur() {
        return codeColeur;
    }

    public void setCodeColeur(String codeColeur) {
        this.codeColeur = codeColeur;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 79 * hash + (this.ordre != null ? this.ordre.hashCode() : 0);
        hash = 79 * hash + (this.codeStatut != null ? this.codeStatut.hashCode() : 0);
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
        final WorkFlowEtape other = (WorkFlowEtape) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.codeStatut == null) ? (other.codeStatut != null) : !this.codeStatut.equals(other.codeStatut)) {
            return false;
        }
        return !(!Objects.equals(other.ordre, this.ordre) && (this.ordre == null || !this.ordre.equals(other.ordre)));
    }

    @Override
    public String toString() {
        return "WorkFlowEtape{" + "id=" + id + ", ordre=" + ordre + ", codeStatut=" + codeStatut + ", libelleStatut=" + libelleStatut + ", codeColeur=" + codeColeur + ", codeCouleurText=" + codeCouleurText + ", idNextAction=" + idNextAction + ", checked=" + checked + ", sequence=" + sequence + '}';
    }

    public String getIdNextAction() {
        if (nextAction != null) {
            idNextAction = nextAction.getId();
        }
        return idNextAction;
    }

    public void setIdNextAction(String idNextAction) {
        this.idNextAction = idNextAction;
    }

    public String getIdPreviousAction() {
        if (previousAction != null) {
            idPreviousAction = previousAction.getId();
        }
        return idPreviousAction;
    }

    public void setIdPreviousAction(String idPreviousAction) {
        this.idPreviousAction = idPreviousAction;
    }

    public Boolean getChecked() {
        return !(id == null || id.isEmpty());
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getCodeCouleurText() {
        return Color.textColorByBackgroundColor(codeColeur);
    }

    public void setCodeCouleurText(String codeCouleurText) {
        this.codeCouleurText = codeCouleurText;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getNextActionLibelle() {
        return nextActionLibelle;
    }

    public void setNextActionLibelle(String nextActionLibelle) {
        this.nextActionLibelle = nextActionLibelle;
    }

    public String getPreviousActionLibelle() {
        return previousActionLibelle;
    }

    public void setPreviousActionLibelle(String previousActionLibelle) {
        this.previousActionLibelle = previousActionLibelle;
    }

    public Boolean getTerminaison() {
        if (terminaison == null) {
            terminaison = Boolean.FALSE;
        }
        return terminaison;
    }

    public void setTerminaison(Boolean terminaison) {
        this.terminaison = terminaison;
    }

}
