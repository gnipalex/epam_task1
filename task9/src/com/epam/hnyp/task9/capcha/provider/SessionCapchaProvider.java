package com.epam.hnyp.task9.capcha.provider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.hnyp.task9.capcha.Capcha;

public class SessionCapchaProvider extends AbstractCapchaProvider {
	public static final String SESSION_CAPCHA_KEY = 
			"com.epam.hnyp.task9.capchaservice.SESSION_CAPCHA_KEY";
	
	public SessionCapchaProvider(int capchaTimeToLive) {
		super(capchaTimeToLive);
	}

	@Override
	public void saveCapcha(HttpServletRequest req, HttpServletResponse resp, Capcha capcha) {
		HttpSession session = req.getSession();
		session.setAttribute(SESSION_CAPCHA_KEY, capcha);
	}

	@Override
	public Capcha getCapcha(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return (Capcha)session.getAttribute(SESSION_CAPCHA_KEY);
	}

	@Override
	public void clearAllExpiredCapcha(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Capcha capcha = (Capcha)session.getAttribute(SESSION_CAPCHA_KEY);
		if (capcha != null) {
			if (isCapchaExpired(capcha)) {
				session.removeAttribute(SESSION_CAPCHA_KEY);
			}
		}
	}

}
