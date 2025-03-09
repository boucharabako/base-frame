///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.core.codification.entities.LogInFonctionTracking;
//import com.ngs.core.codification.entities.LogInOutTracking;
//import com.ngs.core.codification.repositories.LogInFonctionTrackingRepository;
//import com.ngs.core.codification.repositories.LogInOutTrackingRepository;
//import com.ngs.core.sec.service.ILogInTrackingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author kouwonou
// */
//@Service
//public class LogInTrackingService  implements ILogInTrackingService{
//    @Autowired private LogInOutTrackingRepository logInOutTrackingRepository;
//    @Autowired private LogInFonctionTrackingRepository logInFonctionTrackingRepository;
//
//    @Override
//    public void saveLogInOut(String type) {
//        
//        logInOutTrackingRepository.save(new LogInOutTracking(type) );
//    }
//
//    @Override
//    public void saveLogInFonction(String type) {
//        logInFonctionTrackingRepository.save(new LogInFonctionTracking(type));
//    }
//    
//}
