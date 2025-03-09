/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.utils;


import com.base.frame.socle.core.entity.ParamList;
import java.text.Normalizer;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 *
 * @author NGS_004
 */
public final class SprUtils {

    public static final int RAU_LENGTH = 16;
    public static final int TICKET_LENGTH = 4;

    private SprUtils() {
        // Do nothing
    }

    public static String formatAuthority(String... aut) {
        String s = "";
        for (String a : aut) {
            s = s + ",'" + a + "'";
        }
        s = s.substring(1);
        return "hasAnyAuthority(" + s + ")";
    }

    public static String getCouleur(ParamList action) {
        return (action == null || action.getCode().equals(SocleConstant.ACTION_PARAM_ACTIVER)) ? SocleConstant.COLOR_ACTIVER : SocleConstant.COLOR_ABANDONNER;
    }

    /**
     * Conversion de date en GMT
     *
     * @param date
     * @return
     */
    public static Instant getDateToUTC(Instant date) {
        return getDateToUTC(date, LocalTime.MIN);
    }

    /**
     *
     * @param date
     * @param localTime
     * @return
     */
    public static Instant getDateToUTC(Instant date, LocalTime localTime) {
        LocalDate localDate = date.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDateTime time = localDate.atTime(localTime);
        return time.atZone(ZoneOffset.UTC).toInstant();
    }

    public static Instant getDateToString(Instant date, LocalTime localTime) {
        LocalDate localDate = date.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDateTime time = localDate.atTime(localTime);
        return time.atZone(ZoneOffset.UTC).toInstant();
    }

    public static String getNumeroRAU(int numero) {
        return getNumeroRAU(numero, SprUtils.RAU_LENGTH);
    }

    public static String getNumeroRAU(int numero, int rauLength) {
        String numeroRAU = numero + "";
        while (numeroRAU.length() < rauLength) {
            numeroRAU = "0" + numeroRAU;
        }
        return numeroRAU;
    }

    public static String getStringRAU(String value, int rauLength) {
        return filler(value, rauLength, "0", false);
    }

    public static String filler(String value, int length, String fillerChar, boolean after) {
        while (value.length() < length) {
            value = after ? value + fillerChar : fillerChar + value;
        }
        return value;
    }

    public static String formateTicketNumero(int numero) {
        String numeroRAU = numero + "";
        while (numeroRAU.length() < SprUtils.TICKET_LENGTH) {
            numeroRAU = "0" + numeroRAU;
        }
        return numeroRAU;
    }

    /**
     * Remplacer les caractères accentues par les caractères simples
     *
     * @param src
     * @return
     */
    public static String unaccent(String src) {
        return Normalizer
                //                .normalize(src.replaceAll("\\s", "").toUpperCase(), Normalizer.Form.NFD)
                .normalize(src, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
    public static String removeSpaces(String src) {
        return src.replaceAll("\\s+", "");
    }

    public static String getAlphaNumericString(int n, String secretkey) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (secretkey.length() * Math.random());
            sb.append(secretkey.charAt(index));
        }
        return sb.toString();
    }
}
