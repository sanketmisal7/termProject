package com.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

public class LoginDAO {


    public static boolean validateUser(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
       // PreparedStatement ps1 = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("select * from users where username = ? and password = ?"); 
            ps.setString(1, username);
            ps.setString(2, password);
                     
       
           
            
            ResultSet rs = ps.executeQuery();
           
            if (rs.next()) {
            		
                
                return true;
            }
           
        
        }
        catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
        	
        }
        return false;
    }

	public static String getRoleFromUserName(String username) {
		Connection con = null;
        PreparedStatement ps = null;
        String role = "";
       // PreparedStatement ps1 = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("select role from users where username = ? "); 
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next()) {
            	
                role = rs.getString("role");             
                return role;
            }
           
        
        }
        catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        }
		// TODO Auto-generated method stub
		return null;
	}

	public static List<UserBean> getRequests() {
		// TODO Auto-generated method stub
		
		Connection con = null;
        PreparedStatement ps = null;
        String role = "";
        List<UserBean> users = new ArrayList<UserBean>();
        UserBean user ;
       // PreparedStatement ps1 = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("select R.*, U.* from requests R , users U where R.uid=U.uid;"); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	user = new UserBean();
            	user.setRid(rs.getInt("rid"));
            	user.setUsername(rs.getString("username"));
            	user.setFname(rs.getString("firstname"));
            	user.setLname(rs.getString("lastname"));
            	user.setEmail(rs.getString("email"));
                users.add(user);
            }
           return users;
        
        }
        catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        }
		// TODO Auto-generated method stub
		return null;
	}

	public static int getUidFromUserName(String username) {
		Connection con = null;
        PreparedStatement ps = null;
        int uid=0;
       // PreparedStatement ps1 = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("select uid from users where username = ? "); 
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next()) {
            	
                uid = rs.getInt("uid");           
                DataConnect.close(con);
                return uid;
            }
        }
            catch (SQLException ex) {
                System.out.println("Login error -->" + ex.getMessage());
            }
    		// TODO Auto-generated method stub
    		return uid;
    	


        
        }

	
	public static int getStatusFromUserName(String username) {
		Connection con = null;
        PreparedStatement ps = null;
        int status=0;
       // PreparedStatement ps1 = null;
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("select status from users where username = ? "); 
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next()) {
            	
                status = rs.getInt("status");           
                return status;
            }
           
        
        }
        catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        }
		// TODO Auto-generated method stub
		return status;
	}


	}


