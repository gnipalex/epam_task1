package com.epam.hnyp.task2.subtask3.model.parser;

public class DoubleFieldParser implements FieldParser {
	private String fieldName; 
	
	public DoubleFieldParser(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Override
	public String parse(String input) throws IllegalFieldFormatException {
		double v = 0;
		try {
			v = Double.parseDouble(input);
		} catch (NumberFormatException e) {
			throw new IllegalFieldFormatException();
		}
		return fieldName + ":" + v;
	}
}
