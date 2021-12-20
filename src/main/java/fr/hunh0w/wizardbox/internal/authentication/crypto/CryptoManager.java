package fr.hunh0w.wizardbox.internal.authentication.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoManager {

    public static String sha512_Hash(String sensible){
        return Hash(sensible, "SHA-512");
    }

    public static String sha256_Hash(String sensible){
        return Hash(sensible, "SHA-256");
    }

    public static String Hash(String sensible, String algo){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algo);
            byte[] bytes = md.digest(sensible.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
