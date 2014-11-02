package com.epam.hnyp.task9.model;


public class User {
	private int id;
	private String name;
	private String lastName;
	private String password;
	private String login;
	private boolean receiveLetters;
	private Roles role;
	private String avatarFile;
	
	public User() {
	}
	
	public User(String name, String lastName, String password, String login,
			boolean receiveLetters, Roles role) {
		this(name, lastName, password, login, receiveLetters, role, null);
	}
	
	public User(String name, String lastName, String password,
			String login, boolean receiveLetters, Roles role, String avatarFile) {
		this.name = name;
		this.lastName = lastName;
		this.password = password;
		this.login = login;
		this.receiveLetters = receiveLetters;
		this.role = role;
		this.avatarFile = avatarFile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isReceiveLetters() {
		return receiveLetters;
	}

	public void setReceiveLetters(boolean receiveLetters) {
		this.receiveLetters = receiveLetters;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(String avatarFile) {
		this.avatarFile = avatarFile;
	}
	
}
