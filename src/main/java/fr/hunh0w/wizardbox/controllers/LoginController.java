package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.internal.authentication.managers.AuthManager;
import fr.hunh0w.wizardbox.internal.authentication.objects.LoginData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    /* LOGIN */
    @GetMapping("/login")
    public String getLogin(HttpSession httpSession, Model model){
        model.addAttribute("error", new LoginData());
        return "login";
    }

    @PostMapping("/login")
    public String login_postBody(@ModelAttribute("logindata") LoginData loginData, Model model, HttpSession httpSession) {
        LoginData result = AuthManager.check_login(loginData, httpSession);
        if(!result.hasErrors()) {
            return "redirect:/";
        }


        model.addAttribute("error", result);
        model.addAttribute("logindata", loginData);
        return "login";
    }

}
