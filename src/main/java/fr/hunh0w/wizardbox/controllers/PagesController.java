package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.internal.authentication.SQLManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PagesController {

    @GetMapping("/")
    public String getHome(Model model){
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(Model model){
        List<String> users = SQLManager.getUsers();
        model.addAttribute("users", users);
        return "register";
    }

}
