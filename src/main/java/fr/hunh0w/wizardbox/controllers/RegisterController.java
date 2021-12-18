package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.internal.authentication.managers.AuthManager;
import fr.hunh0w.wizardbox.internal.authentication.managers.SQLManager;
import fr.hunh0w.wizardbox.internal.authentication.objects.RegisterData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String register_postBody(@ModelAttribute("regdata") RegisterData regdata, Model model) {
        String result = AuthManager.check_register(regdata);
        if(result.equals(AuthManager.OK)){
            String resp = SQLManager.registerUser(regdata);
            if(resp == null) return "redirect:/";
            result = resp;
        }

        model.addAttribute("error", result);
        model.addAttribute("regdata", regdata);
        return "register";
    }

}
