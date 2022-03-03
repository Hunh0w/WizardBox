package fr.hunh0w.wizardbox.controllers;

import fr.hunh0w.wizardbox.challenges.ChallManager;
import fr.hunh0w.wizardbox.challenges.Challenge;
import fr.hunh0w.wizardbox.internal.managers.SQLManager;
import fr.hunh0w.wizardbox.internal.session.managers.SessionManager;
import fr.hunh0w.wizardbox.internal.session.objects.Account;
import fr.hunh0w.wizardbox.utils.VarUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ChallController {

    private static final Path chall_folder = Paths.get("challenges");

    @RequestMapping("/challenges")
    public String getMainPage(HttpSession session, Model model){
        if(session.getAttribute("account") == null)
            return "redirect:/login";
        model.addAttribute("challs", Challenge.values());
        return "CHALL_main";
    }

    @RequestMapping("/api/challenges/{id}")
    @ResponseBody
    public String getChallInfo(HttpSession session, Model mod, @PathVariable String id){
        if(session.getAttribute("account") == null)
            return "redirect:/login";
        int numid = VarUtils.getInt(id);
        Challenge chall = ChallManager.getChallById(numid);
        if(chall != null && numid != -1){
            return chall.toJson();
        }
        return "null";
    }

    @RequestMapping("/api/challenges/{id}/validate/{flag}")
    @ResponseBody
    public String getCTFInfo(HttpSession session, Model mod, @PathVariable String id, @PathVariable String flag){
        Account acc = (Account)session.getAttribute("account");
        if(acc == null) return "0";
        int numid = VarUtils.getInt(id);
        Challenge challenge = ChallManager.getChallById(numid);
        int winpoints = 0;
        if(challenge != null && numid != -1){
            if(challenge.getFlag().equals(flag)) {
                if(acc.addCtf_Chall(challenge.getId())){
                    winpoints = challenge.getWinpoints();
                    SQLManager.addPoints(acc, winpoints);
                    SessionManager.refreshSession(session, 0);
                }else {
                    winpoints = -2;
                }
            }
        }
        return winpoints+"";
    }

    @RequestMapping("/challenges/download/{id}")
    public void downloadChall(HttpSession session, HttpServletRequest request, HttpServletResponse resp, @PathVariable("id") String id){
        if(session.getAttribute("account") == null)
            return;
        int numid = VarUtils.getInt(id);
        Challenge chall = ChallManager.getChallById(numid);
        if(Files.exists(chall_folder)){
            Path file = Paths.get(chall_folder.toFile().getAbsolutePath(), chall.getFilename());
            if(Files.exists(file)){
                resp.setContentType("application/octet-stream");
                resp.addHeader("Content-Disposition", "attachment; filename="+file.toFile().getName());
                try{
                    Files.copy(file, resp.getOutputStream());
                    resp.getOutputStream().flush();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }else {
            try {
                Files.createDirectory(chall_folder);
                System.out.println("Directory "+chall_folder.toFile().getAbsolutePath()+" created !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
