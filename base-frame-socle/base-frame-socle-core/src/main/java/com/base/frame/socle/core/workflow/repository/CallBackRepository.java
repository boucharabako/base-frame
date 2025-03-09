/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;


import com.base.frame.socle.core.workflow.dto.ActionPermiseExecutorDTO;
import com.base.frame.socle.core.workflow.entity.WCallBack;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *Repository des implémentation des callBack
 * @author WKOUWONOU
 * @since 18/09/2018
 * @version 1.0.0
 */
public interface CallBackRepository extends JpaRepository<WCallBack, String>{
   
    @Query("SELECT new com.base.frame.socle.core.workflow.dto.ActionPermiseExecutorDTO(c.code,c.libelle) FROM WCallBack c")
    public List<ActionPermiseExecutorDTO> findAllCalback();
}