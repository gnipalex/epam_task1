package com.epam.hnyp.task2.subtask3.model.creator;

import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection;

public interface GoodCreator {
	/**
	 * Creates good
	 * @param g Good object that will be filled
	 */
	void createGood(ParsableGoodNoReflection g) throws GoodCreateException;
	
	static class GoodCreateException extends Exception {
		public GoodCreateException() {
		}
		
		public GoodCreateException(String message) {
			super(message);
		}
	}
}
