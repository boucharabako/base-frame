/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.core.repository;

import com.base.frame.account.entity.Profil;
import com.base.frame.account.entity.Utilisateur;
import com.base.frame.account.entity.UtilisateurProfil;
import com.base.frame.socle.core.workflow.dto.UtilisateurDTOV2;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Alassani
 */
public interface UtilisateurProfilRepository extends JpaRepository<UtilisateurProfil, String>{
       @Query("SELECT p FROM UtilisateurProfil p WHERE p.utilisateur.id=:idUtilisateur")
    List<UtilisateurProfil> findByUserId(@Param("idUtilisateur") String idUtilisateur);
    
     @Query("SELECT p FROM UtilisateurProfil p WHERE p.profil.id=:idProfil")
    List<UtilisateurProfil> findByProfilId(@Param("idProfil") String idProfil);
    
      @Query("SELECT p FROM UtilisateurProfil p WHERE p.profil.id=:idProfil AND p.utilisateur.id=:idUtilisateur")
    Optional<UtilisateurProfil> findByIdProfilAndIdUser(@Param("idProfil") String idProfil, @Param("idUtilisateur") String idUtilisateur);
    
       @Query("SELECT p.utilisateur FROM UtilisateurProfil  p WHERE p.profil.id=:idProfil ORDER BY p.utilisateur.username ASC")
    Page<Utilisateur> findByProfilPaginate(Pageable pageable ,@Param("idProfil") String idProfil);
    
     @Query("SELECT p.profil FROM UtilisateurProfil p WHERE p.utilisateur.id=:idUtilisateur")
    List<Profil> findProfilByUserId(@Param("idUtilisateur") String idUtilisateur);
    
    @Query("SELECT new com.base.frame.socle.core.workflow.dto.UtilisateurDTOV2(u.utilisateur.id, u.utilisateur.username,u.utilisateur.firstName,u.utilisateur.lastName) "
            + "FROM UtilisateurProfil u WHERE u.profil.id = :p "
            + " ORDER BY u.utilisateur.firstName ASC, u.utilisateur.lastName ASC")
    public List<UtilisateurDTOV2> findUtilisateurDTOByProfil(@Param("p") String profil);
    
    @Query("SELECT new com.base.frame.socle.core.workflow.dto.UtilisateurDTOV2(u.utilisateur.id, u.utilisateur.username,u.utilisateur.firstName,u.utilisateur.lastName)"
            + " FROM UtilisateurProfil u WHERE u.profil.id IN :p  AND "
            + " u.utilisateur.etat.codeEtat = :statutCode"
            + " ORDER BY u.utilisateur.firstName ASC, u.utilisateur.lastName ASC")
    public List<UtilisateurDTOV2> findUtilisateurDTOByProfils(@Param("p") Object[] profils,
            @Param("statutCode") String statut);
    
    /**
     * @author WKOUWONOU
     * @since 02/10/2018 Recherche les profils liés à un utilisateur
     * @param utilisateur Parametre de recherche peut etre identifiant ,
     * username ou email
     * @return Liste de profil
     */
    @Query("SELECT u.profil.id FROM UtilisateurProfil u WHERE (u.utilisateur.id =:id_utilisateur OR "
            + " u.utilisateur.username =:id_utilisateur OR u.utilisateur.email =:id_utilisateur)")
    public List<String> findProfilByUtilisateurId(@Param("id_utilisateur") String utilisateur);
}
