/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.audit;

/**
 *
 * @author Alassani
 */
public class ZoneDto {
    private String status;
    private String country;
    private String region;
    private String city;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country!=null?country:"";
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region!=null?region:"";
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city!=null?city:"";
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
      
        return getCountry()+"/"+getRegion()+"/"+getCity() ;
    }
    
    
}
