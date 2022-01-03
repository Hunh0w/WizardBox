package fr.hunh0w.wizardbox.internal.authentication.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoManager {

    private static final String x = "0198YPD7Y927Y937@f3#32DBXW781Y3D";
    private static final String algo = "AES";


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


    /* CRYPTO */
    public static byte[] encrypt(byte[] Data) throws Exception {
        Key k = new SecretKeySpec(x.getBytes(), algo);
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.ENCRYPT_MODE, k);
        byte[] encVal = c.doFinal(Data);
        return encVal;
    }
    public static byte[] decrypt(byte[] encryptedData) throws Exception {
        Key k = new SecretKeySpec(x.getBytes(), algo);
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.DECRYPT_MODE, k);
        byte[] decValue = c.doFinal(encryptedData);
        return decValue;
    }

}
