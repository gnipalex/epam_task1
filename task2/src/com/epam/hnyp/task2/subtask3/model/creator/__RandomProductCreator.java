package com.epam.hnyp.task2.subtask3.model.creator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection;
import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection.IllegalDataFormatException;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader.IllegalFieldFormatException;
import com.epam.hnyp.task2.subtask3.model.reader.random.DoubleRandomFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.random.IntRandomFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.random.LongRandomFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.random.StringRandomFieldReader;

public class __RandomProductCreator implements ProductCreator {

	private static final Map<Class<?>, FieldReader> READERS = new HashMap<Class<?>, FieldReader>();
	static {
		READERS.put(String.class, new StringRandomFieldReader(5));
		READERS.put(Integer.class, new IntRandomFieldReader(99));
		READERS.put(Double.class, new DoubleRandomFieldReader(99));
		READERS.put(Long.class, new LongRandomFieldReader());
	}
	
	@Override
	public void createProduct(ParsableGoodNoReflection g) throws ProductCreateException {
		StringBuilder stringData = new StringBuilder();
		Map<String, Class<?>> fields = g.getFields();
		for (Entry<String, Class<?>> e : fields.entrySet()) {
			FieldReader reader = READERS.get(e.getValue());
			if (reader == null) {
				throw new ProductCreateException("reader for type" + e.getValue().getName() + " not found");
			}
			String readedVal = null;
			try {
				readedVal = reader.read().toString();
			} catch (IllegalFieldFormatException e1) {
				throw new ProductCreateException("unexpected random field read exception");
			}
			if (e.getValue() == String.class) {
				readedVal = e.getKey() + "_" + readedVal;
			}
			stringData.append(makeParam(e.getKey(), readedVal));
		}
		try {
			g.make(stringData.toString());
		} catch (IllegalDataFormatException e) {
			throw new ProductCreateException("error while random filling class " + g.getClass().getName());
		}
	}
	
	public String makeParam(String field, String val) {
		return field + ":" + val + ";";
	}

}
