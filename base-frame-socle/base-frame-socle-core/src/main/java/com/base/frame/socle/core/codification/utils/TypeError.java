/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.utils;

/**
 *
 * @author rkoufionou
 */
public enum TypeError {
    INFO, SUCCES ,WARNING, DANGER, DEBUG;

    @Override
    public String toString() {
        switch (this) {
            case INFO:
                return "info";
            case WARNING:
                return "warning";
            case DANGER:
                return "danger";
            case DEBUG:
                return "debug";
            case SUCCES:
                return "success";

        }
        return "";
    }

}
