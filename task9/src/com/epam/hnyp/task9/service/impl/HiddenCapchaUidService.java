package com.epam.hnyp.task9.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.hnyp.task9.service.CapchaUidService;

public class HiddenCapchaUidService implements CapchaUidService {

	private String paramName;

	public HiddenCapchaUidService() {
	}

	public HiddenCapchaUidService(String paramName) {
		this.paramName = paramName;
	}

	@Override
	public String readUid(HttpServletRequest req) {
		String capchaUidHidden = req.getParameter(paramName);
		if (capchaUidHidden != null && !capchaUidHidden.isEmpty()) {
			return capchaUidHidden;
		}
		return null;
	}

	@Override
	public void setUid(HttpServletRequest req, HttpServletResponse resp,
			String uid) {
		 req.setAttribute(paramName, uid);
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

}
