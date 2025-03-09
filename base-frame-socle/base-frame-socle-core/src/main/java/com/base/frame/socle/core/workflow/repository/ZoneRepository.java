///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.dto.SimpleZoneObject;
//import com.ngs.core.codification.entities.Zone;
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
//public interface ZoneRepository extends JpaRepository<Zone, String> {
//
//    /**
//     * Cette methode recupere une page zone a partir de sa structure de contrôle
//     *
//     * @param idStructureDeControle
//     * @param pageable
//     * @return Zone
//     */
//    @Query("select z from Zone z where z.structureDeControle.id=:idStructureDeControle")
//    Page<Zone> findPageableZoneByStructureDeControle(@Param("idStructureDeControle") String idStructureDeControle, Pageable pageable);
//
//    /**
//     * Cette methode recupere une zone a partir de sa structure de contrôle
//     *
//     * @param idStructureDeControle
//     * @return Zone
//     */
//    @Query("select z from Zone z where z.structureDeControle.id=:idStructureDeControle")
//    List<Zone> findZoneByStructureDeControle(@Param("idStructureDeControle") String idStructureDeControle);
//
//    /**
//     * Cette methode recupere une zone a partir de sa structure de contrôle
//     *
//     * @param idStructureDeControle
//     * @return Zone
//     */
//    @Query("select new com.ngs.core.codification.entities.Zone(z.id, z.numeroZone,"
//            + " z.intitule, z.typeDeDonneeString, z.tailleZone, z.separateur) FROM Zone z WHERE z.structureDeControle.id=:idStructureDeControle")
//    List<Zone> findSimpleZoneByStructureDeControle(@Param("idStructureDeControle") String idStructureDeControle);
//
//    /**
//     * Cette methode recupere une zone a partir de son numero zone et sa
//     * structure de contrôle
//     *
//     * @param numeroZone
//     * @param idStructureDeControle
//     * @return Zone
//     */
//    @Query("select z from Zone z where z.numeroZone=:numeroZone AND z.structureDeControle.id=:idStructureDeControle")
//    Optional<Zone> findZoneByNumeroZoneAndStructureDeControle(@Param("numeroZone") Integer numeroZone, @Param("idStructureDeControle") String idStructureDeControle);
//
//    /**
//     * Cette methode recupere une zone a partir de sa structure de contrôle
//     *
//     * @param idStructureDeControle
//     * @return Zone
//     */
//    @Query("select new com.ngs.core.codification.entities.Zone(z.id, z.numeroZone,"
//            + " z.intitule, z.typeDeDonneeString, z.tailleZone, z.separateur) FROM Zone z WHERE z.structureDeControle.id=:idStructureDeControle ORDER BY z.numeroZone ASC")
//    List<Zone> findSimpleZoneByStructureDeControleForNomenclature(@Param("idStructureDeControle") String idStructureDeControle);
//
//    /**
//     * Cette methode recupere une zone a partir de sa structure de contrôle
//     *
//     * @param idStructureDeControle
//     * @return Zone
//     */
//    @Query("select new com.ngs.core.codification.dto.SimpleZoneObject(z.id, z.numeroZone,"
//            + " z.intitule, z.typeDeDonneeString, z.tailleZone, z.separateur, z.listDeControle.code) FROM Zone z WHERE z.structureDeControle.id=:idStructureDeControle ORDER BY z.numeroZone ASC")
//    List<SimpleZoneObject> findSimpleZoneObjectByStructureDeControleForNomenclature(@Param("idStructureDeControle") String idStructureDeControle);
//
//    @Query("select SUM(z.tailleZone) FROM Zone z WHERE z.structureDeControle.id=:idStructureDeControle AND z.numeroZone<=(Select z2.numeroZone FROM Zone z2 WHERE "
//            + "z2.id=:idZone)")
//    Integer findLongueurZone(@Param("idStructureDeControle") String idStructureDeControle, @Param("idZone") String idZone);
//    
//    
//        @Query("select z FROM Zone z WHERE z.structureDeControle.id=:idStructureDeControle AND z.numeroZone<=(Select z2.numeroZone FROM Zone z2 WHERE "
//            + "z2.id=:idZone)")
//    List<Zone> findListZoneNiveauVote(@Param("idStructureDeControle") String idStructureDeControle, @Param("idZone") String idZone);
//    
//    
//    /**
//     * Cette methode recupere une zone a partir de sa structure de contrôle
//     *
//     * @param idStructureDeControle
//     * @param idZone
//     * @return Zone
//     */
//    @Query("select z from Zone z where z.numeroZone =(select max(zz.numeroZone) from Zone zz where zz.structureDeControle.id=:idStructureDeControle) "
//            + " AND z.id=:idZone AND z.structureDeControle.id=:idStructureDeControle")
//    Optional<Zone> checkMaxZoneByStructureDeControle(@Param("idStructureDeControle") String idStructureDeControle,@Param("idZone") String idZone);
//}
