package com.epam.hnyp.task2.subtask3.model.parser;

public class LongFieldParser implements FieldParser {

	private String fieldName; 
	
	public LongFieldParser(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Override
	public String parse(String input) throws IllegalFieldFormatException {
		long v = 0;
		try {
			v = Long.parseLong(input);
		} catch (NumberFormatException e) {
			throw new IllegalFieldFormatException();
		}
		return fieldName + ":" + v;
	}

}
