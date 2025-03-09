///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.dto.SimpleSousZoneObject;
//import com.ngs.core.codification.entities.SousZone;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// * @date 16/04/2018
// * @version 1.0.0
// * @author Pierre
// */
//public interface SousZoneRepository extends JpaRepository<SousZone, String> {
//
//    /**
//     * Cette methode recupere une sous zone à partir de son numero de sous zone
//     *
//     * @param numeroZone
//     * @return SousZone
//     */
//    @Query("select sz from SousZone sz where sz.numeroSousZone=:numeroSousZone")
//    Optional<SousZone> findSousZoneByNumeroSousZone(@Param("numeroSousZone") Integer numeroZone);
//
//    /**
//     * Cette methode recupere une liste de sous zone à partir de l'id de la zone
//     *
//     * @param idZone
//     * @return SousZone
//     */
//    @Query("select sz from SousZone sz where sz.zone.id=:idZone")
//    List<SousZone> findSousZoneByZone(@Param("idZone") String idZone);
//
//    /**
//     * Cette methode recupere une liste filtre de sous zone à partir de l'id la
//     * zone
//     *
//     * @param idZone
//     * @return SousZone
//     */
//    @Query("select new com.ngs.core.codification.entities.SousZone(sz.id, sz.numeroSousZone, sz.intitule, sz.typeDeDonneeString, sz.tailleSousZone) "
//            + "from SousZone sz where sz.zone.id=:idZone")
//    List<SousZone> findSimpleSousZoneByZone(@Param("idZone") String idZone);
//
//    /**
//     * Cette methode recupere une sous zone à partir de son numero de sous zone
//     * et la zone
//     *
//     * @param idZone
//     * @param numeroSousZone
//     * @return SousZone
//     */
//    @Query("select sz from SousZone sz where sz.zone.id=:idZone AND sz.numeroSousZone=:numeroSousZone")
//    Optional<SousZone> findSousZoneByZoneAndNumeroSousZone(@Param("idZone") String idZone, @Param("numeroSousZone") Integer numeroSousZone);
//    
//    /**
//     * Cette methode recupere une liste filtre de sous zone à partir de l'id la
//     * zone
//     *
//     * @param idZone
//     * @param p
//     * @return SousZone
//     */
//    @Query("select sz "
//            + "from SousZone sz where sz.zone.id=:idZone")
//    Page<SousZone> findSousZoneByZone(@Param("idZone") String idZone,Pageable p);
//    
//    /**
//     * Cette methode recupere une liste filtre de sous zone à partir de l'id la
//     * zone
//     *
//     * @param idZone
//     * @return SousZone
//     */
//    @Query("select new com.ngs.core.codification.entities.SousZone(sz.id, sz.numeroSousZone, sz.intitule, sz.typeDeDonneeString, sz.tailleSousZone) "
//            + "from SousZone sz where sz.zone.id=:idZone ORDER BY sz.numeroSousZone ASC")
//    List<SousZone> findSimpleSousZoneByZoneForNomenclature(@Param("idZone") String idZone);
//    /**
//     * Cette methode recupere une liste filtre de sous zone à partir de l'id la
//     * zone
//     *
//     * @param idZone
//     * @return SousZone
//     */
//    @Query("select new com.ngs.core.codification.dto.SimpleSousZoneObject(sz.id, sz.numeroSousZone, sz.intitule, sz.typeDeDonneeString, sz.tailleSousZone, sz.listDeControle.code) "
//            + "from SousZone sz where sz.zone.id=:idZone ORDER BY sz.numeroSousZone ASC")
//    List<SimpleSousZoneObject> findSimpleSousZoneObjectByZoneForNomenclature(@Param("idZone") String idZone);
//
//}
