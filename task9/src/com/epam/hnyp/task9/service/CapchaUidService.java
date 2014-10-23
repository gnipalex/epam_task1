package com.epam.hnyp.task9.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CapchaUidService {
	String readUid(HttpServletRequest req);
	void setUid(HttpServletRequest req, HttpServletResponse resp, String uid);
}
