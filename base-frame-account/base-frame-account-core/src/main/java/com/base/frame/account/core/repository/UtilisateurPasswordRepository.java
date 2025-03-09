/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.core.repository;

import com.base.frame.account.entity.UtilisateurPassword;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Alassani
 */
public interface UtilisateurPasswordRepository  extends JpaRepository<UtilisateurPassword, String>{
        @Query("SELECT u FROM UtilisateurPassword u WHERE u.utilisateur.id=:idUtilisateur ")
    Optional<UtilisateurPassword> findByUserId(@Param("idUtilisateur") String idUtilisateur);
    
    @Query("SELECT u FROM UtilisateurPassword u WHERE u.utilisateur.username=:username ")
    Optional<UtilisateurPassword> findByUserUsername(@Param("username") String username);
}
