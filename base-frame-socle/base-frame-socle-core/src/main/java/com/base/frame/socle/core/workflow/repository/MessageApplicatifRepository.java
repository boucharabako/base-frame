///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.EmbeddedIdMessage;
//import com.ngs.core.codification.entities.MessageApplicatif;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author NGS_004
// */
//public interface MessageApplicatifRepository extends JpaRepository<MessageApplicatif, EmbeddedIdMessage>{
//    
//    @Query("SELECT m"
//            + " FROM MessageApplicatif m WHERE m.idMessage.id=:code AND m.idMessage.codeLangue.id=:id ")
//    public MessageApplicatif findByCodeAndCodeLangue(@Param("code")String code,@Param("id")String codeLangue);
//
//
//    public default String findLibelleByCodeAndCodeLangue(String code,String codeLangue){
//        MessageApplicatif m =this.findByCodeAndCodeLangue(code, codeLangue);
//       
//       return m==null?null:m.getLibelle();
//   }
//
//    public default String findValueByCodeAndCodeLangue(String code,String codeLangue){
//       
//       MessageApplicatif m =this.findByCodeAndCodeLangue(code, codeLangue);
//       
//       return m==null?null:m.getLibelle();
//   }
//}
