package fr.hunh0w.wizardbox.internal.authentication.managers;

import fr.hunh0w.wizardbox.internal.authentication.crypto.CryptoManager;
import fr.hunh0w.wizardbox.internal.authentication.objects.LoginData;
import fr.hunh0w.wizardbox.internal.authentication.objects.RegisterData;
import fr.hunh0w.wizardbox.internal.authentication.sql.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SQLManager {

    public static List<String> getUsers(){
        List<String> list = new ArrayList<>();
        try{
            Connection con = Database.WIZARDBOX.getDatabase().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(rs.getString("pseudo")+" id: "+rs.getInt("id"));
            }
            ps.close();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    public static boolean UserExists(RegisterData user){
        boolean exists = false;
        try{
            Connection con = Database.WIZARDBOX.getDatabase().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=? OR pseudo=?;");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPseudo());
            ResultSet rs = ps.executeQuery();
            exists = rs.next();
            rs.close();
            ps.close();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return exists;
    }

    public static String registerUser(RegisterData regdata){
        String hash = CryptoManager.sha512_Hash(regdata.getPassword());
        if(hash == null || hash.replaceAll(" ", "").isEmpty())
            return "Erreur Interne [Hash]";

        try{
            Connection con = Database.WIZARDBOX.getDatabase().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(email, pseudo, nom, prenom, password) VALUES(?,?,?,?,?);");
            ps.setString(1, regdata.getEmail());
            ps.setString(2, regdata.getPseudo());
            ps.setString(3, regdata.getNom());
            ps.setString(4, regdata.getPrenom());
            ps.setString(5, hash);
            ps.executeUpdate();
            ps.close();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
            return "Erreur Interne [Exception]";
        }
        return null;
    }

    public static String loginUser(LoginData loginData){
        String resp = null;
        String password = CryptoManager.sha512_Hash(loginData.getPassword());
        try{
            Connection con = Database.WIZARDBOX.getDatabase().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id FROM users WHERE email=? AND password=?;");
            ps.setString(1, loginData.getEmail());
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) resp = AuthManager.OK;
            ps.close();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
            return "Erreur Interne [Exception]";
        }
        return resp;
    }

}
