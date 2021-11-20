package model.object.user;

public class User {
	protected String name;
	protected String firstname;
	protected String address;
	protected String phoneNumber;
	protected String mail;
	protected String login;
	protected String password;
	protected boolean isReal;

	public User(String name, String firstname, String address, String phoneNumber, String mail, String login,
			String password) {
		this.name = name;
		this.firstname = firstname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.login = login;
		this.password = password;
		this.isReal = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setReal(boolean isReal) {
		this.isReal = isReal;
	}

	public boolean isReal() {
		return this.isReal;
	}

}
