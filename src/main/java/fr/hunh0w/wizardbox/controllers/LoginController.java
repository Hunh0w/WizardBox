package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.utils.VarUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginController {

    private String formdata = null;

    /* LOGIN */
    @GetMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("formdata", formdata);
        formdata = null;
        return "login";
    }

    @PostMapping("/login")
    public String login_postBody(@RequestBody String fullName) {
        String[] params = fullName.split("&");
        VarUtils.formatPostData(params);
        System.out.println("Login POST Data: "+params[0]+" "+params[1]);
        formdata = params[0]+" "+params[1];
        return "login";
    }

}
