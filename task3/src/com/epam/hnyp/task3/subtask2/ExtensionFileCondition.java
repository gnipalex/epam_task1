package com.epam.hnyp.task3.subtask2;

import java.io.File;

/**
 * Represents condition that checks file's extension.
 * @author Alex
 *
 */
public class ExtensionFileCondition extends BaseFileCondition {
	private String extension;
	
	/**
	 * 
	 * @param extension
	 * @param next
	 * @throws NullPointerException if extension == null
	 */
	public ExtensionFileCondition(String extension, BaseFileCondition next) {
		super(next);
		if (extension == null) {
			throw new NullPointerException();
		}
		this.extension = extension;
	}

	@Override
	protected boolean internalCondition(File file) {
		int dotIndex = file.getName().lastIndexOf('.');
		if (dotIndex >= 0 && dotIndex < file.getName().length() - 1) {
			String ext = file.getName().substring(dotIndex + 1);
			return ext.equalsIgnoreCase(extension);
		}
		return false;
	}

}
