package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.internal.session.objects.AccountData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MyAccountController {

    @GetMapping("/myaccount")
    public String getMyAccount(HttpSession session, Model model){
        if(session.getAttribute("account") == null)
            return "redirect:/";
        model.addAttribute("error", new AccountData());
        return "myaccount";
    }

}
