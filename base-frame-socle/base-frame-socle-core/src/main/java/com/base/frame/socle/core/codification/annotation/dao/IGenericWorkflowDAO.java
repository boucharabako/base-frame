/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.annotation.dao;

import com.base.frame.socle.core.workflow.entity.Etat;

/**
 * @author W.KOUWONOU
 * @version 1.0.0
 * @since 25-10-2017
 * @description  Recherche generique
 */
public interface IGenericWorkflowDAO{

    public Integer saveEtat(String conceptMetier,String id, Etat e);
    public Integer saveEtat(String conceptMetier,String id, String e);
    
    public Etat findEtatById(String conceptMetier,String id);
    public String findAuthorBy(String conceptMetier,String id);
    public Class findClassByConcepMetier(String conceptMetier);
    public String findWorkflowFieldByConceptMetier(String conceptMetier);
    public String findIdFieldByConceptMetier(String conceptMetier);
  
}
