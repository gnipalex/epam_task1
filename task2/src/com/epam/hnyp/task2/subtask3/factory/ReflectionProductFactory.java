package com.epam.hnyp.task2.subtask3.factory;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;

public class ReflectionProductFactory extends AbstractProductFactory {

	public ReflectionProductFactory(
			Map<String, Class<? extends Product>> products,
			Map<Class<?>, FieldReader> readers) {
		super(products, readers);
	}

	@Override
	public Product createProduct(Class<? extends Product> type)
			throws CreateProductException {
		return null;
	}

}
