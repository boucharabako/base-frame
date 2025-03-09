/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;

import com.base.frame.socle.core.workflow.entity.Tracking;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author rkoufionou
 * @since 21/03/2018
 * @version 1.0.0
 */
public interface TrackingRepository extends JpaRepository<Tracking, String> {

    @Query("SELECT t FROM Tracking t WHERE t.idObject = :idObject AND t.conceptMetier.id = :idConceptMetier ORDER BY t.createdDate ASC")
    public List<Tracking> findTrakings(
            @Param("idObject") String idObject,
            @Param("idConceptMetier") String idConceptMetier);

//    @Query("SELECT t FROM Tracking t WHERE t.idObject = :idObject AND t.conceptMetier.ideCode = :idConceptMetier ORDER BY t.createdDate ASC")
//    public List<Tracking> findTrakingsByConcept(
//            @Param("idObject") String idObject,
//            @Param("idConceptMetier") String idConceptMetier);
    
    
//        @Query("SELECT t FROM Tracking t WHERE t.idObject=:idObject AND t.conceptMetier.ideCode=:idConceptMetier AND t.etatFinal.id=:idEtatFinal ORDER BY t.createdDate ASC")
//    public List<Tracking> findTrakingsByObjectConceptAndEtatFinal(
//            @Param("idObject") String idObject,
//            @Param("idConceptMetier") String idConceptMetier,  @Param("idEtatFinal") String  idEtatFinal);

    @Query("SELECT t FROM Tracking t WHERE t.idObject = :idObject AND t.createdBy = :username ")
    public List<Tracking> findByDocumentAndUsername(
            @Param("idObject") String idObject,
            @Param("username") String username);

    @Query("SELECT t FROM Tracking t WHERE t.idObject=:idObject AND "
            + "t.conceptMetier.id = :idConceptMetier AND t.etatFinal.codeEtat=:codeEtat "
            + " AND t.createdDate=(SELECT MAX(a.createdDate)   FROM Tracking a WHERE a.idObject=:idObject AND a.conceptMetier.id = :idConceptMetier AND a.etatFinal.codeEtat=:codeEtat)")
    public Tracking findLastTacking(
            @Param("idObject") String idObject,
            @Param("idConceptMetier") String idConceptMetier,
            @Param("codeEtat") String codeEtat);
   
    
//    @Query("SELECT t FROM Tracking t WHERE t.idObject=:idObject AND "
//            + "t.conceptMetier.ideCode = :idConceptMetier AND t.createdDate>=( SELECT max(a.createdDate) FROM Tracking a WHERE a.idObject=:idObject AND t.conceptMetier.ideCode =:idConceptMetier)")
//    public Optional<Tracking> findLastTacking(
//            @Param("idObject") String idObject,
//            @Param("idConceptMetier") String idConceptMetier);
    
    @Query("SELECT DISTINCT (t.etatInitial.id) FROM Tracking t WHERE t.idObject=:idObject AND "
            + "t.conceptMetier.id = :idConceptMetier AND t.etatInitial.id IN :codeEtat")
    public List<String> findByEtats(
            @Param("idObject") String idObject,
            @Param("idConceptMetier") String idConceptMetier, @Param("codeEtat") List<String> codeEtat);

}
