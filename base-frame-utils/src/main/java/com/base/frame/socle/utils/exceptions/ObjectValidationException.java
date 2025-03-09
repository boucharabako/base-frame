/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.exceptions;


import com.base.frame.socle.utils.exceptions.ApiError;
import com.base.frame.socle.utils.exceptions.ErrorCode;
import static com.base.frame.socle.utils.exceptions.ErrorCode.ERROR_CODE_FOR_UNKNOWN_ERROR;
import java.util.List;
import java.util.Objects;
import org.springframework.http.HttpStatus;

/**
 *Les exceptions par rapport aux controle de validation de hibernat validation
 * @author WIlliam kouwonou
 * @since 19-08-2019
 * 
 */
public class ObjectValidationException extends RuntimeException implements ErrorCode {

    
     /**
     * Le code du message principal dans le fichier des langues
     */
    private final String code;
    private  final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    /**
     * Les messages de validations
     */
    private final List<ApiError> apiErrors;

    public ObjectValidationException(String code, List<ApiError>  errorsCodes) {
        this.code = code;
        this.apiErrors = errorsCodes;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

   
    public static String getErrorCodeForUnknownError() {
        return ERROR_CODE_FOR_UNKNOWN_ERROR;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.code);
        hash = 11 * hash + Objects.hashCode(this.httpStatus);
        hash = 11 * hash + Objects.hashCode(this.apiErrors);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ObjectValidationException other = (ObjectValidationException) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (this.httpStatus != other.httpStatus) {
            return false;
        }
        if (!Objects.equals(this.apiErrors, other.apiErrors)) {
            return false;
        }
        return true;
    }

    public List<ApiError> getApiErrors() {
        return apiErrors;
    }

    public static String getERROR_CODE_FOR_UNKNOWN_ERROR() {
        return ERROR_CODE_FOR_UNKNOWN_ERROR;
    }


    @Override
    public String code() {
        return code;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

}
