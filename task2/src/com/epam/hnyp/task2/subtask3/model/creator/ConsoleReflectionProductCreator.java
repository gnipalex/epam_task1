package com.epam.hnyp.task2.subtask3.model.creator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection;
import com.epam.hnyp.task2.subtask3.model.ProductSetterAnnotation;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader.IllegalFieldFormatException;
import com.epam.hnyp.task2.subtask3.model.reader.console.DoubleConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.IntConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.LongConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.StringConsoleFieldReader;

public class ConsoleReflectionProductCreator implements ProductCreator {

	private static final Map<Class<?>, FieldReader> READERS = new HashMap<Class<?>, FieldReader>();
	static {
		READERS.put(String.class, new StringConsoleFieldReader(System.in));
		READERS.put(Double.class, new DoubleConsoleFieldReader(System.in));
		READERS.put(Integer.class, new IntConsoleFieldReader(System.in));
		READERS.put(Long.class, new LongConsoleFieldReader(System.in));
	}

	@Override
	public void createProduct(ParsableGoodNoReflection g)
			throws ProductCreateException {
		ResourceBundle resources = ResourceBundle.getBundle("modelFieldsNames", new Locale("ru"));
		
		List<Method> setters = getSettersWithAnnotation(g.getClass(), ProductSetterAnnotation.class);
		for (Method m : setters) {
			ProductSetterAnnotation ann = m.getAnnotation(ProductSetterAnnotation.class);
			FieldReader reader = getFieldReader(ann.type());
			if (reader == null) {
				throw new ProductCreateException("reader not found for field '"
						+ ann.type() + "'");
			}
			while(true) {
				//parsing field with parser
				System.out.print("Enter " + resources.getString(ann.friendlyMessage()) + " : ");
				try {
					Object parsed = reader.read();
					parsed = processReadedValue(parsed, ann.type(), m.getName().substring(3));
					try {
						m.invoke(g, parsed);
					} catch (Exception e) {
						throw new ProductCreateException("error setting value to " + g.getClass().getName());
					}
				} catch (IllegalFieldFormatException ex) {
					System.out.println("##field format error##");
					continue;
				}
				break;
			}
		}
	}
	
	private List<Method> getSettersWithAnnotation(Class<?> c, Class<? extends Annotation> a) {
		List<Method> setters = new ArrayList<>();
		Method[] methods = c.getMethods();
		if (methods != null) {
			for (Method m : methods) {
				if (m.isAnnotationPresent(a)) {
					setters.add(m);
				}
			}
		}
		return setters;
	}
	
	protected Object processReadedValue(Object readedValue, Class<?> valType,
			String fieldName) {
		return readedValue;
	}
	
	protected FieldReader getFieldReader(Class<?> type) {
		return READERS.get(type);
	}

}
