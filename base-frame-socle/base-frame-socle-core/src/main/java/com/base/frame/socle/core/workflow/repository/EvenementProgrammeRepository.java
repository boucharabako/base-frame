/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;

import com.base.frame.socle.core.workflow.entity.EvenementPossible;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * core-codification
 *
 * @author Pierre NGS creée le 20 août 2018 12:12:34
 */
public interface EvenementProgrammeRepository extends JpaRepository<EvenementPossible, String> {

    @Query("SELECT e FROM EvenementPossible e WHERE e.action.id=:idAction AND e.workFlow.id=:idWorkFlow")
    public List<EvenementPossible> findEvenementPossibleByActionAndWorkFlow(@Param("idAction") String idAction, @Param("idWorkFlow") String idWorkFlow);
    @Query("SELECT e FROM EvenementPossible e WHERE e.action.id=:idAction ")
    public List<EvenementPossible> findByAction(@Param("idAction") String idAction);

    @Query("SELECT e FROM EvenementPossible e WHERE e.action.id=:idAction AND e.workFlow.id=:idWorkFlow AND e.event.code=:idEvent")
    Optional<EvenementPossible> findEvenementPossibleByWorkflowAndActionAndEvent(@Param("idAction") String idAction, @Param("idWorkFlow") String idWorkFlow, @Param("idEvent") String idEvent);

    @Query("SELECT e FROM EvenementPossible e WHERE e.action.id=:idAction AND e.event.code=:idEvent")
    Optional<EvenementPossible> findOneByEnventAction(@Param("idAction") String idAction,  @Param("idEvent") String idEvent);
}
