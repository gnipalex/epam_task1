package com.epam.hnyp.task2.subtask3.creator;

import java.util.LinkedHashMap;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public abstract class AbstractProductCreator {
	protected IOProvider ioProvider;
	
	protected Map<String, Class<? extends Product>> products;
	
	protected Map<Class<?>, FieldReader> readers;

	public AbstractProductCreator(Map<String, Class<? extends Product>> products,
			Map<Class<?>, FieldReader> readers, IOProvider ioProvider) {
		this.products = products;
		this.readers = readers;
		this.ioProvider = ioProvider;
	}

	public Map<String, Class<? extends Product>> getProducts() {
		return new LinkedHashMap<>(products);
	}

	public abstract Product createProduct(Class<? extends Product> type) throws CreateProductException;
	
	public static class CreateProductException extends Exception {
		public CreateProductException() {
		}
		
		public CreateProductException(String message) {
			super(message);
		}
	}
}
