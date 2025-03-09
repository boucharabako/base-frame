///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.StructureDeControle;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// * Structure de controle repository
// *
// * @date 16/04/2018
// * @version 1.0.0
// * @author Pierre
// */
//public interface StructureDeControleRepository extends JpaRepository<StructureDeControle, String> {
//
//    /**
//     * Cette methode recupere une structure de contrôle a partir de son code
//     * structure et son statut
//     *
//     * @param codeStatut structure de controle
//     * @param codeStructure structure de controle
//     * @return Structure de controle
//     */
//    @Query("select sc from StructureDeControle sc where sc.codeStatut=:codeStatut AND sc.codeStructure=:codeStructure")
//    Optional<StructureDeControle> findByStatutAndCodeStructure(@Param("codeStatut") String codeStatut, @Param("codeStructure") String codeStructure);
//
//    /**
//     * Cette methode recupere une structure de contrôle a partir de son code
//     * structure
//     *
//     * @param codeStructure
//     * @return Structure de controle
//     */
//    @Query("select sc from StructureDeControle sc where sc.codeStructure=:codeStructure")
//    Optional<StructureDeControle> findByCodeStructure(@Param("codeStructure") String codeStructure);
//
//    /**
//     * Cette methode recupere une structure de controle a partir de son id
//     *
//     * @param id structure de controle
//     * @return Structure de controle
//     */
//    @Query("select new com.ngs.core.codification.entities.StructureDeControle(sc.id, sc.codeStructure, sc.intitule) from StructureDeControle sc where sc.id=:id")
//    StructureDeControle findSimpleOne(@Param("id") String id);
//    
//    /**
//     * Cette methode recupere une structure de contrôle a partir de son code
//     * structure et son statut
//     *
//     * @param codeStatut structure de controle
//     * @param codeDomaine
//     * @return Structure de controle
//     */
//    @Query("select sc from StructureDeControle sc where sc.codeStatut.ideCode=:codeStatut AND sc.domaine.ideCode=:codeDomaine")
//    List<StructureDeControle> findStructureByStatut(@Param("codeStatut") String codeStatut,@Param("codeDomaine") String codeDomaine);
//    
//    
//    /**
//     * Cette methode recupere une structure de contrôle a partir de son code
//     * structure et son statut
//     *
//     * @param codeStatut structure de controle
//     * @return Structure de controle
//     */
//    @Query("select sc from StructureDeControle sc where sc.codeStatut.ideCode=:codeStatut")
//    List<StructureDeControle> findStructureByStatut(@Param("codeStatut") String codeStatut);
//
//}
