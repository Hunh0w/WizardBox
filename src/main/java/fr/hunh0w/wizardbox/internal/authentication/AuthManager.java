package fr.hunh0w.wizardbox.internal.authentication;

import fr.hunh0w.wizardbox.internal.authentication.sql.Database;

public class AuthManager {

    public static void init(){
        Database.initAll();
    }

}
