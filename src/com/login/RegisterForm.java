package com.login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class RegisterForm {
	String fname,lname,address,email,phonenumber,password,username,role;
	Connection con = null;
    PreparedStatement ps = null;

	int Id=0;
	public String registration() {
		
		try {
			
		if (role.equals("User"))
		{
			 con = DataConnect.getConnection();
			Class.forName("com.mysql.jdbc.Driver");
            
            PreparedStatement ps = con
                    .prepareStatement("insert into users(firstname,lastname,address,phonenumber,email,username,password, role, status) values(?,?,?,?,?,?,?,?,1)");
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, address);
            ps.setString(4, phonenumber);
            ps.setString(5, email);
            
           ps.setString(6, username);
          ps.setString(7, password);
          ps.setString(8, role);
        //  PreparedStatement ps1=con.prepareStatement("insert into user(username,password) values (?,?);");
         // ps1.setString(1, username);
         // ps1.setString(2, password);
            int i=ps.executeUpdate();
           // int i1=ps1.executeUpdate();
            	/*	 FacesContext facesContext=FacesContext.getCurrentInstance();
            		 Flash flash = facesContext.getExternalContext().getFlash();
            		 flash.setKeepMessages(true);
            		 flash.setRedirect(true);
            		 facesContext.addMessage("cbutton", new javax.faces.application.FacesMessage("Successfully registered")); 
            	*/
           return "index";
		}
		if (role.equals("Manager"))
		{
			con = DataConnect.getConnection();
			Class.forName("com.mysql.jdbc.Driver");
            
            PreparedStatement ps = con
                    .prepareStatement("insert into users(firstname,lastname,address,phonenumber,email,username,password, role,status) values(?,?,?,?,?,?,?,?,0)");
            
            
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, address);
            ps.setString(4, phonenumber);
            ps.setString(5, email);
            
           ps.setString(6, username);
          ps.setString(7, password);
          ps.setString(8, role);
        //  PreparedStatement ps1=con.prepareStatement("insert into user(username,password) values (?,?);");
         // ps1.setString(1, username);
         // ps1.setString(2, password);
          ps.executeUpdate();
          // int i1=ps1.executeUpdate();
            	/*	 FacesContext facesContext=FacesContext.getCurrentInstance();
            		 Flash flash = facesContext.getExternalContext().getFlash();
            		 flash.setKeepMessages(true);
            		 flash.setRedirect(true);
            		 facesContext.addMessage("cbutton", new javax.faces.application.FacesMessage("Successfully registered")); 
            	*/
            PreparedStatement ps0 = con
                    .prepareStatement("select uid from users where username= ?");
            ps0.setString(1, username);
            ResultSet rs = ps0.executeQuery();
            if (rs.next())
            {
            	PreparedStatement ps1 = con
                        .prepareStatement("insert into requests(uid) values(?)");
                ps1.setInt(1, rs.getInt("uid"));
                
                ps1.execute();
            }
            return "requestpending";
            }
		
        } catch (Exception e2) {
            System.out.println(e2);
        }
		return "failed";
	}
	public String getProfile()
	{
		Connection con = null;
        PreparedStatement ps = null;
        
       // PreparedStatement ps1 = null;
        try {
        	String username = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uname").toString();
        	
        	con = DataConnect.getConnection();
            ps = con.prepareStatement("select * from users where username = ? "); 
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next()) {
            	UserBean user = new UserBean();
            	user.setUsername(rs.getString("username"));
            	user.setPassword(rs.getString("Password"));
            	user.setFname(rs.getString("firstname"));
            	user.setLname(rs.getString("lastname"));
            	user.setPnumber(rs.getInt("phonenumber"));
            	user.setEmail(rs.getString("email"));
            	user.setAddress(rs.getString("address"));
            	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("updatedUser",user);
            	
            	
            return "updateUser";	
            	
                             
                
            }
           
        
        }
        catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        }
		// TODO Auto-generated method stub
		return null;
	}


	
	
public String update() {
		
		try {
			
			 con = DataConnect.getConnection();
			Class.forName("com.mysql.jdbc.Driver");
			int uid = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uid");
			UserBean user = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("updatedUser");
			
            PreparedStatement ps = con
                    .prepareStatement("update users SET firstname = ?,lastname = ?,address = ?,phonenumber = ?,email = ?,username = ?,password = ? where uid = ?");
            ps.setString(1, user.getFname());
            ps.setString(2, user.getLname());
            ps.setString(3, user.getAddress());
            ps.setInt(4, user.getPnumber());
            ps.setString(5, user.getEmail());
            
           ps.setString(6, user.getUsername());
          ps.setString(7, user.getPassword());
          ps.setInt(8, uid);
          ps.executeUpdate();
        //  PreparedStatement ps1=con.prepareStatement("insert into user(username,password) values (?,?);");
         // ps1.setString(1, username);
         // ps1.setString(2, password);
                    // int i1=ps1.executeUpdate();
            	/*	 FacesContext facesContext=FacesContext.getCurrentInstance();
            		 Flash flash = facesContext.getExternalContext().getFlash();
            		 flash.setKeepMessages(true);
            		 flash.setRedirect(true);
            		 facesContext.addMessage("cbutton", new javax.faces.application.FacesMessage("Successfully registered")); 
            	*/
        	
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		return "updatedUser";
}	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getPhNo()
	{
		return phonenumber;
	}
	public void setPhNo(String PhNo)
	{
		this.phonenumber = PhNo;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}