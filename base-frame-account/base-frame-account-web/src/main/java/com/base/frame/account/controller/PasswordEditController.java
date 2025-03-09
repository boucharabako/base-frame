/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.controller;

//import com.ngs.core.codification.utils.ApiError;
//import com.ngs.core.codification.utils.CodificationConstant;
//import com.ngs.core.codification.utils.ErrorConstant;
//import com.ngs.core.codification.utils.TypeError;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author AdminPost
 */
@Controller
@RequestMapping(path = "/password-reset")
public class PasswordEditController {

    @GetMapping(path = "")
    public String home(Model model) {
        return "utilisateur/passwordEdit";
    }
    
    
}
