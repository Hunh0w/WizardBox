package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.utils.VarUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String getAdmin(Model mod){
        mod.addAttribute("postvalues", new String[0]);
        return "admin";
    }

    @PostMapping("/admin")
    public String postAdmin(@RequestBody String body, Model mod){
        String[] params = VarUtils.formatPostData(body.split("&"));
        mod.addAttribute("postvalues", params);
        return "admin";
    }

}
