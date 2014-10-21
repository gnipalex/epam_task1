package com.epam.hnyp.task9.validation;

public class UserFormBean {
	private String name;
	private String errorName;
	private String lastName;
	private String errorLastName;
	private String pwd;
	private String errorPwd;
	private String rePwd;
	private String login;
	private String errorLogin;
	private String receiveLetters;
	
	public boolean validate() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getErrorLastName() {
		return errorLastName;
	}

	public void setErrorLastName(String errorLastName) {
		this.errorLastName = errorLastName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getErrorPwd() {
		return errorPwd;
	}

	public void setErrorPwd(String errorPwd) {
		this.errorPwd = errorPwd;
	}

	public String getRePwd() {
		return rePwd;
	}

	public void setRePwd(String rePwd) {
		this.rePwd = rePwd;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getErrorLogin() {
		return errorLogin;
	}

	public void setErrorLogin(String errorLogin) {
		this.errorLogin = errorLogin;
	}

	public String getReceiveLetters() {
		return receiveLetters;
	}

	public void setReceiveLetters(String receiveLetters) {
		this.receiveLetters = receiveLetters;
	}

}
