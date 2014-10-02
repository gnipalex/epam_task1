package com.epam.hnyp.task2.subtask3.creator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.ProductSetterAnnotation;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader.IllegalFieldFormatException;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class ReflectionProductCreator extends AbstractProductCreator {

	private ResourceBundle resources;
	
	public ReflectionProductCreator(
			Map<String, Class<? extends Product>> products,
			Map<Class<?>, FieldReader> readers, IOProvider ioProvider, ResourceBundle resources) {
		super(products, readers, ioProvider);
		this.resources = resources;
	}

	@Override
	public Product createProduct(Class<? extends Product> type)
			throws CreateProductException {		
		Product p = null;
		try {
			p = type.newInstance();
		} catch (Exception e) {
			throw new CreateProductException("cannot instantiate product");
		}
		
		List<Method> setters = getSettersWithAnnotation(type, ProductSetterAnnotation.class);
		for (Method m : setters) {
			ProductSetterAnnotation ann = m.getAnnotation(ProductSetterAnnotation.class);
			FieldReader reader = readers.get((ann.type()));
			if (reader == null) {
				throw new CreateProductException("reader not found for field '"
						+ ann.type() + "'");
			}
			while(true) {
				ioProvider.getOutput().println("Enter " + resources.getString(ann.friendlyMessage()) + " :");
				try {
					Object parsed = reader.read();
					parsed = processReadedValue(parsed, ann.type(), m.getName().substring(3));
					try {
						m.invoke(p, parsed);
					} catch (Exception e) {
						throw new CreateProductException("error setting value to " + ann.type().getName());
					}
				} catch (IllegalFieldFormatException ex) {
					ioProvider.getOutput().println("##field format error##");
					continue;
				}
				break;
			}
		}
		return p;
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
}
