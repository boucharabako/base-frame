/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.service;

import com.base.frame.socle.core.codification.utils.ApiError;
import com.base.frame.socle.core.codification.utils.ErrorMessage;
import com.base.frame.socle.core.codification.utils.TypeError;
import com.base.frame.socle.core.utils.MessageParam;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rkoufionou
 * @since 08/08/2018
 * @version 1.0
 */
@Service
public class ErrorMessageService {

    public static final String ERROR_PARSER_MAKER = "$";
    public static final String ERROR_LOG_PATTERN = "YYYYMMdd HH:mm:ss";
    public static final String ERROR_LOG_STRING_SEPARATOR = "#";

    private static final Logger LOG = LoggerFactory.getLogger(ErrorMessage.class);

//    @Autowired
//    private MessageApplicatifRepository messageApplicatifRepository;
//    @Autowired
//    private NProperties properties;

    public String formatErrorMessage(String message, String... params) {
        if (message == null || message.isEmpty() || !message.contains(ErrorMessageService.ERROR_PARSER_MAKER)) {
            return message;
        }
        if (params == null || params.length == 0) {
            return message;
        }
        StringBuilder b = new StringBuilder(message);

        for (String param : params) {
            b = b.insert(b.indexOf(ErrorMessageService.ERROR_PARSER_MAKER), param);
            b = b.deleteCharAt(b.indexOf(ErrorMessageService.ERROR_PARSER_MAKER));
        }
        return b.toString();
    }

    public String formatErrorLogMessage(String messageCode, String libelle, TypeError typeError) {
        DateFormat dateFormat = new SimpleDateFormat(ERROR_LOG_PATTERN);
        String logLine = dateFormat.format(new Date()) + ERROR_LOG_STRING_SEPARATOR;
        logLine += messageCode + ERROR_LOG_STRING_SEPARATOR;
        logLine += typeError.name() + ERROR_LOG_STRING_SEPARATOR;
        logLine += libelle + ERROR_LOG_STRING_SEPARATOR;

        return logLine;
    }

    public void logError(TypeError typeError, String logMessage) {
        if (typeError.equals(TypeError.INFO)) {
            LOG.info(logMessage);

        }
        if (typeError.equals(TypeError.WARNING)) {
            LOG.warn(logMessage);
        }
        if (typeError.equals(TypeError.DANGER)) {
            LOG.error(logMessage);
        }
        if (typeError.equals(TypeError.DEBUG)) {
            LOG.debug(logMessage);
        }
    }

//    public String formatErrorMessageFromCoded(String codeErrorMessage, String... params) {
//        MessageApplicatif messageApplicatif = this.messageApplicatifRepository.findByCodeAndCodeLangue(codeErrorMessage, properties.getCodeLangue().getId());
//        if (messageApplicatif != null) {
//            return this.formatErrorMessage(messageApplicatif.getLibelle(), params);
//        }
//        return null;
//    }

    public ApiError getApiError(String statut, TypeError typeError, String message, Map<String, MessageParam> errors) {
        ApiError apiError = new ApiError(statut, typeError, message);
        List<ErrorMessage> errorMessages = new ArrayList<>();
        String displayMessage = "";
        if (errors != null) {
            for (Map.Entry<String, MessageParam> entry : errors.entrySet()) {
                String key = entry.getValue().getErrorsCode();
                String[] params = entry.getValue().getParam();

                if (params != null) {
                    //displayMessage = this.formatErrorMessageFromCode(key, params);
                    if (displayMessage == null) {
                        displayMessage = key;
                    }
                    errorMessages.add(new ErrorMessage(key, displayMessage));
                    this.logError(typeError, this.formatErrorLogMessage(key, displayMessage, typeError));
                }
            }
            apiError.setErrorMessages(errorMessages);
        }

//        LOG MESSAGE
        return apiError;
    }

}
