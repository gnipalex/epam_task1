package com.epam.hnyp.shop.locale.provider;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieLocaleProvider extends AbstractLocaleProvider {
	public static final String COOKIE_NAME = "lang";
	
	private final int cookieTTL;
	
	public CookieLocaleProvider(int cookieTTL) {
		if (cookieTTL <= 0) {
			throw new IllegalArgumentException(
					"'cookieTTL' cannot have negative value");
		}
		this.cookieTTL = cookieTTL;
	}

	@Override
	public Locale readLocale(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cook : cookies) {
			if (cook.getName().equals(COOKIE_NAME)
					&& cook.getValue() != null
					&& !cook.getValue().isEmpty()) {
				return new Locale(cook.getValue());
			}
		}
		return null;
	}

	@Override
	public void setCurrentLocale(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		Cookie cook = new Cookie(COOKIE_NAME, locale.getLanguage());
		cook.setMaxAge(cookieTTL);
		response.addCookie(cook);
	}

}
