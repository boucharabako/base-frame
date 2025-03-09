/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.service;


import com.base.frame.socle.core.dto.IdLabelObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *Interface definisatant les extensions lié aux tables
 * @author W.KOUWONOU
 * @since 27/09/2018
 */
public interface IExtensionInterface {

    public default List<IdLabelObject> findAll() {
        
        return new ArrayList<>();
    }
    public default List<String> findParrent(String id) {
        
        return new ArrayList<>();
    }
    public default List<IdLabelObject> findAll(Map<String,String> params) {
        
        return new ArrayList<>();
    }
    public default List<String> findFilterValue() {
        
        return null;
    }

    
    public default IdLabelObject findOne(String id) {
        List<IdLabelObject> l = this.findAll();

        for (IdLabelObject u : l) {
            if (u.getId().equals(id)) {
                return u;
            }
        }

        return null;
    }
}
