/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

/**
 *
 * @author Assima
 */
public class ScannerControleParam {
      private String id;
    private Boolean scan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getScan() {
        return scan;
    }

    public void setScan(Boolean scan) {
        this.scan = scan;
    }

    @Override
    public String toString() {
        return "ScannerControleParam{" + "id=" + id + ", scan=" + scan + '}';
    }
    
}
