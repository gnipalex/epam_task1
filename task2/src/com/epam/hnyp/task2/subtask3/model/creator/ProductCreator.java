package com.epam.hnyp.task2.subtask3.model.creator;

import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection;

public interface ProductCreator {
	/**
	 * Creates product
	 * @param g product object that will be filled
	 */
	void createProduct(ParsableGoodNoReflection g) throws ProductCreateException;
	
	static class ProductCreateException extends Exception {
		public ProductCreateException() {
		}
		
		public ProductCreateException(String message) {
			super(message);
		}
	}
}
