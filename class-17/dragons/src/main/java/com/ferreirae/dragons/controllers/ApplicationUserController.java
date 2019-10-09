package com.ferreirae.dragons.controllers;

import com.ferreirae.dragons.models.ApplicationUser;
import com.ferreirae.dragons.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/signup")
    public RedirectView createNewUser(String username, String password) {
        // actually create a user
        ApplicationUser u = new ApplicationUser(username, passwordEncoder.encode(password));
        // TODO: salt & hash the password

        // save it to a database
        applicationUserRepository.save(u);
        // send user back to homepage
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage() { return "signup"; }

    @GetMapping("/users/{id}")
    public String showSingleUser(@PathVariable long id, Principal p, Model m) {

        m.addAttribute("viewedUser", applicationUserRepository.findById(id).get() ); // TODO: Optional
        m.addAttribute("user", p);
        return "userProfile";
    }
}
