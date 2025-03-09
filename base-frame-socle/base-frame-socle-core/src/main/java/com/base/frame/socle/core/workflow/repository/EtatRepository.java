/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;


import com.base.frame.socle.core.dto.IdLabelObject;
import com.base.frame.socle.core.workflow.dto.EtatDTO;
import com.base.frame.socle.core.workflow.entity.Etat;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author W.KOUWONOU
 * @vesion 1.0.0
 * @since 13/08/2018
 */
public interface EtatRepository extends JpaRepository<Etat, String> {

    @Query("SELECT e.id FROM Etat e WHERE e.workflow.id=:w AND e.ideSequence=:s AND e.indicEtapeValidation.code=:v")
    public List<String> findByWorkflowAndSequenceAndEtapeValidation(@Param("w") String workflow,
            @Param("s") Integer sequence, @Param("v") String etapeValidation);

//    @Query("SELECT new com.ngs.core.codification.dto.EtatDTO(e.id,"
//            + " e.ideSequence, e.ideEtape, e.libelleEtat, e.codeEtat, e.codeCouleur,e.indicModifAuthorisee.code) "
//            + " FROM Etat e WHERE e.workflow.id=:id ORDER BY e.ideSequence , e.ideEtape ASC")
//    public List<EtatDTO> findByWorkflowCycle(@Param("id") String id);
//    @Query("SELECT new com.ngs.core.codification.dto.EtatDTO(e.id,"
//            + " e.ideSequence, e.ideEtape, e.libelleEtat, e.codeEtat, e.codeCouleur,e.indicModifAuthorisee.code) "
//            + " FROM Etat e WHERE e.workflow.id=:id" )
//    public List<EtatDTO> findByWorkflowCycle(@Param("id") String id);
    @Query("SELECT new com.base.frame.socle.core.workflow.dto.EtatDTO(e.id,"
            + " e.ideSequence, e.ideEtape, e.libelleEtat, e.codeEtat, e.codeCouleur,e.indicModifAuthorisee.code) "
            + " FROM Etat e WHERE e.workflow.id=:id ORDER BY e.ideSequence ASC, e.ideEtape ASC")
    public List<EtatDTO> findByWorkflowCycle(@Param("id") String id);

    /**
     *
     * Recherche les Etapes d'un workflow qui ont le meme code
     *
     * @author W.KOUWONOU
     * @vesion 1.0.0
     * @since 17/08/2018
     * @param idw Identifiant du workflow
     * @param code Le code de l'etape
     * @return Les etats
     */
    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw AND e.codeEtat=:code")
    public Optional<Etat> findByWorkflowAndCodeEtat(@Param("idw") String idw, @Param("code") String code);

    /**
     *
     * Recherche les Etapes d'un workflow qui ont le meme code
     *
     * @author W.KOUWONOU
     * @vesion 1.0.0
     * @since 17/08/2018
     * @param idw Identifiant du workflow
     * @param idEtat Identifiant de l'etape
     * @return Les etats
     */
    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw AND e.id=:idEtat")
    public Optional<Etat> findByWorkflowAndIdEtat(@Param("idw") String idw, @Param("idEtat") String idEtat);

    /**
     * Conte les sequence etapes par rapport aux numero de sequence consecuives
     *
     * @param idw Identifiant du workflow
     * @param seq Numero de la sequecence en cours
     * @return Nombre total de sequence
     */
    @Query("SELECT COUNT(e.id) FROM Etat e WHERE  e.workflow.id=:idw AND  e.ideSequence=:seq")

    public Integer countSequenceByWorkflow(@Param("idw") String idw,
            @Param("seq") Integer seq);

    /**
     * Conte les sequence etapes par rapport aux numero de sequence consecuives
     *
     * @param idw Identifiant du workflow
     * @param seqPrec Numero de la sequecence precedente
     * @param seq Numero de la sequecence en cours
     * @return Nombre total de sequence
     */
    @Query("SELECT COUNT(e.id) FROM Etat e WHERE  e.workflow.id=:idw AND ( e.ideSequence=:seqP OR e.ideSequence=:seq)")

