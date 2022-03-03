package fr.hunh0w.wizardbox.controllers.ctfs;

import fr.hunh0w.wizardbox.ctf.CTF;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Controller
public class L1Controller {

    private static final CTF ctf = CTF.L1;

    @GetMapping("/CTF/L1")
    public String getCTFL1(HttpSession session, Model model){
        if(session.getAttribute("account") == null)
            return "redirect:/login";
        return "CTFs/ctf_l1";
    }

    @PostMapping("/CTF/L1")
    public String postCTFL1(@RequestBody String req, HttpSession session, Model model){
        if(session.getAttribute("account") == null)
            return "redirect:/login";
        String message_ret = null;
        try{
            String message = req.split("&")[1].split("=")[1];
            if(message.replaceAll(" ", "").isEmpty()){
                message_ret = "Veuillez remplir le champ";
            }else {
                message_ret = "Bravo ! voici le flag: "+ctf.getFlag();
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            message_ret = "Veuillez remplir le champ";
        }
        model.addAttribute("message_return", message_ret);
        return "CTFs/ctf_l1";
    }

}
