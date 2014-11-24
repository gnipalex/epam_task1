package com.epam.hnyp.shop.util.convscope;

import javax.servlet.http.HttpServletRequest;

public interface ConversationScopeFactory {
	ConversationScopeProvider newConversationScopeProvider(HttpServletRequest request, String mapKey);
}
