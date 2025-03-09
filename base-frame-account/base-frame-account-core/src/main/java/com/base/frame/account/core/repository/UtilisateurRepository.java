/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.core.repository;

import com.base.frame.account.entity.Utilisateur;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 *
 * @author Alassani
 */


public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {

    @Query("SELECT u FROM Utilisateur u WHERE u.username=:username OR u.email=:username ")
   public Optional<Utilisateur> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM Utilisateur u WHERE u.email=:email ")
   public Optional<Utilisateur> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Utilisateur u WHERE "
            + "  (upper(u.username) LIKE CONCAT('%',:mc,'%') "
            + " OR upper(u.firstName) LIKE CONCAT('%',:mc,'%') "
            + " OR upper(u.email) LIKE CONCAT('%',:mc,'%') "
            + " OR upper(u.lastName) LIKE CONCAT('%',:mc,'%') )"
            + " ORDER BY u.createdDate DESC")
    public Page<Utilisateur> findUtilisateur(@Param("mc") String mc, Pageable pageable);

    @Query("SELECT u FROM Utilisateur u ORDER BY u.createdDate DESC")
    public Page<Utilisateur> findUtilisateur(Pageable pageable);

    @Query("SELECT u FROM Utilisateur u ORDER BY u.createdDate DESC")
   public  List<Utilisateur> getAllUser();
}
