/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;

import com.base.frame.socle.core.workflow.entity.WorkFlow;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author W.KOUWONOU
 * @since 21/03/2018
 * @version 1.0.0
 */
public interface WorkFlowRepository extends JpaRepository<WorkFlow, Integer> {

    @Query("SELECT w FROM WorkFlow w WHERE w.description LIKE :mc OR"
            + " w.codeListeActions.libelle LIKE :mc OR w.codeListeActions.code LIKE :mc ")
    public Page<WorkFlow> findByMocle(@Param("mc") String mc, Pageable p);

    @Query("SELECT w FROM WorkFlow w WHERE w.description LIKE :mc OR"
            + " w.codeListeActions.libelle LIKE :mc OR w.codeListeActions.code LIKE :mc ")
    public List<WorkFlow> findByMocle(@Param("mc") String mc);

    @Query("SELECT w FROM WorkFlow w WHERE w.statut.code = :code")
    public List<WorkFlow> findByStatut(@Param("code") String code);
}
