package com.springprojectdefence.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooterController {
    @GetMapping("/cookies")
    public String getCookiePolicyPage(Model model){
        model.addAttribute("title", "Cookies");
        return "cookiePolicy";
    }
}
