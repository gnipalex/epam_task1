package com.epam.hnyp.shop.locale.provider;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionLocaleProvider extends AbstractLocaleProvider {

	public static final String SESSION_CURRENT_LOCALE_KEY = "com.epam.hnyp.shop.locale.provider.SessionLocaleProvider.SESSION_CURRENT_LOCALE_KEY";
	
	@Override
	public Locale readLocale(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (Locale)session.getAttribute(SESSION_CURRENT_LOCALE_KEY);
	}

	@Override
	public void setCurrentLocale(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_CURRENT_LOCALE_KEY, locale);
	}

}
