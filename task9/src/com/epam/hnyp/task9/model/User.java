package com.epam.hnyp.task9.model;


public class User {
	private long id;
	private String name;
	private String lastName;
	private String password;
	private String login;
	private boolean receiveLetters;
	private Roles role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	
}
