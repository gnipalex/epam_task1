package com.epam.hnyp.task2.subtask3.model;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;

/**
 * Interface enabling good to be parseable
 * @author Oleksandr_Hnyp
 *
 */
public interface ParsableGoodNoReflection {
	/**
	 * Fills own fields from string
	 * @param data string in format 'fieldName:value;...'
	 * @throws IllegalDataFormatException if some fields can not be parsed or in wrong format
	 */
	void make(String data) throws IllegalDataFormatException;
	
	/**
	 * Gets Map of fields names and types of the fields
	 * @return
	 */
	Map<String, Class<?>> getFields();
	
	static class IllegalDataFormatException extends Exception {}
}
