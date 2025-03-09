///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.dto.IdLabelObject;
//import com.ngs.core.codification.entities.Etiquette;
//import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author NGS_004
// */
//public interface EtiquetteRepository extends JpaRepository<Etiquette, String> {
//
//    /**
//     * Cette méthode permet de retrouver une liste d'étiqette à partir de son
//     * code de codification
//     *
//     * @param codificationCode le code de codification
//     * @return
//     */
//    @Query("SELECT e FROM Etiquette e WHERE e.codification.code = :code ORDER BY e.dateDebutValidite ASC")
//    public List<Etiquette> findByCodification(@Param("code") String codificationCode);
//
//    /**
//     * Cette méthode permet de retrouver une étiquette à partir du code de sa
//     * codification et de son code personnel
//     *
//     * @param codeCodification le code de codification
//     * @param codeEtiquette le code de l'étiquette
//     * @return
//     */
//    @Query("SELECT e FROM Etiquette e WHERE e.codification.code = :codeCodification AND e.code = :codeEtiquette ORDER BY e.dateDebutValidite ASC")
//    public Etiquette findByCodificationAndCode(
//            @Param("codeCodification") String codeCodification,
//            @Param("codeEtiquette") String codeEtiquette
//    );
//
//    /**
//     * Cette méthode permet d'avoir une liste d'étiquette active pour un domaine
//     * fourni en paramètre
//     *
//     * @param idDomaine
//     * @param codeStatut
//     * @return
//     */
//    @Query("SELECT e FROM Etiquette e WHERE e.codification.domaine.ideCode =:idDomaine AND e.codification.statut.ideCode =:codeStatut ORDER BY e.dateDebutValidite ASC")
//    public List<Etiquette> findByCodificationAndDomaine(
//            @Param("idDomaine") String idDomaine,
//            @Param("codeStatut") String codeStatut
//    );
//
//    @Query("SELECT new com.ngs.core.codification.dto.IdLabelObject(e.code,e.codification.code) FROM Etiquette e WHERE e.codification.statut.ideCode =:codeStatut ORDER BY e.dateDebutValidite ASC")
//    public List<IdLabelObject> findEtiquetteForSchema(@Param("codeStatut") String codeStatut
//    );
//
//    @Query("SELECT e FROM Etiquette e WHERE "
//            + " e.codification.domaine.ideCode=:idDomaine "
//            + " AND e.codification.code=:codification "
//            + " AND e.codification.statut.ideCode=:codeStatut ORDER BY e.dateDebutValidite ASC")
//    public List<Etiquette> findEtiquetteByCodificationAndDomaine(
//            @Param("idDomaine") String idDomaine,
//            @Param("codification") String codification,
//            @Param("codeStatut") String codeStatut
//    );
//
//}
