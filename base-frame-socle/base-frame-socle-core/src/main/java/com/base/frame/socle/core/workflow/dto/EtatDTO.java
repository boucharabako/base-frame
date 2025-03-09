package com.base.frame.socle.core.workflow.dto;

import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.utils.Color;
import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.core.workflow.entity.Etat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author kouwonou
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EtatDTO {

    private String id;
    @NotNull(message = SocleConstant.F_REQUIERD)

    private Integer ideSequence;
    @NotNull(message = SocleConstant.F_REQUIERD)

    private Integer ideEtape;

    @NotBlank(message = SocleConstant.F_REQUIERD)
    private String libelleEtat;

    @NotBlank(message = SocleConstant.F_REQUIERD)
    private String codeEtat;

    @NotBlank(message = SocleConstant.F_REQUIERD)
    private String codeWorkflow;

    @NotBlank(message = SocleConstant.F_REQUIERD)
    private String codeCouleur;

    private Boolean modif = false;
    
    private Boolean etapeValidation = false;

    private Boolean isFirstStep = false;

    private Boolean isterminaison = false;

    private Boolean userAllowed = false;

    private String codeCouleurText;

    private BigDecimal delaiExecution;

    public EtatDTO() {
    }

//    public EtatDTO(Etat e) {
//        this(e.getId(), e.getIdeSequence(), e.getIdeEtape(), e.getLibelleEtat(), e.getCodeEtat(), e.getCodeCouleur(), e.getIndicModifAuthorisee().getIdeCode(), e.getIndicEtapeValidation());
//    }

    public EtatDTO(Etat e) {
        this(e.getId(), e.getIdeSequence(), e.getIdeEtape(), e.getLibelleEtat(), e.getCodeEtat(),e.getDelaiExecution(), e.getCodeCouleur(), e.getIndicModifAuthorisee().getCode(), e.getIndicEtapeValidation());
    }

    public EtatDTO(String id, Integer ideSequence, Integer ideEtape, String libelleEtat, String codeEtat, String codeCouleur, String codif) {

        if (codif != null && !codif.isEmpty() && codif.equals(SocleConstant.OUI_NON_OUI)) {
            modif = true;
        }
        if (codif != null && !codif.isEmpty() && codif.equals(SocleConstant.OUI_NON_NON)) {
            modif = false;
        }

        this.id = id;
        this.ideSequence = ideSequence;
        this.ideEtape = ideEtape;
        this.libelleEtat = libelleEtat;
        this.codeEtat = codeEtat;
        this.codeCouleur = codeCouleur;
    }

    public EtatDTO(String id, Integer ideSequence, Integer ideEtape, String libelleEtat, String codeEtat, String codeCouleur, String codif, ParamList etapeValidation) {

        if (codif != null && !codif.isEmpty() && codif.equals(SocleConstant.OUI_NON_OUI)) {
            modif = true;
        }
        if (codif != null && !codif.isEmpty() && codif.equals(SocleConstant.OUI_NON_NON)) {
            modif = false;
        }
        if (etapeValidation != null && etapeValidation.getCode().equals(SocleConstant.OUI_NON_OUI)) {
            this.etapeValidation = true;
        } else {
            this.etapeValidation = false;
        }

        this.id = id;
        this.ideSequence = ideSequence;
        this.ideEtape = ideEtape;
        this.libelleEtat = libelleEtat;
        this.codeEtat = codeEtat;
        this.codeCouleur = codeCouleur;
    }

    public EtatDTO(String id, Integer ideSequence, Integer ideEtape, String libelleEtat, String codeEtat, BigDecimal delaiExecution, String codeCouleur, String codif, ParamList etapeValidation) {

        if (codif != null && !codif.isEmpty() && codif.equals(SocleConstant.OUI_NON_OUI)) {
            modif = true;
        }
        if (codif != null && !codif.isEmpty() && codif.equals(SocleConstant.OUI_NON_NON)) {
            modif = false;
        }
        if (etapeValidation != null && etapeValidation.getCode().equals(SocleConstant.OUI_NON_OUI)) {
            this.etapeValidation = true;
        } else {
            this.etapeValidation = false;
        }

        this.id = id;
        this.ideSequence = ideSequence;
        this.ideEtape = ideEtape;
        this.libelleEtat = libelleEtat;
        this.delaiExecution = delaiExecution;
        this.codeEtat = codeEtat;
        this.codeCouleur = codeCouleur;
    }

    public EtatDTO(String id, String libelleEtat, String codeCouleur) {
        this.id = id;
        this.libelleEtat = libelleEtat;
        this.codeCouleur = codeCouleur;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdeSequence() {
        return ideSequence;
    }

    public void setIdeSequence(Integer ideSequence) {
        this.ideSequence = ideSequence;
    }

    public Integer getIdeEtape() {
        return ideEtape;
    }

    public void setIdeEtape(Integer ideEtape) {
        this.ideEtape = ideEtape;
    }

    public String getLibelleEtat() {
        return libelleEtat;
    }

    public void setLibelleEtat(String libelleEtat) {
        this.libelleEtat = libelleEtat;
    }

    public String getCodeEtat() {
        return codeEtat;
    }

    public void setCodeEtat(String codeEtat) {
        this.codeEtat = codeEtat;
    }

    public String getCodeWorkflow() {
        return codeWorkflow;
    }

    public void setCodeWorkflow(String codeWorkflow) {
        this.codeWorkflow = codeWorkflow;
    }

    public String getCodeCouleur() {
        return codeCouleur;
    }

    public void setCodeCouleur(String codeCouleur) {
        this.codeCouleur = codeCouleur;
    }

    public String getCodeCouleurText() {
        return Color.textColorByBackgroundColor(codeCouleur);
    }

    public void setCodeCouleurText(String codeCouleurText) {
        this.codeCouleurText = codeCouleurText;
    }

    @Override
    public int hashCode() {
        return (int) 5;
    }

    public Boolean getModif() {
        return modif;
    }

    public void setModif(Boolean modif) {
        this.modif = modif;
    }

    public Boolean getIsterminaison() {
        return isterminaison;
    }

    public void setIsterminaison(Boolean isterminaison) {
        this.isterminaison = isterminaison;
    }

    public Boolean getIsFirstStep() {
        return isFirstStep;
    }

    public void setIsFirstStep(Boolean isFirstStep) {
        this.isFirstStep = isFirstStep;
    }

    public Boolean getUserAllowed() {
        return userAllowed;
    }

    public void setUserAllowed(Boolean userAllowed) {
        this.userAllowed = userAllowed;
    }

    public Boolean getEtapeValidation() {
        if (etapeValidation == null) {
            etapeValidation = false;
        }
        return etapeValidation;
    }

    public void setEtapeValidation(Boolean etapeValidation) {
        this.etapeValidation = etapeValidation;
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
        final EtatDTO other = (EtatDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.ideSequence, other.ideSequence);
    }

    public BigDecimal getDelaiExecution() {
        return delaiExecution;
    }

    public void setDelaiExecution(BigDecimal delaiExecution) {
        this.delaiExecution = delaiExecution;
    }

    @Override
    public String toString() {
        return "EtatDTO{" + "id=" + id + ", ideSequence=" + ideSequence + ", ideEtape=" + ideEtape + ", libelleEtat=" + libelleEtat + ", codeEtat=" + codeEtat + ", codeWorkflow=" + codeWorkflow + ", codeCouleur=" + codeCouleur + ", modif=" + modif + ", etapeValidation=" + etapeValidation + ", isFirstStep=" + isFirstStep + ", isterminaison=" + isterminaison + ", userAllowed=" + userAllowed + ", codeCouleurText=" + codeCouleurText + ", delaiExecution=" + delaiExecution + '}';
    }
}
