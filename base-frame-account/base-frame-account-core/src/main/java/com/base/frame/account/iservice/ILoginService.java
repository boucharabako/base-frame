/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.iservice;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Alassani
 */
public interface ILoginService {
    public List<GrantedAuthority> loadAuthorisation(final String login);
     public User loadUserDetail(final String login);
}
