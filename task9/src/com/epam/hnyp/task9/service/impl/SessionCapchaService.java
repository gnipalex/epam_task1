package com.epam.hnyp.task9.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.hnyp.task9.service.AbstractCapchaService;
import com.epam.hnyp.task9.util.Capcha;

public class SessionCapchaService extends AbstractCapchaService {

	public SessionCapchaService() {
	}
	
	public SessionCapchaService(int capchaTimeToLive) {
		super(capchaTimeToLive);
	}

	@Override
	protected Map<String, Capcha> getCapchaMap(HttpServletRequest req,
			String key) throws ScopeNotFoundException {
		HttpSession session = req.getSession(false);
		if (session == null) {
			throw new ScopeNotFoundException("session does't exist");
		}
		Object obj = session.getAttribute(key);
		Map<String, Capcha> resultMap = null;
		if (obj != null && obj instanceof Map) {
			resultMap = (Map<String, Capcha>)obj;
		} else {
			resultMap = new HashMap<String, Capcha>();
			session.setAttribute(key, resultMap);
		}
		return resultMap;
	}
	
}
