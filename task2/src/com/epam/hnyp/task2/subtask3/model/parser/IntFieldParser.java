package com.epam.hnyp.task2.subtask3.model.parser;

public class IntFieldParser implements FieldParser {

	private String fieldName; 
	
	public IntFieldParser(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Override
	public String parse(String input) throws IllegalFieldFormatException {
		int v = 0;
		try {
			v = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new IllegalFieldFormatException();
		}
		return fieldName + ":" + v; 
	}

}
