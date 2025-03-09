/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frameAncien.account.controller;

import com.base.frame.account.utils.AccountConstant;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Alassani
 */
@Controller
@RequestMapping(path = "/profil-utilisateur")

public class ProfilController {
    @PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_PROFIL_LIRE + "')")
    @GetMapping(path = "/list")
    public String home(Model model) {
        return "account/profil_utilisateur";
    }

    @PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_PROFIL_LIRE + "')")
    @GetMapping(path = "/ajout")
    public String add(Model model, @RequestParam(required = false, name = "me") Optional<String> me) {
        System.out.println("======================== CONTROLLER ADD");
        if (me.isPresent()) {
             System.out.println("======================== CONTROLLER ADD " +  me.isPresent());
            model.addAttribute("identifiant", me.get());
        }
        return "account/add_profil";
//         return "account/test";
    }
}
