/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core.workflow.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author kouwonou
 */
public class UtilisateurDTOV2 {
    private String id;
    private String username;
    private String name;
    private String email;
    private List<String> profil;
    private String libelleProfil;
    private boolean isConnect = false;
    private String idSession ;
    private String lastName;
    private String firstName;
    public UtilisateurDTOV2() {
    }
    
    public UtilisateurDTOV2(String username, String name,String lastName) {
        this.username = username;
        this.name = (name==null?"":name)+" "+(lastName==null?" ":lastName);
        
    }
    public UtilisateurDTOV2(String id,
            String username, String name,String lastName) {
        this.id = id;
        this.username = username;
        this.name = (name==null?"":name)+" "+(lastName==null?" ":lastName);
        
    }

    public String getLibelleProfil() {
        return libelleProfil;
    }

    public void setLibelleProfil(String libelleProfil) {
        this.libelleProfil = libelleProfil;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProfil() {
        return profil;
    }

    public void setProfil(List<String> profil) {
        this.profil = profil;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UtilisateurDTOV2 other = (UtilisateurDTOV2) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsConnect() {
        return isConnect;
    }

    public void setIsConnect(boolean isConnect) {
        this.isConnect = isConnect;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

   /* @Override
    public String toStrings() {
        return "UtilisateurDTOV2{" + "id=" + id + ", username=" + username + ", name=" + name + ", email=" + email + ", profil=" + profil + ", libelleProfil=" + libelleProfil + ", isConnect=" + isConnect + ", idSession=" + idSession + ", lastName=" + lastName + ", firstName=" + firstName + '}';
    }*/
    
    
   
     @Override
    public String toString() {
        return "UtilisateurDTOV2{" + "username=" + username + ", name=" + name + ", profil=" + profil + ", libelleProfil=" + libelleProfil + ", lastName=" + lastName + ", firstName=" + firstName + '}';
    }
    
    
   

   /* @Override
    public String toString() {
        return "UtilisateurDTOV2{" + "username=" + username + ", name=" + name + ", profil=" + profil + ", libelleProfil=" + libelleProfil + '}';
    }*/
    
    
    
}
