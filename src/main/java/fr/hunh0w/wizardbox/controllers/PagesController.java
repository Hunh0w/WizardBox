package fr.hunh0w.wizardbox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/")
    public String getHome(){
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

}
