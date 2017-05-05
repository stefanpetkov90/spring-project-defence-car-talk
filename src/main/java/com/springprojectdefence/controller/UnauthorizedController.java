package com.springprojectdefence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UnauthorizedController {

    @GetMapping("/unauthorized")
    public String GetUnauthorizedPage(){
        return "access-denied";
    }
}
