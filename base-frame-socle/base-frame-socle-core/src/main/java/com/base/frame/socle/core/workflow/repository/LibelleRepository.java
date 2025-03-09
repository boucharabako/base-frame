///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.dto.IdLabelObject;
//import com.ngs.core.codification.dto.LibelleDTO;
//import com.ngs.core.codification.entities.CodifList;
//import com.ngs.core.codification.entities.Libelle;
//import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author william
// * @version 1.0.0
// * @since 06/03/2018
// *
// */
//public interface LibelleRepository extends JpaRepository<Libelle, String> {
//
//    /**
//     * Modified by hakakpo 20-05-2019
//     *
//     * @param codification
//     * @param codeLangue
//     * @return
//     */
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.codification.id =:id "
//            + "and l.codeLangue.id =:codeLangue ORDER BY l.libelle ASC")
//    public List<Libelle> findByCodificationAndCodeLangue(@Param("id") String codification,
//            @Param("codeLangue") String codeLangue);
//
//    /**
//     * Modified by hakakpo 20-05-2019
//     *
//     * @param codeLangue
//     * @return
//     */
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.codification.code =:code "
//            + "and l.codeLangue.id =:codeLangue ORDER BY l.libelle ASC")
//    public List<Libelle> findByCodificationCodeAndCodeLangue(@Param("code") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.codification.code=:id  AND l.codifList.ideCode=:ideCode"
//            + " and l.codeLangue.id=:codeLangue ")
//    public Libelle findByCodificationAndCodifListAndCodeLangue(@Param("id") String codification, @Param("ideCode") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.ideCode =:ideCode ")
//    public List<Libelle> findByCodifListAndCodeLangue(@Param("ideCode") String ideCode);
//
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.id =:id ")
//    public List<Libelle> findByCodifListId(@Param("id") String id);
//
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.codification.code=:id  AND l.codifList.ideCode=:ideCode"
//            + " and l.codeLangue.ideCode=:codeLangue ")
//    public Libelle findByCodificationAndListAndCodeLangue(@Param("id") String codification, @Param("ideCode") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.ideCode=:ideCode"
//            + " and l.codeLangue.ideCode=:codeLangue ")
//    public Libelle findByCodifListAndLangue(@Param("ideCode") String ideCode, @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.ideCode=:ideCode"
//            + " and l.codeLangue.ideCode=:codeLangue ")
//    public List<Libelle> findByAllCodifListAndLangue(@Param("ideCode") String ideCode, @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.id=:id"
//            + " and l.codeLangue.ideCode=:codeLangue ")
//    public Libelle findByCodifListIdAndLangue(@Param("id") String id, @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.ideCode=:ideCode AND l.codifList.codification.code=:codification"
//            + " and l.codeLangue.id=:codeLangue ")
//    public Libelle findLibelleByCodificationAndCodifListIdAndIdCodeLangue(@Param("codification") String codification, @Param("ideCode") String ideCode, @Param("codeLangue") String codeLangue);
//
//    /**
//     *
//     * SELECT l FROM CodifList ls , Libelle l where ls.id= l.codifList.id and
//     * ls.id = :idCodifList and l.codeLangue.id = :code_langue ORDER BY l.id ASC
//     *
//     * @param idCodifList
//     * @param codeLangue
//     * @return
//     */
//    @Query("SELECT l FROM CodifList ls , Libelle l where ls.id= l.codifList.id and "
//            + " ls.id = :idCodifList and l.codeLangue.id = :codeLangue ORDER BY l.id ASC")
//    public Libelle findByCodifListIdAndCodeLangue(@Param("idCodifList") String idCodifList, @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.id=:idcodifList"
//            + " and l.codeLangue.ideCode=:codeLangue ")
//    public Libelle findByIdAndLangue(@Param("idcodifList") String idcodifList, @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT l FROM Libelle l WHERE l.codifList=:cl"
//            + " and l.codeLangue.ideCode=:codeLangue ")
//    public Libelle findByCodifListAndLangue(@Param("cl") CodifList cl, @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.ideCode=:ideCode AND l.codifList.codification.code=:codification"
//            + " and l.codeLangue.ideCode=:codeLangue ")
//    public Libelle findByCodificationCodifListAndLangue(@Param("codification") String codification, @Param("ideCode") String ideCode, @Param("codeLangue") String codeLangue);
//
//    /**
//     * @modifier hakakpo 20-05-2019 1 * @param codeCodification
//     * @param codeLangue
//     * @return
//     */
//    @Query("SELECT new com.ngs.core.codification.entities.CodifList( c.id , c.codification , c.ideCode, l.libelle) FROM CodifList c , Libelle l "
//            + " WHERE l.codifList.id= c.id "
//            + " AND c.codification.code = :codeCodification"
//            + " and l.codeLangue.ideCode=:codeLangue ORDER BY l.libelle ASC")
//    public List<CodifList> findByCodificationListWithLangue(
//            @Param("codeCodification") String codeCodification,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT new com.ngs.core.codification.entities.CodifList( c.id , c.codification , c.ideCode, l.libelle) FROM CodifList c , Libelle l "
//            + " WHERE l.codifList.id= c.id "
//            + " AND c.codification.code = :codeCodification "
//            + " AND c.codification.statut.ideCode = :statut"
//            + " and l.codeLangue.ideCode=:codeLangue ")
//    public List<CodifList> findByCodificationListWithLangueAndStatut(
//            @Param("codeCodification") String codeCodification,
//            @Param("codeLangue") String codeLangue,
//            @Param("statut") String statut
//    );
//
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.id=:id  AND l.codifList.ideCode=:ideCode"
//            + " and l.codeLangue.ideCode=:codeLangue ")
//    public Libelle findLibelleByCodificationAndListAndCodeLangue(@Param("id") String codification, @Param("ideCode") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT new com.ngs.core.codification.dto.LibelleDTO(l.codifList.id,l.codifList.ideCode, l.libelle) FROM Libelle l WHERE l.codifList.codification.code =:code "
//            + "and l.codeLangue.id =:codeLangue ")
//    public List<LibelleDTO> findByCodificationCodeAndCodeLangueUsingDTO(@Param("code") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    /**
//     * @modifier par hakakpo 20-05-2019
//     * @param ideCode
//     * @param codeLangue
//     * @return
//     */
//    @Query("SELECT new com.ngs.core.codification.dto.LibelleDTO(l.codifList.id,l.codifList.ideCode, l.libelle) FROM Libelle l WHERE l.codifList.codification.code =:code "
//            + "and l.codeLangue.ideCode =:codeLangue ORDER BY l.libelle ASC")
//    public List<LibelleDTO> findByCodificationCodeAndCodeLangueDTO(@Param("code") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT new com.ngs.core.codification.dto.IdLabelObject(l.id, l.libelle) FROM Libelle l WHERE l.codifList.codification.code =:code "
//            + "and l.codeLangue.id =:codeLangue ")
//    public List<IdLabelObject> findByCodificationCodeAndCodeLangueUsingIdLabelObject(@Param("code") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT new com.ngs.core.codification.dto.IdLabelObject(l.id, l.libelle) FROM Libelle l WHERE l.codifList.codification.code =:code "
//            + "and l.codeLangue.id =:codeLangue ORDER BY l.codifList.ideCode ASC")
//    public List<IdLabelObject> findByCodification(
//            @Param("code") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT new com.ngs.core.codification.dto.LibelleDTO(l.id, l.libelle) FROM Libelle l WHERE l.codifList.ideCode =:code "
//            + "and l.codeLangue.id =:codeLangue ")
//    public LibelleDTO findByIdeCodeAndCodeLangueUsingDTO(@Param("code") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT new com.ngs.core.codification.dto.LibelleDTO(l.id, l.libelle) FROM Libelle l WHERE l.codifList.ideCode =:code "
//            + "and l.codeLangue.ideCode =:codeLangue ")
//    public LibelleDTO findByIdeCodeAndCodeLangue(@Param("code") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT new com.ngs.core.codification.dto.LibelleDTO(l.id, l.libelle) FROM Libelle l WHERE l.codifList.ideCode =:code "
//            + "and l.codeLangue.id =:codeLangue ")
//    public LibelleDTO findByIdAndCodeLangue(@Param("code") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT new com.ngs.core.codification.dto.LibelleDTO(l.codifList.ideCode, l.libelle) FROM Libelle l WHERE l.codifList.codification.code =:code "
//            + "and l.codeLangue.id =:codeLangue ")
//    public List<LibelleDTO> findByCodificationCodeAndCodeLangueUsingDTOForSTC(@Param("code") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    /**
//     * Ajouté par pierre
//     *
//     * @param codification
//     * @param codeLangue
//     * @return
//     */
//    @Query("SELECT new com.ngs.core.codification.dto.IdLabelObject(l.codifList.ideCode, l.libelle) FROM Libelle l WHERE l.codifList.codification.id =:id "
//            + "and l.codeLangue.id =:codeLangue ORDER BY l.libelle ASC")
//    public List<IdLabelObject> findLibelleByCodificationAndCodeLangue(@Param("id") String codification, @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT  new com.ngs.core.codification.dto.IdLabelObject(l.id, l.libelle,l.codifList.ideCode) FROM Libelle l WHERE l.codifList.codification.code=:id  AND l.codifList.ideCode=:ideCode"
//            + " and l.codeLangue.id=:codeLangue ")
//    public IdLabelObject findLibelleByCodificationAndCodifListAndCodeLangue(@Param("id") String codification, @Param("ideCode") String ideCode,
//            @Param("codeLangue") String codeLangue);
//}
