package com.ferreirae.songr.controllers;

import com.ferreirae.songr.models.Emotion;
import com.ferreirae.songr.models.EmotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

// as a controller, this file has the job of specifying routes and what happens
// when a request comes in to that route
@Controller
public class HomeController {
    @Autowired
    EmotionRepository emotionRepository;

    // to make this a route, we need to specify the method and the path
    // app.get("/", (req, res) => res.send("hello world!") )

    // We know that we want to have a username query param specified
    // The model m is for us to specify variables that the view should have access to.
    // Model works like a HashMap
    @GetMapping("/")
    public String getHome(@RequestParam(required=false, defaultValue = "true") boolean hasDogs,
                          @RequestParam(required = false, defaultValue = "user") String username,
                          Model m) {
        // this string specifies a template that should be rendered
        // looks for a file in templates with the same name as this string
        System.out.println(username);
        m.addAttribute("potato", "russet");
        if (username == null) {
            m.addAttribute("username", "user");
        } else {
            m.addAttribute("username", username);
        }
        m.addAttribute("hasDogs", hasDogs);
        return "home";
    }

    @GetMapping("/hello/{username}")
    // the username is coming from the path this time
    public String getHelloRoute(@PathVariable String username, Model m) {
        m.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/emotions")
    public String getTheEmotions(Model m) {
//        Emotion[] feelings = new Emotion[] {
//                new Emotion("uncertainty", 8, "not sure of what a job actually looks like"),
//                new Emotion("confusion", 20, "Java hurt brain")
//        };
        List<Emotion> feelings = emotionRepository.findAll();
        m.addAttribute("feelings", feelings);
        return "emotions";
    }

    @PostMapping("/emotions")
    public RedirectView addTheEmotions(String emotionName, String reason, int strength){

        Emotion em = new Emotion(emotionName, strength, reason);
        emotionRepository.save(em); // in order to save into the database, I need an instance of my entity

        // this line of code redirects to /emotions
        return new RedirectView("/emotions");
    }
}