    public Integer countSequenceByWorkflowAndSeqencePrecAndSequence(@Param("idw") String idw,
            @Param("seqP") Integer seqPrec, @Param("seq") Integer seq);

    /**
     * Compte les sequence etapes par rapport aux numero de sequence consecuives
     *
     * @param idw Identifiant du workflow
     * @param etapeP Etape précedente
     * @param etape etape en cours
     * @param seq Numero de la sequecence en cours
     * @return Nombre total de sequence
     */
    @Query("SELECT COUNT(e.id ) FROM Etat e WHERE  e.workflow.id=:idw AND e.ideSequence=:seq "
            + "AND( e.ideEtape=:etapeP OR e.ideEtape=:etape)")

    public Integer countEtapeByWorkflowAndEtapePrecAndEtape(@Param("idw") String idw,
            @Param("seq") Integer seq, @Param("etapeP") Integer etapeP, @Param("etape") Integer etape);

    /**
     * Compte les sequence etapes par rapport à un workflow
     *
     * @param idw Identifiant du workflow
     * @return Nombre total de sequence
     */
    @Query("SELECT COUNT(e.id ) FROM Etat e WHERE  e.workflow.id=:idw")
    public Integer countByWorkflow(@Param("idw") String idw);

    /**
     *
     * Recherche les Etats d'un workflow qui ont la meme sequence et etape
     *
     * @author W.KOUWONOU
     * @vesion 1.0.0
     * @since 17/08/2018
     * @param idw Identifiant du workflow
     * @param seq Numero de la sequence
     * @param etape Numero de l'etape
     * @return Les Etats
     */
    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw AND e.ideSequence=:seq AND e.ideEtape=:etape")

    public Optional<Etat> findByWorkflowAndSequenceAndEtape(@Param("idw") String idw, @Param("seq") Integer seq, @Param("etape") Integer etape);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw AND e.ideSequence=:seq")
    public List<Etat> findNextEtatBySequence(@Param("idw") String idw, @Param("seq") Integer seq);
    
    @Query("SELECT e.ideSequence FROM Etat e WHERE e.workflow.id=:idw  AND e.indicEtapeValidation IS NOT NULL AND e.indicEtapeValidation.code=:ev ORDER BY e.ideSequence ASC")
    public List<Integer> findSequenceEtapeValidation(@Param("idw") String idw,@Param("ev") String statutEtapeValidation);
    
    @Query("SELECT e.id FROM Etat e WHERE e.workflow.id=:idw  AND e.indicEtapeValidation IS NOT NULL AND e.indicEtapeValidation.code=:ev ORDER BY e.ideSequence ASC")
    public List<String> findEtapeValidationOrderBySequence(@Param("idw") String idw,@Param("ev") String statutEtapeValidation);
       
    @Query("SELECT e.workflow.id FROM Etat e WHERE e.id=:idEtat ")
    public String findIdWorklowByIdEtat(@Param("idEtat") String idEtat);
    
