package com.epam.hnyp.shop.form;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epam.hnyp.shop.form.UserFormBean;

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
		testBean.setLastName("");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LASTNAME_ERROR_KEY));
	}
	
	@Test
	public void testLastNameNull() {
		testBean.setLastName(null);
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LASTNAME_ERROR_KEY));
	}
	
	@Test
	public void testLastNameInvalidChars() {
		testBean.setLastName("Aaaa&");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LASTNAME_ERROR_KEY));
	}
	
	@Test
	public void testLastNameWhitespaces() {
		testBean.setLastName(" Aaaa");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LASTNAME_ERROR_KEY));
	}
	
	@Test
	public void testNameEmpty() {
		testBean.setName("");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_NAME_ERROR_KEY));
	}
	
	@Test
	public void testNameNull() {
		testBean.setName(null);
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_NAME_ERROR_KEY));
	}
	
	@Test
	public void testNameWhitespaces() {
		testBean.setName(" Aaaa");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_NAME_ERROR_KEY));
	}
	
	@Test
	public void testNameInvalidChars() {
		testBean.setName("Aaaa&");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_NAME_ERROR_KEY));
	}
	
	@Test
	public void testLoginEmpty() {
		testBean.setLogin("");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LOGIN_ERROR_KEY));
	}
	
	@Test
	public void testLoginNull() {
		testBean.setLogin(null);
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LOGIN_ERROR_KEY));
	}
	
	@Test
	public void testLoginInvalidEmail() {
		testBean.setLogin("Aaaa@a");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_LOGIN_ERROR_KEY));
	}
	
	@Test
	public void testPasswordNull() {
		testBean.setPassword(null);
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_PASSWORD_ERROR_KEY));
	}
	
	@Test
	public void testPasswordEmpty() {
		testBean.setPassword("");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_PASSWORD_ERROR_KEY));
	}
	
	@Test
	public void testPasswordMismatch() {
		testBean.setRePassword("");
		assertTrue(testBean.validate().containsKey(UserFormBean.USERBEAN_PASSWORD_ERROR_KEY));
	}
}
