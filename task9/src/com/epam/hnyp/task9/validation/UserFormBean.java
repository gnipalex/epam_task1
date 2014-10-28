package com.epam.hnyp.task9.validation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.epam.hnyp.task9.Constants;
import com.epam.hnyp.task9.model.Roles;
import com.epam.hnyp.task9.model.User;

public class UserFormBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String USER_TEXT_PATTERN = "^[a-zA-ZР-пр-џ_\\d-]+$";
	public static final String USER_EMAIL_PATTERN = "^[\\w\\.\\d-_]+@[\\w\\.\\d-_]+\\.\\w{2,4}$";
	public static final String USERBEAN_NAME_ERROR_KEY = "nameError";
	public static final String USERBEAN_LASTNAME_ERROR_KEY = "lastNameError";
	public static final String USERBEAN_PASSWORD_ERROR_KEY = "passwordError";
	public static final String USERBEAN_LOGIN_ERROR_KEY = "loginError";
	
	private String name;
	private String lastName;
	private String password;
	private String rePassword;
	private String login;
	private String receiveLetters;
	
	public Map<String, String> validate() {
		Map<String, String> errorMap = new HashMap<String, String>();
		if (name == null || name.isEmpty()) {
			errorMap.put(USERBEAN_NAME_ERROR_KEY, "name must be specified");
		} else if (!name.matches(USER_TEXT_PATTERN)) {
			errorMap.put(USERBEAN_NAME_ERROR_KEY, "name should not contain whitespaces and special symbols");
		}
		
		if (lastName == null || lastName.isEmpty()) {
			errorMap.put(USERBEAN_LASTNAME_ERROR_KEY, "lastName must be specified");
		} else if (!lastName.matches(USER_TEXT_PATTERN)) {
			errorMap.put(USERBEAN_LASTNAME_ERROR_KEY, "last name should not contain whitespaces and special symbols");
		}
		
		if (password == null || password.isEmpty()) {
			errorMap.put(USERBEAN_PASSWORD_ERROR_KEY, "password must be specified");
		} else if (rePassword == null || rePassword.isEmpty() || !password.equals(rePassword)) {
			errorMap.put(USERBEAN_PASSWORD_ERROR_KEY, "passwords don't match");
		}
		
		if (login == null || login.isEmpty()) {
			errorMap.put(USERBEAN_LOGIN_ERROR_KEY, "login must be specified");
		} else if (!login.matches(USER_EMAIL_PATTERN)) {
			errorMap.put(USERBEAN_LOGIN_ERROR_KEY, "invalid email");
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
