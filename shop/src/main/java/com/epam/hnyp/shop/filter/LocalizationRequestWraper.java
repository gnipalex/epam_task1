package com.epam.hnyp.shop.filter;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.epam.hnyp.shop.locale.provider.AbstractLocaleProvider;

public class LocalizationRequestWraper extends HttpServletRequestWrapper {	
	private AbstractLocaleProvider localeProvider;
	
	private Locale appliedLocale;
	
	public LocalizationRequestWraper(HttpServletRequest request, AbstractLocaleProvider localeProvider) {
		super(request);
		this.localeProvider = localeProvider;
	}
	
	@Override
	public Locale getLocale() {
		if (appliedLocale != null) {
			return appliedLocale;
		}
		return localeProvider.getCurrentLocale((HttpServletRequest)getRequest());
	}
	
	@Override
	public Enumeration<Locale> getLocales() {
		Vector<Locale> locales = new Vector<>();
		locales.add(getLocale());
		return locales.elements();
	}

	public Locale getAppliedLocale() {
		return appliedLocale;
	}

	public void setAppliedLocale(Locale appliedLocale) {
		this.appliedLocale = appliedLocale;
	}
		
}
