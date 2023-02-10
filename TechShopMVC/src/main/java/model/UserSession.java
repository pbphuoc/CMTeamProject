package model;

public class UserSession {
	private String id;
	private String email;
	private String fullname;
	private String phoneNumber;

	public UserSession(String id, String email, String fullname, String phoneNumber) {
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getFullname() {
		return fullname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String toString() {
		return "UserSession [id=" + id + ", email=" + email + ", fullname=" + fullname + ", phoneNumber=" + phoneNumber
				+ "]";
	}
}
