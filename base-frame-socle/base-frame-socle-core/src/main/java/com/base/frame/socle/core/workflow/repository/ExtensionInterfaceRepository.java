///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.ExtensionInterface;
//import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author kouwonou
// */
//public interface ExtensionInterfaceRepository extends JpaRepository<ExtensionInterface, String>{
//    
//    @Query("SELECT e FROM ExtensionInterface e WHERE e.conceptMetier.ideCode=:ideCode")
//    public List<ExtensionInterface> findByConceptMetier(@Param("ideCode")String concept);
//}
