/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;

import com.base.frame.socle.core.workflow.dto.ActionPermiseExecutorDTO;
import com.base.frame.socle.core.workflow.entity.ActionPermiseExecutor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Sewa
 */
public interface ActionPermiseExecutorRepository extends JpaRepository<ActionPermiseExecutor, String> {

    @Query("SELECT ae FROM ActionPermiseExecutor ae WHERE ae.actionPermise=:actionPermise AND ae.callBack=:callBack")
    Optional<ActionPermiseExecutor> findByActionPermiseAndExecutor(@Param("actionPermise") String actionPermise, @Param("callBack") String callBack);

    @Query("SELECT ae.callBack FROM ActionPermiseExecutor ae WHERE ae.actionPermise=:actionPermise ORDER BY ae.numeroOrdre ASC")
    List<String> findExecutorByActionPermise(@Param("actionPermise") String actionPermise);

    @Query("SELECT ae FROM ActionPermiseExecutor ae WHERE ae.actionPermise=:actionPermise ORDER BY ae.numeroOrdre ASC")
    List<ActionPermiseExecutor> findExecutorActionPermiseByActionPermise(@Param("actionPermise") String actionPermise);

    @Query("SELECT new com.base.frame.socle.core.workflow.dto.ActionPermiseExecutorDTO(c.code,c.libelle) FROM WCallBack c, ActionPermiseExecutor ae WHERE "
            + " ae.actionPermise=:actionPermise AND ae.callBack=c.code ORDER BY ae.numeroOrdre ASC")
    List<ActionPermiseExecutorDTO> findListExecutorByActionPermise(@Param("actionPermise") String actionPermise);

    @Query("SELECT c.implementation FROM WCallBack c, ActionPermiseExecutor ae WHERE ae.actionPermise=:actionPermise AND ae.callBack=c.code "
            + " ORDER BY ae.numeroOrdre ASC")
    List<String> findExecutorImplementationByActionPermise(@Param("actionPermise") String actionPermise);

    //public void delete(List<ActionPermiseExecutor> listActionPermiseExecutor);
}
