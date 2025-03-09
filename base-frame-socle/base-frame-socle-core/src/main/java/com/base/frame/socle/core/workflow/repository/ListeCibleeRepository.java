///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.ListeCiblee;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author rkoufionou
// * @version 1.0.0
// * @since 08/08/2018
// */
//public interface ListeCibleeRepository extends JpaRepository<ListeCiblee, String> {
//
//     @Query("SELECT l FROM ListeCiblee l WHERE l.code =:code ")
//    public ListeCiblee findByCode(@Param("code") String code);
//   
//}
