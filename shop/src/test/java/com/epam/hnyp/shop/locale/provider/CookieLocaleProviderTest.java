package com.epam.hnyp.shop.locale.provider;

import static org.junit.Assert.*;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class CookieLocaleProviderTest {

	public static final String LOCALE_DEFAULT_STRING = "en";
	public static final String LOCALE_ALL_STRING = "en;ru;de";
	public static final int LOCALE_ALL_COUNT = 3;
	public static final int COOKIE_TTL = 30;

	private HttpServletRequest mockRequest;
	private HttpServletResponse mockResponse;
	private AbstractLocaleProvider testProvider;

	@Before
	public void before() {
		mockRequest = Mockito.mock(HttpServletRequest.class);
		mockResponse = Mockito.mock(HttpServletResponse.class);
		testProvider = new CookieLocaleProvider(COOKIE_TTL);
		testProvider.initialize(LOCALE_ALL_STRING, LOCALE_DEFAULT_STRING);
	}

	@Test
	public void testReadLocaleInCookie() {
		Cookie[] fakeCookies = { new Cookie(CookieLocaleProvider.COOKIE_NAME,
				CookieLocaleProvider.COOKIE_NAME) };
		Mockito.when(mockRequest.getCookies()).thenReturn(fakeCookies);
		Locale returnedLocale = testProvider.readLocale(mockRequest);
		assertEquals(CookieLocaleProvider.COOKIE_NAME, returnedLocale.getLanguage());
	}
	
	@Test
	public void testReadLocaleInCookieNoCookiePresent() {
		Cookie[] fakeCookies = { new Cookie("AAA", "AA") };
		Mockito.when(mockRequest.getCookies()).thenReturn(fakeCookies);
		Locale returnedLocale = testProvider.readLocale(mockRequest);
		assertNull(returnedLocale);
	}
	
	@Test
	public void testSetCurrentLocale() {
		final String fakeLang = "en";
		final Locale fakeLocale = new Locale(fakeLang);
		ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
		testProvider.setCurrentLocale(mockRequest, mockResponse, fakeLocale);
		Mockito.verify(mockResponse).addCookie(cookieCaptor.capture());
		assertEquals(fakeLang, cookieCaptor.getValue().getValue());
		assertEquals(CookieLocaleProvider.COOKIE_NAME, cookieCaptor.getValue().getName());
	}
	
	@Test
	public void testReadLocaleInCookieEmptyCookieValue() {
		Cookie[] fakeCookies = { new Cookie(CookieLocaleProvider.COOKIE_NAME, "") };
		Mockito.when(mockRequest.getCookies()).thenReturn(fakeCookies);
		Locale returnedLocale = testProvider.readLocale(mockRequest);
		assertNull(returnedLocale);
	}
}
