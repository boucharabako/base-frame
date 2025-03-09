///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.dto.ClasseurDTO;
//import com.ngs.core.codification.dto.ClasseurRubriqueDTO;
//import com.ngs.core.codification.dto.IdLabelObject;
//import com.ngs.core.codification.entities.Classeur;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// * Repository des implémentation des classeurs
// *
// * @author WKOUWONOU
// * @since 27/02/2019
// * @version 1.0.0
// */
//public interface ClasseurRepository extends JpaRepository<Classeur, String> {
//
//    @Query("SELECT new com.ngs.core.codification.dto.ClasseurDTO(c.id,c.code,c.libelle,c.parent)  "
//            + " FROM Classeur c WHERE c.codification.code=:id")
//    public List<ClasseurDTO> findByCodification(@Param("id") String idCodif);
//    
//    @Query("SELECT new com.ngs.core.codification.dto.ClasseurDTO(c.id,c.code,c.libelle,c.parent)  "
//            + " FROM Classeur c WHERE c.codification.code=:id AND "
//            + " c.parent.code =:parentCode")
//    public List<ClasseurDTO> findChildren(@Param("id") String idCodif, @Param("parentCode") String parentCode );
//
//    @Query("SELECT c FROM Classeur c WHERE c.codification.code=:id AND c.id<>:idClasseur")
//    public List<Classeur> findOtherByCodif(@Param("id") String idCodif, @Param("idClasseur") String idClasseur);
//
//    @Query("SELECT new com.ngs.core.codification.dto.ClasseurRubriqueDTO(c.id, c.code, c.libelle)"
//            + " FROM Classeur c WHERE c.codification.code=:id")
//    public List<ClasseurRubriqueDTO> findByCodificationClass(@Param("id") String idCodif);
//
//    @Query("SELECT c  FROM Classeur c WHERE c.codification.code=:id")
//    public List<Classeur> findByCodif(@Param("id") String idCodif);
//
//    @Query("SELECT c  FROM Classeur c WHERE c.code=:id")
//    public Classeur findByCodifSimple(@Param("id") String idCodif);
//
//    @Query("SELECT c FROM Classeur c WHERE c.codification.code=:code AND c.code=:ideCode")
//    public Optional<Classeur> findByCodificationAndCode(@Param("code") String codification, @Param("ideCode") String code);
//
//    @Query("SELECT c.id FROM Classeur c WHERE c.parent.id=:id")
//    public List<String> findFilleByParent(@Param("id") String id);
//
//    @Query("SELECT c.id FROM Classeur c WHERE c.parent IS NULL AND c.codification.code=:code")
//    public List<String> findRoot(@Param("code") String codification);
//
//    @Query("SELECT new com.ngs.core.codification.dto.ClasseurDTO(c.id,c.code,c.libelle) FROM Classeur c WHERE c.parent IS NULL AND c.codification.code=:code ORDER BY c.libelle DESC")
//    public List<ClasseurDTO> findRootDTO(@Param("code") String codification);
//
//        @Query("SELECT new com.ngs.core.codification.dto.ClasseurDTO(c.id,c.code,c.libelle, c.parent) FROM Classeur c WHERE c.codification.code=:code AND c.id NOT IN (Select  p.parent.id  FROM Classeur p WHERE p.parent IS NOT NULL )ORDER BY c.libelle DESC")
//    public List<ClasseurDTO> findCLasseurLastLevel(@Param("code") String codification);
//    
//    @Query("SELECT new com.ngs.core.codification.dto.ClasseurDTO(c.id,c.code,c.libelle) FROM Classeur c WHERE c.parent.id=:idParent")
//    public List<ClasseurDTO> findFilleDTOByParent(@Param("idParent") String idParent);
//
//    @Query("SELECT c FROM Classeur c WHERE c.parent IS NULL AND c.codification.code=:code ORDER BY c.code, c.libelle ASC")
//    public List<Classeur> findAllClassificationRoot(@Param("code") String codification);
//
//    @Query("SELECT c FROM Classeur c WHERE c.codification.code=:code")
//    public List<Classeur> findAllClassificationCustomRoot(@Param("code") String codification);
//
//    @Query("SELECT c FROM Classeur c WHERE c.parent.id=:id ORDER BY c.code, c.libelle ASC")
//    public List<Classeur> findClassificationFilleByParent(@Param("id") String id);
//
//    @Query("SELECT c.id  FROM Classeur c WHERE c.codification.code=:id")
//    public List<String> findClasseurIdByCodif(@Param("id") String idCodif);
//
//    @Query("SELECT c FROM Classeur c WHERE c.codification.code=:code AND c.code=:ideCode")
//    public Classeur findClasseurByCodeAndVal(@Param("code") String codification, @Param("ideCode") String code);
//    @Query("SELECT c.codification.code FROM Classeur c WHERE c.id=:code")
//    public Optional<String> findCodeCodificationByIdClasseur(@Param("code") String IdClass);
//
//    @Query("SELECT new com.ngs.core.codification.dto.IdLabelObject(c.code,c.libelle,c.codification.code) FROM Classeur c WHERE c.codification.code=:code "
//            + "AND c.codification.statut.ideCode=:codeStatut")
//    public List<IdLabelObject> findClasseurByClassification(
//            @Param("code") String codification,
//            @Param("codeStatut") String codeStatut);
//    @Query("SELECT new com.ngs.core.codification.dto.IdLabelObject(c.id,c.libelle,c.codification.code) FROM Classeur c WHERE c.codification.code=:code "
//            + "AND c.codification.statut.ideCode=:codeStatut")
//    public List<IdLabelObject> findClasseurIdByClassification(
//            @Param("code") String codification,
//            @Param("codeStatut") String codeStatut);
//
//}
