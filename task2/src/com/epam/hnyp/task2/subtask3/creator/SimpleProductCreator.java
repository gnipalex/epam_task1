package com.epam.hnyp.task2.subtask3.creator;

import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.helper.HelperNoReflection;
import com.epam.hnyp.task2.subtask3.model.helper.HelperNoReflection.IllegalDataFormatException;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader.IllegalFieldFormatException;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class SimpleProductCreator extends AbstractProductCreator {

	protected Map<Class<? extends Product>, HelperNoReflection> helpers;
	
	public SimpleProductCreator(Map<String, Class<? extends Product>> products,
			Map<Class<?>, FieldReader> readers, Map<Class<? extends Product>, HelperNoReflection> helpers, IOProvider ioProvider) {
		super(products, readers, ioProvider);
		this.helpers = helpers;
	}

	@Override
	public Product createProduct(Class<? extends Product> type) throws CreateProductException{
		HelperNoReflection helper = helpers.get(type);
		if (helper == null) {
			throw new CreateProductException("helper for " + type.getName() + " not found");
		}
		Map<String, Class<?>> fields = helper.getFields();
		StringBuilder stringData = new StringBuilder();
		for (Entry<String, Class<?>> e : fields.entrySet()) {
			FieldReader reader = readers.get((e.getValue()));
			if (reader == null) {
				throw new CreateProductException("reader not found for type '" + e.getValue() + "'");
			}
			while(true) {
				ioProvider.getOutput().println("Please enter field '" + e.getKey() + "' :");
				try {
					Object parsed = reader.read();
					parsed = processReadedValue(parsed, e.getValue(), e.getKey());
					stringData.append(e.getKey()).append(":").append(parsed.toString()).append(";");
				} catch (IllegalFieldFormatException ex) {
					ioProvider.getOutput().println("##field format error##");
					continue;
				}
				break;
			}
		}
		try {
			return helper.make(stringData.toString());
		} catch (IllegalDataFormatException e) {
			throw new CreateProductException("cannot make product, helper IllegalDataFormatException");
		}
	}
	
	protected Object processReadedValue(Object readedValue, Class<?> valType, String fieldName) {
		return readedValue;
	}

}
