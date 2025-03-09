/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.dto;

/**
 *
 * @author Alassani
 */
public class ReinitPasswordConfirmationDTO {

    private String token;
    private String userCode;
    private String password;
    private String confirmPassword;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "ReinitPasswordConfirmationDTO{" + "token=" + token + ", userCode=" + userCode + ", password=" + password + ", confirmPassword=" + confirmPassword + '}';
    }

}
