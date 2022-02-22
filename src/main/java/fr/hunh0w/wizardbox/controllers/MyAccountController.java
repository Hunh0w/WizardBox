package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.internal.authentication.crypto.CryptoManager;
import fr.hunh0w.wizardbox.internal.managers.SQLManager;
import fr.hunh0w.wizardbox.internal.session.objects.Account;
import fr.hunh0w.wizardbox.internal.session.objects.AccountData;
import fr.hunh0w.wizardbox.utils.VarUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/myaccount")
    public String postMyAccount(@ModelAttribute("accountdata") AccountData accountData, HttpSession session, Model model){
        if(session.getAttribute("account") == null)
            return "redirect:/";

        System.out.println(accountData.toString());

        Account account = (Account)session.getAttribute("account");
        AccountData errors = new AccountData();

        int id = accountData.getId();
        String pseudo = accountData.getPseudo();
        String email = accountData.getEmail();
        String newpassword = accountData.getNew_password();
        String oldpass = accountData.getPassword();

        boolean verification = SQLManager.checkUserPasswd(id, oldpass);
        if(!verification) errors.setPassword("Mot de passe incorrect");


        //TODO à sécuriser !
        if(!pseudo.isEmpty()){
            boolean pseudoexists = SQLManager.checkPseudoExists(pseudo);
            if(!pseudoexists && verification)
                SQLManager.changeString(pseudo, "pseudo", id);
            else if(!pseudo.isEmpty() && pseudoexists)
                errors.setPseudo("Ce pseudo est déjà enregistré");
        }

        if(!email.isEmpty()){
            boolean emailexists = SQLManager.checkEmailExists(email);
            if(!emailexists && verification) {
                if(VarUtils.isEmail(email))
                    SQLManager.changeString(email, "email", id);
                else
                    errors.setEmail("Addresse Email invalide");
            }else if(!email.isEmpty() && emailexists)
                errors.setEmail("Cette email est déjà enregistré");
        }

        if(!newpassword.isEmpty() && verification){
            SQLManager.changeString(CryptoManager.sha512_Hash(newpassword), "password", id);
        }

        model.addAttribute("error", errors);
        return "myaccount";
    }

}
