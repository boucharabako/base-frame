/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;

import com.base.frame.socle.core.workflow.entity.WorkFlowCycle;
import java.math.BigDecimal;
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
 * @author W.KOUWONOU
 * @since 21/03/2018
 * @version 1.0.0
 */
public interface WorkFlowCycleRepository extends JpaRepository<WorkFlowCycle, String>, JpaSpecificationExecutor<WorkFlowCycle> {

    @Query("SELECT w FROM WorkFlowCycle w WHERE w.description LIKE :mc OR"
            + " w.codeListeActions.libelle LIKE :mc OR w.codeListeActions.code LIKE :mc ")
    public Page<WorkFlowCycle> findByMocle(@Param("mc") String mc, Pageable p);

    @Query("SELECT w FROM WorkFlowCycle w WHERE w.description LIKE :mc OR"
            + " w.codeListeActions.libelle LIKE :mc OR w.codeListeActions.code LIKE :mc ")
    public List<WorkFlowCycle> findByMocle(@Param("mc") String mc);

    /**
     * @modifier par hakakpo 20-05-2019
     * @param code
     * @return
     */
    @Query("SELECT w FROM WorkFlowCycle w WHERE w.statut.code = :code ORDER BY w.libelle ASC")
    public List<WorkFlowCycle> findByStatut(@Param("code") String code);

    /**
     * @modifier par hakakpo 20-05-2018
     * @param idw
     * @param code
     * @return
     */
    @Query("SELECT w FROM WorkFlowCycle w WHERE w.id=:idw AND w.statut.code = :code ORDER BY w.libelle")
    public WorkFlowCycle findByIDAndStatut(@Param("idw") String idw, @Param("code") String code);

    @Query("SELECT w FROM WorkFlowCycle w WHERE w.statut.code = :code AND w.codeListeActions.domaine.code=:codeDomaine")
    public List<WorkFlowCycle> findByStatutAndDomaine(@Param("code") String code, @Param("codeDomaine") String codeDomaine);

    @Query("SELECT w FROM WorkFlowCycle w WHERE w.id = :id ORDER BY w.libelle ASC")
    public List<WorkFlowCycle> findById1(@Param("id") String id);

    @Query("SELECT w FROM  WorkFlowCycle w WHERE w.id=:code")
    public WorkFlowCycle findWorkflowCycleByCode(@Param("code") String code);

    @Query("SELECT Max(w.numero) FROM  WorkFlowCycle w WHERE w.typeWorkflow.code=:code")
    public Optional<Integer> findLastNumeroByType(@Param("code") String type);

    @Query("SELECT w.delaiExecution FROM  WorkFlowCycle w WHERE w.id=:idWkf")
    public Optional<Integer> findNombreJoursWkf1(@Param("idWkf") String idWkf);
    
    @Query("SELECT w.delaiExecution FROM  WorkFlowCycle w WHERE w.id=:idWkf")
    public Optional<BigDecimal> findNombreJoursWkf(@Param("idWkf") String idWkf);

}
