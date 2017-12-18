package com.login;
import java.io.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;

import java.io.IOException;

import javax.faces.application.FacesMessage;



@ManagedBean
@SessionScoped
public class UserBean {
	private int rid;
    public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}
	private String address;
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	private String username;
    private String password;
    private String role;
    private String fname;
    private String lname;
    private String email;
    private int pnumber;
    private int status;
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsername() {
        return username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPnumber() {
		return pnumber;
	}

	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}

	public void setUsername(String username) {
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
	
	
    public String validateUser() {
        boolean valid = LoginDAO.validateUser(username, password);
        if (valid == true) {
        	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uname",username);
            String role = LoginDAO.getRoleFromUserName(username);
            int uid = LoginDAO.getUidFromUserName(username);
            int status = LoginDAO.getStatusFromUserName(username);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid",uid);
            //System.out.println(uid);
            if(status==1 && role.equals("Manager")){
            	return "managerhome?faces-redirect=true";
            }
            if(role.equals("User")){
        			return "userhome?faces-redirect=true";
            }
            if(status==0)
            {
            	 return "requestpending?faces-redirect=true";
            }
            if(role.equals("admin")){
        
            	List<UserBean> users = LoginDAO.getRequests();
            	if(users.isEmpty()){
            		return "adminhome?message=no";
            	}else{
            	System.out.println(users.get(0).getFname());
            	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("users",users);
            	return "adminhome?faces-redirect=true";
            	}
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd. Please enter correct username and Password",""));
            return "index";
        }
		return "index";
		
    }
    
  /*  public String validateManager() {
        boolean valid = LoginDAO.validateManager(username, password);
        if (valid == true) {
        			return "managerhome?faces-redirect=true";
        		
        } else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect Username and Passowrd. Please enter correct username and Password",""));
            return "index";
        }
    }
    */// logout event, invalidate session
    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
}
