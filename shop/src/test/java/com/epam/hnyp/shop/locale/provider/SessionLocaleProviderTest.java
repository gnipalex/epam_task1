package com.epam.hnyp.shop.locale.provider;

import static org.junit.Assert.*;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class SessionLocaleProviderTest {

	public static final String LOCALE_DEFAULT_STRING = "en";
	public static final String LOCALE_ALL_STRING = "en;ru;de";
	public static final int LOCALE_ALL_COUNT = 3;

	private HttpServletRequest mockRequest;
	private HttpServletResponse mockResponse;
	private HttpSession mockSession;
	private AbstractLocaleProvider testProvider;

	@Before
	public void before() {
		mockRequest = Mockito.mock(HttpServletRequest.class);
		mockSession = Mockito.mock(HttpSession.class);
		Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
		mockResponse = Mockito.mock(HttpServletResponse.class);
		testProvider = new SessionLocaleProvider();
		testProvider.initialize(LOCALE_ALL_STRING, LOCALE_DEFAULT_STRING);
	}
	
	@Test
	public void testReadLocale() {
		final Locale fakeLocale = new Locale("en");
		Mockito.when(mockSession.getAttribute(SessionLocaleProvider.SESSION_CURRENT_LOCALE_KEY))
			.thenReturn(fakeLocale);
		Locale returnedLocale = testProvider.readLocale(mockRequest);
		assertSame(fakeLocale, returnedLocale);
	}
	
	@Test
	public void testSetCurrentLocale() {
		final Locale fakeLocale = new Locale("en");
		ArgumentCaptor<Locale> localeCaptor = ArgumentCaptor.forClass(Locale.class);
		testProvider.setCurrentLocale(mockRequest, mockResponse, fakeLocale);
		Mockito.verify(mockSession).setAttribute(Mockito.eq(SessionLocaleProvider.SESSION_CURRENT_LOCALE_KEY), localeCaptor.capture());
		assertSame(fakeLocale, localeCaptor.getValue());
	}
}
