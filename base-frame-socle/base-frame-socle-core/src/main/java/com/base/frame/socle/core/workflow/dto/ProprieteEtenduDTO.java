///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.entities.ProprieteEtendu;
//import com.ngs.core.utils.pagination.PageParam;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author NGS_004
// */
//public class ProprieteEtenduDTO extends PageParam<ProprieteEtendu> {
//
//    private List<ProprieteEtendu> liste;
//    private ProprieteEtendu data;
//
//    private List<ProprieteEtendu> content;
//    private String motCle;
//    private String caption;
//
//    public ProprieteEtenduDTO() {
//    }
//
//    public ProprieteEtenduDTO(List<ProprieteEtendu> liste, ProprieteEtendu data) {
//        this.liste = liste;
//        this.data = data;
//    }
//
//    public List<ProprieteEtendu> getListe() {
//        return liste;
//    }
//
//    public void setListe(List<ProprieteEtendu> liste) {
//        this.liste = liste;
//    }
//
//    public void setCaption(String caption) {
//        this.caption = caption;
//    }
//
//    public ProprieteEtendu getData() {
//        return data;
//    }
//
//    public void setData(ProprieteEtendu data) {
//        this.data = data;
//    }
//
//    public List<ProprieteEtendu> getContent() {
//        if (content == null) {
//            content = new ArrayList<>();
//        }
//        return content;
//    }
//
//    public void setContent(List<ProprieteEtendu> content) {
//        this.content = content;
//    }
//
//    public String getCaption() {
//        return caption;
//    }
//
//    public String getMotCle() {
//        return motCle;
//    }
//
//    public void setMotCle(String motCle) {
//        this.motCle = motCle;
//    }
//
//}
