package fr.hunh0w.wizardbox.utils;

import java.net.URLDecoder;

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

}