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
public interface UserDeviceInfo {
    
    /**
     * 
     * @return 
     */
 public default  String getUserAgent(){return null;}
 public default  String getIpAdresse(){return null;}
 public default  String getZone(){return null;}
 public default  String getDevice(){return null;}
    /**
     * 
     * @param userAgent nom du navigateur
     */
public default  void setUserAgent(final String userAgent){}
public default  void setIpAdresse(final String userAgent){}
public default  void setZone(final String userAgent){}
public default  void setDevice(final String userAgent){}
}
