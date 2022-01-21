package fr.hunh0w.wizardbox.controllers.ctfs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Controller
public class L1Controller {

    @GetMapping("/CTF/L1")
    public String getCTFL1(HttpSession session, Model model){
        return "CTFs/ctf_l1";
    }

    @PostMapping("/CTF/L1")
    public String postCTFL1(@RequestBody String req, Model model){
        System.out.println("Request: "+req);
        return "CTFs/ctf_l1";
    }
}
