///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.base.frame.socle.core.workflow.dto;
//
//import com.ngs.core.codification.entities.Extension;
//import java.util.Objects;
//
///**
// *
// * @author Herve
// */
//public class RedoExtensionDTO {
//
//    private String id;
//    private String typeDonnee;
//    private String requis;
//    private String codeConcept;
//    private String propriete;
//    private boolean selected;
//
//    public RedoExtensionDTO() {
//        this.selected = false;
//    }
//
//    public RedoExtensionDTO(String id, String typeDonnee, String requis, String codeConcept, String propriete) {
//        this.id = id;
//        this.typeDonnee = typeDonnee;
//        this.requis = requis;
//        this.codeConcept = codeConcept;
//        this.propriete = propriete;
//        this.selected = false;
//    }
//
//    public RedoExtensionDTO(String id, String typeDonnee, String requis, String codeConcept, String propriete, boolean selected) {
//        this.id = id;
//        this.typeDonnee = typeDonnee;
//        this.requis = requis;
//        this.codeConcept = codeConcept;
//        this.propriete = propriete;
//        this.selected = selected;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getTypeDonnee() {
//        return typeDonnee;
//    }
//
//    public void setTypeDonnee(String typeDonnee) {
//        this.typeDonnee = typeDonnee;
//    }
//
//    public String getRequis() {
//        return requis;
//    }
//
//    public void setRequis(String requis) {
//        this.requis = requis;
//    }
//
//    public String getCodeConcept() {
//        return codeConcept;
//    }
//
//    public void setCodeConcept(String codeConcept) {
//        this.codeConcept = codeConcept;
//    }
//
//    public String getPropriete() {
//        return propriete;
//    }
//
//    public void setPropriete(String propriete) {
//        this.propriete = propriete;
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
//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 11 * hash + Objects.hashCode(this.id);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final RedoExtensionDTO other = (RedoExtensionDTO) obj;
//        if (!Objects.equals(this.id, other.id)) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "RedoExtensionDTO{" + "id=" + id + ", typeDonnee=" + typeDonnee + ", requis=" + requis + ", codeConcept=" + codeConcept + ", propriete=" + propriete + ", selected=" + selected + '}';
//    }
//
//    public static RedoExtensionDTO from(Extension extension) {
//        RedoExtensionDTO rd = new RedoExtensionDTO();
//        rd.setId(extension.getId());
//        rd.setPropriete(extension.getPropriete().getIde());
//        rd.setRequis(extension.getRequis().getId());
//        rd.setTypeDonnee(extension.getTypeDonnee().getId());
//        return rd;
//    }
//    
//    public Extension createExtension() {
//        Extension ext = new Extension();
//        ext.setId(id);
//        return ext;
//    }
//
//}
