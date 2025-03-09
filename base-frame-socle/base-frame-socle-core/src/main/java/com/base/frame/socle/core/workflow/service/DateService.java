/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;

import com.base.frame.socle.core.utils.SocleConstant;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * abs-bud-nomenclature
 *
 * @author Pierre NGS creée le 8 oct. 2018 09:34:24
 */
@Service
@Transactional
public class DateService {

    /**
     * Cette méthode permet de vérifier si la date début est antérieure à la
     * date fin
     *
     * @param dateDebut
     * @param dateFin
     * @return
     */
    public boolean twoDateComparator(String dateDebut, String dateFin) {
        boolean dateError = false;
        if (Instant.parse(dateDebut).isAfter(Instant.parse(dateFin)) || Instant.parse(dateFin).isBefore(Instant.parse(dateDebut))) {
            dateError = true;
        }
        return dateError;
    }
   

    /**
     * Cette méthode permet de controler si la date debut et date fin d'une
     * fille est comprise entre la date début et la date fin du parent
     *
     * @param parentDateDebut
     * @param parentDateFin
     * @param childDateDebut
     * @param childDateFin
     * @return
     */
    public boolean parentAndChilDateComparator(String parentDateDebut, String parentDateFin, String childDateDebut, String childDateFin) {
        boolean value = false;
        if ((Instant.parse(childDateDebut).isBefore(Instant.parse(parentDateDebut)) || (Instant.parse(childDateDebut).isAfter(Instant.parse(parentDateFin))))
                || (Instant.parse(childDateFin).isAfter(Instant.parse(parentDateFin)) || Instant.parse(childDateFin).isBefore(Instant.parse(parentDateDebut)))) {
            value = true;
        }
        return value;
    }

    /**
     * Cette méthode permet de controler si la date debut et date fin d'une
     * fille est comprise entre la date début et la date fin du parent
     *
     * @param parentDateDebut
     * @param parentDateFin
     * @param dateCible
     * @return
     */
    public boolean dateIsBetewenTwoDate(String parentDateDebut, String parentDateFin, String dateCible) {
        boolean value = false;
        if ((Instant.parse(dateCible).isBefore(Instant.parse(parentDateDebut)) || (Instant.parse(dateCible).isAfter(Instant.parse(parentDateFin))))) {
            value = true;
        }
        return value;
    }
    
       public boolean dateIsAfterDate(String parentDateFin, String dateCible) {
        boolean value = false;
        if (Instant.parse(dateCible).isAfter(Instant.parse(parentDateFin))) {
            value = true;
        }
        return value;
    }
        public boolean dateIsBeforeDate(String parentDateFin, String dateCible) {
        boolean value = false;
        if (Instant.parse(dateCible).isBefore(Instant.parse(parentDateFin))) {
            value = true;
        }
        return value;
    }
    /**
     * Cette méthode permet de controler si la date debut et date fin d'une
     * fille est comprise entre la date début et la date fin du parent
     *
     * @param parentDateDebut
     * @param parentDateFin
     * @param dateCible
     * @return
     */
    public boolean dateIsBetewenTwoDateOrEqual(String parentDateDebut, String parentDateFin, String dateCible) {
        boolean value = false;
        if ((Instant.parse(dateCible).isBefore(Instant.parse(parentDateDebut)) || (Instant.parse(dateCible).isAfter(Instant.parse(parentDateFin))))
                && (Instant.parse(dateCible) != Instant.parse(parentDateDebut) && Instant.parse(dateCible) != Instant.parse(parentDateFin))) {
            value = true;
        }
        return value;
    }

    /**
     * Verifie la duree de la période comptable
     *
     * @param d1
     * @param d2
     * @param nbMois
     * @return
     */
    public boolean dateControle(String d1, String d2, Integer nbMois) {
        boolean dateError = false;
        Instant date1 = Instant.parse(d1);
        Instant date2 = Instant.parse(d2);
        LocalDate ld1 = date1.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ld2 = date2.atZone(ZoneId.systemDefault()).toLocalDate();
        Period p = Period.between(ld1, ld2);
        Integer finalCountMonth = ((p.getYears() * 12) + p.getMonths());
        if (finalCountMonth > nbMois) {
            dateError = true;
        }
        return dateError;
    }

    /**
     * Cette methode permet de calculer le nombre de mois entre deux dates
     *
     * @param d1
     * @param d2
     * @param parametre (jour, mois ou année)
     * @return
     */
    public Integer durationBetwenTwoDate(String d1, String d2, String parametre) {
        Instant date1 = Instant.parse(d1);
        Instant date2 = Instant.parse(d2);
        LocalDate ld1 = date1.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ld2 = date2.atZone(ZoneId.systemDefault()).toLocalDate();
        Period p = Period.between(ld1, ld2);
        if (p != null && parametre != null && !parametre.isEmpty()) {
            if (parametre.equals(SocleConstant.DAY_PARAM)) {
                return p.getDays();
            }
            if (parametre.equals(SocleConstant.MONTH_PARAM)) {
                return p.getMonths();
            }
            if (parametre.equals(SocleConstant.YEAR_PARAM)) {
                return p.getYears();
            }
        }
        return null;
    }
}
