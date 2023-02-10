package entity;

import annotation.DBField;
import annotation.Table;

@Table(name = "user")
public class User {
	
	private String id = "";

	@DBField(name = "email", type = String.class)
	private String email = "";
	
	@DBField(name = "password", type = String.class)
	private String password = "";	
	
	@DBField(name = "fullname", type = String.class)
	private String name = "";
	
	@DBField(name = "phone_number", type = String.class)
	private String phoneNumber = "";
	
	@DBField(name = "salt", type = String.class)
	private String salt = "";	

	public User(String email, String password, String name, String phoneNumber) {		
		this.email = email;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public User(String id, String email, String password, String name, String phoneNumber, String salt) {		
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.salt = salt;
	}	

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public String getSalt() {
		return salt;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}
		
}
