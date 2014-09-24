package com.epam.hnyp.task2.subtask3.model.creator;

import java.util.HashMap;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.random.DoubleRandomFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.random.IntRandomFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.random.LongRandomFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.random.StringRandomFieldReader;

public class RandomReflectionProductCreator extends ConsoleReflectionProductCreator {
	private static final Map<Class<?>, FieldReader> READERS = new HashMap<Class<?>, FieldReader>();
	static {
		READERS.put(String.class, new StringRandomFieldReader(5));
		READERS.put(Integer.class, new IntRandomFieldReader(99));
		READERS.put(Double.class, new DoubleRandomFieldReader(99));
		READERS.put(Long.class, new LongRandomFieldReader());
	}
	
	@Override
	protected FieldReader getFieldReader(Class<?> type) {
		return READERS.get(type);
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
