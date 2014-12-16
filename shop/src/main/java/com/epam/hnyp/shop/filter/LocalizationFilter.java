package com.epam.hnyp.shop.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.epam.hnyp.shop.listener.ContextInitializer;
import com.epam.hnyp.shop.locale.provider.AbstractLocaleProvider;

public class LocalizationFilter implements Filter {

	public static final String PARAM_LOCALES = "locales";
	public static final String PARAM_DEFAULT_LOCALE = "defaultLocale";
	
	public static final String REQUEST_PARAM_LANG = "lang";
	
	public static final String LOCALE_STRING_PATTERN = "^\\w{2,3}(;\\w{2,3})*$";
	public static final String PARAM_REPLACE_PATTERN = "(?<=\\?)?{param}=.*?(?=&)|(?<=&){param}=.*?(?=&)|(?<=&){param}=.*$";
	
	private AbstractLocaleProvider localeProvider;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String requestParamLang = httpRequest.getParameter(REQUEST_PARAM_LANG);
		if (requestParamLang != null) {
			localeProvider.setCurrentLocale(httpRequest, httpResponse, new Locale(requestParamLang));
			String url = removeParam(httpRequest.getRequestURI() + httpRequest.getQueryString(), REQUEST_PARAM_LANG);
			httpResponse.sendRedirect(url);
			return;
		}
		
		chain.doFilter(new LocalizationRequestWraper(httpRequest, localeProvider), response);
	}
	
	private String removeParam(String query, String param) {
		String pattern = PARAM_REPLACE_PATTERN.replaceAll("{param}", param);
		return query.replaceAll(pattern, "");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		ServletContext context = fConfig.getServletContext();
		localeProvider = (AbstractLocaleProvider)context.getAttribute(ContextInitializer.INIT_LOCALE_PROVIDER_KEY);
		String locales = fConfig.getInitParameter(PARAM_LOCALES);
		if (locales == null || !locales.matches(LOCALE_STRING_PATTERN)) {
			throw new IllegalArgumentException("filter init param '" + PARAM_LOCALES + "' has wrong format");
		}
		String defaultLocale = fConfig.getInitParameter(PARAM_DEFAULT_LOCALE);
		if (defaultLocale == null || defaultLocale.isEmpty()) {
			throw new IllegalArgumentException("default locale '" + PARAM_DEFAULT_LOCALE + "' not specified");
		}
		localeProvider.initialize(locales, defaultLocale);
	}
	
	public void destroy() {
	}
	
	private class LocalizationRequestWraper extends HttpServletRequestWrapper {	
		private AbstractLocaleProvider localeProvider;
		
		public LocalizationRequestWraper(HttpServletRequest request, AbstractLocaleProvider localeProvider) {
			super(request);
			this.localeProvider = localeProvider;
		}
		
		@Override
		public Locale getLocale() {
			return localeProvider.getCurrentLocale((HttpServletRequest)getRequest());
		}
		
		@Override
		public Enumeration<Locale> getLocales() {
			return new Vector<Locale>(localeProvider.getSupportedLocales()).elements();
		}
	}

}
