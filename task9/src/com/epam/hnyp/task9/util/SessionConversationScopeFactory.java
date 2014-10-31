package com.epam.hnyp.task9.util;

import javax.servlet.http.HttpServletRequest;

public class SessionConversationScopeFactory implements ConversationScopeFactory {

	@Override
	public ConversationScopeProvider newConversationScopeProvider(
			HttpServletRequest request, String mapKey) {
		return new SessionConversationScopeProvider(request, mapKey);
	}

}
