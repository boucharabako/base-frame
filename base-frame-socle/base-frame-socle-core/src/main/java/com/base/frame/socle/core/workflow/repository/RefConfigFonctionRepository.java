///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.RefConfigFonction;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.List;
//import org.springframework.data.jpa.repository.Query;
//
///**
// *
// * @author NGS_004
// */
//public interface RefConfigFonctionRepository extends JpaRepository<RefConfigFonction, String>{
//   
//    @Query("SELECT Rc FROM RefConfigFonction Rc ")
//    public List<RefConfigFonction> findRefConfiguration();
//    
//    @Query("SELECT r FROM RefConfigFonction r WHERE  r.type_fonction IS NOT NULL AND r.type_fonction=:tf")
//    public List<RefConfigFonction> findByTypeFonction(String typeFonction);
//    
//}
