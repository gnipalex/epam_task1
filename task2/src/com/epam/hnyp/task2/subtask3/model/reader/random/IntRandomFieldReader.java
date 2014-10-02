package com.epam.hnyp.task2.subtask3.model.reader.random;

import java.util.Random;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;

public class IntRandomFieldReader implements FieldReader {
	private int max;
	private Random rand;
	
	public IntRandomFieldReader(int max, Random rand) {
		if (max <= 0) {
			throw new IllegalArgumentException();
		}
		this.max = max;
		this.rand = rand;
	}
	
	@Override
	public Object read() throws IllegalFieldFormatException {
		return rand.nextInt(max);
	}

}
