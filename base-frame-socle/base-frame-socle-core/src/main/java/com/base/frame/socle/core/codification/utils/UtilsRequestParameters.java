/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.utils;

/**
 *
 * @author WKOUWONOU
 */
public class UtilsRequestParameters {

    private Object valeurParametre;
    private String comparaison;
    private String finRequette;

    public UtilsRequestParameters(Object valeurParametre, String comparaison) {
        this.valeurParametre = valeurParametre;
        this.comparaison = comparaison;
    }

    public UtilsRequestParameters(Object valeurParametre, String comparaison, String finRequette) {
        this.valeurParametre = valeurParametre;
        this.comparaison = comparaison;
        this.finRequette = finRequette;
    }

    public Object getValeurParametre() {
        return valeurParametre;
    }

    public void setValeurParametre(Object valeurParametre) {
        this.valeurParametre = valeurParametre;
    }

    public String getComparaison() {
        return comparaison;
    }

    public void setComparaison(String comparaison) {
        this.comparaison = comparaison;
    }

    public String getFinRequette() {
        if (finRequette == null) {
            return "";
        }
        return finRequette;
    }

    public void setFinRequette(String finRequette) {
        this.finRequette = finRequette;
    }

    @Override
    public String toString() {
        return "UtilsRequestParameters{" + "valeurParametre=" + valeurParametre + ", comparaison=" + comparaison + ", finRequette=" + finRequette + '}';
    }

}
