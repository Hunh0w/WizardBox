package fr.hunh0w.wizardbox.internal.managers;

import fr.hunh0w.wizardbox.utils.VarUtils;

import java.util.regex.Pattern;

public class FiltersManager {

    public static final String ERROR_INVALID_CHAR = "Caractère(s) interdit";
    public static final String ERROR_EMPTY = "Champ vide";
    public static final String ERROR_INVALID_INPUT = "Champ invalide";

    public static final Pattern pattern = Pattern.compile("[^0-9a-zA-Z_]");
    public static final Pattern pattern_space = Pattern.compile("[^0-9a-zA-Z_ ]");

    public static String isValidEmail(String email){
        if(!VarUtils.isEmail(email))
            return ERROR_INVALID_INPUT;
        if(email.length() > 32)
            return "Dépasse 32 caractères";
        if(email.replaceAll(" ", "").isEmpty())
            return ERROR_EMPTY;
        return null;
    }

    public static String isValidNP(String np){
        if(pattern_space.matcher(np).find())
            return ERROR_INVALID_CHAR;
        if(np.length() > 20)
            return "Dépasse 20 caractères";
        if(np.replaceAll(" ", "").isEmpty())
            return ERROR_EMPTY;
        return null;
    }

    public static String isValidPseudo(String pseudo){
        if(pattern.matcher(pseudo).find())
            return ERROR_INVALID_CHAR;
        if(pseudo.length() > 24)
            return "Dépasse 24 caractères";
        if(pseudo.replaceAll(" ", "").isEmpty())
            return ERROR_EMPTY;
        return null;
    }

    public static String isValidPassword(String passwd, boolean newpass) {
        if(passwd.replaceAll(" ", "").isEmpty()) return ERROR_EMPTY;
        if(!newpass && passwd.length() < 8) return "Il faut au moins 8 caractères";
        return null;
    }



}
