package fr.hunh0w.wizardbox.internal.authentication;

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

}
