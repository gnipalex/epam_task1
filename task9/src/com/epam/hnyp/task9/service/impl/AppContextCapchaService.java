package com.epam.hnyp.task9.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.epam.hnyp.task9.service.AbstractCapchaService;
import com.epam.hnyp.task9.util.Capcha;

public class AppContextCapchaService extends AbstractCapchaService {

	public AppContextCapchaService() {
	}

	public AppContextCapchaService(int capchaTimeToLive) {
		super(capchaTimeToLive);
	}

	@Override
	protected Map<String, Capcha> getCapchaMap(HttpServletRequest req,
			String key) {
		Object obj = req.getServletContext().getAttribute(key);
		Map<String, Capcha> resultMap = null;
		if (obj != null && obj instanceof Map) {
			resultMap = (Map<String, Capcha>)obj;
		} else {
			resultMap = new HashMap<String, Capcha>();
			req.getServletContext().setAttribute(key, resultMap);
		}
		return resultMap;
	}

}
