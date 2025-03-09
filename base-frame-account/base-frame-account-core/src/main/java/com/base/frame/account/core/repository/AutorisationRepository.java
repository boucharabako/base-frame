/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.core.repository;

import com.base.frame.account.entity.Autorisation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Alassani
 */
public interface AutorisationRepository extends JpaRepository<Autorisation, String>{
    
   @Query("SELECT a FROM Autorisation a WHERE a.ideObjet=:idObjet")
    List<Autorisation> findByIdObjet(@Param("idObjet") String idObjet);
    
}
