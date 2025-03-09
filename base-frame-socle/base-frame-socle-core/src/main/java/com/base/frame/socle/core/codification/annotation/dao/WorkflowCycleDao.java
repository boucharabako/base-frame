/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.annotation.dao;

import com.base.frame.socle.core.workflow.entity.WorkFlowCycle;
import org.springframework.stereotype.Component;

/**
 *
 * @author kouwonou
 */
@Component
public class WorkflowCycleDao  extends GenericDAO<WorkFlowCycle, String>{
    
    public WorkflowCycleDao() {
        super(WorkFlowCycle.class);
    }
    
   
    
}
