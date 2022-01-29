package fr.hunh0w.wizardbox.internal.session.managers;

import fr.hunh0w.wizardbox.internal.managers.SQLManager;
import fr.hunh0w.wizardbox.internal.session.objects.Account;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;

public class SessionManager {

    public static final int refresh_cooldown = 3; // seconds

    public static void refreshSession(HttpSession httpSession, int cooldown){
        if(httpSession.getAttribute("account") == null) return;
        Account current = (Account) httpSession.getAttribute("account");

        Date now = new Date();
        now.setSeconds(now.getSeconds() - cooldown);
        Date init_date = current.getInit_date();
        if(init_date.getTime() >= now.getTime()) return;

        String email = current.getEmail();
        if(email == null) throw new NullPointerException("Email is null [Account]");
        Account acc = SQLManager.getAccount(email);
        if(acc == null) throw new NullPointerException("new Account is null [Refresh]");

        httpSession.setAttribute("account", acc);
    }

}
