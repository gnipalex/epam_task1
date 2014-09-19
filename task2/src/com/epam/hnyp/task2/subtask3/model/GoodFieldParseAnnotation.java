package com.epam.hnyp.task2.subtask3.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.epam.hnyp.task2.subtask3.model.parser.FieldParser;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GoodFieldParseAnnotation {
	String friendlyMessage();
	Class<? extends FieldParser> parser();
}
