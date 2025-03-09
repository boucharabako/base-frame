/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;


import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.workflow.entity.WorkFlowCycle;
import com.base.frame.socle.core.workflow.entity.WorkflowCycleObjet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author WKOUWONOU
 */
public interface WorkflowCycleObjetRepository extends
        JpaRepository<WorkflowCycleObjet, String> {

    @Query("SELECT w FROM  WorkflowCycleObjet w WHERE w.workflow.id=:id")
    public List<WorkflowCycleObjet> findByWorkflowCycle(@Param("id") String id);

    @Query("SELECT w.domaine.id FROM  WorkflowCycleObjet w")
    public List<String> findIdDomaine();

//    @Query("SELECT new com.ngs.core.codification.entities.CodifList(p.paramCode.id , "
//            + "l.paramCode.code, p.libelle) FROM WorkflowCycleObjet c , ParamList p "
//            + " WHERE c.workflow.id=:idw AND p.paramCode.id= c.domaine.id   ")
//    public List<ParamList> findDomaineByWorkflowCycle(@Param("idw") String id, @Param("codeLangue") String codeLangue);

//    @Query("SELECT new com.ngs.core.codification.entities.CodifList(p.paramCode.id , "
//            + "p.paramCode.code, p.libelle) FROM ParamList p , ParamCode pc "
//            + " WHERE p.paramCode.codification.code=:domaine "
//            + " AND   p.codifList.id= c.id  AND l.codeLangue.code= :codeLangue  "
//            + " AND c.code NOT IN ( SELECT w.domaine.code FROM WorkflowCycleObjet w WHERE w.workflow.statut.code IN :codeStatut)")
//    public List<ParamList> findDomaineNotSetToWorkflow(@Param("domaine") String domaine, @Param("codeLangue") String codeLangue,
//            @Param("codeStatut") String[] codeStatut);

//    @Query("SELECT new com.ngs.core.codification.entities.CodifList( l.codifList.id , l.codifList.code, l.libelle)"
//            + " FROM Libelle l "
//            + " WHERE l.codifList.codification.code=:domaine  AND l.codeLangue.code= :codeLangue")
//    public List<ParamList> findDomaineByDomaine(@Param("domaine") String domaine, @Param("codeLangue") String codeLangue);

    @Query("SELECT w FROM  WorkflowCycleObjet w WHERE w.domaine.id=:id AND w.workflow.id=:idw")
    public Optional<WorkflowCycleObjet> findByDomaine(@Param("idw") String idw, @Param("id") String id);

    @Query("SELECT w FROM  WorkflowCycleObjet w WHERE w.domaine.code=:codeConcept AND w.workflow.statut.code=:codeStatut")
    public WorkflowCycleObjet findByCodeConcept(@Param("codeConcept") String codeConcept, @Param("codeStatut") String codeStatut);
    
    @Query("SELECT w.workflow FROM  WorkflowCycleObjet w WHERE w.domaine.code=:codeConcept AND w.workflow.statut.code=:codeStatut")
    public Optional<WorkFlowCycle> findWorkflowByCodeConcept(@Param("codeConcept") String codeConcept, @Param("codeStatut") String codeStatut);

    @Query("SELECT w FROM  WorkflowCycleObjet w WHERE w.domaine.code=:codeConcept")
    public List<WorkflowCycleObjet> findByCodeConceptAndIdWkF(@Param("codeConcept") String codeConcept);

    @Query("SELECT w FROM  WorkflowCycleObjet w WHERE w.domaine.code=:codeConcept AND w.workflow.statut.code=:codeStatut")
    public List<WorkflowCycleObjet> findByCodeConceptMetier(@Param("codeConcept") String codeConcept, @Param("codeStatut") String codeStatut);

    @Query("SELECT w FROM  WorkflowCycleObjet w WHERE w.domaine.code=:codeConcept AND w.workflow.statut.code=:codeStatut")
    public List<WorkflowCycleObjet> findByCodeConceptList(@Param("codeConcept") String codeConcept, @Param("codeStatut") String codeStatut);

    @Query("SELECT w FROM  WorkflowCycleObjet w WHERE w.workflow.statut.code=:codeStatut")
    public List<WorkflowCycleObjet> findWorkflowCycleObjectByWorkflowStatut(@Param("codeStatut") String codeStatut);

    @Query("SELECT w FROM  WorkflowCycleObjet w WHERE w.workflow.id=:workflow")
    public WorkflowCycleObjet findWorkflowCycleObjectByWorkfId(@Param("workflow") String workflow);

}
