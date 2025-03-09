///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.config.web.errors.InternalServerException;
//import com.ngs.core.codification.conf.NProperties;
//import java.util.Set;
//import javax.annotation.PostConstruct;
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @author kouwonou
// */
//@Component
//public class NextframeModeleValidationService {
//
//    private static Validator validator;
//
//    @Autowired
//    NProperties properties;
//    @Autowired
//    protected ErrorMessageService errorMessage;
//
//    @PostConstruct
//    private void init() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//    }
//
//    public <T> boolean validate(T t) {
//
//        Set<ConstraintViolation<T>> constraintViolations
//                = validator.validate(t);
//
//        constraintViolations.stream().forEach(x -> {
//            /**
//             * ADD BY GBENOU 07/08/2019
//             */
//            System.out.println("executable parameters -->> " + x.getInvalidValue());
//            System.out.println("message sur le controle du nombre de caractères -->> " + x.getMessage());
//            throw new InternalServerException(x.getMessage(), null);
//
//        });
//
//        return true;
//    }
//}
