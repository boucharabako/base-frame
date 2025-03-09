///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.dto.UserLogSimpleObject;
//import com.ngs.core.codification.entities.LogInOutTracking;
//import java.time.Instant;
//import java.util.List;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author kouwonou
// */
//public interface LogInOutTrackingRepository extends JpaRepository<LogInOutTracking, String>, JpaSpecificationExecutor<LogInOutTracking> {
//
////    @Query("SELECT l FROM LogInOutTracking l WHERE (upper(l.type) LIKE CONCAT('%',:motCle,'%') "
////            + " OR upper(l.createdBy) LIKE CONCAT('%',:date,'%')) "
////            + " ORDER BY l.createdDate DESC")
////    public Page<LogInOutTracking> findUtilisateursByMotCLE(Pageable p, @Param("motCle") String motCle, @Param("date") String date);
//    @Query("SELECT l FROM LogInOutTracking l, Utilisateur u WHERE l.createdBy != :systemeAccount AND "
//            + "u.username=l.createdBy AND "
//            + "l.createdDate BETWEEN :dateMin AND :dateMax"
//            + " AND (upper(l.type) LIKE CONCAT('%',:motCle,'%')"
//            + " OR upper(u.username) LIKE CONCAT('%',:motCle,'%')"
//            + " OR upper(u.firstName) LIKE CONCAT('%',:motCle,'%')"
//            + " OR upper(u.lastName) LIKE CONCAT('%',:motCle,'%'))"
//            + " ORDER BY l.createdDate DESC")
//    public Page<LogInOutTracking> findUtilisateursByMotCLEByDate(Pageable p, @Param("motCle") String motCle, @Param("dateMin") Instant dateMin, @Param("dateMax") Instant dateMax, @Param("systemeAccount") String systemeAccount);
//
//    @Query("SELECT l FROM LogInOutTracking l, Utilisateur u  WHERE l.createdBy != :systemeAccount AND "
//            + " u.username=l.createdBy "
//            + " AND (upper(l.type) LIKE CONCAT('%',:motCle,'%')"
//            + " OR upper(u.username) LIKE CONCAT('%',:motCle,'%')"
//            + " OR upper(u.firstName) LIKE CONCAT('%',:motCle,'%')"
//            + " OR upper(u.lastName) LIKE CONCAT('%',:motCle,'%'))"
//            + " ORDER BY l.createdDate DESC")
//    public Page<LogInOutTracking> findUtilisateursByMotCLE(Pageable p, @Param("motCle") String motCle, @Param("systemeAccount") String systemeAccount);
////    @Query("SELECT l FROM LogInOutTracking l WHERE (upper(l.type) LIKE CONCAT('%',:motCle,'%')"
////            + " OR upper((select u.username From Utilisateur u where u.createdBy=l.createdBy)) LIKE CONCAT('%',:motCle,'%')"
////            + " OR upper((select u.firstName From Utilisateur u where u.createdBy=l.createdBy)) LIKE CONCAT('%',:motCle,'%')"
////            + " OR upper((select u.lastName From Utilisateur u where u.createdBy=l.createdBy)) LIKE CONCAT('%',:motCle,'%'))"
////            + " ORDER BY l.createdDate DESC")
////    public Page<LogInOutTracking> findUtilisateursByMotCLE(Pageable p, @Param("motCle") String motCle);
//
//    @Query("SELECT l FROM LogInOutTracking l WHERE l.createdDate BETWEEN :dateMin AND :dateMax AND l.createdBy != :systemeAccount "
//            + " ORDER BY l.createdDate DESC")
//
//    public Page<LogInOutTracking> findUtilisateursByDate(Pageable p, @Param("dateMin") Instant dateMin, @Param("dateMax") Instant dateMax, @Param("systemeAccount") String systemeAccount);
//
//    @Query("SELECT new com.ngs.core.codification.dto.UserLogSimpleObject(l.type,l.createdBy) "
//            + " FROM LogInOutTracking l WHERE (lower(REPLACE((l.type), ' ', ''))) LIKE CONCAT('%',:keyword,'%')")
//    public List<UserLogSimpleObject> findListUtilisateursByMotCLE(Pageable p, @Param("keyword") String keyword);
//
//    @Query("SELECT l From LogInOutTracking l WHERE l.createdBy != :systemeAccount "
//            + " ORDER BY l.createdDate DESC")
//    public Page<LogInOutTracking> findAllUsers(Pageable pageable, @Param("systemeAccount") String systemeAccount);
//
//}
////+ " OR lower(REPLACE((d.typeDocument.intitule), ' ', '')) like %:keyword%"
