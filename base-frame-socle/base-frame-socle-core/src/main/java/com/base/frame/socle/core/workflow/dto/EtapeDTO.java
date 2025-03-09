/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;


import com.base.frame.socle.core.codification.utils.CodificationConstant;
import com.base.frame.socle.core.workflow.entity.WorkFlowEtape;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author W.KOUWONOU
 * @Date 28/03/2018
 * @version 1.0.0
 */
public class EtapeDTO {
    @NotBlank(message = CodificationConstant.F_REQUIERD)
     private String idStatut;
    private String codeCouleur;
    private String libelleStatut;

    public EtapeDTO(String idStatut, String codeCouleur, String libelleStatut) {
        this.idStatut = idStatut;
        this.codeCouleur = codeCouleur;
        this.libelleStatut = libelleStatut;
    }
    public EtapeDTO(WorkFlowEtape e) {
        this.idStatut = e.getId();
        this.codeCouleur =e.getCodeColeur();
        this.libelleStatut = e.getLibelleStatut();
    }

    public String getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(String idStatut) {
        this.idStatut = idStatut;
    }

    public String getCodeCouleur() {
        return codeCouleur;
    }

    public void setCodeCouleur(String codeCouleur) {
        this.codeCouleur = codeCouleur;
    }

    public String getLibelleStatut() {
        return libelleStatut;
    }

    public void setLibelleStatut(String libelleStatut) {
        this.libelleStatut = libelleStatut;
    }
    
    
}
