/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.controller;

import com.base.frame.account.utils.AccountConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author PIERRE
 * @since 17-06-2019
 */
@Controller
@RequestMapping(path = AccountConstant.FC_GROUPE_UTILISATEUR_URL)

public class GroupeUtilisateurController {

    private static final String PAGE_DE_GROUPE_UTILISATEUR = "utilisateur/groupe_utilisateurs";

    @GetMapping()
    public String paramMode(Model model) {
//        model.addAttribute("conceptMetier", SecConstante.CONCEPT_METIER_GROUPE_UTILISATEUR);
        return PAGE_DE_GROUPE_UTILISATEUR;
    }
}
