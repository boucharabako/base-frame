/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frameAncien.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Alassani
 */
@Controller
@RequestMapping(path = "/groupe-utilisateur")
public class GroupeController {

    @GetMapping(name = "/")
    public String home(Model
            model) {
        return "account/groupe_utilisateur";
    }

    @GetMapping(path = "/programme")
    public String programme(Model
                               model) {
        return "account/component";
    }
}
