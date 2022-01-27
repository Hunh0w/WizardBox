package fr.hunh0w.wizardbox.ctf;

public class CTFManager {

    public static synchronized CTF getCTFById(int id){
        for(CTF ctf : CTF.values()){
            if(ctf.getId() == id) return ctf;
        }
        return null;
    }

}
