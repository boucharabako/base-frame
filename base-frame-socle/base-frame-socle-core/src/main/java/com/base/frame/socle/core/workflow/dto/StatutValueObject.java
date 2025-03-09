///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.base.frame.socle.core.utils.SocleConstant;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
///**
// *@JsonIgnoreProperties(ignoreUnknown = true)
// * @author KOMLA
// */
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class StatutValueObject {
//    
//      private String id;
//    private String libelle;
//    private String ideCode;
//    private String libelleStatutSuivant;
//    private String ideCodeStatutSuivant;
//    private String nextStatutBackColor;
//    private String nextStatutTextColor;
//    private String statutTextColor;
//    private String statutBackColor;
//    private boolean editable=true;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getLibelle() {
//        return libelle;
//    }
//
//    public void setLibelle(String libelle) {
//        this.libelle = libelle;
//    }
//
//    public String getIdeCode() {
//        return ideCode;
//    }
//
//    public void setIdeCode(String ideCode) {
//        this.ideCode = ideCode;
//    }
//
//    public String getLibelleStatutSuivant() {
//        return libelleStatutSuivant;
//    }
//
//    public void setLibelleStatutSuivant(String libelleStatutSuivant) {
//        this.libelleStatutSuivant = libelleStatutSuivant;
//    }
//
//    public String getIdeCodeStatutSuivant() {
//        return ideCodeStatutSuivant;
//    }
//
//    public void setIdeCodeStatutSuivant(String ideCodeStatutSuivant) {
//        this.ideCodeStatutSuivant = ideCodeStatutSuivant;
//    }
//    
//    
//        public String getStatutBackColor() {
//        switch (this.ideCode) {
//            case SocleConstant.STATUT_PARAM_EN_SAISIE:
//                return SocleConstant.COLOR_EN_SAISIE;
//            case SocleConstant.STATUT_PARAM_ACTIF:
//                return SocleConstant.COLOR_ACTIVER;
//            case SocleConstant.STATUT_PARAM_OBSOLETE:
//                return SocleConstant.COLOR_ABANDONNER;
//            default:
//                return "";
//        }
//    }
//        
//        
//         public String getStatutTextColor() {
//        switch (this.ideCode) {
//            case SocleConstant.STATUT_PARAM_ACTIF:
//                return SocleConstant.COLOR_TEXT_ACTIVER;
//            case SocleConstant.STATUT_PARAM_OBSOLETE:
//                return SocleConstant.COLOR_TEXT_ABANDONNER;
//            default:
//                return SocleConstant.COLOR_WHITE;
//        }
//    }
//         
//          public String getNextStatutBackColor() {
//        if (this.ideCode != null && !this.ideCode.isEmpty()) {
//            if (ideCode.equals(SocleConstant.STATUT_PARAM_EN_SAISIE)) {
//                return SocleConstant.COLOR_ACTIVER;
//            }
//            if (ideCode.equals(SocleConstant.STATUT_PARAM_ACTIF)) {
//                return SocleConstant.COLOR_ABANDONNER;
//            }
//
//        }
//        return "";
//    }
//          
//            public String getNextStatutTextColor() {
//        if (this.ideCode != null && !this.ideCode.isEmpty()) {
//            if (ideCode.equals(SocleConstant.STATUT_PARAM_ACTIF)) {
//                return SocleConstant.COLOR_TEXT_ABANDONNER;
//            }
//            if (ideCode.equals(SocleConstant.STATUT_PARAM_EN_SAISIE)) {
//                return SocleConstant.COLOR_TEXT_ACTIVER;
//            } else {
//                return SocleConstant.COLOR_WHITE;
//            }
//        }
//        return "";
//    }
//    
//    public  String getNextStatutIdeCode() {
//        if (ideCode.equals(SocleConstant.STATUT_PARAM_EN_SAISIE)) {
//            return SocleConstant.STATUT_PARAM_ACTIF;
//        }
//        if (ideCode.equals(SocleConstant.STATUT_PARAM_ACTIF)) {
//            return SocleConstant.STATUT_PARAM_OBSOLETE;
//        } else {
//            return "";
//        }
//    }
//
//     public boolean getEditable() {
//        
//        return  ideCode.equals(SocleConstant.STATUT_PARAM_EN_SAISIE)
//       ;
//    }
//
//    public void setEditable(boolean editable) {
//        this.editable = editable;
//    }
//
//    @Override
//    public String toString() {
//        return "StatutValueObject{" + "id=" + id + ", libelle=" + libelle + ", ideCode=" + ideCode + ", libelleStatutSuivant=" + libelleStatutSuivant + ", ideCodeStatutSuivant=" + ideCodeStatutSuivant + ", nextStatutBackColor=" + nextStatutBackColor + ", nextStatutTextColor=" + nextStatutTextColor + ", statutTextColor=" + statutTextColor + ", statutBackColor=" + statutBackColor + ", editable=" + editable + '}';
//    }
//    
//    
//}
