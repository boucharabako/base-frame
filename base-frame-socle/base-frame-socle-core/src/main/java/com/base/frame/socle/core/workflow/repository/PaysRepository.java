///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.Pays;
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
//public interface PaysRepository extends JpaRepository<Pays, String> {
//
//    @Query("SELECT p FROM Pays p WHERE upper(p.nom) =:nom ")
//    public Optional<Pays> findByNom(@Param("nom") String nom);
//}
