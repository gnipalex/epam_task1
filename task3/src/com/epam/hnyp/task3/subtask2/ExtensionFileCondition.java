package com.epam.hnyp.task3.subtask2;

import java.io.File;

public class ExtensionFileCondition extends BaseFileCondition {
	private String extension;
	
	public ExtensionFileCondition(String extension, BaseFileCondition next) {
		super(next);
		this.extension = extension;
	}

	@Override
	protected boolean internalCondition(File file) {
		// TODO Auto-generated method stub
		return false;
	}

}
