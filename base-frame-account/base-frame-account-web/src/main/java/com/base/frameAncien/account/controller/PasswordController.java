/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frameAncien.account.controller;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Alassani
 */
@Controller
@RequestMapping(path = "/password")
public class PasswordController {
//@GetMapping(path = "/reinit")
//    public String reinitPassword(Model model) {
//        System.out.println("======================== reinitPassword CONTROLLER ADD ");
//        return "account/reinitPassword";
//    }

    @GetMapping(path = "/reinit")
    public String reinitPassword(Model model, @RequestParam(name = "token") String token,
            @RequestParam(name = "code") String code) {
        System.out.println("======================== reinitPassword CONTROLLER ADD ");
        model.addAttribute("generateCode", token);
        model.addAttribute("genereteUserCode", code);
        return "account/reinitPassword";
    }
    

    @GetMapping(path = "/forgetPassword")
    public String forgetPassword(Model model) {
        return "account/forgetPassword";
    }
}
