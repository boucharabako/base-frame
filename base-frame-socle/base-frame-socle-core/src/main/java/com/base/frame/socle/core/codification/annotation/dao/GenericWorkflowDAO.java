/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.annotation.dao;


import com.base.frame.socle.core.codification.annotation.WorkflowField;
import com.base.frame.socle.core.workflow.entity.Etat;
import com.base.frame.socle.utils.exceptions.InternalServerException;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kouwonou
 */
@Component
@Transactional
public class GenericWorkflowDAO implements IGenericWorkflowDAO {

    @PersistenceContext
    protected EntityManager em;
    
//    @Autowired
//    private NProperties properties;

    @Override
    public Integer saveEtat(String conceptMetier, String id, String e) {
        Class cl = findClassByConcepMetier(conceptMetier);
        String idField = findIdFieldByConceptMetier(conceptMetier);
        String workflowField = findWorkflowFieldByConceptMetier(conceptMetier);
        if (cl == null || idField == null || workflowField == null) {
            return -1;
            
        }

        Query q = em.createQuery("UPDATE " + cl.getSimpleName() + " e SET e." + workflowField + ".id=:idw  WHERE e." + idField + "=:id");
        q.setParameter("id", id);
        q.setParameter("idw", e);
        q.executeUpdate();
        return 1;
    }

    @Override
    public Etat findEtatById(String conceptMetier, String id) {
        
        Class cl = findClassByConcepMetier(conceptMetier);
        String idField = findIdFieldByConceptMetier(conceptMetier);
        String workflowField = findWorkflowFieldByConceptMetier(conceptMetier);
        

        if (cl == null || idField == null || workflowField == null) {
            return null;
        }
        Query q = em.createQuery("SELECT e." + workflowField + " FROM " + cl.getName() + " e WHERE e." + idField + "=:id");

        q.setParameter("id", id);
        List<Etat> l= q.getResultList();
        return l.isEmpty()?null:l.get(0) ;

    }

    @Override
    public Class findClassByConcepMetier(String conceptMetier) {
//       properties.getWorkflowImples().forEach((key,value) -> {
//       });
        return null;//properties.getWorkflowImples().get(conceptMetier);
    }

    @Override
    public String findWorkflowFieldByConceptMetier(String conceptMetier) {
        Class cl = findClassByConcepMetier(conceptMetier);
        if (cl == null) {
            return null;
        }
        Field[] fs = cl.getDeclaredFields();
        for (Field f : fs) {
            if (f.getAnnotation(WorkflowField.class) != null) {
                return f.getName();
            }
        }
        return null;
    }

    @Override
    public String findIdFieldByConceptMetier(String conceptMetier) {
        Class cl = findClassByConcepMetier(conceptMetier);
        if (cl == null) {
            return null;
        }
        Field[] fs = cl.getDeclaredFields();
        for (Field f : fs) {
            if (f.getAnnotation(Id.class) != null) {
                return f.getName();
            }
        }
        return null;
    }

    @Override
    public Integer saveEtat(String conceptMetier, String id, Etat e) {
return saveEtat(conceptMetier, id, e.getId());}

    @Override
    public String findAuthorBy(String conceptMetier, String id) {
        System.out.println("hhhhh "+conceptMetier);
        
 Class cl = findClassByConcepMetier(conceptMetier);
        String idField = findIdFieldByConceptMetier(conceptMetier);
        String workflowField = findWorkflowFieldByConceptMetier(conceptMetier);

        if (cl == null || id == null || workflowField == null) {
              throw new InternalServerException("Parametre invalide", null);
                }
     
        Query q = em.createQuery("SELECT e.createdBy FROM " + cl.getName() + " e WHERE e." + idField + "=:id");

        q.setParameter("id", id);
        return (String) q.getSingleResult();
    }

}
