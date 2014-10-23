package com.epam.hnyp.task9.validation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.epam.hnyp.task9.model.Roles;
import com.epam.hnyp.task9.model.User;

public class UserFormBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	public static final String NAME_ERROR_KEY = "nameError";
	private String lastName;
	public static final String LASTNAME_ERROR_KEY = "lastNameError";
	private String password;
	public static final String PASSWORD_ERROR_KEY = "passwordError";
	private String rePassword;
	private String login;
	public static final String LOGIN_ERROR_KEY = "loginError";
	private String receiveLetters;
	
	public Map<String, String> validate() {
		Map<String, String> errorMap = new HashMap<String, String>();
		if (name == null || name.isEmpty() || name.trim().isEmpty()) {
			errorMap.put("nameError", "name must be specified");
		}
		
		if (lastName == null || lastName.isEmpty() || lastName.trim().isEmpty()) {
			errorMap.put("lastNameError", "lastName must be specified");
		}
		
		if (password == null || password.isEmpty() || password.trim().isEmpty()) {
			errorMap.put("passwordError", "password must be specified");
		} else if (rePassword == null || rePassword.isEmpty() || !password.equals(rePassword)) {
			errorMap.put("passwordError", "passwords don't match");
		}
		
		if (login == null || login.isEmpty() || login.trim().isEmpty()) {
			errorMap.put("loginError", "login must be specified");
		}
		return errorMap;
	}
	
	public User buildUser() {
		User u = new User();
		u.setLastName(lastName);
		u.setLogin(login);
		u.setName(name);
		u.setPassword(password);
		u.setReceiveLetters(receiveLetters != null);
		u.setRole(Roles.CUSTOMER);
		return u;
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
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getReceiveLetters() {
		return receiveLetters;
	}
	public void setReceiveLetters(String receiveLetters) {
		this.receiveLetters = receiveLetters;
	}
}
