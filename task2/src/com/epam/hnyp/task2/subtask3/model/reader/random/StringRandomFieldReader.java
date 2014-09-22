package com.epam.hnyp.task2.subtask3.model.reader.random;

import java.util.Random;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;

public class StringRandomFieldReader implements FieldReader {
	private Random rand = new Random(System.currentTimeMillis());
	private int digitCount;
	
	public StringRandomFieldReader(int digitCount) {
		if (digitCount <=0) {
			throw new IllegalArgumentException();
		}
		this.digitCount = digitCount;
	}
	
	
	@Override
	public Object read() throws IllegalFieldFormatException {
		StringBuilder str = new StringBuilder();
		for (int i=0; i < digitCount; i++){
			int r = rand.nextInt(9);
			str.append(String.valueOf(r));
		}
		return str.toString();
	}

}
