package com.epam.hnyp.task2.subtask3.model.parser;

public class StringFieldParser implements FieldParser{
	private String fieldName; 
	
	public StringFieldParser(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public String parse(String input) {
		return fieldName + ":" + input;
	}
	

}
