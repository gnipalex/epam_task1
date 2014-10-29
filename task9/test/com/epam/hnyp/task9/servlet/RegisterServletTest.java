package com.epam.hnyp.task9.servlet;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.epam.hnyp.task9.capcha.Capcha;
import com.epam.hnyp.task9.capcha.provider.AbstractCapchaProvider;
import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.service.UserService;
import com.epam.hnyp.task9.validation.UserFormBean;

public class RegisterServletTest {

	private final String fakeContextPath = "fakeContextPath";
	
	private HttpSession mockSession;
	private HttpServletRequest mockRequest;
	private RequestDispatcher mockDispatcher;
	private HttpServletResponse mockResponse;
	private AbstractCapchaProvider mockCapchaProvider;
	private UserService mockUserService;
	private ServletContext mockServletContext;
	
	private RegisterServlet testServlet = new RegisterServlet();
	
	@Before
	public void prepare() {
		mockSession = Mockito.mock(HttpSession.class);
		
		mockDispatcher = Mockito.mock(RequestDispatcher.class);
		mockRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
		Mockito.when(mockRequest.getRequestDispatcher(RegisterServlet.REGISTER_JSP_URL))
			.thenReturn(mockDispatcher);
		
		mockResponse = Mockito.mock(HttpServletResponse.class);
		mockCapchaProvider = Mockito.mock(AbstractCapchaProvider.class);
		mockUserService = Mockito.mock(UserService.class);
		
		mockServletContext = Mockito.mock(ServletContext.class);
		Mockito.when(mockServletContext.getContextPath()).thenReturn(fakeContextPath);
		Mockito.when(mockRequest.getServletContext()).thenReturn(mockServletContext);
		
		testServlet.setCapchaProvider(mockCapchaProvider);
		testServlet.setUserService(mockUserService);
	}
	
	@Test
	public void testDoGetClearCapcha() {
		try {
			testServlet.doGet(mockRequest, mockResponse);
		} catch (Exception ex) {
			fail("Unexpected exception " + ex.getClass().getName());
		}
		Mockito.verify(mockCapchaProvider).clearAllExpiredCapcha(mockRequest);
	}

	@Test
	public void testDoGetSaveCapcha() {
		try {
			testServlet.doGet(mockRequest, mockResponse);
		} catch (Exception ex) {
			fail("Unexpected exception " + ex.getClass().getName());
		}
		Mockito.verify(mockCapchaProvider).saveCapcha(Mockito.any(HttpServletRequest.class),
				Mockito.any(HttpServletResponse.class), Mockito.any(Capcha.class));
	}
	
	@Test
	public void testDoGetReadConversationScope() {
		final Object fakeErrorMessages = new Object(); 
		final Object fakeUserBean = new Object();
		
		Map<String, Object> conversationMap = new HashMap<>();
		conversationMap.put(RegisterServlet.CONVSCOPE_ERRORMESSAGES_KEY, fakeErrorMessages);
		conversationMap.put(RegisterServlet.CONVSCOPE_USERBEAN_KEY, fakeUserBean);
		
		//ArgumentCaptor<Object> argCaptor = ArgumentCaptor.forClass(Object.class);
		//Mockito.when(mockRequest.setAttribute(RegisterServlet.ERRORMESSAGES_KEY, argCaptor.capture()))
		
		Mockito.when(mockSession.getAttribute(RegisterServlet.POSTREDIRECT_REGISTER_CONVSCOPE_KEY))
			.thenReturn(conversationMap);
		try {
			testServlet.doGet(mockRequest, mockResponse);
		} catch (Exception ex) {
			fail("Unexpected exception " + ex.getClass().getName());
		}
		
		Mockito.verify(mockRequest).setAttribute(RegisterServlet.ERRORMESSAGES_KEY, fakeErrorMessages);
		Mockito.verify(mockRequest).setAttribute(RegisterServlet.USERBEAN_KEY, fakeUserBean);
	}
	
	@Test
	public void testDoGetDeleteConversationScope() {
		try {
			testServlet.doGet(mockRequest, mockResponse);
		} catch (Exception ex) {
			fail("Unexpected exception " + ex.getClass().getName());
		}
		Mockito.verify(mockSession).removeAttribute(RegisterServlet.POSTREDIRECT_REGISTER_CONVSCOPE_KEY);
	}
	
