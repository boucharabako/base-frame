///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.entities.CodifList;
//import com.ngs.core.codification.entities.Extension;
//import com.ngs.core.codification.entities.GroupeExtension;
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// *
// * @author Herve
// */
//public class GroupeExtensionDTO {
//
//    /**
//     * numero du groupe
//     */
//    private String num = "";
//    
//    private String titre;
//    
//    private String numero = "";
//    
//    private String description;
//    
//    private String liste;
//    private List<String> listeExtId = new ArrayList<>();
//    
//    private boolean selected = false;
//    
//    private List<Extension> extensions;
//    
//    private List<GroupeExtension> groupeExtentions;
//    
//    private List<GroupeExtension> grpes;
//    private CodifList conceptMetier;
//    
//    public GroupeExtensionDTO() {
//        this.groupeExtentions = new ArrayList();
//        this.grpes = new ArrayList();
//    }
//    
//    public GroupeExtensionDTO(String titre, String description, String numero) {
//        this.titre = titre;
//        this.description = description;
//        this.numero = numero;
//        this.groupeExtentions = new ArrayList();
//        this.grpes = new ArrayList();
//    }
//    
//    public String getTitre() {
//        return titre;
//    }
//    
//    public void setTitre(String titre) {
//        this.titre = titre;
//    }
//    
//    public String getDescription() {
//        return description;
//    }
//    
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    
//    public List<Extension> getExtensions() {
//        return extensions;
//    }
//    
//    public void setExtensions(List<Extension> extensions) {
//        this.extensions = extensions;
//    }
//    
//    public String getNumero() {
//        return numero;
//    }
//    
//    public void setNumero(String numero) {
//        this.numero = numero;
//    }
//    
//    public String getListe() {
//        return liste;
//    }
//    
//    public void setListe(String liste) {
//        this.liste = liste;
//    }
//    
//    public List<GroupeExtension> getGroupeExtentions() {
//        return groupeExtentions;
//    }
//    
//    public void setGroupeExtentions(List<GroupeExtension> groupeExtentions) {
//        this.groupeExtentions = groupeExtentions;
//    }
//    
//    public List<GroupeExtension> getGrpes() {
//        return grpes;
//    }
//    
//    public void setGrpes(List<GroupeExtension> grpes) {
//        this.grpes = grpes;
//    }
//    
//    public boolean isSelected() {
//        return selected;
//    }
//    
//    public void setSelected(boolean selected) {
//        this.selected = selected;
//    }
//
//    public CodifList getConceptMetier() {
//        return conceptMetier;
//    }
//
//    public void setConceptMetier(CodifList conceptMetier) {
//        this.conceptMetier = conceptMetier;
//    }
//
//    @Override
//    public String toString() {
//        return "GroupeExtensionDTO{" + "num=" + num + ", titre=" + titre + ", numero=" + numero + ", description=" + description + ", liste=" + liste + ", listeExtId=" + listeExtId + ", selected=" + selected + ", extensions=" + extensions + ", groupeExtentions=" + groupeExtentions + ", grpes=" + grpes + ", conceptMetier=" + conceptMetier + '}';
//    }
//    public static GroupeExtensionDTO from(GroupeExtension groupeExtention) {
//        GroupeExtensionDTO rd = new GroupeExtensionDTO();
//        rd.setNumero(groupeExtention.getId());
//        rd.setDescription(groupeExtention.getDescription());
//        rd.setTitre(groupeExtention.getTitre());
//        if(groupeExtention.getCodeConcept()!=null ){
//        rd.setConceptMetier(groupeExtention.getCodeConcept());
//        System.out.println("CONCEPT METIER---------+++++++++++++  "+groupeExtention.getCodeConcept());}
//        String str = "";
//        for (Extension extension : groupeExtention.getExtensions()) {
//            if (str.isEmpty()) {
//                str = extension.getId() + ',';
//            } else {
//                str = str + ',' + extension.getId();
//            }
//        }
//        
//        rd.setListe(str);
//        return rd;
//    }
//    
//    public static GroupeExtensionDTO from(GroupeExtension groupeExtention, boolean selected) {
//        GroupeExtensionDTO rd = new GroupeExtensionDTO();
//        rd.setNumero(groupeExtention.getId());
//        rd.setDescription(groupeExtention.getDescription());
//        rd.setTitre(groupeExtention.getTitre());
//        rd.setConceptMetier(groupeExtention.getCodeConcept());
//        System.out.println("CONCEPT METIER 01+++++++++++++  "+groupeExtention.getCodeConcept());
//        String str = "";
//        for (Extension extension : groupeExtention.getExtensions()) {
//            if (str.isEmpty()) {
//                str = extension.getId() + ',';
//            } else {
//                str = str + ',' + extension.getId();
//            }
//        }
//        rd.setSelected(selected);
//        rd.setListe(str);
//        return rd;
//    }
//    
//    public GroupeExtension extract() {
//        GroupeExtension ge = new GroupeExtension();
//        ge.setTitre(this.titre);
//        ge.setDescription(this.getDescription());
//        ge.setId(this.numero);
//        ge.setCodeConcept(this.getConceptMetier());
//        System.out.println("CONCEPT METIER 03+++++++++++++  "+this.getConceptMetier());
//        return ge;
//    }
//    
//    public String getNum() {
//        return num;
//    }
//    
//    public void setNum(String num) {
//        this.num = num;
//    }
//
//    public List<String> getListeExtId() {
//        return listeExtId;
//    }
//
//    public void setListeExtId(List<String> listeExtId) {
//        this.listeExtId = listeExtId;
//    }
//    
//}
