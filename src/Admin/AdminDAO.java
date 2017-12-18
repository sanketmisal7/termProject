package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.login.DataConnect;

public class AdminDAO {
    public static void updateStatus(String username) {
        Connection con = null;
        PreparedStatement ps = null;
       // PreparedStatement ps1 = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("update users SET Status=1 where username = ?"); 
            ps.setString(1, username);
            ps.executeUpdate();
           
        }
        catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        } 
       }
    public static void deleteRequest(int rid) {
        Connection con = null;
        PreparedStatement ps = null;
       // PreparedStatement ps1 = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("delete from requests where rid = ? "); 
            ps.setInt(1, rid);
           ps.executeUpdate(); 
        }
        catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        } 
    }



}