	@Test
	public void testDoPostSuccessRegistration() {
		final String fakeStringParam = "AAA";
		final String fakeEmailParam = "1@ya.ru";
		final String fakeCapcha = "12345";
		
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_CAPCHA_PARAM)).thenReturn(fakeCapcha);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_LASTNAME_PARAM)).thenReturn(fakeStringParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_LOGIN_PARAM)).thenReturn(fakeEmailParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_NAME_PARAM)).thenReturn(fakeStringParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_PWD_PARAM)).thenReturn(fakeStringParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_RECEIVELETTERS_PARAM)).thenReturn(fakeStringParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_REPWD_PARAM)).thenReturn(fakeStringParam);
		
		Capcha mockCapcha = Mockito.mock(Capcha.class);
		Mockito.when(mockCapcha.getCapcha()).thenReturn(fakeCapcha);
		try {
			Mockito.when(mockCapchaProvider.getCapcha(mockRequest)).thenReturn(mockCapcha);
		} catch (Exception e) {}
		Mockito.when(mockCapchaProvider.isCapchaExpired(mockCapcha)).thenReturn(false);
		
		Mockito.when(mockUserService.userExists(fakeEmailParam)).thenReturn(false);
		
		try {
			testServlet.doPost(mockRequest, mockResponse);
		} catch (Exception ex) {
			fail("Unexpected exception " + ex.getClass().getName());
		}
		
		Mockito.verify(mockUserService).add(Mockito.any(User.class));
		try {
			Mockito.verify(mockResponse).sendRedirect(fakeContextPath + RegisterServlet.REDIRECT_REGISTER_SUCCESS);
		} catch (IOException e) {}
	}
	
	@Test
	public void testDoPostRegisterFailedCapchaExpired() {
		final String fakeStringParam = "AAA";
		final String fakeEmailParam = "1@ya.ru";
		final String fakeCapcha = "12345";
		
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_CAPCHA_PARAM)).thenReturn(fakeCapcha);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_LASTNAME_PARAM)).thenReturn(fakeStringParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_LOGIN_PARAM)).thenReturn(fakeEmailParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_NAME_PARAM)).thenReturn(fakeStringParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_PWD_PARAM)).thenReturn(fakeStringParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_RECEIVELETTERS_PARAM)).thenReturn(fakeStringParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_REPWD_PARAM)).thenReturn(fakeStringParam);
		
		Capcha mockCapcha = Mockito.mock(Capcha.class);
		Mockito.when(mockCapcha.getCapcha()).thenReturn(fakeCapcha);
		try {
			Mockito.when(mockCapchaProvider.getCapcha(mockRequest)).thenReturn(mockCapcha);
		} catch (Exception e) {}
		Mockito.when(mockCapchaProvider.isCapchaExpired(mockCapcha)).thenReturn(true);
		
		Mockito.when(mockUserService.userExists(fakeEmailParam)).thenReturn(false);
		
		try {
			testServlet.doPost(mockRequest, mockResponse);
		} catch (Exception ex) {
			fail("Unexpected exception " + ex.getClass().getName());
		}
		
		ArgumentCaptor<Object> argCaptor = ArgumentCaptor.forClass(Object.class);
		Mockito.verify(mockSession).setAttribute(Mockito.eq(RegisterServlet.POSTREDIRECT_REGISTER_CONVSCOPE_KEY),
				argCaptor.capture());
		
		Map<String, Object> conversationMap = (Map<String, Object>)argCaptor.getValue();
		Map<String, String> errorMap = (Map<String, String>)conversationMap.
				get(RegisterServlet.CONVSCOPE_ERRORMESSAGES_KEY);
		
		assertTrue("not found capcha error message" ,errorMap.containsKey(RegisterServlet.CAPCHA_ERROR_KEY));
		
		try {
			Mockito.verify(mockResponse).sendRedirect(fakeContextPath + RegisterServlet.REDIRECT_REGISTER_FAIL);
		} catch (IOException e) {}
	}
	
	@Test
	public void testDoPostRegisterFailedUserExist() {
		final String fakeStringParam = "AAA";
		final String fakeEmailParam = "1@ya.ru";
		final String fakeCapcha = "12345";
		
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_CAPCHA_PARAM)).thenReturn(fakeCapcha);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_LASTNAME_PARAM)).thenReturn(fakeStringParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_LOGIN_PARAM)).thenReturn(fakeEmailParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_NAME_PARAM)).thenReturn(fakeStringParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_PWD_PARAM)).thenReturn(fakeStringParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_RECEIVELETTERS_PARAM)).thenReturn(fakeStringParam);
		Mockito.when(mockRequest.getParameter(RegisterServlet.REGISTER_FORM_REPWD_PARAM)).thenReturn(fakeStringParam);
		
		Capcha mockCapcha = Mockito.mock(Capcha.class);
		Mockito.when(mockCapcha.getCapcha()).thenReturn(fakeCapcha);
		try {
			Mockito.when(mockCapchaProvider.getCapcha(mockRequest)).thenReturn(mockCapcha);
		} catch (Exception e) {}
		Mockito.when(mockCapchaProvider.isCapchaExpired(mockCapcha)).thenReturn(false);
		
		Mockito.when(mockUserService.userExists(fakeEmailParam)).thenReturn(true);
		
		try {
			testServlet.doPost(mockRequest, mockResponse);
		} catch (Exception ex) {
			fail("Unexpected exception " + ex.getClass().getName());
		}
		
		ArgumentCaptor<Object> argCaptor = ArgumentCaptor.forClass(Object.class);
		Mockito.verify(mockSession).setAttribute(Mockito.eq(RegisterServlet.POSTREDIRECT_REGISTER_CONVSCOPE_KEY),
				argCaptor.capture());
		
		Map<String, Object> conversationMap = (Map<String, Object>)argCaptor.getValue();
		Map<String, String> errorMap = (Map<String, String>)conversationMap.
				get(RegisterServlet.CONVSCOPE_ERRORMESSAGES_KEY);
		
		assertTrue("not found user exist error message" ,errorMap.containsKey(UserFormBean.USERBEAN_LOGIN_ERROR_KEY));
		
		try {
			Mockito.verify(mockResponse).sendRedirect(fakeContextPath + RegisterServlet.REDIRECT_REGISTER_FAIL);
		} catch (IOException e) {}
	}
	
}
