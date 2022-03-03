package fr.hunh0w.wizardbox.internal.session.objects;

public class AccountData {

    private String error = null;
    private String password = null;
    private String new_password = null;

    private int id;
    private String email;
    private String pseudo;
    private String nom;
    private String prenom;
    private String activity;
    private int points;
    private int rank;


    public AccountData(Account acc) {
        id = acc.getId();
        email = acc.getEmail();
        pseudo = acc.getPseudo();
        nom = acc.getNom();
        prenom = acc.getPrenom();
        activity = acc.getActivity();
        points = acc.getPoints();
        rank = acc.getRank();
    }

    public AccountData(){
        id = -1;
        email = null;
        pseudo = null;
        nom = null;
        prenom = null;
        activity = null;
        points = -1;
        rank = -1;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String str){
        this.password = str;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError(){ return error; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public boolean hasErrors(){
        return (error != null || email != null || pseudo != null || nom != null || prenom != null || password != null || new_password != null);
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "error='" + error + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", activity='" + activity + '\'' +
                ", points=" + points +
                ", rank=" + rank +
                '}';
    }
}
