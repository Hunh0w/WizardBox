package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.internal.session.managers.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    /* ACCUEIL */
    @GetMapping("/")
    public String getHome(HttpSession httpSession){
        SessionManager.refreshSession(httpSession);
        return "index";
    }

}
