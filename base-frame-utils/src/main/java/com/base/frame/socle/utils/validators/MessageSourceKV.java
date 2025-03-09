/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.validators;

import java.util.Map;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

/**
 *
 * @author Alassani
 */

public interface MessageSourceKV {
    public String getMessage(String code,Map<String,Object> params) throws NoSuchMessageException;
    
       
    public String getMessage(String string, Object[] os, String string1) throws NoSuchMessageException ;
   
    public String getMessage(String string, Object[] os) throws NoSuchMessageException ;

    public String getMessage(MessageSourceResolvable msr) throws NoSuchMessageException ;
    
    

}
