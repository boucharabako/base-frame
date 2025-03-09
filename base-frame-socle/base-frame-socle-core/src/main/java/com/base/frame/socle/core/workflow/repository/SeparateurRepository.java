///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.Separateur;
//import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**Repository des séparateur
// * @date 16/04/2018
// * @version 1.0.0
// * @author Pierre
// */
//public interface SeparateurRepository extends JpaRepository<Separateur, String> {
//
//    /**
//     * Cette methode recupere un séparateur à partir de son libelle
//     * @param libelle
//     * @return Separateur
//     */
//    @Query("select s from Separateur s where s.libelle=:libelle")
//    Optional<Separateur> findSeparateurByLibelle(@Param("libelle") String libelle);
//    
//    /**
//     * Cette methode recupere un separateur a partir de sa valeur
//     * @param value
//     * @return Separateur
//     */
//    @Query("select s from Separateur s where s.value=:value")
//    Optional<Separateur> findSeparateurByValue(@Param("value") String value);
//
//    /**
//     * Cette methode recupere un separateur a partir de son libelle et sa valeur
//     * @param libelle
//     * @param value
//     * @return Separateur
//     */
//    @Query("select s from Separateur s where s.libelle=:libelle AND s.value=:value")
//    Optional<Separateur> findSeparateurByLibelleAndValue(@Param("libelle") String libelle, @Param("value") String value);
//    
//    /**
//     * Cette methode recupere un separateur a partir de son libelle ou sa valeur
//     * @param libelle
//     * @param value
//     * @return Separateur
//     */
//    @Query("select s from Separateur s where s.libelle=:libelle OR s.value=:value")
//    Optional<Separateur> findSeparateurByLibelleORValue(@Param("libelle") String libelle, @Param("value") String value);
//}
