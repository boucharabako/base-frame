/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.repository;

import com.base.frame.socle.core.entity.ParamList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Alassani
 */
public interface ParamListRepository extends JpaRepository<ParamList, String>, JpaSpecificationExecutor<ParamList> {

    @Query("SELECT p FROM ParamList p WHERE p.code=:code ")
    Optional<ParamList> findParamListByCode(@Param("code") String code);

    @Query("SELECT p FROM ParamList p WHERE p.paramCode.code=:paramCode ORDER BY p.libelle ASC")
    List<ParamList> findParamListByCodeParamCode(@Param("paramCode") String paramCode);
}
