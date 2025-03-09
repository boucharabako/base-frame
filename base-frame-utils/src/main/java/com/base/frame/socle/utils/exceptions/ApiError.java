/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

    /**
     * An immutable data structure representing each application-level error.
     * JSON representation of this class would be something like the following:
     * <pre>
     *     {"code": "res-12", "message": "some error"}
     * </pre>
     *
     * @author William kouwonou
     */
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
   public   class ApiError {

        /**
         * The error code
         */
        private final String code;

        /**
         * Possibly localized error message
         */
        private final String message;
        
        public ApiError(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String toString() {
            return "ApiError{" + "code=" + code + ", message=" + message + '}';
        }
        
    }
