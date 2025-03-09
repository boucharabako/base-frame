/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;

import com.base.frame.socle.core.workflow.entity.EtatUtilisateurAutorise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author kouwonou
 */
public interface EtatUtilisateurAutoriseRepository extends JpaRepository<EtatUtilisateurAutorise, String> {

    @Query("SELECT e FROM  EtatUtilisateurAutorise e WHERE e.username=:u AND e.idObjet=:o AND e.etat.id=:e"
            + " AND e.conceptMetier=:c")
    public EtatUtilisateurAutorise findOneByUsernameEtatConcetpMetierIbjet(@Param("u") String username,
            @Param("e") String idEtat, @Param("c") String concept, @Param("o") String idObjet);

    @Query("SELECT e FROM  EtatUtilisateurAutorise e WHERE e.username IN :u AND e.idObjet=:o AND e.etat.id=:e"
            + " AND e.conceptMetier=:c")
    public List<EtatUtilisateurAutorise> findOneByUsernameEtatConcetpMetierIbjet(@Param("u") String[] username,
            @Param("e") String idEtat, @Param("c") String concept, @Param("o") String idObjet);

    @Query("SELECT e FROM  EtatUtilisateurAutorise e WHERE  e.idObjet=:o "
            + " AND e.conceptMetier=:c AND e.username=:username")
    public List<EtatUtilisateurAutorise> findOneByEtatConcetpMetierIObjetUsername(
            @Param("c") String concept, @Param("o") String idObjet, @Param("username") String username);

    @Query("SELECT e FROM  EtatUtilisateurAutorise e WHERE  e.idObjet=:o AND e.etat.id=:e"
            + " AND e.conceptMetier=:c")
    public List<EtatUtilisateurAutorise> findOneByEtatConcetpMetierIObjet(
            @Param("e") String idEtat, @Param("c") String concept, @Param("o") String idObjet);
    
    @Query("SELECT DISTINCT e FROM  EtatUtilisateurAutorise e WHERE  e.idObjet=:o AND e.etat.id=:e"
            + " AND e.conceptMetier=:c ORDER BY e.createdDate DESC")
    public List<EtatUtilisateurAutorise> findUsersByEtatUnique(
            @Param("e") String idEtat, @Param("c") String concept, @Param("o") String idObjet);

}
