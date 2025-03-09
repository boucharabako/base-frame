/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.base.frame.account.utils.AccountConstant;
import com.base.frame.socle.utils.audit.AbstractAuditingEntity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Alassani
 */

@Entity
@Table(name = "sec_authorisation", schema = AccountConstant.SCHEMA)
public class Autorisation extends AbstractAuditingEntity{
    
     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;
    
    @Column(name = "ide_objet")
    private String ideObjet;
    
    @Column(name = "ide_objet_autorisant")
    private String ideObjetAutorisant;

    public Autorisation( String ideObjet, String ideObjetAutorisant) {
        this.ideObjet = ideObjet;
        this.ideObjetAutorisant = ideObjetAutorisant;
    }

    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdeObjet() {
        return ideObjet;
    }

    public void setIdeObjet(String ideObjet) {
        this.ideObjet = ideObjet;
    }

    public String getIdeObjetAutorisant() {
        return ideObjetAutorisant;
    }

    public void setIdeObjetAutorisant(String ideObjetAutorisant) {
        this.ideObjetAutorisant = ideObjetAutorisant;
    }
    
    

    
}
