package fr.hunh0w.wizardbox.internal.session.objects;

import fr.hunh0w.wizardbox.ctf.CTF;
import fr.hunh0w.wizardbox.internal.managers.SQLManager;

import java.util.ArrayList;
import java.util.Date;

public class Account {

    protected final int id;
    protected final String email;
    protected final String pseudo;
    protected final String nom;
    protected final String prenom;
    protected final String activity;
    protected final int points;
    protected final int rank;
    protected ArrayList<String> ctf_flags = new ArrayList<>();
    protected ArrayList<String> chall_flags = new ArrayList<>();

    protected final Date init_date = new Date();

    public Account(int id, String email, String pseudo, String nom, String prenom, String activity, int points, int rank) {
        this.id = id;
        this.email = email;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.activity = activity;
        this.points = points;
        this.rank = rank;
    }

    public Date getInit_date(){ return init_date; }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getActivity() {
        return activity;
    }

    public int getPoints() {
        return points;
    }

    public int getRank() {
        return rank;
    }

    public ArrayList<String> getCtf_flags() {
        return ctf_flags;
    }

    public ArrayList<String> getChall_flags() {
        return chall_flags;
    }

    public int getLevel(){
        int level = (int) Math.floor((Math.abs(this.points) + 100) / 100);
        return level;
    }

    public int getPourcentPoints(){
        int xmin = (int) Math.floor(points/100) * 100;
        int pourcent = points - xmin;
        return pourcent;
    }

    public void setCtf_flags(ArrayList<String> ctf_flags) {
        this.ctf_flags = ctf_flags;
    }

    public void setChall_flags(ArrayList<String> chall_flags) {
        this.chall_flags = chall_flags;
    }

    public boolean addCtf_Flag(int id){
        String reg = "CTF_"+id;
        if(ctf_flags.contains(reg)) return false;
        ctf_flags.add(reg);
        SQLManager.updateCTF_Flags(this);
        return true;
    }

    public boolean addCtf_Chall(int id){
        String reg = "CHALL_"+id;
        if(chall_flags.contains(reg)) return false;
        chall_flags.add(reg);
        SQLManager.updateChall_Flags(this);
        return true;
    }

    public boolean hasValidateCTF(CTF ctf){
        for(String str : ctf_flags)
            if(str.equals("CTF_"+ctf.getId())) return true;
        return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", activity='" + activity + '\'' +
                ", points=" + points +
                ", rank=" + rank +
                ", init_date=" + init_date +
                '}';
    }
}
