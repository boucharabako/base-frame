/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.core.repository;

import com.base.frame.account.entity.Habilitation;
import com.base.frame.account.entity.UtilisateurReinitPassword;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Alassani
 */
public interface UtilisateurReinitPasswordRepository extends JpaRepository<UtilisateurReinitPassword, String>{
    
         @Query("SELECT h FROM UtilisateurReinitPassword h WHERE h.utilisateur.id=:idUtilisateur AND h.etat.code=:codeStatut")
    List<UtilisateurReinitPassword> findByUserAndCodeStatut(@Param("idUtilisateur") String idUtilisateur, @Param("codeStatut") String codeStatut);
    
       @Query("SELECT h FROM UtilisateurReinitPassword h WHERE h.utilisateur.id=:idUtilisateur")
    List<UtilisateurReinitPassword> findByUserId(@Param("idUtilisateur") String idUtilisateur);
    
       @Query("SELECT h FROM UtilisateurReinitPassword h WHERE h.reinitCode=:reinitCode AND h.etat.code=:codeStatut")
    List<UtilisateurReinitPassword> findByReinitCode(@Param("reinitCode") String reinitCode, @Param("codeStatut") String codeStatut);
}
