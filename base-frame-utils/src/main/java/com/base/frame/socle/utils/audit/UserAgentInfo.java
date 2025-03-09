/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.audit;

/**
 *Interface pour injecter le nom du navigateur
 * @author Alassani
 * @version 1.0.0
 * @since 04/06/2018
 * 
 * 
 */
public interface UserAgentInfo {
    
    /**
     * 
     * @param userAgent nom du navigateur
     */
    public void setLastModifiedUserAgent(final String userAgent);
    public void setLastModifiedIpAdresse(final String userAgent);
    public void setLastModifiedZone(final String userAgent);
    public void setLastModifiedUserDevice(final String userAgent);
    /**
     * 
     * @param userAgent nom du navigateur
     */
    public void setCreatedUserAgent(final String userAgent);
    public void setCreatedIpAdresse(final String userAgent);
    public void setCreatedZone(final String userAgent);
    public void setCreatedUserDevice(final String userAgent);
}
