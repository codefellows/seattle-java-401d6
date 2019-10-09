package com.ferreirae.dragons.controllers;

import com.ferreirae.dragons.models.ApplicationUser;
import com.ferreirae.dragons.models.ApplicationUserRepository;
import com.ferreirae.dragons.models.Dragon;
import com.ferreirae.dragons.models.DragonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class DragonController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    DragonRepository dragonRepository;

    @GetMapping("/addDragon")
    public String showDragonForm(){
        return "addDragon";
    }

    @PostMapping("/addDragon")
    public RedirectView addDragon(Principal p, String color, int headCount, boolean spikes, String element){
        ApplicationUser theUser = applicationUserRepository.findByUsername(p.getName());

        Dragon dragon = new Dragon(color, headCount, element, spikes, theUser);

        dragonRepository.save(dragon);

        return new RedirectView("/users/" + theUser.getId());
    }
}
