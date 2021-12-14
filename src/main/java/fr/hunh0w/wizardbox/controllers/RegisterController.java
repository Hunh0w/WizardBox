package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.internal.authentication.SQLManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class RegisterController {

    /* REGISTER */
    @GetMapping("/register")
    public String getRegister(Model model){
        List<String> users = SQLManager.getUsers();
        model.addAttribute("users", users);
        return "register";
    }

    @PostMapping("/register")
    public String register_postBody(@RequestBody String fullName) {
        return "index";
    }

}