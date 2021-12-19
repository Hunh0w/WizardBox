package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.internal.managers.CookieManager;
import fr.hunh0w.wizardbox.internal.managers.SessionManager;
import fr.hunh0w.wizardbox.internal.session.objects.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
