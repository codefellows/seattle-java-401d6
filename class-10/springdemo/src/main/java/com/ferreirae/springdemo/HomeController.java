package com.ferreirae.springdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// annotation for Spring: please look here for routes
@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomeRoute() {
        // this string matches the name of a template inside resources/templates
        return "home";
    }
}
