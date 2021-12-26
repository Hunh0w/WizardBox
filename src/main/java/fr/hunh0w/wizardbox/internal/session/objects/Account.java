package fr.hunh0w.wizardbox.internal.session.objects;

import java.util.ArrayList;
import java.util.Date;

public class Account {

    private final int id;
    private final String email;
    private final String pseudo;
    private final String nom;
    private final String prenom;
    private final String activity;
    private final int points;
    private final int rank;
    private ArrayList<String> ctf_flags = new ArrayList<>();
    private ArrayList<String> chall_flags = new ArrayList<>();

    private final Date init_date = new Date();

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
