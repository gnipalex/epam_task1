package com.epam.hnyp.task9.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hnyp.task9.service.CapchaUidService;

public class CookieCapchaUidService implements CapchaUidService {

	private String cookieName;
	
	public CookieCapchaUidService() {
	}
	
	public CookieCapchaUidService(String cookieName) {
		this.cookieName = cookieName;
	}

	@Override
	public String readUid(HttpServletRequest req) {
		for (Cookie c : req.getCookies()) {
			if (c.getName().equals(cookieName)){
				return c.getValue();
			}
		}
		return null;
	}

	@Override
	public void setUid(HttpServletRequest req, HttpServletResponse resp, String uid) {
		Cookie c = new Cookie(cookieName, uid);
		resp.addCookie(c);
	}

	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

}
