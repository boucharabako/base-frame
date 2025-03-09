///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.TrackingParamObjet;
//import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author W.KOUWONOU
// * @since 02/05/2018
// * @version 1.0.0
// *
// * Couche DAO de TrackingParamObjet
// */
//public interface TrackingParamObjetRepository extends JpaRepository<TrackingParamObjet, String> {
//
//    @Query(("SELECT t FROM TrackingParamObjet t WHERE t.conceptMetier.codification.code= :cptMetier AND t.conceptMetier.ideCode=:cm AND t.idObject=:ido ORDER BY t.createdBy ASC"))
//    public List<TrackingParamObjet> findByConceptMetierAndIdObjet(@Param("cptMetier") String conceptMetier , @Param("cm") String ideCodeConceptMetier,@Param("ido")String idObjet );
//
//}
