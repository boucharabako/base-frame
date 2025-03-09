/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.dao;

import com.base.frame.account.entity.Profil;
import com.base.frame.socle.core.codification.annotation.dao.GenericDAO;
import com.base.frame.socle.core.service.CodificationService;
import java.util.List;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alassani
 */

@Component
@Transactional
public class ProfilDAO extends GenericDAO<Profil, String> {
     public ProfilDAO() {
        super(Profil.class);
    }
     @Autowired
    private CodificationService codificationService;
     public Page<Profil> findPageProfil(Pageable p,String motCle){
         
         String queryChain = "SELECT DISTINCT p FROM Profil p WHERE 1=1";
         if (motCle != null && !motCle.isEmpty()) {
             queryChain+= " AND (upper(p.code) LIKE CONCAT('%',:motCle,'%') "
            + " OR upper(p.intitule) LIKE CONCAT('%',:motCle,'%') "
            + " OR upper(p.description) LIKE CONCAT('%',:motCle,'%') )"
            + "ORDER BY p.intitule ASC";
         }
         Query query = em.createQuery(queryChain);
         if (motCle != null && !motCle.isEmpty()) {
            query.setParameter("motCle", motCle);
        }
         List<Profil> list = query.getResultList();
         
         return codificationService.genericPage(list, p);
         
     }
}
