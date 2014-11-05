package com.epam.hnyp.task9.util.convscope;

import javax.servlet.http.HttpServletRequest;

public interface ConversationScopeFactory {
	ConversationScopeProvider newConversationScopeProvider(HttpServletRequest request, String mapKey);
}
