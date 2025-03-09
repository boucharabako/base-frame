///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//
//import com.base.frame.socle.core.workflow.entity.TypeWorkFlow;
//import com.base.frame.socle.core.workflow.repository.TypeWorkflowRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Transactional
//@Component
//public class TypeWorkflwService {
//    
//    @Autowired
//    private TypeWorkflowRepository typeWorkflowRepository;
//    @Autowired
//    private IWorkflowCycleService  workflowCycleService;
//    
//    public void seveTypeWorkflow(TypeWorkFlow w,String idW){
//        
//      w=  typeWorkflowRepository.save(w);
//        
//        workflowCycleService.initWorkflowId("BUD", w.getCode(), idW);
//        
//        
//    }
//}
