/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.core;

/**
 *
 * @author Alassani
 */
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Optional;

public interface ISecurityUtils {
   public  Optional<String> getCurrentUserLogin();
    public  Optional<String> getCurrentUserJWT();
    public boolean isAuthenticated();
    public boolean isCurrentUserInRole(String authority);
    public  List<GrantedAuthority> getRoles();
}

