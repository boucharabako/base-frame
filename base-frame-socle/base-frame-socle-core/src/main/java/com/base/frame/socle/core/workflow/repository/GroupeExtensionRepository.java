///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.dto.GroupeExtensionObject;
//import com.ngs.core.codification.dto.IdLabelObject;
//import com.ngs.core.codification.entities.GroupeExtension;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author W.KOUWONOU
// * @date 16/03/2018
// * @version 1.0.0
// */
//public interface GroupeExtensionRepository extends JpaRepository<GroupeExtension, String>, JpaSpecificationExecutor<GroupeExtension> {
//
//    @Query("SELECT COUNT(g) FROM GroupeExtension g WHERE g.titre=:titre")
//    public int sizeByTitre(@Param("titre") String titre);
//
//    @Query("SELECT g FROM GroupeExtension g JOIN FETCH g.extensions WHERE g.id = :id")
//    public GroupeExtension findGroupFetchExtension(@Param("id") String idGroup);
//
//    /**
//     * Liste des GroupeExtension par concept metier
//     *
//     * @param statutCode
//     * @return
//     */
//    @Query("SELECT g FROM GroupeExtension g WHERE  g.statut.ideCode=:ideCode ORDER BY  g.titre ASC")
//    public List<GroupeExtension> listGroupeExtentions(@Param("ideCode") String statutCode);
//
//
//    @Query("SELECT g FROM GroupeExtension g WHERE  g.statut.ideCode=:ideCode AND g.codeConcept.ideCode=:codeConcept ORDER By g.titre ASC")
//    public List<GroupeExtension> listGroupeExtensionByConceptMetier(@Param("ideCode") String statutCode, @Param("codeConcept") String codeConcept);
//    
//        @Query("SELECT g FROM GroupeExtension g WHERE  g.statut.id=:idStatut AND g.codeConcept.ideCode=:codeConcept ORDER By g.titre ASC")
//    public List<GroupeExtension> listGroupeExtensionByConceptMetierTwo(@Param("idStatut") String idStatut, @Param("codeConcept")String codeConcept);
//
//    @Query("SELECT g FROM GroupeExtension g WHERE g.id=:id ORDER BY g.createdDate DESC")
//    public Optional<GroupeExtension> findGroupeExtensionById(@Param("id") String id);
//
//    @Query("SELECT g FROM GroupeExtension g WHERE g.codeConcept.ideCode=:codeConcept")
//    public Optional<GroupeExtension> findGroupeExtensionByConceptMetier(@Param("codeConcept") String codeConcept);
//
//    @Query("SELECT new com.ngs.core.codification.dto.GroupeExtensionObject(g.id, g.titre, g.description, g.statut.ideCode, g.statut.ideCode, g.codeConcept.id )"
//            + " FROM GroupeExtension g WHERE (upper(g.titre) LIKE CONCAT('%',:motcle,'%') "
//            + " OR upper(g.description) LIKE CONCAT('%',:motcle,'%') )"
//            + "ORDER BY g.createdDate DESC")
//    public Page<GroupeExtensionObject> findGroupeExtensionByMotCle(Pageable p, @Param("motcle") String motcle);
//
//    @Query("select new com.ngs.core.codification.dto.GroupeExtensionObject(g.id, g.titre, g.description, g.statut.ideCode, g.statut.ideCode, g.codeConcept.id)"
//            + " from GroupeExtension g ORDER BY g.createdDate DESC")
//    Page<GroupeExtensionObject> listeGroupeExtensionPagination(Pageable p);
//
//    @Query("SELECT DISTINCT new com.ngs.core.codification.dto.IdLabelObject(ge.id,ge.titre) FROM GroupeExtension ge")
//    public List<IdLabelObject> findAllGroupeExtension();
//    
//    @Query("SELECT DISTINCT new com.ngs.core.codification.dto.IdLabelObject(ge.id,ge.titre) FROM GroupeExtension ge  WHERE  ge.statut.ideCode=:ideCode AND ge.codeConcept.ideCode=:codeConcept  ORDER By ge.titre ASC")
//    public List<IdLabelObject> findAllGroupeExtensionByConceptMetier(@Param("ideCode") String statutCode, @Param("codeConcept") String codeConcept);
//    
//
//    @Query("SELECT g FROM GroupeExtension g WHERE g.codeConcept.ideCode=:codeConcept")
//    public List<GroupeExtension> allGroupeExtensionByConceptMetier(@Param("codeConcept") String codeConcept);
//    
//    @Query("SELECT g FROM GroupeExtension g ORDER BY g.createdDate DESC")
//    Page<GroupeExtension> findListOrdered(Pageable p);
//}
