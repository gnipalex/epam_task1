package com.epam.hnyp.task2.subtask3.model.creator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection;
import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection.IllegalDataFormatException;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader.IllegalFieldFormatException;
import com.epam.hnyp.task2.subtask3.model.reader.console.DoubleConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.IntConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.LongConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.StringConsoleFieldReader;

public class ConsoleProductCreator implements ProductCreator {

	private static final Map<Class<?>, FieldReader> READERS = new HashMap<Class<?>, FieldReader>();
	static {
		READERS.put(String.class, new StringConsoleFieldReader(System.in));
		READERS.put(Double.class, new DoubleConsoleFieldReader(System.in));
		READERS.put(Integer.class, new IntConsoleFieldReader(System.in));
		READERS.put(Long.class, new LongConsoleFieldReader(System.in));
	}
	
	@Override
	public void createProduct(ParsableGoodNoReflection g) throws ProductCreateException {
		Map<String, Class<?>> fields = g.getFields();
		StringBuilder stringData = new StringBuilder();
		for (Entry<String, Class<?>> e : fields.entrySet()) {
			//getting parser for field
			FieldReader reader = getFieldReader(e.getValue());
			if (reader == null) {
				throw new ProductCreateException("parser not found for field '" + e.getKey() + "'");
			}
			while(true) {
				//parsing field with parser
				System.out.print("Please enter field '" + e.getKey() + "' : ");
				try {
					Object parsed = reader.read();
					parsed = processReadedValue(parsed, e.getValue(), e.getKey());
					stringData.append(e.getKey()).append(":").append(parsed.toString()).append(";");
				} catch (IllegalFieldFormatException ex) {
					System.out.println("##field format error##");
					continue;
				}
				break;
			}
		}
		try {
			g.make(stringData.toString());
		} catch (IllegalDataFormatException e) {
			throw new ProductCreateException("error while filling fields of class " + g.getClass().getName());
		}
	}
	
	protected Object processReadedValue(Object readedValue, Class<?> valType, String fieldName) {
		return readedValue;
	}
	
	protected FieldReader getFieldReader(Class<?> type) {
		return READERS.get(type);
	}

}
