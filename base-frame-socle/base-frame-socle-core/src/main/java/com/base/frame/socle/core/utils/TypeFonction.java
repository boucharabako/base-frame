/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.utils;

/**
 *
 * @author Alassani
 */
public enum TypeFonction {
    MENU, FONCTION;
    public String toString(){
        switch (this){
            case  MENU : 
                return "MENU";
            case FONCTION :
                 return "FONCTION";
            default:
                return "";
        }
    }
}
    