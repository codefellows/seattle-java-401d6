package com.ferreirae.dragons.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    // principal is the currently logged in user
    // it might be null
    // or it might have useful information
    @GetMapping("/")
    public String getHome(Principal p, Model m) {
        if (p != null) {

            m.addAttribute("username", p.getName());
        }
        return "home";
    }
}
