/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

import com.base.frame.socle.core.workflow.entity.WorkFlowEtape;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NGS_004
 */
public class Sequence {
    private Integer id;
    private String intitule;
    private List<WorkFlowEtape> liste=new ArrayList<>();
    private List<EtatDTO> liste2=new ArrayList<>();
   private boolean nextExist=true;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public List<WorkFlowEtape> getListe() {
        return liste;
    }

    public void setListe(List<WorkFlowEtape> liste) {
        this.liste = liste;
    }

    public boolean getNextExist() {
        return nextExist;
    }

    public void setNextExist(boolean nextExist) {
        this.nextExist = nextExist;
    }

    public List<EtatDTO> getListe2() {
        return liste2;
    }

    public void setListe2(List<EtatDTO> liste2) {
        this.liste2 = liste2;
    }
    
    
}
