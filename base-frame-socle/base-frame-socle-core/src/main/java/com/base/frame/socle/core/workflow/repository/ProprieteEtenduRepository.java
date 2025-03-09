///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.ProprieteEtendu;
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
// * @date 14/03/2018
// * @version 1.0.0
// */
//public interface ProprieteEtenduRepository extends JpaRepository<ProprieteEtendu, String>, JpaSpecificationExecutor<ProprieteEtendu> {
//
//    @Query("SELECT new com.ngs.core.codification.entities.ProprieteEtendu"
//            + "(p.id,p.ide,p.libelle,p.caption,p.statut)  FROM ProprieteEtendu p ORDER BY p.createdDate ")
//    public Page<ProprieteEtendu> findByDate(Pageable p);
//
//    @Query("SELECT new com.ngs.core.codification.entities.ProprieteEtendu"
//            + "(p.id,p.ide,p.libelle,p.caption,p.statut)  FROM ProprieteEtendu p ORDER BY p.createdDate")
//    public List<ProprieteEtendu> findByDate();
//
//    @Query("SELECT new com.ngs.core.codification.entities.ProprieteEtendu"
//            + "(p.id,p.ide,p.libelle,p.caption,p.statut)  FROM ProprieteEtendu p WHERE p.ide LIKE :moc"
//            + " OR p.libelle LIKE :moc OR p.caption LIKE :moc ORDER BY p.createdDate")
//    public List<ProprieteEtendu> findByParam(@Param("moc") String moc);
//
//    @Query("SELECT new com.ngs.core.codification.entities.ProprieteEtendu"
//            + "(p.id,p.ide,p.libelle,p.caption,p.statut)  FROM ProprieteEtendu p WHERE p.ide LIKE :moc"
//            + " OR p.libelle LIKE :moc OR p.caption LIKE :moc ORDER BY p.createdDate")
//    public Page<ProprieteEtendu> findByParam(@Param("moc") String moc, Pageable p);
//
//    @Query("SELECT p FROM ProprieteEtendu p WHERE p.ide=:l AND p.codeLangue.ideCode=:lk")
//    public ProprieteEtendu findByLibelleAndCodeLAngue(@Param("l") String ide, @Param("lk") String codeLangue);
//
//    @Query("SELECT p FROM ProprieteEtendu p WHERE p.codeLangue.id = :keyLangue")
//    public List<ProprieteEtendu> findByLangue(@Param("keyLangue") String keyLangue);
//
//    @Query("SELECT p FROM ProprieteEtendu p WHERE p.codeLangue.id = :keyLangue "
//            + " AND p.statut.ideCode = :statut ")
//    public List<ProprieteEtendu> findByLangueAndStatut(@Param("keyLangue") String keyLangue,
//            @Param("statut") String codeStatut);
//
//    @Query("SELECT p FROM ProprieteEtendu p WHERE p.ide=:ide ORDER BY p.createdDate DESC")
//    public Optional<ProprieteEtendu> findProprieteEtenduBycode(@Param("ide") String ide);
//
//    @Query("SELECT p FROM ProprieteEtendu p WHERE p.statut.ideCode = :statut")
//    public List<ProprieteEtendu> findByStatutActif(@Param("statut") String codeStatut);
//
//    @Query("SELECT p FROM ProprieteEtendu p ORDER BY p.createdDate DESC")
//    Page<ProprieteEtendu> findOrderedProp(Pageable p);
//
//    @Query("SELECT p FROM ProprieteEtendu p WHERE "
//            + "(upper(p.ide) LIKE CONCAT('%',:motCle,'%')"
//            + " OR upper(p.libelle) LIKE CONCAT('%',:motCle,'%') "
//            + " OR upper(p.caption) LIKE CONCAT('%',:motCle,'%')) "
//            + " ORDER BY p.createdDate DESC")
//    Page<ProprieteEtendu> findOrderedPropByMotCle(Pageable p, @Param("motCle") String motcle);
//
//}
