package fr.hunh0w.wizardbox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SessionController {

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

}
