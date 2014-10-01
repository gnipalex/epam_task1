package com.epam.hnyp.task2.subtask3.factory;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.helper.HelperNoReflection;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;

public class SimpleProductFactory extends AbstractProductFactory {

	protected Map<Class<? extends Product>, HelperNoReflection> helpers;
	
	public SimpleProductFactory(Map<String, Class<? extends Product>> products,
			Map<Class<?>, FieldReader> readers, Map<Class<? extends Product>, HelperNoReflection> helpers) {
		super(products, readers);
		this.helpers = helpers;
	}

	@Override
	public Product createProduct(Class<? extends Product> type) throws CreateProductException{
		return null;
	}

}
