/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;


import com.base.frame.socle.core.workflow.dto.ActionDTO;
import com.base.frame.socle.core.workflow.dto.ActionSimpleObject;
import com.base.frame.socle.core.workflow.entity.ActionPermise;
import com.base.frame.socle.core.workflow.entity.Etat;
import com.base.frame.socle.core.workflow.entity.WCallBack;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * core-codification
 *
 * @author Pierre NGS creée le 20 août 2018 12:07:31
 */
public interface ActionPermiseRepository extends JpaRepository<ActionPermise, String> {

    @Query("SELECT a FROM ActionPermise a WHERE a.action.id=:idAction AND a.etat.workflow.id=:idW AND a.etat.id=:idEtat")
    Optional<ActionPermise> findByActionAndWorkFlow(@Param("idAction") String idAction, @Param("idW") String idW, @Param("idEtat") String idEtat);

    @Query("SELECT a FROM ActionPermise a WHERE a.etat.id=:idEtat AND a.etat.workflow.id=:idW")
    List<ActionPermise> findAllActionPermiseByEtatWorkFlow(@Param("idEtat") String idEtat, @Param("idW") String idW);

    @Query("SELECT a FROM ActionPermise a WHERE a.etatSuivant.id=:idEtat AND a.etat.workflow.id=:idW")
    List<ActionPermise> findAllActionPermiseByEtatSuivantWorkFlow(@Param("idEtat") String idEtat, @Param("idW") String idW);

    @Query("SELECT a FROM ActionPermise a WHERE a.etat.id=:idEtat")
    List<ActionPermise> findByEtatWorkFlow(@Param("idEtat") String idEtat);

    @Query("SELECT a.etatSuivant FROM ActionPermise a WHERE a.etat.id=:idEtat"
            + " AND a.etatSuivant IS NOT NULL ")
    List<Etat> findNextEtatsByEtatWorkFlow(@Param("idEtat") String idEtat);

    @Query("SELECT a.etatSuivant FROM ActionPermise a WHERE a.etat.id=:idEtat "
            + " AND a.etatSuivant IS NOT NULL "
            + " AND a.etatSuivant.ideSequence =:seq")
    List<Etat> findNextEtatsByEtatWorkFlow(@Param("idEtat") String idEtat,
            @Param("seq") Integer sequence);

    @Query("SELECT a FROM ActionPermise a WHERE a.etat.id=:idEtat AND a.etatSuivant IS NOT NULL")
    List<ActionPermise> findByEtatWorkFlowEtatSuivant(@Param("idEtat") String idEtat);

    @Query("SELECT a.callBack FROM ActionPermise a WHERE a.id=:ida")
    WCallBack findCallbackByAction(@Param("ida") String idAction);

    @Query("SELECT new com.base.frame.socle.core.workflow.dto.ActionDTO(a.id,a.action.id,a.action.libelle,"
            + "a.codeCouleur, a.callBack.code) "
            + "FROM ActionPermise a WHERE a.etat.id=:idEtat ")
    List<ActionDTO> findByEtat(@Param("idEtat") String idEtat);

    @Query("SELECT new com.base.frame.socle.core.workflow.dto.ActionDTO(a.id,a.action.id,a.action.libelle,"
            + "a.codeCouleur, a.callBack.code) "
            + " FROM ActionPermise a WHERE a.etat.id=:idEtat  "
            
            + " AND a.habilitation.code <= :codeHabilitation ")
    List<ActionDTO> findByEtatByHabilitation(@Param("idEtat") String idEtat, 
            @Param("codeHabilitation") String codeHabilitation);

    @Query("SELECT new com.base.frame.socle.core.workflow.dto.ActionSimpleObject(a.id, a.etat.workflow.id, a.action.id, a.etat.id) FROM ActionPermise a WHERE a.etat.id=:idEtat AND a.etat.workflow.id=:idW ")
    Page<ActionSimpleObject> findAllAction(@Param("idEtat") String idEtat, @Param("idW") String idW, Pageable p);

    @Query("SELECT a FROM ActionPermise a WHERE  a.etat.id=:idEtat")
    public List<ActionPermise> listActionPermises(@Param("idEtat") String idEtat);

    @Query("SELECT a FROM ActionPermise a WHERE  a.etat.id=:idEtat  AND a.etatSuivant.id=:idEtatSuivant ")
    public Optional<ActionPermise> getActionPermise(@Param("idEtat") String idEtat,
            @Param("idEtatSuivant") String idEtatSuivant);

    @Query("SELECT a FROM ActionPermise a WHERE  a.etat.id=:idEtat AND a.habilitation.code =:codeHabilitation")
    public List<ActionPermise> listActionPermisesParHabilitation(@Param("idEtat") String idEtat,
            @Param("codeHabilitation") String codeHabilitation);

    @Query("SELECT COUNT(a.id) FROM ActionPermise a WHERE  a.etat.id=:idEtat")
    public Integer countActionPermises(@Param("idEtat") String idEtat);

    @Query("SELECT a.id FROM ActionPermise a WHERE a.action.code=:codeAction AND a.etat.id=:idEtat")
    public Optional<String> findActionByCode(@Param("codeAction") String codeAction, @Param("idEtat") String idEtat);

    @Query("SELECT a.indicateurCommentaire.code FROM ActionPermise a WHERE a.id=:id")
    public Optional<String> findCommentaireBydeAction(@Param("id") String codeAction);
    
    @Query("SELECT a FROM ActionPermise a WHERE a.callBack IS NOT NULL")
    List<ActionPermise> findAllActionHavingCallBack();

}
