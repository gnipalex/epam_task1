package com.epam.hnyp.shop.locale.provider;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieLocaleProvider extends AbstractLocaleProvider {
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
			if (cook.getName().equals(PARAMETER_NAME)
					&& cook.getValue() != null
					&& cook.getValue().length() == PARAMETER_LEN) {
				return new Locale(cook.getValue());
			}
		}
		return null;
	}

	@Override
	public void setCurrentLocale(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		Cookie cook = new Cookie(PARAMETER_NAME, locale.getLanguage());
		cook.setMaxAge(cookieTTL);
		response.addCookie(cook);
	}

	@Override
	protected Locale getCurrentLocaleByImpl(HttpServletRequest request) {
		return readLocale(request);
	}

}
