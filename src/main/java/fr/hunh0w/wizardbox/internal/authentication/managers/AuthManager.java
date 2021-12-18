package fr.hunh0w.wizardbox.internal.authentication.managers;

import fr.hunh0w.wizardbox.internal.authentication.objects.LoginData;
import fr.hunh0w.wizardbox.internal.authentication.objects.RegisterData;
import fr.hunh0w.wizardbox.internal.authentication.sql.Database;
import fr.hunh0w.wizardbox.utils.VarUtils;

public class AuthManager {

    public static final String OK = "OK";

    public static void init(){
        Database.initAll();
    }

    public static String check_register(RegisterData regdata){
        if(regdata == null) return "Erreur Inconnue [RegisterData]";

        if(regdata.getNom().replaceAll(" ", "").isEmpty())
            return "Le nom est vide";
        if(regdata.getNom().length() > 20) return "Le nom dépasse 20 caractères";

        if(regdata.getPrenom().replaceAll(" ", "").isEmpty())
            return "Le prénom est vide";
        if(regdata.getPrenom().length() > 20) return "Le prénom dépasse 20 caractères";

        if(regdata.getPseudo().replaceAll(" ", "").isEmpty())
            return "Le pseudonyme est vide";
        if(regdata.getPseudo().length() > 24)
            return "Le pseudonyme dépasse 24 caractères";

        if(regdata.getEmail().replaceAll(" ", "").isEmpty())
            return "L'Email est vide";
        if(regdata.getEmail().length() > 32)
            return "L'Email dépasse 32 caractères";

        if(regdata.getPassword().replaceAll(" ", "").isEmpty())
            return "Le mot de passe est vide";
        if(regdata.getPassword().length() > 50)
            return "Le mot de passe dépasse 50 caractères";
        if(regdata.getPassword().length() < 8)
            return "Le mot de passe est trop court";

        if(SQLManager.UserExists(regdata))
            return "Email ou Pseudonyme déjà enregistré";

        return OK;
    }


    public static String check_login(LoginData loginData){
        if(loginData == null) return "Erreur Inconnue [LoginData]";

        if(loginData.getEmail().replaceAll(" ", "").isEmpty())
            return "L'Email est vide";
        if(!VarUtils.isEmail(loginData.getEmail()))
            return "Email Invalide";
        if(loginData.getEmail().length() > 32)
            return "L'Email dépasse 32 caractères";

        if(loginData.getPassword().replaceAll(" ", "").isEmpty())
            return "Le mot de passe est vide";

        String resp = SQLManager.loginUser(loginData);
        if(resp == null)
            return "Échec de l'Authentification";
        if(!resp.equals(OK))
            return resp;

        return OK;
    }

}
