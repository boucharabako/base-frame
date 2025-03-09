/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.entity;

import com.base.frame.socle.core.utils.SocleConstant;
import com.base.frame.socle.core.utils.TypeFonction;
import com.base.frame.socle.utils.audit.AbstractAuditingEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Alassani
 */
@Entity
@Table(name = "socle_fonction", schema = SocleConstant.SCHEMA)
public class Fonction extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "libelle")
    private String libelle;

    @Column(name = "page_uri_base")
    private String pageUriBase;

    @Column(name = "api_uri_base")
    private String apiUriBase;
    @Column(name = "parent")
    private String parent;

//    @ManyToOne
//    @JoinColumn(name = "type")
////    MENU FONCTION
//    private ParamList type;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_fonction", nullable = false)
    private TypeFonction typeFonction = TypeFonction.FONCTION;

    @ManyToOne
    @JoinColumn(name = "niveau_habilitation_max")
    private ParamList niveauHabilitationMax;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPageUriBase() {
        return pageUriBase;
    }

    public void setPageUriBase(String pageUriBase) {
        this.pageUriBase = pageUriBase;
    }

    public String getApiUriBase() {
        return apiUriBase;
    }

    public void setApiUriBase(String apiUriBase) {
        this.apiUriBase = apiUriBase;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public TypeFonction getTypeFonction() {
        return typeFonction;
    }

    public void setTypeFonction(TypeFonction typeFonction) {
        this.typeFonction = typeFonction;
    }

    

//    public ParamList getType() {
//        return type;
//    }
//
//    public void setType(ParamList type) {
//        this.type = type;
//    }
    

    public ParamList getNiveauHabilitationMax() {
        return niveauHabilitationMax;
    }

    public void setNiveauHabilitationMax(ParamList niveauHabilitationMax) {
        this.niveauHabilitationMax = niveauHabilitationMax;
    }

}
