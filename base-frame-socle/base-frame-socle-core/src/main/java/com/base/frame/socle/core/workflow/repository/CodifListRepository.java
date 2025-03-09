///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.dto.CodifListDTO;
//import com.ngs.core.codification.entities.CodifList;
//import com.ngs.core.codification.entities.Libelle;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author Adnaane
// * @version 1.0.0
// * @since 27/02/2018
// */
//public interface CodifListRepository extends JpaRepository<CodifList, String>, JpaSpecificationExecutor<CodifList> {
//
//    @Modifying
//    @Query("DELETE FROM Libelle l WHERE l.codifList.id =:id ")
//    public void deleteLibelle(@Param("id") String id);
//
//    /**
//     * Recupere les libelles en fonction de la codiflist
//     *
//     * @param id
//     * @return
//     */
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.id =:id ")
//    public List<Libelle> findByCodifListId(@Param("id") String id);
//
//    /**
//     * Recupere les libelles en fonction de la codiflist
//     *
//     * @param code
//     * @return
//     */
//    @Query("SELECT l FROM Libelle l WHERE l.codifList.codification.code =:code ")
//    public List<Libelle> findLibelleByCodification(@Param("code") String code);
//
//    /**
//     * Cette méthode permet de retrouver un codiflist via son id
//     *
//     * @param id l'identifiant
//     * @return le codiflist
//     */
//    public CodifList findOneById(@Param("id") String id);
//
//    /**
//     * Cette méhode permet de retrouver une liste de codification via son
//     * ideCode
//     *
//     * @param ideCode l'idecode concerné
//     * @return
//     */
//    @Query("SELECT c FROM CodifList c WHERE c.ideCode=:ideCode")
//    public List<CodifList> findByIdeCode(@Param("ideCode") String ideCode);
//
//    /**
//     * La taille des elements en fonction de la CodifList
//     *
//     * @param ideCode le code de la codiflist
//     * @return
//     */
//    @Query("SELECT COUNT(c) FROM CodifList c WHERE c.ideCode=:ideCode")
//    public int sizeByIdeCode(@Param("ideCode") String ideCode);
//
//    /**
//     * La taille des elements en fonction du code de la codification et de la
//     * CodifList
//     *
//     * @param code le code de la codification
//     * @param ideCode le code de la codiflist
//     * @return
//     */
//    @Query("SELECT COUNT(c) FROM CodifList c WHERE c.ideCode=:ideCode AND c.codification.code=:code")
//    public int sizeByCodificationAndIdeCode(@Param("code") String code, @Param("ideCode") String ideCode);
//
//    @Query("SELECT c FROM CodifList c WHERE c.ideCode=:ideCode")
//    public CodifList findOneByIdeCode(@Param("ideCode") String ideCode);
//
//    @Query("SELECT c FROM CodifList c WHERE c.codification.id=:id")
//    public List<CodifList> findByCodification(@Param("id") String codification);
//
//    @Query("SELECT c.ideCode FROM CodifList c WHERE c.codification.id=:id ORDER BY c.ideCode ASC")
//    public List<String> findByCodificationOrder(@Param("id") String codification);
//
//    /**
//     * Retrouve les codifList en focntion du code de la codification
//     *
//     * @param code
//     * @return
//     */
//    @Query("SELECT c FROM CodifList c WHERE c.codification.code = :code")
//    public List<CodifList> findByCodificationCode(@Param("code") String code);
//
//    /**
//     *
//     * @param codification la codification au quelle la liste est rattachée
//     * @param code le code identifiant la l'lement de la liste
//     * @return un element de la liste en fonction du code et de la codification
//     */
//    @Query("SELECT c FROM CodifList c WHERE c.codification.code=:id AND c.ideCode=:code")
//    public CodifList findByCodificationAndCode(@Param("id") String codification, @Param("code") String code);
//
//    /**
//     *
//     * @param codification la codification au quelle la liste est rattachée
//     * @param code le code identifiant la l'lement de la liste
//     * @param codeLangue le code de la langue concerné
//     * @return un element de la liste en fonction du code et de la codification
//     *
//     * by Ad
//     */
//    @Query("SELECT new com.ngs.core.codification.entities.CodifList( c.id , c.codification , c.ideCode, l.libelle) FROM CodifList c , Libelle l "
//            + " WHERE l.codifList.id= c.id  AND  c.codification.id=:id AND c.ideCode=:code  AND l.codeLangue.ideCode= :codeLangue ")
//    public CodifList findByCodificationAndCodeWithLangue(@Param("id") String codification, @Param("code") String code, @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT new com.ngs.core.codification.entities.CodifList( c.id , c.codification , c.ideCode, l.libelle) FROM CodifList c , Libelle l "
//            + " WHERE l.codifList.id= c.id "
//            + " AND c.codification.code = :codeCodification"
//            + " and l.codeLangue.ideCode=:codeLangue ")
//    public List<CodifList> findCodifListWithLangue(
//            @Param("codeCodification") String codeCodification,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT new com.ngs.core.codification.dto.CodifListDTO( c.id , c.ideCode, l.libelle) "
//            + " FROM CodifList c , Libelle l "
//            + " WHERE l.codifList.id= c.id "
//            + " AND c.codification.code = :codeCodification AND l.codifList.ideCode=:ideCode"
//            + " and l.codeLangue.ideCode=:codeLangue ")
//    public CodifListDTO findCodifListDTOWithLangue(
//            @Param("codeCodification") String codeCodification, @Param("ideCode") String ideCode,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT new com.ngs.core.codification.dto.CodifListDTO( c.id , c.ideCode, l.libelle) "
//            + " FROM CodifList c , Libelle l "
//            + " WHERE l.codifList.id= c.id "
//            + " AND c.codification.code = :codeCodification "
//            + " and l.codeLangue.ideCode=:codeLangue ")
//    public List<CodifListDTO> listCodifListDTOWithLangue(@Param("codeCodification") String codeCodification,
//            @Param("codeLangue") String codeLangue);
//
//    /**
//     * Cette fonction permet de retrouver un codiflist dans une langue donnée
//     *
//     * @param idCodifList l'id du codifList
//     * @param codeLangue le code de la langue
//     * @return
//     */
//    @Query("SELECT new com.ngs.core.codification.entities.CodifList( c.id , c.codification , c.ideCode, l.libelle) FROM CodifList c , Libelle l "
//            + " WHERE l.codifList.id= c.id "
//            + " AND c.id = :idCodifList"
//            + " AND l.codeLangue.ideCode= :codeLangue "
//    )
//    public CodifList findOneCodifListWithLangue(
//            @Param("idCodifList") String idCodifList,
//            @Param("codeLangue") String codeLangue
//    );
//
//    /**
//     * Cette fonction permet de retrouver un codiflist dans une langue donnée
//     *
//     * @param idCodifList l'id du codifList
//     * @param idCodification l'id de la codification
//     * @param codeLangue le code de la langue
//     * @return
//     */
//    @Query("SELECT new com.ngs.core.codification.entities.CodifList( c.id , c.codification , c.ideCode, l.libelle) FROM CodifList c , Libelle l "
//            + " WHERE l.codifList.id= c.id "
//            + " AND c.ideCode = :idCodifList "
//            + " AND c.codification.code = :idCodification "
//            + " AND l.codeLangue.ideCode= :codeLangue ")
//    public CodifList findOneCodifListWithCodificationAndLangue(
//            @Param("idCodification") String idCodification,
//            @Param("idCodifList") String idCodifList,
//            @Param("codeLangue") String codeLangue);
//
//    /**
//     * Cette fonction permet de retrouver le libelle d'un codif list
//     *
//     * @param idCodifList l'id du codifList
//     * @param idCodification l'id de la codification
//     * @param codeLangue le code de la langue
//     * @return
//     */
//    @Query("SELECT l.libelle FROM CodifList c , Libelle l "
//            + " WHERE l.codifList.id= c.id "
//            + " AND c.ideCode = :idCodifList "
//            + " AND c.codification.code = :idCodification "
//            + " AND l.codeLangue.ideCode= :codeLangue ")
//    public String findLabelForOneCodifListWithCodificationAndLangue(
//            @Param("idCodification") String idCodification,
//            @Param("idCodifList") String idCodifList,
//            @Param("codeLangue") String codeLangue);
//
//    @Query("SELECT c FROM CodifList c WHERE c.codification.code =:code AND c.ideCode=:ideCode")
//    public List<CodifList> findCurrentStep(
//            @Param("code") String codificationCode,
//            @Param("ideCode") String currentIde,
//            Pageable p
//    );
//
//    @Query("SELECT c FROM CodifList c WHERE c.codification.code =:code AND c.ideCode=:ideCode")
//    public CodifList findStep(
//            @Param("code") String codificationCode,
//            @Param("ideCode") String currentIde);
//
//    @Query("SELECT c FROM CodifList c WHERE c.codification.code =:code AND c.ideCode>:ideCode")
//    public List<CodifList> findNextStep(
//            @Param("code") String codificationCode,
//            @Param("ideCode") String currentIde,
//            Pageable p
//    );
//
//    @Query("SELECT c FROM CodifList c WHERE c.codification.code=:id")
//    public List<CodifList> findListCodifListByCodification(@Param("id") String codification);
//
//    @Query("SELECT c.ideCode FROM CodifList c WHERE c.codification.code =:code ORDER BY c.ideCode ASC")
//    public List<String> findOrderedByCodificationCode(@Param("code") String code);
//
//    public default CodifList getFirstElement(String codif) {
//        List<CodifList> lc = this.findListCodifListByCodification(codif);
//        CodifList result = null;
//        for (CodifList c : lc) {
//            if (result == null || Integer.valueOf(result.getIdeCode()) > Integer.valueOf(c.getIdeCode())) {
//                result = c;
//            }
//        }
//        return result;
//    }
//
//    public default CodifList getNextLevelCode(String codif, String currentIdeCode) {
//        List<CodifList> lc = this.findListCodifListByCodification(codif);
//        CodifList result = null;
//        for (CodifList c : lc) {
//            if (Integer.valueOf(c.getIdeCode()) > Integer.valueOf(currentIdeCode)) {
//                if (result == null || Integer.valueOf(result.getIdeCode()) > Integer.valueOf(c.getIdeCode())) {
//                    result = c;
//                }
//            }
//        }
//        return result;
//    }
//
//    /**
//     * Assima Validation import piece
//     */
//    @Query("SELECT c FROM CodifList c WHERE c.codification.id=:idCodification AND c.ideCode=:ideCode")
//    public Optional<CodifList> findCodifByCode(@Param("idCodification") String idCodification, @Param("ideCode") String ideCode);
//
//    @Query("SELECT c FROM CodifList c WHERE c.id=:id")
//    public Optional<CodifList> findCodifById(@Param("id") String id);
//
//    @Query("SELECT c FROM CodifList c WHERE c.ideCode=:ideCode AND c.codification.id=:idCodification")
//    public Optional<CodifList> findCodifByIdAndCodification(@Param("ideCode") String ideCode, @Param("idCodification") String idCodification);
//
//    @Query("SELECT new com.ngs.core.codification.dto.CodifListDTO( c.id , c.ideCode, l.libelle) "
//            + " FROM CodifList c , Libelle l "
//            + " WHERE l.codifList.id= c.id "
//            + " AND c.codification.code = :codeCodification "
//            + " and l.codeLangue.ideCode=:codeLangue "
//            + " AND c.codification.statut.ideCode = :code ORDER BY l.libelle ASC ")
//    public List<CodifListDTO> listCodifListDTOWithLangueActif(@Param("codeCodification") String codeCodification,
//            @Param("codeLangue") String codeLangue,
//            @Param("code") String code);
//}
