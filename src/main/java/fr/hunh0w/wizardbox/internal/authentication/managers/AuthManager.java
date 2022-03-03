package fr.hunh0w.wizardbox.internal.authentication.managers;

import fr.hunh0w.wizardbox.internal.authentication.objects.LoginData;
import fr.hunh0w.wizardbox.internal.authentication.objects.RegisterData;
import fr.hunh0w.wizardbox.internal.managers.FiltersManager;
import fr.hunh0w.wizardbox.internal.managers.SQLManager;
import fr.hunh0w.wizardbox.internal.session.objects.Account;
import fr.hunh0w.wizardbox.internal.sql.Database;

import javax.servlet.http.HttpSession;

public class AuthManager {

    public static final String OK = "OK";

    public static void init(){
        Database.initAll();
    }

    public static RegisterData check_register(RegisterData regdata){

        RegisterData errors = new RegisterData();
        if(regdata == null) {
            errors.setError("Erreur Inconnue [RegisterData]");
            return errors;
        }

        errors.setNom(FiltersManager.isValidNP(regdata.getNom()));
        errors.setPrenom(FiltersManager.isValidNP(regdata.getPrenom()));
        errors.setPseudo(FiltersManager.isValidPseudo(regdata.getPseudo()));
        errors.setEmail(FiltersManager.isValidEmail(regdata.getEmail()));
        errors.setPassword(FiltersManager.isValidPassword(regdata.getPassword(), true));

        if(!errors.hasErrors()){
            if(SQLManager.UserExists(regdata))
                errors.setError("Email ou Pseudonyme déjà enregistré");
        }

        return errors;
    }


    public static LoginData check_login(LoginData loginData, HttpSession httpSession){

        LoginData errors = new LoginData();
        if(loginData == null) {
            errors.setError("Erreur Inconnue [LoginData]");
            return errors;
        }

        errors.setEmail(FiltersManager.isValidEmail(loginData.getEmail()));
        errors.setPassword(FiltersManager.isValidPassword(loginData.getPassword(), false));

        if(!errors.hasErrors()){
            Account resp = SQLManager.loginUser(loginData);
            if(resp == null) errors.setError("Échec de l'Authentification");
            else httpSession.setAttribute("account", resp); // SESSION OPEN
        }

        return errors;
    }

}
