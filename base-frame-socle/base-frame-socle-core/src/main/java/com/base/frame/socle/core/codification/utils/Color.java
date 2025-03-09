/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.codification.utils;

/**
 *
 * @author W.KOUWONOU
 * @Since 02/05/2018
 * @Description @Brightness Calcul la brillance d'une couleur en fonction de sa
 * valeur hexadécimal
 * @version 1.0.0
 */
public  class Color {

    public Color() {
        // do nothing
    }
    
/**
 * 
 * @param c Couleur RGB
 * @return Brillance en fonction de la couleur RGB
 */
    
    private static int brightness(java.awt.Color c) {
        return (int) Math.sqrt(
                c.getRed() * c.getRed() * .241
                + c.getGreen() * c.getGreen() * .691
                + c.getBlue() * c.getBlue() * .068);
    }
/**
 * 
 * @param colorStr Code Hexadecimal
 * @return Couleur RGB en fonction du code hexadecimal
 */
    public static java.awt.Color hexToRgb(String colorStr) {
        return new java.awt.Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    /**
     * 
     * @param color Code Hexadécimal
     * @return Code Hexadécimal en fonction de la Brillance
     */
    public static String textColorByBackgroundColor(String color) {

        return (color==null|| color.isEmpty())?CodificationConstant.COLOR_BLACK:(brightness(hexToRgb(color)) < 130 ? CodificationConstant.COLOR_WHITE
                : CodificationConstant.COLOR_BLACK);
    }
}
