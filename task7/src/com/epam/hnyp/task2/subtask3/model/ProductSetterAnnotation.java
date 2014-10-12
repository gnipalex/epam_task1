package com.epam.hnyp.task2.subtask3.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ProductSetterAnnotation {
	String friendlyMessage();
	Class<?> type();
}
