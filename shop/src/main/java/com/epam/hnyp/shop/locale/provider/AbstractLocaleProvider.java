package com.epam.hnyp.shop.locale.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractLocaleProvider {
	
	public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
	
	private List<Locale> supportedLocales = new ArrayList<Locale>();
	private Locale defaultLocale = DEFAULT_LOCALE;
	
	/**
	 * Initializes this locale provider with supported locales and default locale
	 * @param locales
	 * @param def
	 * @throws IllegalArgumentException if 'locales' doesnt contain default locale 'def' 
	 */
	public void initialize(String locales, String def) {
		List<Locale> parsedLocales = parseLocales(locales);
		Locale defLoc = new Locale(def);
		if (!parsedLocales.contains(defLoc)) {
			throw new IllegalArgumentException("`locales` doesnt contain default locale 'def'");
		}
		this.supportedLocales = parsedLocales;
		this.defaultLocale = defLoc;
	}
	
	private List<Locale> parseLocales(String str) {
		List<Locale> locales = new ArrayList<Locale>();
		if (str == null || str.isEmpty()) {
			return locales;
		}
		for (String s : str.split(";")) {
			Locale loc = new Locale(s);
			locales.add(loc);
		}
		return locales;
	}
	
	public abstract Locale readLocale(HttpServletRequest request);
	
	public abstract void setCurrentLocale(HttpServletRequest request, HttpServletResponse response, Locale locale);
		
	public Locale getCurrentLocale(HttpServletRequest request) {
		Locale loc = readLocale(request);
		if (loc != null) {
			if (supportedLocales.contains(loc)) {
				return loc;
			} else {
				return getTheMostAppropriateLocale(request);
			}
		} else {
			return getTheMostAppropriateLocale(request);
		}
	}
	
	/**
	 * Gets the most appropriate locale according to browser, if browser hasn't specified locale or locale not supported then returns default locale
	 * @param request
	 * @return
	 */
	public Locale getTheMostAppropriateLocale(HttpServletRequest request) {
		Enumeration<Locale> clientLocales = request.getLocales();
		while (clientLocales.hasMoreElements()) {
			Locale loc = clientLocales.nextElement();
			if (supportedLocales.contains(loc)) {
				return loc;
			}
		}
		return defaultLocale;
	}
	
	public List<Locale> getSupportedLocales() {
		return Collections.unmodifiableList(supportedLocales);
	}

	public Locale getDefaultLocale() {
		return defaultLocale;
	}

}
