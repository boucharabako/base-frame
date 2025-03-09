///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.config.web.errors.MessageInterface;
//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.core.codification.entities.MessageApplicatif;
//import com.ngs.core.codification.repositories.MessageApplicatifRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * abs-bud-nomenclature
// *
// * @author Pierre NGS creée le 30 nov. 2018 08:18:47
// */
//@Service
//public class MessageInterfaceImpl implements MessageInterface {
//
//    @Autowired
//    private MessageApplicatifRepository messageApplicatifRepository;
//    @Autowired
//    private NProperties properties;
//
//    String params[];
//    @Autowired
//    private ErrorMessageService errorMessageService;
//
//    @Override
//    public String getMessage(String code) {
//        if (code.contains("#")) {
//            if (code.split("#").length == 2) {
//                params = code.split("#")[1].split("\\$");
//                code = code.split("#")[0];
//            }
//            if (params != null && params.length > 0) {
//                for (int i = 0; i < params.length; i++) {
//                    MessageApplicatif m = this.messageApplicatifRepository.findByCodeAndCodeLangue(params[i], properties.getCodeLangue().getId());
//
//                    if (m != null) {
//                        params[i] = m.getLibelle();
//                    }
//                }
//            }
//        }
//
//        return errorMessageService.formatErrorMessageFromCode(code, params);
//    }
//
//}
