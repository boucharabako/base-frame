///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.core.codification.conf.NProperties;
//import com.ngs.core.codification.entities.MessageApplicatif;
//import com.ngs.core.codification.repositories.MessageApplicatifRepository;
//import com.ngs.core.codification.utils.ApiError;
//import com.ngs.core.codification.utils.CodificationConstant;
//import com.ngs.core.codification.utils.ErrorConstant;
//import com.ngs.core.codification.utils.MessageParam;
//import com.ngs.core.codification.utils.TypeError;
//import java.util.HashMap;
//import java.util.Map;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.ui.Model;
//
///**
// * Cette classe permet d'envoyer des messages de façon plus simple avec un model
// * et aussi de faire certains opérations de facon génériques
// *
// * @author Adnaane
// * @version 1.0.0
// * @since 27/02/2018
// *
// */
//@Component
//public class GenericController {
//
//    @Autowired
//    NProperties properties;
//    private ApiError apiValidation;
//    @Autowired
//    private ErrorMessageService errorMessage;
//    @Autowired
//    private MessageApplicatifRepository messageRepository;
//    Map<String, MessageParam> validationErrors = new HashMap<>();
//
//    public static final String MESSAGE_STATUS_INFO = ErrorConstant.STATUS_SUCCESS;
//    public static final String MESSAGE_STATUS_ERROR = ErrorConstant.STATUS_BAD_REQUEST;
//
//    public static final String MESSAGE_TITLE_ERROR = "Erreur";
//    public static final String MESSAGE_TITLE_SUCCESS = "Succès";
//
//    public static final TypeError MESSAGE_DANGER = TypeError.DANGER;
//    public static final TypeError MESSAGE_INFO = TypeError.INFO;
//    public static final TypeError MESSAGE_WARNING = TypeError.WARNING;
//    public static final TypeError MESSAGE_DEBUG = TypeError.DEBUG;
//
//    /**
//     * Cette fonction permet de faire l'ajout d'un message sur un model
//     *
//     * @param model le modele en question
//     * @param status le status du message
//     * @param msgType le type de message (message d'erreur ou d'informations)
//     * @param msgTitle le titre du message
//     * @param msgLabel le label du message
//     * @param clearOldContent s'il faut supprimer les anciens messages contenues
//     * dans les tableaux ou pas
//     */
//    public void sendOneMessage(Model model, String status, TypeError msgType, String msgTitle, String msgLabel, boolean clearOldContent) {
//        if (clearOldContent) {
//            this.validationErrors.clear();
//        }
//        this.validationErrors.put("msg", new MessageParam(msgLabel, new String[]{}));
//        this.apiValidation = this.errorMessage.getApiError(
//                status,
//                msgType,
//                this.messageRepository.findLibelleByCodeAndCodeLangue(msgTitle, properties.getLangue().getId()),
//                this.validationErrors);
//
//        model.addAttribute(CodificationConstant.ERROR_API_MODEL_ATTRIBUTE, apiValidation);
//    }
//
//    /**
//     * Cette fonction permet de faire l'ajout d'un message sur un model
//     *
//     * @param model le modele en question
//     * @param status le status du message
//     * @param msgType le type de message (message d'erreur ou d'informations)
//     * @param msgTitle le titre du message
//     * @param msgLabel le label du message contenues dans les tableaux ou pas
//     * @param params les paramètres liées au messages
//     */
//    public void sendOneMessageWithParams(Model model, String status, TypeError msgType, String msgTitle, String msgLabel, String[] params) {
//        this.validationErrors.clear();
//
//        this.validationErrors.put("msg", new MessageParam("msg", msgLabel, params));
//        this.apiValidation = this.errorMessage.getApiError(
//                status,
//                msgType,
//                this.messageRepository.findLibelleByCodeAndCodeLangue(msgTitle, properties.getLangue().getId()),
//                this.validationErrors);
//
//        model.addAttribute(CodificationConstant.ERROR_API_MODEL_ATTRIBUTE, apiValidation);
//    }
//
//    /**
//     * Cette fonction permet de faire l'ajout d'un message sur un model avec un
//     * id spécifique
//     *
//     * @param model le modele en question
//     * @param msgId l'id du message
//     * @param status le status du message
//     * @param msgType le type de message (message d'erreur ou d'informations)
//     * @param msgTitle le titre du message
//     * @param msgLabel le label du message
//     * @param clearOldContent s'il faut supprimer les anciens messages contenues
//     * dans les tableaux ou pas
//     */
//    public void sendOneMessageWithId(Model model, String msgId, String status, TypeError msgType, String msgTitle, String msgLabel, boolean clearOldContent) {
//        if (clearOldContent) {
//            this.validationErrors.clear();
//        }
//        if (msgId == null || msgId.isEmpty()) {
//            msgId = "msg";
//        }
//        this.validationErrors.put(msgId, new MessageParam(msgLabel, new String[]{}));
//        this.apiValidation = this.errorMessage.getApiError(
//                status,
//                msgType,
//                this.messageRepository.findLibelleByCodeAndCodeLangue(msgTitle, properties.getLangue().getId()),
//                this.validationErrors);
//
//        model.addAttribute(CodificationConstant.ERROR_API_MODEL_ATTRIBUTE, apiValidation);
//    }
//
//    /**
//     * Cette opération permet d'envoyer le message d'opération éffectuée avec
//     * succès
//     *
//     * @param model le model concerné
//     */
//    public void finishOperationWithSuccess(Model model) {
//        this.validationErrors.clear();
//        this.apiValidation = this.errorMessage.getApiError(
//                ErrorConstant.STATUS_SUCCESS,
//                TypeError.INFO,
//                this.messageRepository.findLibelleByCodeAndCodeLangue(ErrorConstant.OPERATION_SUCCESS_LABEL, properties.getLangue().getId()),
//                this.validationErrors);
//
//        model.addAttribute(CodificationConstant.ERROR_API_MODEL_ATTRIBUTE, apiValidation);
//    }
//
//    /**
//     * Cette opération permet d'envoyer le message d'opération terminée avec une
//     * erreur
//     *
//     * @param model le model concerné
//     */
//    public void finishOperationWithError(Model model) {
//        this.validationErrors.clear();
//        this.apiValidation = this.errorMessage.getApiError(
//                ErrorConstant.STATUS_BAD_REQUEST,
//                TypeError.DANGER,
//                this.messageRepository.findLibelleByCodeAndCodeLangue(ErrorConstant.OPERATION_ERROR_LABEL, properties.getLangue().getId()),
//                this.validationErrors);
//        model.addAttribute(CodificationConstant.ERROR_API_MODEL_ATTRIBUTE, apiValidation);
//    }
//
//    public void finishOperationWithErrorAndParams(Model model, String code, String[] params) {
//        MessageApplicatif msg = this.messageRepository.findByCodeAndCodeLangue(code, properties.getCodeLangue().getId());
//        this.validationErrors.clear();
//        this.validationErrors.put("msg", new MessageParam("msg", code, params));
//        this.apiValidation = this.errorMessage.getApiError(
//                ErrorConstant.STATUS_BAD_REQUEST,
//                TypeError.DANGER,
//                this.messageRepository.findLibelleByCodeAndCodeLangue(msg.getId(), properties.getLangue().getId()),
//                this.validationErrors);
//        model.addAttribute(CodificationConstant.ERROR_API_MODEL_ATTRIBUTE, apiValidation);
//    }
//
//    public void finishOperationWithSuccesAndParams(Model model, String code, String[] params) {
//        MessageApplicatif msg = this.messageRepository.findByCodeAndCodeLangue(code, properties.getCodeLangue().getId());
//        this.validationErrors.clear();
//        this.validationErrors.put("msg", new MessageParam("msg", code, params));
//        this.apiValidation = this.errorMessage.getApiError(
//                ErrorConstant.STATUS_BAD_REQUEST,
//                TypeError.SUCCES,
//                this.messageRepository.findLibelleByCodeAndCodeLangue(msg.getId(), properties.getLangue().getId()),
//                this.validationErrors);
//        model.addAttribute(CodificationConstant.ERROR_API_MODEL_ATTRIBUTE, apiValidation);
//    }
//
//}
