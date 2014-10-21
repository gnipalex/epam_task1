package com.epam.hnyp.task9.validation;

import javax.servlet.http.HttpServletRequest;

import com.epam.hnyp.task9.service.CapchaService;

public abstract class AbstractCapchaValidator {
	public String readCapchaUidFromRequest(HttpServletRequest req) {
		
	}
	
	public abstract void saveCapcha(HttpServletRequest req, String uid, CapchaService cs);
	
	public abstract boolean checkCapcha(HttpServletRequest req)
}
