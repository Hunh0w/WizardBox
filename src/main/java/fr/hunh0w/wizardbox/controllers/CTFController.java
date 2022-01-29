package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.ctf.CTF;
import fr.hunh0w.wizardbox.ctf.CTFManager;
import fr.hunh0w.wizardbox.internal.managers.SQLManager;
import fr.hunh0w.wizardbox.internal.objects.Rank;
import fr.hunh0w.wizardbox.internal.session.managers.SessionManager;
import fr.hunh0w.wizardbox.internal.session.objects.Account;
import fr.hunh0w.wizardbox.server.WizardBoxClient;
import fr.hunh0w.wizardbox.utils.VarUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class CTFController {

    @RequestMapping("/CTF")
    public String getMainPage(HttpSession session, Model model){
        if(session.getAttribute("account") == null)
            return "redirect:/";


        model.addAttribute("ctfs", CTF.values());
        return "CTF_main";
    }

    @RequestMapping("/api/CTF/{id}")
    @ResponseBody
    public String getCTFInfo(HttpSession session, Model mod, @PathVariable String id){
        int numid = VarUtils.getInt(id);
        CTF ctf = CTFManager.getCTFById(numid);
        if(ctf != null && numid != -1){
            return ctf.toJson();
        }
        return "null";
    }

    @RequestMapping("/api/CTF/{id}/validate/{flag}")
    @ResponseBody
    public String getCTFInfo(HttpSession session, Model mod, @PathVariable String id, @PathVariable String flag){
        Account acc = (Account)session.getAttribute("account");
        if(acc == null) return "0";
        int numid = VarUtils.getInt(id);
        CTF ctf = CTFManager.getCTFById(numid);
        int winpoints = 0;
        if(ctf != null && numid != -1){
            if(ctf.getFlag().equals(flag)) {
                if(acc.addCtf_Flag(ctf.getId())){
                    winpoints = ctf.getWinpoints();
                    SQLManager.addPoints(acc, winpoints);
                    SessionManager.refreshSession(session, 0);
                }else {
                    winpoints = -2;
                }
            }
        }
        return winpoints+"";
    }


    @RequestMapping("/CTF/terminal")
    public String getTerminalPage(HttpSession session, Model model){
        if(session.getAttribute("account") == null)
            return "redirect:/";
        Account acc = (Account)session.getAttribute("account");
        if(acc.getRank() != Rank.ADMIN.getId())
            return "redirect:/";

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        boolean send = WizardBoxClient.sendMessage("TOKEN::"+ token);
        if(!send) return "error";
        model.addAttribute("token", token);
        return "CTFs/CTF_Terminal";
    }


}
