/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;

/**
 *
 * @author william
 */
public interface IWorkflowEtapeValidationService {
    public boolean isBeforeFirstEtapeValidation(String concepteMetier,String idObjet,String idEtape);
    public boolean isOnFirstEtapeValidation(String concepteMetier,String idObjet,String idEtape);
    public boolean isBetweenFirstAndSecondEtapeValidation(String concepteMetier,String idObjet,String idEtape);
    public boolean isOnSecondEtapeValidation(String concepteMetier,String idObjet,String idEtape);
    public boolean isPassedSecondEtapeValidation(String concepteMetier,String idObjet,String idEtape);
    public boolean isNotPassAnyEtapeValidation(String concepteMetier,String idObjet,String idEtape);
   
}
