///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.ValeurExtensionInterface;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author kouwonou
// */
//public interface ValeurExtensionInterfaceRepository extends JpaRepository<ValeurExtensionInterface, String>{
//    
//    /**
//     *
//     * Recherches les differetes propriété enregistré pour un objet
//     *
//     * @author W.KOUWONOU
//     * @param concetMetier
//     * @since 27/09/2018
//     * @param id Identifiant de l'utilisateur.. ca pru etre son id, ou username
//     * ou email
//     */
//    @Query("DELETE  FROM ValeurExtensionInterface v WHERE"
//            + " v.idObjet=:idObjet  AND v.extension.conceptMetier.ideCode=:cp")
//
//    public void deletAllByUsername(@Param("idObjet") String id,@Param("cp") String concetMetier);
//    /**
//     *
//     * Recherches les differetes propriété enregistré pour cet objet
//     *
//     * @author W.KOUWONOU
//     * @param idObjet identifiant de l'objet
//     * @param concetMetier
//     * @since 27/09/2018
//     * @return liste des ValeurExtensionInterface
//     */
//    @Query("SELECT v  FROM ValeurExtensionInterface v WHERE"
//            + " v.idObjet=:idObjet  AND v.extension.conceptMetier.ideCode=:cp")
//
//    public List<ValeurExtensionInterface> findAllByIdObjet(@Param("idObjet") String idObjet,@Param("cp") String concetMetier);
//
//    /**
//     *
//     * Recherches les differetes propriété enregistré pour un utilisateur
//     *
//     * @author W.KOUWONOU
//     * @param ext
//     * @since 27/09/2018
//     * @param id Identifiant de l'utilisateur.. ca pru etre son id, ou username
//     * ou email
//     * @return liste des ValeurExtensionInterface
//     */
//    @Query("SELECT v  FROM ValeurExtensionInterface v WHERE"
//            + " v.idObjet=:idObjet  AND v.extension.code=:extc")
//    public Optional<ValeurExtensionInterface> findAllByIdObjetAndExtension(@Param("idObjet") String id, @Param("extc") String ext);
//
//    @Query("SELECT v  FROM ValeurExtensionInterface v WHERE v.valeur=:valeur AND v.extension.conceptMetier.ideCode=:cm")
//    public List<ValeurExtensionInterface> findAllByValeur(@Param("valeur") String valeur, @Param("cm") String conceptMetier);
//
// 
//}
