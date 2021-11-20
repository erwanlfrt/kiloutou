package model.object.user;

public class Employee extends User {
	private String function;
	private String service;
	private int deskNumber;
	private Profil profil;

	public Employee(String name, String firstname, String address, String phoneNumber, String mail, String login,
			String password, String function, String service, int deskNumber, Profil profil) {
		super(name, firstname, address, phoneNumber, mail, login, password);
		this.function = function;
		this.service = service;
		this.deskNumber = deskNumber;
		this.profil = profil;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getDeskNumber() {
		return deskNumber;
	}

	public void setDeskNumber(int deskNumber) {
		this.deskNumber = deskNumber;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

}
