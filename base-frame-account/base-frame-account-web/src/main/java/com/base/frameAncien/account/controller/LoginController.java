/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frameAncien.account.controller;

//import groovyjarjarpicocli.CommandLine.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Alassani
 */
@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @GetMapping(name = "/")
    public String login(Model model) {
        return "login";
    }
}
