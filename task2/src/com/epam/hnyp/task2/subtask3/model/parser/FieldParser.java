package com.epam.hnyp.task2.subtask3.model.parser;


/**
 * Interface of field parser
 * @author Oleksandr_Hnyp
 *
 */
public interface FieldParser {
	/**
	 * Reads from line value of the field
	 * @param input input string to parse
	 * @return parsed value
	 * @throws IllegalFieldFormatException field in wrong format
	 */
	Object parse(String input) throws IllegalFieldFormatException;
	
	/**
	 * IllegalFieldFormatException is thrown when parse method reads string in wrong format
	 * @author Oleksandr_Hnyp
	 *
	 */
	static class IllegalFieldFormatException extends Exception { }
}
