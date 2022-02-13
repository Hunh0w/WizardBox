package fr.hunh0w.wizardbox.internal.session.objects;

public class AccountData extends Account {

    private String error = null;
    private String passwordField = null;

    public AccountData(Account acc) {
        super(acc.getId(), acc.getEmail(), acc.getPseudo(), acc.getNom(), acc.getPrenom(), acc.getActivity(), acc.getPoints(), acc.getRank());
    }

    public AccountData(){
        super(-1, null, null, null, null, null, -1, -1);

    }

    public String getPassword(){
        return passwordField;
    }

    public void setPasswordField(String str){
        this.passwordField = str;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError(){ return error; }

    public boolean hasErrors(){
        return (error != null);
    }
}
