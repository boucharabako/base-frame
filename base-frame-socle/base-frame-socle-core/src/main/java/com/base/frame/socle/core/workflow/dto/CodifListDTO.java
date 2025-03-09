/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

import com.base.frame.socle.core.entity.ParamList;
import com.base.frame.socle.core.utils.PageParam;
import com.base.frame.socle.core.workflow.entity.Codification;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Herve
 */
public class CodifListDTO extends PageParam<CodifListDTO> {

    private String id;

    private Codification codification;

    private String ideCode;

    private String libelle;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateDebutValidite;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateFinValidite;

    public CodifListDTO() {

    }

    public CodifListDTO(ParamList codifList) {
        this.id = codifList.getId();
        //this.codification = codifList.getParamCode();
        this.ideCode = codifList.getCode();
        this.libelle = codifList.getLibelle();
       
    }

    public CodifListDTO(String id, Codification codification, String ideCode, String libelle, Date dateDebutValidite, Date dateFinValidite) {
        this.id = id;
        this.codification = codification;
        this.ideCode = ideCode;
        this.libelle = libelle;
        this.dateDebutValidite = dateDebutValidite;
        this.dateFinValidite = dateFinValidite;
    }
    public CodifListDTO(String id, String ideCode, String libelle) {
        this.id = id;
        this.ideCode = ideCode;
        this.libelle = libelle;
    }

    public CodifListDTO(String id, Codification codification, String ideCode, String libelle, String dateDebutValidite, String dateFinValidite) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.id = id;
            this.codification = codification;
            this.ideCode = ideCode;
            this.libelle = libelle;
            this.dateDebutValidite = sdf.parse(dateDebutValidite);
            this.dateFinValidite = sdf.parse(dateFinValidite);
        } catch (ParseException ex) {

            Logger.getLogger(CodifListDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Codification getCodification() {
        return codification;
    }

    public void setCodification(Codification codification) {
        this.codification = codification;
    }

    public String getIdeCode() {
        return ideCode;
    }

    public void setIdeCode(String ideCode) {
        this.ideCode = ideCode;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDateDebutValidite() {
        return dateDebutValidite;
    }

    public void setDateDebutValidite(Date dateDebutValidite) {
        this.dateDebutValidite = dateDebutValidite;
    }

    public Date getDateFinValidite() {
        return dateFinValidite;
    }

    public void setDateFinValidite(Date dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    @Override
    public String toString() {
        return "CodifListDTO{" + "id=" + id + ", codification=" + codification + ", ideCode=" + ideCode + ", libelle=" + libelle + ", dateDebutValidite=" + dateDebutValidite + ", dateFinValidite=" + dateFinValidite + '}';
    }

    public ParamList from() {
        //null, this.ideCode, this.libelle, this.dateDebutValidite, this.dateFinValidite
        ParamList param = new ParamList();
        param.setCode(this.ideCode);
        param.setLibelle(this.libelle);
        return new ParamList();
    }

    public ParamList castCodifList(ParamList codifList) {
        if ("".equals(this.id)) {
            codifList.setId("");
        } else {
            codifList.setId(this.id);
        }
        codifList.setCode(this.ideCode);
        
        codifList.setLibelle(this.libelle);
        return codifList;
    }

}
