/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;


import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.workflow.entity.WorkFlowEtape;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author W.KOUWONOU
 * @date 22/03/2018
 * @version 1.0.0
 */
public interface WorkFlowEtapeRepository extends JpaRepository<WorkFlowEtape, String> {

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:id ORDER BY w.sequence ASC")
    public List<WorkFlowEtape> findByWorkFlow(@Param("id") Integer id);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.codeListeActions.code=:code")
    public List<WorkFlowEtape> findByCodification(@Param("code") String code);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:id AND w.ordre=:o")
    public List<WorkFlowEtape> findByWorkFlowAndOrder(@Param("id") Integer id, @Param("o") Integer ordre);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:id AND w.codeStatut=:c")
    public List<WorkFlowEtape> findByWorkFlowAndCodeStatut(@Param("id") Integer id, @Param("c") String codeStatut);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:id AND w.sequence=:c AND w.nextAction.id=:na")
    public List<WorkFlowEtape> findByWorkFlowAndSequenceAndNextAction(@Param("id") Integer id, @Param("c") Integer sequence, @Param("na") String idNextAction);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:id AND w.sequence=:c AND w.previousAction.id=:pa AND w.nextAction.id=:na")
    public List<WorkFlowEtape> findByWorkFlowAndSequenceAndPreviousAndNextAction(@Param("id") Integer id, @Param("c") Integer sequence, @Param("pa") String idPreviousAction, @Param("na") String idNextAction);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:id AND w.ordre=:o AND w.sequence=:s")
    public List<WorkFlowEtape> findByWorkFlowAndOrderAndSequence(@Param("id") Integer id, @Param("o") Integer ordre, @Param("s") Integer sequence);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:id AND w.ordre = 1")
    public WorkFlowEtape getFirstStep(@Param("id") Integer idWorkFlow);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:idWorkflow AND w.sequence = (SELECT MIN(w1.sequence) FROM WorkFlowEtape w1 WHERE w1.workFlow.id = :idWorkflow) ORDER BY w.libelleStatut ASC")
    public List<WorkFlowEtape> getFirstSequence(@Param("idWorkflow") Integer idWorkFlow);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:idWorkflow "
            + "AND w.sequence = (SELECT MIN(w1.sequence) FROM WorkFlowEtape w1 WHERE w1.workFlow.id = :idWorkflow)"
            + "AND w.ordre = (SELECT MIN(w2.ordre) FROM WorkFlowEtape w2 WHERE w2.workFlow.id = :idWorkflow)"
    )
    public WorkFlowEtape getFirstSequenceFirstStep(@Param("idWorkflow") Integer idWorkFlow);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:idWorkflow AND w.sequence = (SELECT MIN(w1.sequence) FROM WorkFlowEtape w1 WHERE w1.workFlow.id = :idWorkflow AND w1.sequence> :currentSequence) ORDER BY w.libelleStatut ASC")
    public List<WorkFlowEtape> getNextSequenceEtapes(@Param("idWorkflow") Integer idWorkFlow, @Param("currentSequence") Integer sequence);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:idWorkflow "
            + " AND w.previousAction.id = :actionEntree "
            + " AND w.sequence = (SELECT MIN(w1.sequence)"
            + " FROM WorkFlowEtape w1 WHERE w1.workFlow.id = :idWorkflow "
            + " AND w1.sequence> :currentSequence) ORDER BY w.libelleStatut ASC")
    public List<WorkFlowEtape> getNextSequenceEtapesByAction(@Param("idWorkflow") Integer idWorkFlow,
            @Param("currentSequence") Integer sequence,
            @Param("actionEntree") String actionEntreeId);

    /**
     * Cette méthode permet d'avoir accès a la liste des actions suivantes d'un
     * workflow
     *
     * @param idWorkFlow l'id du workflow
     * @param sequence la sequence actuelle
     * @param nextEntry l'id codiflist de la séquence suivante
     * @return
     */
    @Query("SELECT w FROM WorkFlowEtape w WHERE w.previousAction.id IS NOT NULL AND w.workFlow.id=:idWorkflow AND w.sequence = "
            + " ( SELECT MIN(w1.sequence) FROM WorkFlowEtape w1 WHERE w1.workFlow.id = :idWorkflow AND w1.sequence> :currentSequence) "
            + " AND w.previousAction.id = :nextEntryId "
            + " ORDER BY w.libelleStatut ASC")
    public List<WorkFlowEtape> getNextSequenceEtapes(
            @Param("idWorkflow") Integer idWorkFlow,
            @Param("currentSequence") Integer sequence,
            @Param("nextEntryId") String nextEntry
    );

    @Query("SELECT MIN(w1.sequence) FROM WorkFlowEtape w1 WHERE w1.workFlow.id = :idWorkflow AND w1.sequence > :currentSequence")
    public Integer getNextSequence(@Param("idWorkflow") Integer idWorkFlow, @Param("currentSequence") Integer sequence);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:idWorkflow AND w.sequence = (SELECT MIN(w1.sequence) FROM WorkFlowEtape w1 WHERE w1.workFlow.id = :idWorkflow AND w1.sequence = :currentSequence) ORDER BY w.libelleStatut ASC")
    public List<WorkFlowEtape> getSequence(@Param("idWorkflow") Integer idWorkFlow, @Param("currentSequence") Integer sequence);

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.codeListeActions.code = :codeCodif AND w.codeStatut = :codeStatut")
    public WorkFlowEtape findOne(
            @Param("codeCodif") String codeCodif,
            @Param("codeStatut") String codeStatut
    );

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:idWorkflow AND w.ordre > :order ORDER BY w.ordre ASC")
    public List<WorkFlowEtape> findNextStep(
            @Param("idWorkflow") Integer idWorkFlow,
            @Param("order") Integer order,
            Pageable page
    );

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.workFlow.id=:idWorkflow ORDER BY w.ordre ASC")
    public List<WorkFlowEtape> findNextStep(
            @Param("idWorkflow") Integer idWorkFlow,
            Pageable page
    );

    @Query("SELECT w FROM WorkFlowEtape w WHERE w.ordre > (SELECT w1.ordre FROM WorkFlowEtape w1 WHERE w1.id = :idWorkflowStep) AND w.workFlow.id = (SELECT w1.workFlow.id FROM WorkFlowEtape w1 WHERE w1.id = :idWorkflowStep) ORDER BY w.ordre ASC")
    public List<WorkFlowEtape> findNextStepByWorkflowStep(
            @Param("idWorkflowStep") String idWorkFlowStep,
            Pageable page
    );

    @Query("SELECT DISTINCT (w.previousAction) , w.createdBy  FROM WorkFlowEtape w WHERE w.workFlow.id=:idWorkFlow"
            + "  ORDER BY w.createdBy ")
    public List<ParamList> listActionsWorkflow(@Param("idWorkFlow") Integer idWorkFlow);
}
