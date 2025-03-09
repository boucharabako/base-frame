/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;

import com.base.frame.socle.core.workflow.dto.ActionDTO;
import com.base.frame.socle.core.workflow.dto.UtilisateurDTOV2;
import com.base.frame.socle.core.workflow.entity.ActionPermise;
import com.base.frame.socle.core.workflow.entity.Etat;
import java.util.List;

/**
 *
 * @author Alassani
 */
public interface IWorkflowService {
     
    public List<ActionDTO> recupererActionsSuivantsEtat(String etatId);
    public Integer executerAction(String etatId,String idObjet, String idAction, String codeFonction, String commentaire,   String[] users);
    public List<UtilisateurDTOV2> findUserByAction(String idAcion);
    /**
     * Cette méthode retourne l'etat initial d'un objet geré
     *
     * @param conceptMetier concept métier
     * @return
     */
    public Etat getEtatInitial(String conceptMetier);
}
