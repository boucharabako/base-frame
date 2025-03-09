///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.repository;
//
//import com.ngs.core.codification.entities.Parameter;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
///**
// *
// * @author Adnaane
// * @version 1.0.0
// * @since 27/02/2018
// */
//public interface ParameterRepository extends JpaRepository<Parameter, String> {
//
//    /**
//     * Cette méthode permet de retrouver un paramètre dans une intervalle de
//     * date donnée grace au code de sa codification et à sa date
//     *
//     * @param codif le code de codif
//     * @param dateOperation la date recherchée
//     * @return
//     */
//    @Query("SELECT p FROM Parameter p WHERE p.codification.code=:code  "
//            + "AND p.codification.dateActivation IS NOT NULL "
//            + "AND p.dateDebutValidite IS NOT NULL AND p.dateDebutValidite>=:date "
//            + "AND (p.dateFinValidite IS NULL OR p.dateFinValidite<=:date)")
//    public Optional<Parameter> findActivetedParamByCodification(@Param("code") String codif, @Param("date") Date dateOperation);
//
//    /**
//     * Cette méthode permet de retrouver un paramètre grace au code de
//     * codification
//     *
//     * @param codif
//     * @return
//     */
//    @Query("SELECT p FROM Parameter p WHERE p.codification.code=:code")
//    public Parameter findSystemParamByCode(@Param("code") String codif);
//
//    /**
//     * Cette méthode permet de retrouver une liste de paramètre grace au code de
//     * codification
//     *
//     * @param codificationCode le code de codification
//     * @return
//     */
//    @Query("SELECT p FROM Parameter p WHERE p.codification.code = :code ORDER BY p.dateDebutValidite ASC")
//    public List<Parameter> findByCodification(@Param("code") String codificationCode);
//
//    @Query("SELECT p FROM Parameter p WHERE p.codification.code = :code "
//            + " AND (p.dateFinValidite IS NULL "
//            + " OR (p.dateDebutValidite<= :date AND p.dateFinValidite>= :date)"
//            + ")")
//    public Optional<Parameter> findValidParamByCodification(@Param("code") String codificationCode,
//            @Param("date") Date date);
//
//    @Query("SELECT p FROM Parameter p WHERE p.codification.code=:code  "
//            + "AND p.dateDebutValidite IS NOT NULL AND p.dateDebutValidite<=:date "
//            + "AND (p.dateFinValidite IS NULL OR p.dateFinValidite>=:date)")
//    public Optional<Parameter> findActivetedByCodification(@Param("code") String codif, @Param("date") Date dateOperation);
//
//    @Query("SELECT p FROM Parameter p WHERE p.codification.code=:code  "
//            + "AND p.dateDebutValidite IS NOT NULL AND p.dateDebutValidite<=:date "
//            + "AND (p.dateFinValidite IS NULL OR p.dateFinValidite>=:date)")
//    public List<Parameter> findParamActivatedByCodification(@Param("code") String codif, @Param("date") Date dateOperation);
//
//}
