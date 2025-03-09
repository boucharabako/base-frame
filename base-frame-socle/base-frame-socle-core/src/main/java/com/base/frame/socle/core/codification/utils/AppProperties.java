/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author Adnaane
 */
@Component
@ConfigurationProperties(prefix = "system", ignoreUnknownFields = true)
public class AppProperties {
     private final DefaultLanguage language=new DefaultLanguage();

    public DefaultLanguage getLanguage() {
        return language;
    }
     
     
    public static class DefaultLanguage{
        private String code="FR";
        private String label="Francais";

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
        
    }
}
