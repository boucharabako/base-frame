/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.validators;

import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alassani
 */
@Service
public class MessageSourceKVImpl implements MessageSourceKV {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String code, Map<String, Object> params) {
        try {
            String msg=messageSource.getMessage(code, null, Locale.FRANCE);;
        
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            msg=  msg.replaceAll("(\\{"+key+"\\})", value.toString());
        }
        return msg;
        } catch (Exception e) {
        }
        
    
        return  code;
    }

    @Override
    public String getMessage(String string, Object[] os, String string1) {
        return messageSource.getMessage(string, os, string1,  Locale.FRANCE);
    }

    @Override
    public String getMessage(String string, Object[] os) throws NoSuchMessageException {
        return messageSource.getMessage(string, os,  Locale.FRANCE);
    }

    @Override
    public String getMessage(MessageSourceResolvable msr) throws NoSuchMessageException {
        return messageSource.getMessage(msr,  Locale.FRANCE);
    }

}
