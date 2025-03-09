/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kouwonou
 */
public class WorkflowCycleInitDTO {
    private List<UtilisateurDTOV2> utilisateursAutorises =new ArrayList<>();
    private List<UtilisateurDTOV2> utilisateursChoisies =new ArrayList<>();
    private EtatDTO etatCourant;
    private List<ActionDTO> actions;
    
    private CodifListDTO concepMetier;

    public List<UtilisateurDTOV2> getUtilisateursAutorises() {
        return utilisateursAutorises;
    }

    public void setUtilisateursAutorises(List<UtilisateurDTOV2> utilisateursAutorises) {
        this.utilisateursAutorises = utilisateursAutorises;
    }

   

    public List<UtilisateurDTOV2> getUtilisateursChoisies() {
        return utilisateursChoisies;
    }

    public void setUtilisateursChoisies(List<UtilisateurDTOV2> utilisateursChoisies) {
        this.utilisateursChoisies = utilisateursChoisies;
    }

    public EtatDTO getEtatCourant() {
        return etatCourant;
    }

    public void setEtatCourant(EtatDTO etatCourant) {
        this.etatCourant = etatCourant;
    }

    public List<ActionDTO> getActions() {
        return actions;
    }

    public void setActions(List<ActionDTO> actions) {
        this.actions = actions;
    }

    public CodifListDTO getConcepMetier() {
        return concepMetier;
    }

    public void setConcepMetier(CodifListDTO concepMetier) {
        

        this.concepMetier = concepMetier;
    }

   
    @Override
    public String toString() {
        return "WorkflowCycleInitDTO{" + "utilisateursAutorises=" + utilisateursAutorises + ", utilisateursChoisies=" + utilisateursChoisies + ", etatCourant=" + etatCourant + ", actions=" + actions + ", concepMetier=" + concepMetier + '}';
    }
    
    
}
