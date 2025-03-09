/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.core.repository;

import com.base.frame.account.entity.Groupe;
import com.base.frame.account.entity.Utilisateur;
import com.base.frame.account.entity.UtilisateurGroupe;
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
public interface UtilisateurGroupeRepository extends JpaRepository<UtilisateurGroupe, String> {

    @Query("SELECT ug FROM UtilisateurGroupe ug WHERE ug.utilisateur.id=:idUtilisateur")
    List<UtilisateurGroupe> findByUserId(@Param("idUtilisateur") String idUtilisateur);

    @Query("SELECT ug FROM UtilisateurGroupe ug WHERE ug.groupe.id=:idGroupe")
    List<UtilisateurGroupe> findByGroupeId(@Param("idGroupe") String idGroupe);

    @Query("SELECT ug FROM UtilisateurGroupe ug WHERE ug.groupe.id=:idGroupe AND ug.utilisateur.id=:idUtilisateur")
    Optional<UtilisateurGroupe> findByIdGroupeAndIdUser(@Param("idGroupe") String idGroupe, @Param("idUtilisateur") String idUtilisateur);

    @Query("SELECT ug.utilisateur FROM UtilisateurGroupe  ug WHERE ug.groupe.id=:idGroupe ORDER BY ug.utilisateur.username ASC")
    Page<Utilisateur> findByGroupePaginate(Pageable pageable, @Param("idGroupe") String idGroupe);

    @Query("SELECT ug.groupe FROM UtilisateurGroupe ug WHERE ug.utilisateur.id=:idUtilisateur")
    List<Groupe> findGroupeByUserId(@Param("idUtilisateur") String idUtilisateur);
}
