/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.core.repository;

import com.base.frame.account.entity.Profil;
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
public interface ProfilRepository extends JpaRepository<Profil, String>, JpaSpecificationExecutor<Profil> {

    @Query("SELECT p FROM Profil p ORDER BY p.intitule ASC")
    Page<Profil> findProfil(Pageable pageable);
    
        @Query("SELECT p FROM Profil p WHERE p.code=:code ")
    Optional<Profil> findProfilByCode(@Param("code") String code);
    

    @Query("SELECT p FROM Profil p WHERE "
            + "  (upper(p.code) LIKE CONCAT('%',:mc,'%') "
            + " OR upper(p.intitule) LIKE CONCAT('%',:mc,'%') "
            + " OR upper(p.description) LIKE CONCAT('%',:mc,'%') )"
            + "ORDER BY p.intitule ASC")
    Page<Profil> findProfil(@Param("mc") String mc, Pageable pageable);
    
    
     @Query("SELECT p FROM Profil p WHERE p.etat.id=:codeStatut ORDER BY p.intitule ASC")
    List<Profil> findProfilByStatus(@Param("codeStatut") String codeStatut);
    
    @Query("SELECT p FROM Profil p WHERE p.etat.id=:codeStatut "
            + " AND p.id NOT IN (SELECT u.profil.id FROM UtilisateurProfil u WHERE u.utilisateur.id=:idUser) "
            + " ORDER BY p.intitule ASC")
    List<Profil> findProfilByStatus(@Param("codeStatut") String codeStatut, @Param("idUser") String idUser);
}
