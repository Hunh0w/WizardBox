package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.internal.authentication.managers.AuthManager;
import fr.hunh0w.wizardbox.internal.authentication.managers.SQLManager;
import fr.hunh0w.wizardbox.internal.authentication.objects.LoginData;
import fr.hunh0w.wizardbox.utils.VarUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    /* LOGIN */
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login_postBody(@ModelAttribute("logindata") LoginData loginData, Model model) {
        String result = AuthManager.check_login(loginData);
        if(result.equals(AuthManager.OK)){
            //TODO SESSION OPEN
            return "redirect:/";
        }
        
        model.addAttribute("error", result);
        model.addAttribute("logindata", loginData);
        return "login";
    }

}
