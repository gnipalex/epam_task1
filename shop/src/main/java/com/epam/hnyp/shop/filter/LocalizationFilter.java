package com.epam.hnyp.shop.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.locale.provider.AbstractLocaleProvider;

public class LocalizationFilter implements Filter {

	public static final String PARAM_LOCALES = "locales";
	public static final String PARAM_DEFAULT_LOCALE = "defaultLocale";
	
	public static final String CONTEXT_AVAILABLE_LOCALES_KEY = "CONTEXT_AVAILABLE_LOCALES";
	
	public static final String REQUEST_PARAM_LANG = "lang";
	
	//public static final String LOCALE_STRING_PATTERN = "^\\w{2,3}(;\\w{2,3})*$";
	//public static final String PARAM_REPLACE_PATTERN = "(?<=\\?)?{param}=.*?(?=&)|(?<=&){param}=.*?(?=&)|(?<=&){param}=.*$";
	
	private AbstractLocaleProvider localeProvider;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		LocalizationRequestWraper requestWraper = new LocalizationRequestWraper(httpRequest, localeProvider);
		
		String requestParamLang = httpRequest.getParameter(REQUEST_PARAM_LANG);
		if (requestParamLang != null) {
			Locale locale = new Locale(requestParamLang);
			
			if (!localeProvider.supportsLocale(locale)) {
				locale = localeProvider.getDefaultLocale();
			}
				
			localeProvider.setCurrentLocale(httpRequest, httpResponse, locale);
			requestWraper.setAppliedLocale(locale);
		}
		chain.doFilter(requestWraper, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		ServletContext context = fConfig.getServletContext();
		localeProvider = (AbstractLocaleProvider)context.getAttribute(ContextInitializer.INIT_LOCALE_PROVIDER_KEY);
		String locales = fConfig.getInitParameter(PARAM_LOCALES);
		if (locales == null || locales.isEmpty()) {
			throw new IllegalArgumentException("filter init param '" + PARAM_LOCALES + "' not specified");
		}
		String defaultLocale = fConfig.getInitParameter(PARAM_DEFAULT_LOCALE);
		if (defaultLocale == null || defaultLocale.isEmpty()) {
			throw new IllegalArgumentException("default locale '" + PARAM_DEFAULT_LOCALE + "' not specified");
		}
		localeProvider.initialize(locales, defaultLocale);
		context.setAttribute(CONTEXT_AVAILABLE_LOCALES_KEY, localeProvider.getSupportedLocales());
	}
	
	public void destroy() {
	}

}
