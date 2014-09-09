package com.epam.hnyp.task3.subtask2;

import java.io.File;

public abstract class BaseFileCondition {
	private BaseFileCondition next;
	
	public BaseFileCondition(BaseFileCondition next) {
		this.next = next;
	}
	
	public boolean satisfies(File file) {
		boolean res = internalCondition(file);
		if (res == false) {
			return false;
		}
		return next != null ? next.satisfies(file) : res;
	}
	
	protected abstract boolean internalCondition(File file);
}
