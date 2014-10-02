package com.epam.hnyp.task2.subtask3.creator;

import java.util.Map;
import java.util.ResourceBundle;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class ReflectionRandomProductCreator extends ReflectionProductCreator {

	public ReflectionRandomProductCreator(
			Map<String, Class<? extends Product>> products,
			Map<Class<?>, FieldReader> readers, IOProvider ioProvider,
			ResourceBundle resources) {
		super(products, readers, ioProvider, resources);
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
