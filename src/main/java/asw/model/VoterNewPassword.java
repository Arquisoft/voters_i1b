package asw.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "voter")
public class VoterNewPassword {
	
	 private String password;
	 private String newPassword;
	 private String email;
	 
	 @XmlElement
	    public String getEmail() {
	        return email;
	    }
	 @XmlElement
	    public String getPassword() {
	        return password;
	    }
	 @XmlElement
	    public String getNewPassword() {
	        return newPassword;
	    }
	 
	public void setPassword(String password) {
		this.password = password;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	 

}
