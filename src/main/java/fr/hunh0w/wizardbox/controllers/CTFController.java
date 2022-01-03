package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.server.WizardBoxClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class CTFController {

    @RequestMapping("/CTF")
    public String getTerminalPage(HttpSession session, Model model){
        if(session.getAttribute("account") == null)
            return "redirect:/";
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        boolean send = WizardBoxClient.sendMessage("TOKEN::"+ token);
        if(!send) return "error";
        model.addAttribute("token", token);
        return "CTFs/CTF_Terminal";
    }

}