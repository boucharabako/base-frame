///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.ObjetEtiquette;
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
//public interface ObjetEtiquetteRepository extends JpaRepository<ObjetEtiquette, String> {
//
//    /**
//     *
//     * @param idObjet
//     * @param codeConcept
//     * @param idEtiquette
//     * @return
//     */
//    @Query("SELECT e FROM ObjetEtiquette e WHERE e.idObjet=:idObjet AND e.codeConcept.ideCode=:codeConcept AND e.etiquette.id=:idEtiquette")
//    public Optional<ObjetEtiquette> findObjectEtiquetteByIdObjectAndIdeConceptAndEtiquetteId(
//            @Param("idObjet") String idObjet, @Param("codeConcept") String codeConcept, @Param("idEtiquette") String idEtiquette);
//
//    @Query("SELECT e.idObjet FROM ObjetEtiquette e WHERE e.codeConcept.ideCode=:codeConcept AND e.etiquette.id=:idEtiquette")
//    public List<String> findObjectEtiquetteByIdObjectAndIdeConceptAndEtiquetteId(
//            @Param("codeConcept") String codeConcept, @Param("idEtiquette") String idEtiquette);
//
//    @Query("SELECT e FROM ObjetEtiquette e WHERE e.idObjet=:idObjet")
//    public List<ObjetEtiquette> findObjectEtiquetteByIdObject(@Param("idObjet") String idObjet);
//
//    @Query("SELECT e FROM ObjetEtiquette e WHERE e.idObjet=:idObjet AND e.codeConcept.ideCode=:code")
//    public List<ObjetEtiquette> findObjectEtiquetteByIdObjectAndConceptMetier(@Param("idObjet") String idObjet, @Param("code") String code);
//
//    @Query("SELECT e FROM ObjetEtiquette e WHERE e.idObjet=:idObjet AND e.codeConcept.ideCode=:code")
//    public Optional<ObjetEtiquette> findByIdObjectAndConceptMetier(@Param("idObjet") String idObjet, @Param("code") String code);
//
//    @Query("SELECT e.etiquette.id FROM ObjetEtiquette e WHERE e.idObjet=:idObjet AND e.codeConcept.ideCode=:conceptMetier "
//            + " AND e.etiquette.codification.code=:codifEtiquette")
//    public Optional<String> findByIdObjectAndConceptMetierAndGroupeEtiquette(
//            @Param("idObjet") String idObjet,
//            @Param("conceptMetier") String conceptMetier,
//            @Param("codifEtiquette") String codifEtiquette);
//    
//        @Query("SELECT e FROM ObjetEtiquette e WHERE e.idObjet=:idObjet AND e.codeConcept.ideCode=:conceptMetier "
//            + " AND e.etiquette.codification.code=:codifEtiquette")
//    public Optional<ObjetEtiquette> findByIdObjectAndConceptMetierAndCodificationEtiquette(
//            @Param("idObjet") String idObjet,
//            @Param("conceptMetier") String conceptMetier,
//            @Param("codifEtiquette") String codifEtiquette);
//
//    @Query("SELECT e.id FROM ObjetEtiquette e WHERE "
//            + " e.idObjet=:idObjet "
//            + " AND e.codeConcept.ideCode=:conceptMetier "
//            + " AND e.etiquette.id=:etiquette")
//    public List<String> findLigneByEtiquette(
//            @Param("idObjet") String idObjet,
//            @Param("conceptMetier") String conceptMetier,
//            @Param("etiquette") String etiquette);
//    
//     @Query("SELECT e.id FROM ObjetEtiquette e WHERE "
//            + " e.idObjet=:idObjet "
//            + " AND e.codeConcept.ideCode=:conceptMetier "
//            + " AND e.etiquette.codification.code=:codeCodification")
//    public List<String> findLigneByEtiquetteByCodificationEtiquette(
//            @Param("idObjet") String idObjet,
//            @Param("conceptMetier") String conceptMetier,
//            @Param("codeCodification") String codeCodification);
//    
//     @Query("SELECT e.id FROM ObjetEtiquette e WHERE "
//            + " e.idObjet=:idObjet "
//            + " AND e.codeConcept.ideCode=:conceptMetier "
//            + " AND e.etiquette.codification.code=:codeCodification "
//            + " AND e.etiquette.code=:codeEtiquette " )
//    public List<String> findLigneByEtiquetteByCodificationEtiquetteAndEtiquette(
//            @Param("idObjet") String idObjet,
//            @Param("conceptMetier") String conceptMetier,
//            @Param("codeCodification") String codeCodification,
//            @Param("codeEtiquette") String codeEtiquette);
//
//   
//
//}
