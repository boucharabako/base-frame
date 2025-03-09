/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.audit;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Alassani
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserDeviceInfoService implements UserDeviceInfo{

    private String userAgent;
    private String ipAdresse;
    private String zone;
    private String device;
    @Autowired
    HttpServletRequest httpServletRequest;
    
    
    @Override
    public String getUserAgent() {
        Object d=httpServletRequest.getSession().getAttribute("USER-AGENT");
        return d!=null?d.toString():"";
    }

    @Override
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String getIpAdresse() {
          Object d=httpServletRequest.getSession().getAttribute("IP");
        return d!=null?d.toString():"";
    }

    @Override
    public void setIpAdresse(String ipAdresse) {
        this.ipAdresse = ipAdresse;
    }

    @Override
    public String getZone() {
         Object d=httpServletRequest.getSession().getAttribute("ZONE");
        return d!=null?d.toString():"";
    }

    @Override
    public void setZone(String zone) {
        try {
                    httpServletRequest.getSession().setAttribute("ZONE", this.zone(zone));

        } catch (Exception e) {
                                httpServletRequest.getSession().setAttribute("ZONE", "Local");

        }
        this.zone = zone;
    }

    @Override
    public String getDevice() {
        Object d=httpServletRequest.getSession().getAttribute("DEVICE");
        return d!=null?d.toString():"";
    }

    @Override
    public void setDevice(String device) {
        this.device = device;
    }
    
    @Async
    public String zone(String path) {
        
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://ipapi.co/";

        if (path != null) {
            uri = uri +path+ "/json" ;
        }else{
           uri = uri + "json"; 
        }

        ZoneDto result = restTemplate.getForObject(uri, ZoneDto.class);

       
        if (result != null) {
           return  result.toString();
        }
        return "";
    }
    
}
