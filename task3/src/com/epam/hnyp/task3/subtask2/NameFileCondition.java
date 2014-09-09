package com.epam.hnyp.task3.subtask2;

import java.io.File;

/**
 * Condition that checks whether prefix matches to file name. Ignore case.
 * @author Oleksandr_Hnyp
 *
 */
public class NameFileCondition extends BaseFileCondition {
	private String pattern;
	
	/**
	 * 
	 * @param pattern string prefix of file
	 * @param next next element of condition chain
	 */
	public NameFileCondition(String pattern, BaseFileCondition next) {
		super(next);
	}

	@Override
	protected boolean internalCondition(File file) {
		return file.getName().matches("(?i)" + pattern + ".*");
	}
}
