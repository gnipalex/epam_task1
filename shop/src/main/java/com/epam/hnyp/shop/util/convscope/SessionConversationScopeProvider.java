package com.epam.hnyp.shop.util.convscope;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionConversationScopeProvider implements ConversationScopeProvider {

	private HttpServletRequest request;
	private String mapKey;
	private Map<String, Object> convMap = new HashMap<>();
	
	public SessionConversationScopeProvider(HttpServletRequest request,
			String mapKey) {
		this.request = request;
		this.mapKey = mapKey;
	}

	@Override
	public void addAttribute(String key, Object value) {
		convMap.put(key, value);
	}

	@Override
	public void save() {
		HttpSession session = request.getSession();
		session.setAttribute(mapKey, new HashMap<>(convMap));
	}

	@Override
	public void restore() {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(mapKey);
		session.removeAttribute(mapKey);
		if (obj != null && obj instanceof Map) {
			Map<String, Object> map = (Map<String, Object>)obj;
			for (Entry<String, Object> e : map.entrySet()) {
				request.setAttribute(e.getKey(), e.getValue());
			}
		}
	}

}
