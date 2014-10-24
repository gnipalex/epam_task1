package com.epam.hnyp.task9;

public class Constants {
	public static final String INIT_CAPCHAUID_SERVICE_KEY = "init:capchaUidService";
	public static final String INIT_CAPCHA_SERVICE_KEY = "init:capchaService";
	public static final String INIT_CAPCHA_LENGTH_KEY = "init:capchaLength";
	public static final String INIT_USER_SERVICE_KEY = "init:userService";
	
	public static final String REGISTER_FORM_NAME_PARAM = "name";
	public static final String REGISTER_FORM_LASTNAME_PARAM = "lastName";
	public static final String REGISTER_FORM_LOGIN_PARAM = "login";
	public static final String REGISTER_FORM_PWD_PARAM = "password";
	public static final String REGISTER_FORM_REPWD_PARAM = "rePassword";
	public static final String REGISTER_FORM_RECEIVELETTERS_PARAM = "receiveLetters";
	public static final String REGISTER_FORM_CAPCHA_PARAM = "capcha";
	
	public static final String USERBEAN_NAME_ERROR_KEY = "nameError";
	public static final String USERBEAN_LASTNAME_ERROR_KEY = "lastNameError";
	public static final String USERBEAN_PASSWORD_ERROR_KEY = "passwordError";
	public static final String USERBEAN_LOGIN_ERROR_KEY = "loginError";
	
	public static final String REGISTER_ERRORMESSAGES_KEY = "errorMessages";
	public static final String REGISTER_USERBEAN_KEY = "userFormBean";
	
	public static final String CAPCHA_ERROR_KEY = "capchaError";
	public static final String SESSION_CURRENT_CAPCHA_KEY = "currentCapcha";
	public static final int CAPCHA_PICTURE_WIDTH = 200;
	public static final int CAPCHA_PICTURE_HEIGHT = 100;
	public static final int CAPCHA_PICTURE_FONT_SZ = 40;
	
	public static final String REGISTER_POSTREDIRECT_USERBEAN_KEY = "registerUserFormBean";
	public static final String REGISTER_POSTREDIRECT_ERRORMESSAGES_KEY = "registerErrorMessages";
	
	public static final String VALIDATION_USER_TEXT_PATTERN = "^[a-zA-ZР-пр-џ_\\d-]+$";
	public static final String VALIDATION_USER_EMAIL_PATTERN = "^[\\w\\.\\d-_]+@[\\w\\.\\d-_]+\\.\\w{2,4}$";
}
