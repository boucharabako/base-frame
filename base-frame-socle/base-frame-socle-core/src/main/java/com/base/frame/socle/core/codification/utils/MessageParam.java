/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.utils;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author W.KOUWONOU
 * @date 14/03/2018
 * Le code et les parametres d'un message applicatif
 */
public class MessageParam {
    
    private String code;
    private String errorsCode;
    private String[] param;

    public MessageParam(String errorsCode, String[] param) {
        this.errorsCode = errorsCode;
        this.param = param;
    }

    public MessageParam(String code, String errorsCode, String[] param) {
        this.code = code;
        this.errorsCode = errorsCode;
        this.param = param;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorsCode() {
        return errorsCode;
    }

    public void setErrorsCode(String errorsCode) {
        this.errorsCode = errorsCode;
    }

    public String[] getParam() {
        return param;
    }

    public void setParam(String[] param) {
        this.param = param;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.code);
        hash = 23 * hash + Objects.hashCode(this.errorsCode);
        hash = 23 * hash + Arrays.deepHashCode(this.param);
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
        final MessageParam other = (MessageParam) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.errorsCode, other.errorsCode)) {
            return false;
        }
        return Arrays.deepEquals(this.param, other.param);
    }

    @Override
    public String toString() {
        return "MessageParam{" + "code=" + code + ", errorsCode=" + errorsCode + ", param=" + Arrays.toString(param) + '}';
    }
    
    
}
