package com.ferreirae.dragons.controllers;

import com.ferreirae.dragons.models.ApplicationUser;
import com.ferreirae.dragons.models.ApplicationUserRepository;
import com.ferreirae.dragons.models.Dragon;
import com.ferreirae.dragons.models.DragonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

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
    public RedirectView addDragon(Principal p, String color, int headCount, boolean spikes, String element, String quest){
        ApplicationUser theUser = applicationUserRepository.findByUsername(p.getName());

        Dragon dragon = new Dragon(color, headCount, element, spikes, theUser, quest);

        dragonRepository.save(dragon);

        return new RedirectView("/users/" + theUser.getId());
    }

    @GetMapping("/doQuest")
    public String showQuests (Model m){
        List<Dragon> dragons = dragonRepository.findAll();

        m.addAttribute("dragons", dragons);

        return "questing";
    }

    @PostMapping("/doQuest")
    public RedirectView doQuest(Principal p, long quest, long questingDragon){
        //get dragons
        Dragon dragonDoingQuest = dragonRepository.getOne(questingDragon);
        Dragon givingTheQuest = dragonRepository.getOne(quest);

        // update and save one dragon
        dragonDoingQuest.completeQuest(givingTheQuest);
        dragonRepository.save(dragonDoingQuest);

        // send me back to my page
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        return new RedirectView("/users/" + user.getId() );
    }
}
