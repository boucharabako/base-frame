/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 *
 * @author william KOUWONOU
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ErrorResponse {
    /**
     * The 4xx or 5xx status code for error cases, e.g. 404
     */
    private final int statusCode;

    /**
     * The HTTP reason phrase corresponding the {@linkplain #statusCode}, e.g.
     * Not Found
     *
     * @see <a href="https://www.w3.org/Protocols/rfc2616/rfc2616-sec6.html">
     * Status Code and Reason Phrase</a>
     */
    private final String reasonPhrase;

    /**
     * List of application-level error code and message combinations. Using
     * these errors we provide more information about the actual error
     */
    private final List<ApiError> errors;

    private ErrorResponse(int statusCode, String reason, List<ApiError> errors) {
        // Some precondition checks

        this.statusCode = statusCode;
        this.reasonPhrase = reason;
        this.errors = errors;
    }

    /**
     * Static factory method to create a {@linkplain ErrorResponse} with
     * multiple {@linkplain ApiError}s. The canonical use case of this factory
     * method is when we're handling validation exceptions, since we may have
     * multiple validation errors.
     */
    static ErrorResponse ofErrors(HttpStatus status, List<ApiError> errors) {
        return new ErrorResponse(status.value(), status.getReasonPhrase(), errors);
    }

    /**
     * Static factory method to create a {@linkplain ErrorResponse} with a
     * single {@linkplain ApiError}. The canonical use case for this method is
     * when we trying to create {@linkplain ErrorResponse}es for regular
     * non-validation exceptions.
     */
    static ErrorResponse of(HttpStatus status, ApiError error) {
        return ofErrors(status, Collections.singletonList(error));
    }
    /**
     * Static factory method to create a {@linkplain ErrorResponse} with a
     * single {@linkplain ApiError}. The canonical use case for this method is
     * when we trying to create {@linkplain ErrorResponse}es for regular
     * non-validation exceptions.
     */
    static ErrorResponse of(HttpStatus status, String errorPhrase,List<ApiError> errors) {
        return new ErrorResponse(status.value(),errorPhrase,errors);
    }



    @Override
    public String toString() {
        return "ErrorResponse{" + "statusCode=" + statusCode + ", reasonPhrase=" + reasonPhrase + ", errors=" + errors + '}';
    }
    
}
