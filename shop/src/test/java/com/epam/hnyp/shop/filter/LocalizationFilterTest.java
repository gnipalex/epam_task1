package com.epam.hnyp.shop.filter;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.locale.provider.AbstractLocaleProvider;

public class LocalizationFilterTest {

	public static final String LOCALE_DEFAULT_STRING = "en";
	public static final String LOCALE_ALL_STRING = "en;ru;de";
	
	private HttpServletRequest mockRequest;
	private HttpServletResponse mockResponse;
	private AbstractLocaleProvider mockLocaleProvider;
	private Locale mockLocaleFromProvider;
	private FilterConfig mockFilterConfig;
	private ServletContext mockContext;
	private FilterChain mockChain;
	private LocalizationFilter testFilter;
	
	@Before
	public void before() {
		mockLocaleProvider = Mockito.mock(AbstractLocaleProvider.class);
		mockLocaleFromProvider = new Locale("en");
		Mockito.when(mockLocaleProvider.getCurrentLocale(Mockito.any(HttpServletRequest.class)))
			.thenReturn(mockLocaleFromProvider);
		mockRequest = Mockito.mock(HttpServletRequest.class);
		mockResponse = Mockito.mock(HttpServletResponse.class);
		mockFilterConfig = Mockito.mock(FilterConfig.class);
		Mockito.when(mockFilterConfig.getInitParameter(LocalizationFilter.PARAM_LOCALES))
			.thenReturn(LOCALE_ALL_STRING);
		Mockito.when(mockFilterConfig.getInitParameter(LocalizationFilter.PARAM_DEFAULT_LOCALE))
			.thenReturn(LOCALE_DEFAULT_STRING);
		mockContext = Mockito.mock(ServletContext.class);
		Mockito.when(mockFilterConfig.getServletContext()).thenReturn(mockContext);
		Mockito.when(mockContext.getAttribute(ContextInitializer.INIT_LOCALE_PROVIDER_KEY))
			.thenReturn(mockLocaleProvider);
		mockChain = Mockito.mock(FilterChain.class);
		testFilter = new LocalizationFilter();
	}
	
	@Test
	public void testInit() throws ServletException  {
		testFilter.init(mockFilterConfig);
		Mockito.verify(mockLocaleProvider).initialize(LOCALE_ALL_STRING, LOCALE_DEFAULT_STRING);
		Mockito.verify(mockContext).setAttribute(LocalizationFilter.CONTEXT_AVAILABLE_LOCALES_KEY, 
					mockLocaleProvider.getSupportedLocales());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInitAllLocalesFail() throws ServletException {
		Mockito.when(mockFilterConfig.getInitParameter(LocalizationFilter.PARAM_LOCALES))
		.thenReturn("");
		testFilter.init(mockFilterConfig);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInitDefLocaleFail() throws ServletException {
		Mockito.when(mockFilterConfig.getInitParameter(LocalizationFilter.PARAM_DEFAULT_LOCALE))
		.thenReturn("");
		testFilter.init(mockFilterConfig);
	}
	
	@Test
	public void testDoFilterNoParam() throws ServletException, IOException {
		testFilter.init(mockFilterConfig);
		Mockito.when(mockRequest.getParameter(LocalizationFilter.REQUEST_PARAM_LANG))
			.thenReturn(null);
		testFilter.doFilter(mockRequest, mockResponse, mockChain);
		Mockito.verify(mockLocaleProvider, Mockito.never())
			.setCurrentLocale(Mockito.eq(mockRequest), Mockito.eq(mockResponse), Mockito.any(Locale.class));
	}
	
	@Test
	public void testDoFilterWithParam() throws ServletException, IOException {
		testFilter.init(mockFilterConfig);
		final String lang = "en";
		Mockito.when(mockRequest.getParameter(LocalizationFilter.REQUEST_PARAM_LANG))
			.thenReturn(lang);
		testFilter.doFilter(mockRequest, mockResponse, mockChain);
		ArgumentCaptor<Locale> argCaptor = ArgumentCaptor.forClass(Locale.class);
		Mockito.verify(mockLocaleProvider)
			.setCurrentLocale(Mockito.eq(mockRequest), Mockito.eq(mockResponse), argCaptor.capture());
		assertEquals(lang, argCaptor.getValue().getLanguage());
	}

}
