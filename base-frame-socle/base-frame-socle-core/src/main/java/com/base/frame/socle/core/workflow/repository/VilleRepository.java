///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.Ville;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author W.KOUWONOU
// * @Date 03/03/2018
// * @version 1.0.0
// */
//public interface VilleRepository extends JpaRepository<Ville, String> {
//
//    @Query("SELECT new com.ngs.core.codification.entities.Ville(v.code,v.nom) "
//            + "FROM Ville v WHERE v.pays.alpha2=:code OR v.pays.alpha3=:code ORDER BY v.nom ASC")
//    public List<Ville> findByPays(@Param("code") String codePays);
//
//    @Query("SELECT new com.ngs.core.codification.entities.Ville(v.code,v.nom) "
//            + "FROM Ville v WHERE v.code=:code AND v.pays.alpha2=:codePays OR v.pays.alpha3=:codePays ORDER BY v.nom ASC")
//    public Ville findByCodeAndPays(@Param("code") String code, @Param("codePays") String codePays);
//
//    @Query("SELECT new com.ngs.core.codification.entities.Ville(v.code,v.nom) "
//            + "FROM Ville v WHERE trim(lower(v.nom)) =:nom AND v.pays.alpha2=:codePays OR v.pays.alpha3=:codePays ORDER BY v.nom ASC")
//    public Ville findByNomAndPays(@Param("nom") String nom, @Param("codePays") String codePays);
//
//    @Query("SELECT v FROM Ville v WHERE v.code=:code ")
//    public Optional<Ville> findByCode(@Param("code") String code);
//}
