/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.repository;

import com.base.frame.socle.core.entity.ParamCode;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Alassani
 */
public interface ParamCodeRepository extends JpaRepository<ParamCode, String>, JpaSpecificationExecutor<ParamCode>{
            @Query("SELECT p FROM ParamCode p WHERE p.code=:code ")
    Optional<ParamCode> findParamCodeByCode(@Param("code") String code);
}
