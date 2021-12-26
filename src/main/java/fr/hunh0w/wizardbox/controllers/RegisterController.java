package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.internal.authentication.managers.AuthManager;
import fr.hunh0w.wizardbox.internal.authentication.objects.RegisterData;
import fr.hunh0w.wizardbox.internal.managers.SQLManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    /* REGISTER */
    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("error", new RegisterData());
        return "register";
    }

    @PostMapping("/register")
    public String register_postBody(@ModelAttribute("regdata") RegisterData regdata, Model model) {
        RegisterData result = AuthManager.check_register(regdata);
        if(!result.hasErrors()){
            String resp = SQLManager.registerUser(regdata);
            if(resp == null) return "redirect:/login";
            result.setError(resp);
        }

        model.addAttribute("error", result);
        model.addAttribute("regdata", regdata);
        return "register";
    }

}
