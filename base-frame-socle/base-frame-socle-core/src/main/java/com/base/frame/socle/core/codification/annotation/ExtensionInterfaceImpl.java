/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.annotation;

import com.base.frame.socle.core.codification.utils.ComposantsHtml;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 * @author WKOUWONOU
 * @since 19/09/2018
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExtensionInterfaceImpl  {

    /**
     * Le code identifiant
     *
     * @return
     */
    String code();

    /**
     * le libelle
     *
     * @return
     */
    String libelle();
    String comceptMetier();

    boolean required() default false;
    boolean hiden() default false;
    boolean isFilter() default false;
    ComposantsHtml composant() default ComposantsHtml.LIST;

}
