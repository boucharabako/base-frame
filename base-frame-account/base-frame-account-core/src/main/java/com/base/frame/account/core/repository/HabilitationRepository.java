/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.core.repository;

import com.base.frame.account.entity.Habilitation;
import com.base.frame.account.entity.Profil;
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
public interface HabilitationRepository  extends JpaRepository<Habilitation, String>{
     @Query("SELECT h FROM Habilitation h WHERE h.profil.id=:idProfil ORDER BY h.fonction.libelle ASC")
    List<Habilitation> findByProfil(@Param("idProfil") String idProfil);
    
      @Query("SELECT h FROM Habilitation  h WHERE h.profil.id=:idProfil ORDER BY h.fonction.libelle ASC")
    Page<Habilitation> findByProfilPaginate(Pageable pageable ,@Param("idProfil") String idProfil);
    
     @Query("SELECT h FROM Habilitation h WHERE h.profil.id=:idProfil AND h.fonction.code=:codeFonction")
    Optional<Habilitation> findByProfilAndFonction(@Param("idProfil") String idProfil, @Param("codeFonction") String codeFonction);
}
