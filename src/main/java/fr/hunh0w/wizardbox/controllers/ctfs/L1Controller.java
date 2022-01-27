package fr.hunh0w.wizardbox.controllers.ctfs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.hunh0w.wizardbox.ctf.CTF;
import fr.hunh0w.wizardbox.ctf.CTFManager;
import fr.hunh0w.wizardbox.utils.VarUtils;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class L1Controller {

    @GetMapping("/CTF/L1")
    public String getCTFL1(HttpSession session, Model model){
        return "CTFs/ctf_l1";
    }

    @PostMapping("/CTF/L1")
    public String postCTFL1(@RequestBody String req, HttpSession session, Model model){
        String message_ret = null;
        try{
            String message = req.split("&")[1].split("=")[1];
            if(message.replaceAll(" ", "").isEmpty()){
                message_ret = "Veuillez remplir le champ";
            }else {
                message_ret = "Bravo ! voici le flag: {WbFl4gnv1lCyeN0}";
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            message_ret = "Veuillez remplir le champ";
        }
        model.addAttribute("message_return", message_ret);
        return "CTFs/ctf_l1";
    }

}
