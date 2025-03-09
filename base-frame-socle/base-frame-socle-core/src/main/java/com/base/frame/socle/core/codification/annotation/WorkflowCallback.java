/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Identifie les services qui implementent une fonction callback workflow Ces
 * services sont scannés au deploiement de l'apllication et stockés dans une
 * table de la base de donné
 *
 * @author WKOUWONOU
 * @since 19/09/2018
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WorkflowCallback {

    /**
     * Le code identifiant les service
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
}
