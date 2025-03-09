/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Assima
 */
@Target(value = {})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface field {

    public String code();

    public String libelle();
}
