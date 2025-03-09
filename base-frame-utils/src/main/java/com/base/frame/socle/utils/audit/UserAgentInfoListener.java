/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.audit;

import java.io.Serializable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *Injecte dans les objet à persister, et à mise a jours, des attribut supplmentaires 
 * @author WKOUWONOU
 * @since 02/02/2017
 * @version 1.0.1
 */
public class UserAgentInfoListener implements Serializable{

    @Autowired
    private UserDeviceInfo userDeviceInfo;

    @PrePersist
    public void prePersist(final UserAgentInfo agentInfo) {
        AutowireHelper.autowire(this, this.userDeviceInfo);
        if (userDeviceInfo != null) {
            
            agentInfo.setCreatedUserAgent(userDeviceInfo.getUserAgent());
            agentInfo.setCreatedUserDevice(userDeviceInfo.getDevice());
            agentInfo.setCreatedZone(userDeviceInfo.getZone());
            agentInfo.setCreatedIpAdresse(userDeviceInfo.getIpAdresse());

            agentInfo.setLastModifiedIpAdresse(userDeviceInfo.getIpAdresse());
            agentInfo.setLastModifiedUserAgent(userDeviceInfo.getUserAgent());
            agentInfo.setLastModifiedZone(userDeviceInfo.getZone());
            agentInfo.setLastModifiedUserDevice(userDeviceInfo.getDevice());

        }
    }

    @PreUpdate
    public void preUpdate(final UserAgentInfo agentInfo) {
                AutowireHelper.autowire(this, this.userDeviceInfo);

        AutowireHelper.autowire(this, this.userDeviceInfo);
        if (userDeviceInfo != null) {
            agentInfo.setLastModifiedUserAgent(userDeviceInfo.getUserAgent());
            agentInfo.setLastModifiedIpAdresse(userDeviceInfo.getIpAdresse());
            agentInfo.setLastModifiedZone(userDeviceInfo.getZone());
            agentInfo.setLastModifiedUserDevice(userDeviceInfo.getDevice());

        }
    }
}
