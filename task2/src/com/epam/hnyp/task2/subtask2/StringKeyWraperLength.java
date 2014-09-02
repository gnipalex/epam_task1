package com.epam.hnyp.task2.subtask2;

public class StringKeyWraperLength extends StringKeyWraper {
	
	public StringKeyWraperLength(String key) {
		super(key);
	}
	
	@Override
	public int hashCode() {
		return getKey().length();
	}
}
