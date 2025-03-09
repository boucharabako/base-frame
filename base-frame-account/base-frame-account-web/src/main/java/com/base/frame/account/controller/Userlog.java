/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author KOMLA
 */
@Controller
@RequestMapping("parametre/log")
public class Userlog {

    private static final String TYPE_LOG_PAGE = "utilisateur/userLog";

    @GetMapping(path = "")
    public String log(Model model) {
        return TYPE_LOG_PAGE;
    }

}
