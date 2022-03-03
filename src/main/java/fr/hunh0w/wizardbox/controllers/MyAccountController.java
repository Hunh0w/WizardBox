package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.internal.authentication.crypto.CryptoManager;
import fr.hunh0w.wizardbox.internal.managers.FiltersManager;
import fr.hunh0w.wizardbox.internal.managers.SQLManager;
import fr.hunh0w.wizardbox.internal.session.objects.Account;
import fr.hunh0w.wizardbox.internal.session.objects.AccountData;
import fr.hunh0w.wizardbox.utils.VarUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.Filter;
import javax.servlet.http.HttpSession;

@Controller
public class MyAccountController {

    @GetMapping("/myaccount")
    public String getMyAccount(HttpSession session, Model model){
        if(session.getAttribute("account") == null)
            return "redirect:/login";
        model.addAttribute("error", new AccountData());
        return "myaccount";
    }

    @PostMapping("/myaccount")
    public String postMyAccount(@ModelAttribute("accountdata") AccountData accountData, HttpSession session, Model model){
        if(session.getAttribute("account") == null)
            return "redirect:/login";

        AccountData errors = new AccountData();
        Account acc = (Account)session.getAttribute("account");

        int id = acc.getId();
        String pseudo = accountData.getPseudo();
        String email = accountData.getEmail();
        String newpassword = accountData.getNew_password();
        String oldpass = accountData.getPassword();

        boolean verification = SQLManager.checkUserPasswd(id, oldpass);
        if(!verification) errors.setPassword("Mot de passe incorrect");

        String pseudoerr = FiltersManager.isValidPseudo(pseudo);
        String emailerr = FiltersManager.isValidEmail(email);

        if(verification){
            if(pseudoerr == null) {
                boolean pseudoexists = SQLManager.checkPseudoExists(pseudo);
                if(!pseudoexists)
                    SQLManager.changeString(pseudo, "pseudo", id);
                else if(!pseudo.isEmpty() && pseudoexists)
                    pseudoerr = "Ce pseudo est déjà enregistré";
            }
            if(emailerr == null){
                boolean emailexists = SQLManager.checkEmailExists(email);
                if(!emailexists) {
                    SQLManager.changeString(email, "email", id);
                }else if(!email.isEmpty() && emailexists)
                    errors.setEmail("Cette email est déjà enregistré");
            }
        }

        errors.setPseudo(pseudoerr);
        errors.setEmail(emailerr);

        if(!newpassword.isEmpty() && verification){
            String newpasserr = FiltersManager.isValidPassword(newpassword, true);
            if(newpasserr == null)
                SQLManager.changeString(CryptoManager.sha512_Hash(newpassword), "password", id);
            errors.setNew_password(newpasserr);
        }

        if(!errors.hasErrors()) return "redirect:/";

        model.addAttribute("error", errors);
        return "myaccount";
    }

}
