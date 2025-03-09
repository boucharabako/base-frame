///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.ValeurExtension;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author NGS_004
// */
//public interface ValeurExtensionRepository extends JpaRepository<ValeurExtension, String> {
//
//    @Query("SELECT ve FROM ValeurExtension ve WHERE ve.extension.id=:idExtension AND ve.idObjet=:idObjet")
//    List<ValeurExtension> findByIdExtensionIdObject(@Param("idExtension") String idExtension, @Param("idObjet") String idObjet);
//
//    @Query("SELECT ve FROM ValeurExtension ve WHERE ve.extension.id=:idExtension AND ve.idObjet=:idObjet")
//    Optional<ValeurExtension> findByIdExtensionIdLabelObject(@Param("idExtension") String idExtension, @Param("idObjet") String idObjet);
//
//    @Query("SELECT ve FROM ValeurExtension ve WHERE ve.extension.id=:idExtension AND ve.idObjet=:idObjet")
//    ValeurExtension findOneByIdExtensionIdObject(@Param("idExtension") String idExtension, @Param("idObjet") String idObjet);
//
//    @Query("SELECT ve FROM ValeurExtension ve WHERE ve.extension.id=:idExtension AND ve.idObjet=:idObjet")
//    Optional<ValeurExtension> findOneByIdExtensionIdObjectOpt(@Param("idExtension") String idExtension, @Param("idObjet") String idObjet);
//
//    @Query("SELECT ve FROM ValeurExtension ve WHERE ve.idObjet=:idObjet")
//    List<ValeurExtension> findByIdObject(@Param("idObjet") String idObjet);
//
//    @Query("SELECT ve FROM ValeurExtension ve WHERE ve.extension.id=:idExtension AND ve.idObjet=:idObjet AND ve.valeur=:valeur")
//    Optional<ValeurExtension> findByExtensionIdAndValueLabelObject(@Param("idExtension") String idExtension, @Param("idObjet") String idObjet, @Param("valeur") String valeur);
//
//    /**
//     * ASSIMA
//     *
//     * @param idExtension
//     * @param idObjet
//     * @param valeur
//     * @return
//     */
//    @Query("SELECT ve FROM ValeurExtension ve WHERE ve.extension.id=:idExtension AND ve.idObjet=:idObjet AND ve.valeur !=:valeur")
//    Optional<ValeurExtension> findByExtensionIdAndValueLabelObjectDifferent(@Param("idExtension") String idExtension, @Param("idObjet") String idObjet, @Param("valeur") String valeur);
//
//    @Query("SELECT ve FROM ValeurExtension ve WHERE ve.extension.id=:idExtension AND ve.idObjet=:idObjet AND (ve.valeur=:valeur OR ve.valeur=:valeur2)")
//    Optional<ValeurExtension> findByExtensionIdAndValueLabelObjectTwo(@Param("idExtension") String idExtension, @Param("idObjet") String idObjet, @Param("valeur") String valeur, @Param("valeur2") String valeur2);
//
//    @Query("SELECT ve.idObjet FROM ValeurExtension ve WHERE ve.extension.codeConcept.ideCode=:conceptMetierCode"
//            + " AND UPPER(ve.valeur) LIKE UPPER(:mot)")
//    List<String> searchIdObjects(@Param("conceptMetierCode") String conceptMetierCode,
//            @Param("mot") String mot);
//    
//    @Query("SELECT DISTINCT ve FROM ValeurExtension ve WHERE ve.extension.id=:idExtension")
//    List<ValeurExtension> findValeurByIdExtensionIdObject(@Param("idExtension") String idExtension);
//}
