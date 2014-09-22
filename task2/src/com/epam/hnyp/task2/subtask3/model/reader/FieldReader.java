package com.epam.hnyp.task2.subtask3.model.reader;


/**
 * Interface of field parser
 * @author Oleksandr_Hnyp
 *
 */
public interface FieldReader {
	/**
	 * Reads value of the field
	 * @return read value
	 * @throws IllegalFieldFormatException field in wrong format
	 */
	Object read() throws IllegalFieldFormatException;
	
	/**
	 * IllegalFieldFormatException is thrown when parse method reads string in wrong format
	 * @author Oleksandr_Hnyp
	 *
	 */
	static class IllegalFieldFormatException extends Exception { }
}
