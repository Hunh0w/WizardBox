package fr.hunh0w.wizardbox.challenges;

public class ChallManager {

    public static synchronized Challenge getChallById(int id){
        for(Challenge chall : Challenge.values()){
            if(chall.getId() == id) return chall;
        }
        return null;
    }

}
