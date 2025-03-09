///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import java.util.List;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author kouwonou
// */
//public interface GroupFonctionPermissionRepository extends JpaRepository<GroupFonctionPermission, String>{
//    
//    @Query("SELECT g.permission FROM GroupFonctionPermission g WHERE g.fonction.id=:id")
//    public List<String> findPermissionByFonction(@Param("id")String idFoncion);
//}
