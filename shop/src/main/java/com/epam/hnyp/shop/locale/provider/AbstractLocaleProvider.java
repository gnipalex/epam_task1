package com.epam.hnyp.shop.locale.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractLocaleProvider {
	
	public static final String PARAMETER_NAME = "lang";
	public static final int PARAMETER_LEN = 2;
	
	private List<Locale> supportedLocales;
	private Locale defaultLocale;
	
	public void initialize(String locales, String def) {
		this.supportedLocales = parseLocales(locales);
		Locale defLoc = new Locale(def);
		if (!this.supportedLocales.contains(defLoc)) {
			throw new IllegalArgumentException("`locales` doesnt contain default locale 'def'");
		}
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
	
	protected abstract Locale getCurrentLocaleByImpl(HttpServletRequest request); 
	
	public Locale getCurrentLocale(HttpServletRequest request) {
		Locale loc = getCurrentLocaleByImpl(request);
		if (loc == null) {
			
		} else {
			
		}
	}
	
	public Locale getTheMostAppropriateLocale(HttpServletRequest request) {
		Enumeration<Locale> clientLocales = request.getLocales();
		while (clientLocales.hasMoreElements()) {
			Locale loc = clientLocales.nextElement();
			if (supportedLocales.contains(loc)) {
				return loc;
			}
		}
		return null;
	}
	
	public List<Locale> getSupportedLocales() {
		return Collections.unmodifiableList(supportedLocales);
	}

	public Locale getDefaultLocale() {
		return defaultLocale;
	}

}