    @Query("SELECT e.ideSequence FROM Etat e WHERE e.id=:idEtat ")
    public Integer findSequenceWorklowByIdEtat(@Param("idEtat") String idEtat);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw AND e.ideSequence<:seq")
    public List<Etat> findEtatBeforeFirstValidationEtat(@Param("idw") String idw, @Param("seq") Integer seq);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw AND e.ideSequence=:seq"
            + " AND e.indicEtapeValidation.code=:v ")
    public List<Etat> findNextEtatBySequence(@Param("idw") String idw,
            @Param("seq") Integer seq, @Param("v") String codeValidation);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw AND e.id!=:idCurrentEtat")
    public List<Etat> findEtatByWorkflow(@Param("idw") String idw, @Param("idCurrentEtat") String idCurrentEtat);

    @Query("SELECT w FROM Etat w WHERE w.workflow.id=:idWorkflow AND "
            + "w.ideSequence = (SELECT MIN(w1.ideSequence) FROM Etat w1 "
            + " WHERE w1.workflow.id = :idWorkflow AND w1.ideSequence> :currentSequence) "
            + " ORDER BY w.libelleEtat ASC")
    public List<Etat> getNextSequenceEtats(@Param("idWorkflow") String idWorkFlow,
            @Param("currentSequence") Integer sequence);

    @Query("SELECT w FROM Etat w WHERE w.workflow.id=:idWorkflow "
            + "AND w.ideSequence = (SELECT MIN(w1.ideSequence) FROM Etat w1 WHERE w1.workflow.id = :idWorkflow)"
            + "AND w.ideEtape=(SELECT MIN(e.ideEtape) FROM Etat e WHERE e.workflow.id=:idWorkflow)")
    public Etat getFirstSequenceFirstEtat(@Param("idWorkflow") String idWorkFlow);

    @Query("SELECT w FROM Etat w WHERE w.workflow.id=:idWorkflow "
            + "AND w.ideSequence = (SELECT MIN(w1.ideSequence) FROM Etat w1 WHERE w1.workflow.id = :idWorkflow)"
            + "AND w.ideEtape=(SELECT MIN(e.ideEtape) FROM Etat e WHERE e.workflow.id=:idWorkflow)")
    public Optional<Etat> getOptionalFirstSequenceFirstEtat(@Param("idWorkflow") String idWorkFlow);

    @Query("SELECT MIN(w1.ideSequence) FROM Etat w1 WHERE w1.workflow.id = :idWorkflow AND w1.ideSequence > :currentSequence")
    public Integer getNextSequence(@Param("idWorkflow") String idWorkFlow, @Param("currentSequence") Integer sequence);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw AND e.ideSequence=(SELECT MIN(e.ideSequence) "
            + "FROM Etat e WHERE e.workflow.id=:idw) AND e.ideEtape=(SELECT MIN(e.ideEtape) FROM Etat e WHERE e.workflow.id=:idw)")

    public Optional<Etat> findFirtEtatByWorkfow(@Param("idw") String idWorkflow);

    @Query("SELECT e FROM Etat e WHERE e.id=:ide AND e.ideSequence=(SELECT MIN(e.ideSequence) "
            + "FROM Etat e WHERE e.workflow.id=:idw) AND e.ideEtape=(SELECT MIN(e.ideEtape) FROM Etat e WHERE e.workflow.id=:idw)")
    public Optional<Etat> checkIsFirtEtatByWorkfow(@Param("idw") String idWorkflow, @Param("ide") String idEtat);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw ORDER BY e.ideSequence ASC, e.ideEtape ASC")
    public List<Etat> findAllEtatByWorkflow(@Param("idw") String idw);

    /**
     * Retourne par ordre croissant toutes les sequences d'un workflow
     *
     * @param idWorkFlow
     * @return
     */
    @Query("SELECT e.ideSequence FROM Etat e WHERE e.workflow.id =:idWorkflow ORDER BY e.ideSequence ASC")
    public List<Integer> findAllSequenceAsc(@Param("idWorkflow") String idWorkFlow);

    @Query("SELECT DISTINCT e.ideSequence FROM Etat e WHERE e.workflow.id =:idWorkflow ORDER BY e.ideSequence ASC")
    public List<Integer> findAllSequenceAscDistinct(@Param("idWorkflow") String idWorkFlow);

    @Query("SELECT e.ideEtape FROM Etat e WHERE e.workflow.id =:idWorkflow AND e.ideSequence=:ideSequence ORDER BY e.ideEtape ASC")
    public List<Integer> findAllEtapeOfSequence(@Param("idWorkflow") String idWorkFlow, @Param("ideSequence") Integer ideSequence);

    @Query("SELECT w FROM Etat w WHERE w.workflow.id=:idWorkflow AND w.ideSequence = (SELECT MAX(w1.ideSequence) FROM Etat w1 WHERE w1.workflow.id = :idWorkflow)"
            + "AND w.ideEtape=(SELECT MAX(e.ideEtape) FROM Etat e WHERE e.workflow.id=:idWorkflow)")
    public Optional<Etat> getLastSequenceLastEtat(@Param("idWorkflow") String idWorkFlow);

    @Query("SELECT DISTINCT e.ideSequence FROM Etat e WHERE e.workflow.id=:idw ORDER BY e.ideSequence ASC")
    public Page<Integer> findSequenceCodeByIndexAndWorkflow(@Param("idw") String idw, Pageable p);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw AND e.ideSequence =:ideSequence ORDER BY e.ideEtape ASC")
    public Page<Etat> findSequenceEtatByIndexAndWorkflow(@Param("idw") String idw, @Param("ideSequence") Integer ideSequence, Pageable p);

    @Query("SELECT new com.base.frame.socle.core.workflow.dto.IdLabelObject(e.id, e.libelleEtat) "
            + " FROM Etat e WHERE e.workflow.id=:id AND e.workflow.statut.code=:codeStatut ORDER BY e.ideSequence , e.ideEtape ASC")
    public List<IdLabelObject> findEtatByWorkflowCycle(@Param("id") String id, @Param("codeStatut") String codeStatut);

    @Query("SELECT new com.base.frame.socle.core.workflow.dto.IdLabelObject(e.id, e.libelleEtat) "
            + " FROM Etat e WHERE e.workflow.id=:id AND e.workflow.statut.code=:codeStatut AND e.indicEtapeValidation.id=:valid ORDER BY e.ideSequence , e.ideEtape ASC")
    public List<IdLabelObject> findEtatValidByWorkflowCycle(@Param("id") String id, @Param("codeStatut") String codeStatut, @Param("valid") String valid);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:id AND e.workflow.statut.code=:codeStatut AND e.indicEtapeValidation.id=:valid ORDER BY e.ideSequence , e.ideEtape ASC")
    public List<Etat> findEtatEntitieValidByWorkflowCycle(@Param("id") String id, @Param("codeStatut") String codeStatut, @Param("valid") String valid);

    @Query("SELECT MIN(e.ideSequence) FROM Etat e WHERE e.workflow.id=:id AND e.workflow.statut.code=:codeStatut AND e.indicEtapeValidation.id=:valid ")
    public Integer findEtatValidationSeqMinByWorkflow(@Param("id") String id, @Param("codeStatut") String codeStatut, @Param("valid") String valid);

    @Query("SELECT MAX(e.ideSequence) FROM Etat e WHERE e.workflow.id=:id AND e.workflow.statut.code=:codeStatut AND e.indicEtapeValidation.id=:valid ")
    public Integer findEtatValidationSeqMaxByWorkflow(@Param("id") String id, @Param("codeStatut") String codeStatut, @Param("valid") String valid);

    @Query("SELECT MAX(e.ideSequence) FROM Etat e WHERE e.workflow.id=:id AND e.indicEtapeValidation.code=:valid ")
    public Integer findEtatValidationSeqMaxByWorkflow(@Param("id") String id, @Param("valid") String valid);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw AND e.ideSequence=:seq AND e.ideEtape=(SELECT MIN(e.ideEtape) FROM Etat e WHERE e.workflow.id=:idw AND e.ideSequence=:seq)")
    public Optional<Etat> findFirstEtatByWorkflowAndSequence(@Param("idw") String idw, @Param("seq") Integer seq);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idWorkflow "
            + " AND e.indicEtapeValidation.code=:v "
            + " ORDER BY e.ideSequence ASC"
    )
    public List<Etat> etatsValidation(@Param("idWorkflow") String workflow,
            @Param("v") String validationCode);

    @Query("SELECT e.id FROM Etat e WHERE e.workflow.id IN :idsWorkflow "
            + " AND e.indicEtapeValidation.code=:v "
            + " ORDER BY e.ideSequence ASC"
    )
    public List<String> wfEtatsValidation(@Param("idsWorkflow") Object[] workflows,
            @Param("v") String validationCode);

    @Query("SELECT e FROM Etat e  "
            + " Where e.workflow.id=:idWorkflow "
            + " AND e.indicEtapeValidation.code=:v"
            + " AND e.ideSequence=(SELECT max(e.ideSequence) FROM Etat e WHERE e.workflow.id=:idWorkflow "
            + " AND e.indicEtapeValidation.code=:v)"
    )
    public Etat lastEtatValidation(@Param("idWorkflow") String workflow,
            @Param("v") String validationCode);

    @Query("SELECT e FROM Etat e  "
            + " Where e.workflow.id=:idWorkflow AND e.indicEtapeValidation.code=:v "
            + " AND e.ideSequence=(SELECT min(t.ideSequence) FROM Etat t WHERE t.workflow.id=:idWorkflow "
            + " AND t.indicEtapeValidation.code=:v)"
    )
    public Etat firstEtatValidation(@Param("idWorkflow") String workflow,
            @Param("v") String validationCode);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idWorkflow "
            + " AND e.indicEtapeValidation.code=:v "
            + " AND  e.ideSequence < :maxSeq AND e.ideSequence > :minSeq"
    )
    public List<Etat> etatBetweenSequences(@Param("idWorkflow") String workflow,
            @Param("v") String validationCode, @Param("minSeq") Integer minSeq,
            @Param("maxSeq") Integer maxSeq);

    @Query("SELECT new com.base.frame.socle.core.workflow.dto.EtatDTO(e.id,"
            + " e.ideSequence, e.ideEtape, e.libelleEtat, e.codeEtat, e.codeCouleur,e.indicModifAuthorisee.code) "
            + " FROM Etat e WHERE e.workflow.id=:id AND e.indicEtapeValidation.id=:valid ORDER BY e.ideSequence , e.ideEtape ASC")
    public List<EtatDTO> findByWorkflowCycleValider(@Param("id") String id, @Param("valid") String valid);

       @Query("SELECT new com.base.frame.socle.core.workflow.dto.EtatDTO(e.id,"
            + " e.ideSequence, e.ideEtape, e.libelleEtat, e.codeEtat, e.codeCouleur,e.indicModifAuthorisee.code) "
            + " FROM Etat e WHERE e.workflow.id=:idWorkflow "
               + " AND "
               + " ("
               + "e.ideSequence > (SELECT s.ideSequence FROM Etat s Where s.workflow.id=:idWorkflow AND s.indicEtapeValidation.code=:v  AND s.ideSequence=(SELECT min(t.ideSequence) FROM Etat t WHERE t.workflow.id=:idWorkflow "
            + " AND t.indicEtapeValidation.code=:v)) "
                    + " OR( e.ideEtape >= (SELECT s.ideEtape FROM Etat s Where s.workflow.id=:idWorkflow AND s.indicEtapeValidation.code=:v  AND s.ideSequence=(SELECT min(t.ideSequence) FROM Etat t WHERE t.workflow.id=:idWorkflow "
            + " AND t.indicEtapeValidation.code=:v))"
               + " AND e.ideSequence = (SELECT s.ideSequence FROM Etat s Where s.workflow.id=:idWorkflow AND s.indicEtapeValidation.code=:v  AND s.ideSequence=(SELECT min(t.ideSequence) FROM Etat t WHERE t.workflow.id=:idWorkflow  AND t.indicEtapeValidation.code=:v)) "
               + " )"
               + " OR "
               + " e.id = (SELECT s.id FROM Etat s Where s.workflow.id=:idWorkflow AND s.indicEtapeValidation.code=:v  AND s.ideSequence=(SELECT min(t.ideSequence) FROM Etat t WHERE t.workflow.id=:idWorkflow  AND t.indicEtapeValidation.code=:v))"
               + " )"
               + " ORDER BY e.ideSequence , e.ideEtape ASC")
    public List<EtatDTO> findByEtatByWorkflowSequenceSupOrEqualFirstEtapeValidation(@Param("idWorkflow") String idWorkflow, @Param("v") String validationCode);

    
    @Query("SELECT e.id FROM Etat e WHERE e.workflow.id=:id AND e.indicEtapeValidation.id=:valid ORDER BY e.ideSequence , e.ideEtape ASC")
    public List<String> findByWorkflowCycleIdEtatValider(@Param("id") String id, @Param("valid") String valid);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw AND e.codeEtat=:idCurrentEtat")
    public Etat findEtatOfWorkflow(@Param("idw") String idw, @Param("idCurrentEtat") String idCurrentEtat);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idWorkflow AND e.ideSequence >:seq")
    public List<Etat> etatSuperieurDernierEtapeValidation(@Param("idWorkflow") String workflow, @Param("seq") Integer seq);

    @Query("SELECT e FROM Etat e WHERE e.workflow.id=:idw "
            + " AND e.ideSequence=:seq "
            + " AND e.workflow.statut.code=:codeStatut "
            + " AND e.indicEtapeValidation.id=:valid")
    public Optional<Etat> findEtatByWorkflowAndSequenceAndEtapeValidation(
            @Param("idw") String idw,
            @Param("seq") Integer seq,
            @Param("codeStatut") String codeStatut,
            @Param("valid") String valid);

