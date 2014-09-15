package com.epam.hnyp.task2.subtask3.model.parser;

import java.util.Map;

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
	Map<String, FieldParser> getParsers();
	
	static class IllegalDataFormatException extends Exception {}
}
