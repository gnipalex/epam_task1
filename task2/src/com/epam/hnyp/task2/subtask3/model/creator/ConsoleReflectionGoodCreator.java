package com.epam.hnyp.task2.subtask3.model.creator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection;

public class ConsoleReflectionGoodCreator implements GoodCreator {

	@Override
	public void createGood(ParsableGoodNoReflection g)
			throws GoodCreateException {
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
