package Admin;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class AdminController {
	
	 private String username;
	    private String password;
	    private String role;
	    private String fname;
	    private String lname;
	    private String email;
	    private int phonenumber;
	    private int status;
	    private int uid;
	    private int rid;
	   
public String getUsername() {
			return username;
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

		public int getPhonenumber() {
			return phonenumber;
		}

		public void setPhonenumber(int phonenumber) {
			this.phonenumber = phonenumber;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public int getUid() {
			return uid;
		}

		public void setUid(int uid) {
			this.uid = uid;
		}

		public int getRid() {
			return rid;
		}

		public void setRid(int rid) {
			this.rid = rid;
		}

public String approveManager(String username, int rid)
{
	AdminDAO.updateStatus(username);
	AdminDAO.deleteRequest(rid);
	return "adminhome";
	
	
}

public String declineManager(int rid)
{
	 AdminDAO.deleteRequest(rid);
	return "adminhome";
}
public void logout() throws IOException {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
}
}
