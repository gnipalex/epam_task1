package com.epam.hnyp.task2.subtask3.creator;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.helper.HelperNoReflection;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class SimpleRandomProductCreator extends SimpleProductCreator {

	public SimpleRandomProductCreator(
			Map<String, Class<? extends Product>> products,
			Map<Class<?>, FieldReader> readers,
			Map<Class<? extends Product>, HelperNoReflection> helpers,
			IOProvider ioProvider) {
		super(products, readers, helpers, ioProvider);
	}
	
	@Override
	protected Object processReadedValue(Object readedValue, Class<?> valType,
			String fieldName) {
		if (valType == String.class) {
			readedValue = fieldName + "_" + readedValue;
		}
		return readedValue;
	}

}
