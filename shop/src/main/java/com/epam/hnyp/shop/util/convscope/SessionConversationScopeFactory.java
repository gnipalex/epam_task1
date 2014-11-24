package com.epam.hnyp.shop.util.convscope;

import javax.servlet.http.HttpServletRequest;

public class SessionConversationScopeFactory implements ConversationScopeFactory {

	@Override
	public ConversationScopeProvider newConversationScopeProvider(
			HttpServletRequest request, String mapKey) {
		return new SessionConversationScopeProvider(request, mapKey);
	}

}
