package com.epam.hnyp.shop.capcha.provider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieCapchaProvider extends AbstractContextCapchaProvider {

	public static final String COOKIE_NAME = "register_capchaUid";

	public CookieCapchaProvider(int capchaTimeToLive) {
		super(capchaTimeToLive);
	}

	@Override
	protected void setCapchaUidToClient(HttpServletRequest req,
			HttpServletResponse resp, String uid) {
		Cookie c = new Cookie(COOKIE_NAME, uid);
		resp.addCookie(c);
	}

	@Override
	protected String getCapchaUidFromClient(HttpServletRequest req)
			throws CapchaUidMissedException {
		for (Cookie c : req.getCookies()) {
			if (c.getName().equals(COOKIE_NAME)){
				return c.getValue();
			}
		}
		return null;
	}
}
