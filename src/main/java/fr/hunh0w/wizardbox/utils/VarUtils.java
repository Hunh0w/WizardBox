package fr.hunh0w.wizardbox.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;

public class VarUtils {

    public static String[] formatPostData(String[] postdata){
        String[] ret = postdata;
        for(int i = 0; i < ret.length; i++){
            ret[i] = ret[i].split("=")[1];
            try {
                ret[i] = URLDecoder.decode(ret[i], "UTF-8");
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return ret;
    }

    public static boolean isEmail(String email){
        Pattern specialchars = Pattern.compile("[^0-9a-zA-Z@\\.]");
        Pattern validemail = Pattern.compile("^\\S+@\\S+\\.\\S+$");
        return (!specialchars.matcher(email).find() && validemail.matcher(email).find());
    }

    public static byte[] toByteArray(Object obj){
        byte[] ba = null;
        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            ba = bos.toByteArray();
            oos.close();
            bos.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return ba;
    }

    public static Object getObject(byte[] ba){
        Object obj = null;
        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(ba);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
            ois.close();
            bais.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return obj;
    }

    public static int getInt(String str){
        try{
            int i = Integer.parseInt(str);
            return i;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return -1;
    }

    public static String toBase64(String str){
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

}
