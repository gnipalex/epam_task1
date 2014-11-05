package com.epam.hnyp.task9.util.convscope;

public interface ConversationScopeProvider {
	
	void addAttribute(String key, Object value);
	
	/**
	 * Saves scope to conversation holder
	 */
	void save();
	
	/**
	 * Restores scope to request from conversation holder
	 */
	void restore();
	
}
