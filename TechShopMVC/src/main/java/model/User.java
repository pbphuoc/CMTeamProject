package model;

public class User {
	private String email;
	private String password;
	private String fullname;
	private String phoneNumber;
	
	public User(String email, String fullname, String phoneNumber) {
		this.email = email;
		this.fullname = fullname;
		this.phoneNumber = phoneNumber;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getFullname() {
		return fullname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
}
