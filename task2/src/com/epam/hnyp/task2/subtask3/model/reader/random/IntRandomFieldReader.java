package com.epam.hnyp.task2.subtask3.model.reader.random;

import java.util.Random;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;

public class IntRandomFieldReader implements FieldReader {
	private int max;
	private Random rand = new Random(System.currentTimeMillis());
	
	public IntRandomFieldReader(int max) {
		if (max <= 0) {
			throw new IllegalArgumentException();
		}
		this.max = max;
	}
	
	@Override
	public Object read() throws IllegalFieldFormatException {
		return rand.nextInt(max);
	}

}
