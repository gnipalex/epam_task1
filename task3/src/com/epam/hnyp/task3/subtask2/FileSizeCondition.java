package com.epam.hnyp.task3.subtask2;

import java.io.File;

public class FileSizeCondition extends BaseFileCondition {

	private long min;
	private long max;
	
	/**
	 * Creates condition that compares size of file
	 * @param min
	 * @param max
	 * @param next
	 * @throws IllegalArgumentException if min < 0 || max < min
	 */
	public FileSizeCondition(long min, long max, BaseFileCondition next) {
		super(next);
		if (min < 0 || max < min) {
			throw new IllegalArgumentException();
		}
		this.max = max;
		this.min = min;
	}

	@Override
	protected boolean internalCondition(File file) {
		return file.length() >= min && file.length() <= max;
	}

}
