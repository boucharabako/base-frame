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
@RequestMapping(path = "/utilisateur")
public class UtilisateurController {

        @PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_USER_LIRE + "')")
    @GetMapping(path = "/liste")
    public String home(Model model) {
        return "account/utilisateurs";
    }

    @PreAuthorize("hasAnyAuthority('" + AccountConstant.FC_USER_LIRE + "')")
    @GetMapping(path = "/ajouter")
    public String add(Model model, @RequestParam(required = false, name = "me") Optional<String> me) {
        System.out.println("======================== CONTROLLER ADD");
        if (me.isPresent()) {
            System.out.println("======================== CONTROLLER ADD " + me.isPresent());
            model.addAttribute("identifiant", me.get());
        }
        return "account/add_user";
    }
    
       @GetMapping(path = "/profil")
    public String edit(Model model) {
        return "account/edit_user";
    }
    
}
