/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.repository;

import com.base.frame.socle.core.workflow.entity.TypeWorkFlow;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kouwonou
 */
public interface TypeWorkflowRepository extends JpaRepository<TypeWorkFlow, String>{
    
}
