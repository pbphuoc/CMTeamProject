package entity;

public class User {
	private String id;
	private String email;
//	private String password;
	private String name;
	private String phoneNumber;
	
//	public User(String email, String name, String phoneNumber) {
//		this.email = email;		
//		this.name = name;
//		this.phoneNumber = phoneNumber;
//	}
	
	public User(String id, String email, String name, String phoneNumber) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
		
	public String getId() {
		return id;
	}

//	public void setPassword(String password) {
//		this.password = password;
//	}

	public String getEmail() {
		return email;
	}

//	public String getPassword() {
//		return password;
//	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
}
