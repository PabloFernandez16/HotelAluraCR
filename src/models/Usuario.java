package models;

public class Usuario {
	
	private int id;
	private String user;
	private String pass;
	
	
	public Usuario(String user, String pass) {
		this.user = user;
		this.pass= pass;
	}
	
	
	public String getUser() {
		return user;
	}
	
	public String getPass() {
		return pass;
	}

}
