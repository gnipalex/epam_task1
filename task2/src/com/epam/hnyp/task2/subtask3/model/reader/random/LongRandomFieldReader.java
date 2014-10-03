package com.epam.hnyp.task2.subtask3.model.reader.random;

import java.util.Random;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;

public class LongRandomFieldReader implements FieldReader {
	private Random rand;
	
	public LongRandomFieldReader(Random rand) {
		this.rand = rand;
	}
	
	@Override
	public Long read() throws IllegalFieldFormatException {
		return rand.nextLong();
	}

}
