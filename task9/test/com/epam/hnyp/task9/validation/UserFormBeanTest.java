package com.epam.hnyp.task9.validation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserFormBeanTest {

	private UserFormBean testBean;
	
	private final String lastName = "LastName";
	private final String login = "valid@email.com";
	private final String name = "Name";
	private final String pwd = "12345";
	
	@Before
	public void prepare() {
		testBean = new UserFormBean();
		testBean.setLastName(lastName);
		testBean.setLogin(login);
		testBean.setName(name);
		testBean.setPassword(pwd);
		testBean.setRePassword(pwd);
	}
	
	@Test
	public void testAllFieldsOk() {
		assertTrue(testBean.validate().isEmpty());
	}
	
	@Test
	public void testLastNameEmpty() {
		testBean.setLastName(null);
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LASTNAME_ERROR_KEY));
		testBean.setLastName("");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LASTNAME_ERROR_KEY));
	}
	
	@Test
	public void testLastNameInvalid() {
		testBean.setLastName(" Aaaa");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LASTNAME_ERROR_KEY));
		testBean.setLastName("Aaaa&");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LASTNAME_ERROR_KEY));
	}
	
	@Test
	public void testNameEmpty() {
		testBean.setName(null);
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_NAME_ERROR_KEY));
		testBean.setName("");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_NAME_ERROR_KEY));
	}
	
	@Test
	public void testNameInvalid() {
		testBean.setName(" Aaaa");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_NAME_ERROR_KEY));
		testBean.setName("Aaaa&");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_NAME_ERROR_KEY));
	}
	
	@Test
	public void testLoginEmpty() {
		testBean.setLogin(null);
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LOGIN_ERROR_KEY));
		testBean.setLogin("");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LOGIN_ERROR_KEY));
	}
	
	@Test
	public void testLoginInvalid() {
		testBean.setLogin("Aaaa");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LOGIN_ERROR_KEY));
	}
	
	@Test
	public void testPasswordEmpty() {
		testBean.setPassword(null);
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_PASSWORD_ERROR_KEY));
		testBean.setPassword("");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_PASSWORD_ERROR_KEY));
	}
	
	@Test
	public void testPasswordMismatch() {
		testBean.setRePassword("");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_PASSWORD_ERROR_KEY));
	}
}
