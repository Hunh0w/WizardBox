package fr.hunh0w.wizardbox.internal.managers;

import fr.hunh0w.wizardbox.internal.authentication.crypto.CryptoManager;
import fr.hunh0w.wizardbox.internal.authentication.managers.AuthManager;
import fr.hunh0w.wizardbox.internal.authentication.objects.LoginData;
import fr.hunh0w.wizardbox.internal.authentication.objects.RegisterData;
import fr.hunh0w.wizardbox.internal.session.objects.Account;
import fr.hunh0w.wizardbox.internal.sql.Database;
import fr.hunh0w.wizardbox.utils.VarUtils;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static Account loginUser(LoginData loginData){
        Account resp = null;
        String password = CryptoManager.sha512_Hash(loginData.getPassword());
        try{
            Connection con = Database.WIZARDBOX.getDatabase().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?;");
            ps.setString(1, loginData.getEmail());
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            resp = getAccount(rs);
            ps.close();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return resp;
    }

    public static Account getAccount(String email){
        Account resp = null;
        try{
            Connection con = Database.WIZARDBOX.getDatabase().getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            resp = getAccount(rs);
            ps.close();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return resp;
    }

    public static Account getAccount(ResultSet rs){
        Account resp = null;
        try{
            if(rs.next()){
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String pseudo = rs.getString("pseudo");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String activity = rs.getString("activity");
                int points = rs.getInt("points");
                int rank = rs.getInt("rank");
                byte[] ba_ctf_flags = rs.getBytes("ctf_flags");
                byte[] ba_chall_flags = rs.getBytes("chall_flags");
                resp = new Account(id, email, pseudo, nom, prenom, activity, points, rank);
                if(ba_ctf_flags != null){
                    ArrayList<String> list = (ArrayList<String>) VarUtils.getObject(ba_ctf_flags);
                    if(list != null && !list.isEmpty()) resp.setCtf_flags(list);
                }
                if(ba_chall_flags != null){
                    ArrayList<String> list = (ArrayList<String>) VarUtils.getObject(ba_chall_flags);
                    if(list != null && !list.isEmpty()) resp.setChall_flags(list);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return resp;
    }

    public static void addCTF_Flags(Account account, String... flags) {
        try{
            ArrayList<String> list = account.getCtf_flags();
            if(list == null) list = new ArrayList<String>();
            else{
                ArrayList<String> torm = new ArrayList<>();
                for(String str1 : flags){
                    for(String str2 : list){
                        if(str1.equalsIgnoreCase(str2)) torm.add(str2);
                    }
                }
                for(String str : torm) list.remove(str);
            }
            list.addAll(Arrays.asList(flags));

            account.setCtf_flags(list);
            byte[] ba = VarUtils.toByteArray(list);
            if(ba == null) return;

            Connection con = Database.WIZARDBOX.getDatabase().getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE users SET ctf_flags=? WHERE email=?");

            ps.setBytes(1, ba);
            ps.setString(2, account.getEmail());

            ps.executeUpdate();

            ps.close();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void addChall_Flags(Account account, String... flags) {
        try{
            ArrayList<String> list = account.getChall_flags();
            if(list == null) list = new ArrayList<String>();
            else{
                ArrayList<String> torm = new ArrayList<>();
                for(String str1 : flags){
                    for(String str2 : list){
                        if(str1.equalsIgnoreCase(str2)) torm.add(str2);
                    }
                }
                for(String str : torm) list.remove(str);
            }
            list.addAll(Arrays.asList(flags));

            account.setChall_flags(list);
            byte[] ba = VarUtils.toByteArray(list);
            if(ba == null) return;

            Connection con = Database.WIZARDBOX.getDatabase().getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE users SET chall_flags=? WHERE email=?");

            ps.setBytes(1, ba);
            ps.setString(2, account.getEmail());

            ps.executeUpdate();

            ps.close();
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