//     @Query("SELECT DISTINCT e.id "
//            + " FROM Etat e WHERE e.workflow.id=:idw AND e.id IN (SELECT a.etat.id "
//            + " FROM ActionPermise a"
//            + " WHERE (SELECT COUNT(ap.id) FROM ActionPermise ap WHERE  ap.etat.id=:d.etat.id)=0)")
//    public List<String> findDernierEtatWorkflowId(@Param("idw") String idw);
//    
    @Query("SELECT DISTINCT e.id "
            + " FROM Etat e WHERE e.workflow.id=:idw AND e.id NOT IN (SELECT a.etat.id "
            + " FROM ActionPermise a WHERE a.etat.workflow.id=:idw) "
    )
    public List<String> findDernierEtatWorkflowId(@Param("idw") String idw);

    @Query("SELECT e.id FROM Etat e WHERE e.workflow.id=:idw AND e.ideSequence=(SELECT MIN(e.ideSequence) "
            + "FROM Etat e WHERE e.workflow.id=:idw) AND e.ideEtape=(SELECT MIN(e.ideEtape) FROM Etat e WHERE e.workflow.id=:idw)")

    public List<String> findFirtEtatOfWorkfows(@Param("idw") String idWorkflow);

    @Query("SELECT e.id FROM Etat e  "
            + " Where e.workflow.id=:idWorkflow "
            + " AND e.ideSequence=(SELECT min(e.ideSequence) FROM Etat e WHERE e.workflow.id=:idWorkflow "
            + " AND e.indicEtapeValidation.code=:v)"
    )
    public List<String> firstEtatValidationId(@Param("idWorkflow") String workflow,
            @Param("v") String validationCode);

    @Query("SELECT e.workflow.delaiExecution FROM  Etat e WHERE e.id=:idEtat")
    public Optional<Integer> findNombreJoursWkfEtat1(@Param("idEtat") String idEtat);

    @Query("SELECT e.workflow.delaiExecution FROM  Etat e WHERE e.id=:idEtat")
    public Optional<BigDecimal> findNombreJoursWkfEtat(@Param("idEtat") String idEtat);

    @Query("SELECT SUM (e.delaiExecution) From Etat e WHERE e.workflow.id=:idWkf")
    public Optional<Integer> sommeDesDelaisEtatDuWKf1(@Param("idWkf") String idWkf);

    @Query("SELECT SUM (e.delaiExecution) From Etat e WHERE e.workflow.id=:idWkf")
    public Optional<BigDecimal> sommeDesDelaisEtatDuWKf(@Param("idWkf") String idWkf);

    @Query("SELECT w FROM Etat w WHERE w.workflow.id=:idWorkflow "
            + " AND w.ideSequence = (SELECT MIN(w1.ideSequence) FROM Etat w1 WHERE w1.workflow.id = :idWorkflow)")
    public List<Etat> getFirstSequenceLastEtat(@Param("idWorkflow") String idWorkFlow);
}
