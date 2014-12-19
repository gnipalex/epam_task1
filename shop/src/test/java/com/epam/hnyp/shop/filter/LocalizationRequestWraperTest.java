package com.epam.hnyp.shop.filter;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.epam.hnyp.shop.locale.provider.AbstractLocaleProvider;

public class LocalizationRequestWraperTest {

	private HttpServletRequest mockRequest;
	private AbstractLocaleProvider mockLocaleProvider;
	private Locale mockLocaleFromProvider;
	private LocalizationRequestWraper testRequest;
	
	@Before
	public void before() {
		mockLocaleProvider = Mockito.mock(AbstractLocaleProvider.class);
		mockLocaleFromProvider = new Locale("en");
		Mockito.when(mockLocaleProvider.getCurrentLocale(Mockito.any(HttpServletRequest.class))).thenReturn(mockLocaleFromProvider);
		mockRequest = Mockito.mock(HttpServletRequest.class);
		testRequest = new LocalizationRequestWraper(mockRequest, mockLocaleProvider);
	}
	
	@Test
	public void testGetLocaleNotApplied() {
		assertSame(mockLocaleFromProvider, testRequest.getLocale());
	}
	
	@Test
	public void testGetLocaleApplied() {
		Locale loc = new Locale("ru");
		testRequest.setAppliedLocale(loc);
		assertSame(loc, testRequest.getLocale());
	}
	
	@Test
	public void testGetLocales() {
		Enumeration<Locale> enumerationLocales = testRequest.getLocales();
		assertTrue(enumerationLocales.hasMoreElements());
		assertSame(mockLocaleFromProvider, enumerationLocales.nextElement());
	}

}
