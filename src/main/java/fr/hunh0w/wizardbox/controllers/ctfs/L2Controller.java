package fr.hunh0w.wizardbox.controllers.ctfs;

import fr.hunh0w.wizardbox.ctf.CTF;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Controller
public class L2Controller {

    private static final CTF ctf = CTF.L2;

    @GetMapping("/CTF/L2")
    public String getCTFL1(HttpSession session, Model model){
        if(session.getAttribute("account") == null)
            return "redirect:/";
        return "CTFs/ctf_l2";
    }

    @PostMapping("/CTF/L2")
    public String postCTFL1(@RequestBody String req, HttpSession session, Model model){
        if(session.getAttribute("account") == null)
            return "redirect:/";
        String message_ret = null;
        try{
            String identifiant = req.split("&")[1].split("=")[1];
            String password = req.split("&")[2].split("=")[1];

            if(identifiant.replaceAll(" ", "").isEmpty() || password.replaceAll(" ", "").isEmpty()){
                message_ret = "Identifiant / Mot de passe Manquant";
            }else if(identifiant.equals("ADM1N83") && password.equals("WIZARD38673")){
                message_ret = "Bravo ! voici le flag: "+ctf.getFlag();
            }else {
                message_ret = "Identifiant / Mot de passe Incorrect !";
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            message_ret = "Identifiant / Mot de passe Manquant";
        }
        model.addAttribute("message_return", message_ret);
        return "CTFs/ctf_l2";
    }


}
