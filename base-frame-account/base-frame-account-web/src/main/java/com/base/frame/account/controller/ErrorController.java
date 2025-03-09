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
 * @author Adnaane
 */
@Controller
@RequestMapping("/errors")
public class ErrorController {

    @GetMapping("404")
    private String home404(Model model) {
        return AccountConstant.ERROR_404_FILE;
    }

}
