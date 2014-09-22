package com.epam.hnyp.task2.subtask3.model.creator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.GoodFieldAnnotation;
import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.DoubleConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.IntConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.LongConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.StringConsoleFieldReader;

public class ConsoleReflectionGoodCreator implements GoodCreator {

	private static final Map<Class<?>, FieldReader> READERS = new HashMap<Class<?>, FieldReader>();
	static {
		READERS.put(String.class, new StringConsoleFieldReader(System.in));
		READERS.put(Double.class, new DoubleConsoleFieldReader(System.in));
		READERS.put(Integer.class, new IntConsoleFieldReader(System.in));
		READERS.put(Long.class, new LongConsoleFieldReader(System.in));
	}
	
	@Override
	public void createGood(ParsableGoodNoReflection g)
			throws GoodCreateException {
		List<Field> fields = readFieldsWithAnn(g.getClass(), GoodFieldAnnotation.class);
		for (Field f : fields) {
			
		}
	}
	
	public List<Field> readFieldsWithAnn(Class<?> c, Class<? extends Annotation> a) {
		List<Field> fieldsList = new ArrayList<>();
		Field[] fields = c.getDeclaredFields();
		if (fields != null) {
			for (Field f : fields) {
				if (f.isAnnotationPresent(a)) {
					fieldsList.add(f);
				}
			}
		}
		Class<?> sclazz = c.getSuperclass();
		if (sclazz != null) {
			fieldsList.addAll(readFieldsWithAnn(sclazz, a));
		}
		return fieldsList;
	}

}
