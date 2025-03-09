/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.controller;

import com.base.frame.account.utils.AccountConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
sssssssssssssssssss

 */
/**
 * Cette classe sert de controlleur pour les pages de gestion d'utilisateur
 *
 * @author Adnaane
 * @version 1.0.0
 * @since 27/02/2018
 *
 */
@Controller
@RequestMapping(path = "/parametre/utilisateur")
public class UtilisateurNewController {

    public static final String PAGE_FILE_PATH = "utilisateur/edit_user_new_1_1";

    public static final String PAGE_LIST_FILE_PATH = "utilisateur/gestion_utilisateur";
    public static final String CONCEPT_METIER = "conceptMetier";
    public static final String PAGE_EDIT_PASSWORD_FILE_PATH = "utilisateur/reset_password_new";


    @RequestMapping()
    public String homeList(Model model) {
        //model.addAttribute(CONCEPT_METIER, SecConstante.CONCEPT_METIER_UTILISATEUR);
        return UtilisateurNewController.PAGE_LIST_FILE_PATH;
    }

    /**
     * Cette page redirige sur la page de création d'un utilisateur
     *
     * @param model
     * @return
     */
    @RequestMapping(path = "/edit_user_new_1_1")
    public String homeNewUser(Model model) {

        //model.addAttribute(CONCEPT_METIER, SecConstante.CONCEPT_METIER_UTILISATEUR);
        return UtilisateurNewController.PAGE_FILE_PATH;
    }

        @RequestMapping(path = "reset_password_new")
    public String changePassword(Model model) {
        return UtilisateurNewController.PAGE_EDIT_PASSWORD_FILE_PATH;
    }
}
