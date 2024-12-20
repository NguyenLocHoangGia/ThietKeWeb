package model;

public class User {
	private int id;
	private String username;
	private String password;
	private String name;
	private String position;
	
	
	public User() {
		super();
	}


	public User(int id, String username, String password, String name, String position) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.position = position;
	}
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", position="
				+ position + "]";
	}
	
}
