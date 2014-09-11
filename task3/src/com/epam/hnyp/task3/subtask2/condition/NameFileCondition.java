package com.epam.hnyp.task3.subtask2.condition;

import java.io.File;


/**
 * Represents condition that checks whether prefix matches to file name. Ignore case.
 * @author Oleksandr_Hnyp
 *
 */
public class NameFileCondition extends BaseFileCondition {
	private String pattern;
	
	/**
	 * 
	 * @param pattern string prefix of file
	 * @param next next element of condition chain
	 * @throws NullPointerException if pattern == null
	 */
	public NameFileCondition(String pattern, BaseFileCondition next) {
		super(next);
		if (pattern == null) {
			throw new NullPointerException();
		}
		this.pattern = pattern;
	}

	@Override
	protected boolean internalCondition(File file) {
		return file.getName().matches("(?i)" + pattern + ".*");
	}
}
