/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;

/**
 * 01-04-2019
 *
 * @author Assima
 * @param <T>
 */
public interface LicenceParamsValidation<T extends Comparable> {

    public T getInitParam();

    public T getRealParam();

    public String getErrorMessage();

    public default boolean validate() {
        if (this.getInitParam().getClass().equals(Boolean.class)) {
            return getInitParam().compareTo(getRealParam()) == 0;
        } else if (this.getInitParam().getClass().equals(Integer.class)) {
            return getInitParam().compareTo(getRealParam()) >= 0;
        }

        return false;

    }
}
