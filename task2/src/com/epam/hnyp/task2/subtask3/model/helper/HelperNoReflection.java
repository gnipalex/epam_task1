package com.epam.hnyp.task2.subtask3.model.helper;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Product;

/**
 * Interface enabling good to be parseable
 * @author Oleksandr_Hnyp
 *
 */
public interface HelperNoReflection {
	/**
	 * Fills fields from string
	 * @param data string must be in format 'fieldName:value;...'
	 * @throws IllegalDataFormatException if some fields can not be parsed or in wrong format
	 */
	Product make(String data) throws IllegalDataFormatException;
	
	/**
	 * Gets Map of fields names and types of the fields
	 * @return
	 */
	Map<String, Class<?>> getFields();
	
	static class IllegalDataFormatException extends Exception {}
}
