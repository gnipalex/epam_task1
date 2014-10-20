package com.epam.hnyp.task9.model;

import java.util.Collection;
import java.util.HashSet;

public class User {
	private long id;
	private String name;
	private String lastName;
	private String password;
	private String login;
	
	private Collection<Distribution> distributions = new HashSet<>();

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

	public Collection<Distribution> getDistributions() {
		return distributions;
	}

	public void setDistributions(Collection<Distribution> distributions) {
		this.distributions = distributions;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
