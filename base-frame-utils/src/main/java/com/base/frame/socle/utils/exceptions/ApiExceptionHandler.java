/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.exceptions;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception handler that catches all exceptions thrown by the REST layer and
 * convert them to the appropriate {@linkplain ErrorResponse}s with a suitable
 * HTTP status code.
 *
 * @see ErrorCode
 * @see ErrorCodes
 * @see ErrorResponse
 *
 * @author WIlliam KOUWONOU
 */
@ControllerAdvice
class ApiExceptionHandler {

    /**
     * Factory to convert the given {@linkplain Exception} to an instance of
     * {@linkplain ErrorCode}
     */
    private final ErrorCodes errorCodes;

    /**
     * Responsible for finding the appropriate error message(s) based on the
     * given {@linkplain ErrorCode} and {@linkplain Locale}
     */
    private final MessageSource apiErrorMessageSource;


    /**
     * Construct a valid instance of the exception handler
     *
     * @throws NullPointerException If either of required parameters were
     * {@code null}
     */
    ApiExceptionHandler(ErrorCodes errorCodes, MessageSource apiErrorMessageSource) {
        Objects.requireNonNull(errorCodes);
        Objects.requireNonNull(apiErrorMessageSource);
        //Objects.require(messageInterface);

        this.errorCodes = errorCodes;
        this.apiErrorMessageSource = apiErrorMessageSource;
       // this.messageInterface = messageInterface;
    }

    /**
     * Catches all non-validation exceptions and tries to convert them to
     * appropriate HTTP Error responses
     *
     * <p>
     * First using the {@linkplain #errorCodes} will find the corresponding
     * {@linkplain ErrorCode} for the given {@code exception}. Then based on the
     * resolved {@linkplain Locale}, a suitable instance of
     * {@linkplain ErrorResponse} with appropriate and localized message will
     * return to the client. {@linkplain ErrorCode} itself determines the HTTP
     * status of the response.
     *
     * @param exception The exception to convert
     * @param locale The locale that usually resolved by {@code Accept-Language}
     * header. This locale will determine the language of the returned error
     * message.
     * @return An appropriate HTTP Error Response with suitable status code and
     * error messages
     */
 

    @ExceptionHandler(ObjectValidationException.class)
    ResponseEntity<ErrorResponse> handleObjectValidationExceptions(ObjectValidationException exception,
            Locale locale) {
        System.out.println("*************** "+locale);

        ErrorResponse errorResponse = ErrorResponse.of(exception.httpStatus(),toErrorPhrase(exception.getCode(), locale),
                exception.getApiErrors());

        return ResponseEntity.status(exception.httpStatus()).body(errorResponse);
    }
    @ExceptionHandler(SimpleValidationException.class)
    ResponseEntity<ErrorResponse> handleSimpleValidationExceptions(SimpleValidationException exception,
            Locale locale) {

        ErrorResponse errorResponse = ErrorResponse.of(exception.httpStatus(),toErrorPhrase(exception.getCode(), locale),
                toApiErrors(exception.getErrorsCodes(), locale));

        return ResponseEntity.status(exception.httpStatus()).body(errorResponse);
    }

    /**
     * Catches all validation exceptions and render appropriate error responses
     * based on each validation exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception,
            Locale locale) {
        Stream<ObjectError> errors = exception.getBindingResult().getAllErrors().stream();
        List<ApiError> apiErrors = errors
                .map(ObjectError::getDefaultMessage)
                .map(this::validationErrorCode)
                .map(code -> toApiError(code, locale))
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(ErrorResponse.ofErrors(HttpStatus.BAD_REQUEST, apiErrors));
    }

    private ErrorCode validationErrorCode(final String errorCode) {
        return new ErrorCode() {
            @Override
            public String code() {
                return errorCode;
            }

            @Override
            public HttpStatus httpStatus() {
                return HttpStatus.BAD_REQUEST;
            }
        };
    }

    /**
     * Convert the passed {@code errorCode} to an instance of
     * {@linkplain ErrorResponse} using the given {@code locale}
     */
    private ApiError toApiError(ErrorCode errorCode, Locale locale) {
        String message;
        try {
            message = apiErrorMessageSource.getMessage(errorCode.code(),
                    new Object[]{},
                    locale);
        } catch (NoSuchMessageException e) {
           
               
                message = errorCode.code();
                
        }

        return new ApiError(errorCode.code(), message);
    }
    /**
     * Convert the passed {@code errorCode} to an instance of
     * {@linkplain ErrorResponse} using the given {@code locale}
     */
    private String toErrorPhrase(String errorCode, Locale locale) {
        String message;
        try {
            message = apiErrorMessageSource.getMessage(errorCode,
                    new Object[]{},
                    locale);
        } catch (NoSuchMessageException e) {
            
                message = errorCode;
                
               
        }

        return message;
    }

    /**
     * Convert the passed {@code errorCode} to an instance of
     * {@linkplain ErrorResponse} using the given {@code locale}
     */
    private List<ApiError> toApiErrors(HashSet<String> errorCode, Locale locale) {
        if (errorCode == null || errorCode.isEmpty()) {
            return new ArrayList<>();
        }
        String message;
        List<ApiError> list = new ArrayList<>();
        for (String er : errorCode) {
            try {

                message = apiErrorMessageSource.getMessage(er,
                        new Object[]{},
                        locale);
            } catch (NoSuchMessageException e) {
               
                message = errorCode.toString();
                
            }
            list.add(new ApiError(er, message));
        }
        return list;
    }
}
