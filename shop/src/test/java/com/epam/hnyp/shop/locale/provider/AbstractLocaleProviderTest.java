package com.epam.hnyp.shop.locale.provider;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AbstractLocaleProviderTest {

	public static final String LOCALE_DEFAULT_STRING = "en";
	public static final String LOCALE_ALL_STRING = "en;ru;de";
	public static final int LOCALE_ALL_COUNT = 3;
	
	private HttpServletRequest mockRequest;
	private HttpServletResponse mockResponse;
	private AbstractLocaleProvider testProvider;
	
	@Before
	public void before() {
		mockRequest = Mockito.mock(HttpServletRequest.class);
		mockResponse = Mockito.mock(HttpServletResponse.class);
		testProvider = new SessionLocaleProvider();
		testProvider.initialize(LOCALE_ALL_STRING, LOCALE_DEFAULT_STRING);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeLocalesBadFormat() {
		testProvider.initialize("enru;de", LOCALE_DEFAULT_STRING);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInitializeBadDefaultLocale() {
		testProvider.initialize(LOCALE_ALL_STRING, "QAZ");
	}
	
	@Test
	public void testInitializeParseAll() {
		List<Locale> locales = testProvider.getSupportedLocales();
		assertTrue(locales.size() == LOCALE_ALL_COUNT);
		assertEquals(LOCALE_DEFAULT_STRING, 
				testProvider.getDefaultLocale().getLanguage());
	}
	
	@Test
	public void testGetCurrentLocaleFromUserSupported() {
		Locale loc = new Locale("en");
		AbstractLocaleProvider spyProvider = Mockito.spy(testProvider);
		Mockito.doReturn(loc).when(spyProvider).readLocale(mockRequest);
		Locale returnedLoc = spyProvider.getCurrentLocale(mockRequest);
		assertEquals(loc, returnedLoc);
	}
	
	@Test
	public void testGetCurrentLocaleFromUserNotSupported() {
		Locale loc = new Locale("QWERYU");
		Locale locTheMost = new Locale("en");
		AbstractLocaleProvider spyProvider = Mockito.spy(testProvider);
		Mockito.doReturn(loc).when(spyProvider).readLocale(mockRequest);
		Mockito.doReturn(locTheMost).when(spyProvider).getTheMostAppropriateLocale(mockRequest);
		Locale returnedLoc = spyProvider.getCurrentLocale(mockRequest);
		assertEquals(locTheMost, returnedLoc);
	}
	
	@Test
	public void testGetCurrentLocaleFromUserNotSpecified() {
		Locale locTheMost = new Locale("en");
		AbstractLocaleProvider spyProvider = Mockito.spy(testProvider);
		Mockito.doReturn(null).when(spyProvider).readLocale(mockRequest);
		Mockito.doReturn(locTheMost).when(spyProvider).getTheMostAppropriateLocale(mockRequest);
		Locale returnedLoc = spyProvider.getCurrentLocale(mockRequest);
		assertEquals(locTheMost, returnedLoc);
	}
	
	@Test
	public void testGetTheMostAppropriateLocaleNotSpecifiedByBrowser() {
		Enumeration<Locale> fakeLocEnum = new Vector<Locale>().elements();
		Mockito.when(mockRequest.getLocales()).thenReturn(fakeLocEnum);
		Locale returnedLoc = testProvider.getTheMostAppropriateLocale(mockRequest);
		assertEquals(testProvider.getDefaultLocale(), returnedLoc);
	}
	
	@Test
	public void testGetTheMostAppropriateLocaleSpecifiedByBrowserContains() {
		Vector<Locale> browserLocales = new Vector<Locale>();
		browserLocales.add(Locale.CANADA);
		Locale enLocale = new Locale("en");
		browserLocales.add(enLocale);
		Enumeration<Locale> fakeLocEnum = browserLocales.elements();
		Mockito.when(mockRequest.getLocales()).thenReturn(fakeLocEnum);
		Locale returnedLoc = testProvider.getTheMostAppropriateLocale(mockRequest);
		assertEquals(enLocale, returnedLoc);
	}
	
	@Test
	public void testGetTheMostAppropriateLocaleSpecifiedByBrowserNotContains() {
		Vector<Locale> browserLocales = new Vector<Locale>();
		browserLocales.add(Locale.CANADA);
		browserLocales.add(Locale.CHINESE);
		Enumeration<Locale> fakeLocEnum = browserLocales.elements();
		Mockito.when(mockRequest.getLocales()).thenReturn(fakeLocEnum);
		Locale returnedLoc = testProvider.getTheMostAppropriateLocale(mockRequest);
		assertEquals(testProvider.getDefaultLocale(), returnedLoc);
	}
	
	@Test
	public void testGetSupportedLocales() {
		List<Locale> supported = testProvider.getSupportedLocales();
		assertTrue(supported.contains(new Locale("en")));
		assertTrue(supported.contains(new Locale("ru")));
		assertTrue(supported.contains(new Locale("de")));
	}
	

}
