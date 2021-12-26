package fr.hunh0w.wizardbox.internal.authentication.managers;

import fr.hunh0w.wizardbox.internal.authentication.objects.LoginData;
import fr.hunh0w.wizardbox.internal.authentication.objects.RegisterData;
import fr.hunh0w.wizardbox.internal.managers.SQLManager;
import fr.hunh0w.wizardbox.internal.session.objects.Account;
import fr.hunh0w.wizardbox.internal.sql.Database;
import fr.hunh0w.wizardbox.utils.VarUtils;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

public class AuthManager {

    public static final String OK = "OK";

    public static void init(){
        Database.initAll();
    }

    public static final String ERROR_INVALID_CHAR = "Caractère(s) interdit";
    public static final String ERROR_EMPTY = "Champ vide";
    public static final String ERROR_INVALID_INPUT = "Champ invalide";

    public static RegisterData check_register(RegisterData regdata){
        Pattern pattern = Pattern.compile("[^0-9a-zA-Z_]");

        RegisterData errors = new RegisterData();
        if(regdata == null) {
            errors.setError("Erreur Inconnue [RegisterData]");
            return errors;
        }


        if(pattern.matcher(regdata.getNom()).find())
            errors.setNom(ERROR_INVALID_CHAR);
        if(regdata.getNom().length() > 20)
            errors.setNom("Dépasse 20 caractères");
        if(regdata.getNom().replaceAll(" ", "").isEmpty())
            errors.setNom(ERROR_EMPTY);



        if(pattern.matcher(regdata.getPrenom()).find())
            errors.setPrenom(ERROR_INVALID_CHAR);
        if(regdata.getPrenom().length() > 20) errors.setPrenom("Dépasse 20 caractères");
        if(regdata.getPrenom().replaceAll(" ", "").isEmpty())
            errors.setPrenom(ERROR_EMPTY);



        if(pattern.matcher(regdata.getPseudo()).find())
            errors.setPseudo(ERROR_INVALID_CHAR);
        if(regdata.getPseudo().length() > 24)
            errors.setPseudo("Dépasse 24 caractères");
        if(regdata.getPseudo().replaceAll(" ", "").isEmpty())
            errors.setPseudo(ERROR_EMPTY);



        if(regdata.getEmail().length() > 32)
            errors.setEmail("Dépasse 32 caractères");
        if(!VarUtils.isEmail(regdata.getEmail()))
            errors.setEmail(ERROR_INVALID_INPUT);
        if(regdata.getEmail().replaceAll(" ", "").isEmpty())
            errors.setEmail(ERROR_EMPTY);



        if(regdata.getPassword().length() < 8)
            errors.setPassword("Inférieur à 8 caractères");
        if(regdata.getPassword().length() > 50)
            errors.setPassword("Dépasse 50 caractères");
        if(regdata.getPassword().replaceAll(" ", "").isEmpty())
            errors.setPassword(ERROR_EMPTY);

        if(!errors.hasErrors()){
            if(SQLManager.UserExists(regdata))
                errors.setError("Email ou Pseudonyme déjà enregistré");
        }

        return errors;
    }


    public static LoginData check_login(LoginData loginData, HttpSession httpSession){
        Pattern pattern = Pattern.compile("[^0-9a-zA-Z_]");

        LoginData errors = new LoginData();
        if(loginData == null) {
            errors.setError("Erreur Inconnue [LoginData]");
            return errors;
        }


        if(!VarUtils.isEmail(loginData.getEmail()))
            errors.setEmail(ERROR_INVALID_INPUT);
        if(loginData.getEmail().length() > 32)
            errors.setEmail("Dépasse 32 caractères");
        if(loginData.getEmail().replaceAll(" ", "").isEmpty())
            errors.setEmail(ERROR_EMPTY);

        if(loginData.getPassword().replaceAll(" ", "").isEmpty())
            errors.setPassword(ERROR_EMPTY);

        if(!errors.hasErrors()){
            Account resp = SQLManager.loginUser(loginData);
            if(resp == null) errors.setError("Échec de l'Authentification");
            else httpSession.setAttribute("account", resp); // SESSION OPEN
        }

        return errors;
    }

}
