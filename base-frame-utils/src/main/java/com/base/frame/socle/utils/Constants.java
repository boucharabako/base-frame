/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils;

import java.util.Map;

/**
 * Classe des contantes
 *
 * @author William
 * @since 19-08-2018
 */
public class Constants {

    /**
     * Taille maximal des libelles
     */
    public static final int COLUMN_LIBELLE_SIZE = 100;
    /**
     * Taille des descriptions simples
     */
    public static final int COLUMN_SIMPLE_DESCRIPTION_SIZE = 250;
    /**
     * Taille des descriptions longues
     */
    public static final int COLUMN_LARGE_DESCRIPTION_SIZE = 800;
    /**
     * Taille des codes auto générés
     */
    public static final int COLUMN_GENERATES_CODE_SIZE = 40;
    /**
     * Tail des codes saisis par l'utilsateur
     */
    public static final int COLUMN_MANUAL_CODE_SIZE = 20;

    public static final String VAL_GLOBAL_ERROR_CODE = "val.global.error.msg";

    public static final String VAL_SIZE_ERROR_CODE = "val.size.error.msg";
    public static final String VAL_PATTERN_ERROR_CODE = "val.pattern.error.msg";
    public static final String VAL_REQUIRE_ERROR_CODE = "val.required.error.msg";
    public static final String VAL_UNIQUE_ERROR_CODE = "val.uique.error.msg";
    public static final String VAL_UNIQUE_PLUS_ERROR_CODE = "val.uique.error.msg2";
    public static final String VAL_REGEX_ERROR_CODE = "val.regex.error.msg";
    public static final String VAL_REQUIRE_ATLEAST_ERROR_CODE = "val.requireatleastone.error.msg";
    public static final String OP_NOT_AUTHORIZE_ERROR_CODE = "op.not.autorise.error";
    public static final String OP_SUCCESS_MSG_CODE = "op.success.msg";
    public static final String OP_FAILD_MSG_CODE = "op.faild.msg";

    /**
     * Expression reguliere pour les libelle
     */
    public static final String ALPHA_NUMERIQUE_REGEX = "(\\w|-|_)+";
    /**
     * Expression reguliere pour les code
     */
    public static final String CODE_REGEX = "^[/_'.@A-Za-z0-9-]*$";

    public static final String PROPRIETE_HEADERS_RESPONDED = "Responded";
    public static final String PROPRIETE_HEADERS_TIMEZONESCONTROLLER = "TimeZonesController";

}
