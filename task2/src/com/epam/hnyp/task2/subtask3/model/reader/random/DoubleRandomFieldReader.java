package com.epam.hnyp.task2.subtask3.model.reader.random;

import java.util.Random;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;

public class DoubleRandomFieldReader implements FieldReader {
	private int max;
	private Random rand;
	/**
	 * 
	 * @param max
	 * @throws IllegalArgumentException if max <= 0
	 */
	public DoubleRandomFieldReader(int max, Random rand) {
		if (max <= 0) {
			throw new IllegalArgumentException();
		}
		this.max = max;
		this.rand = rand;
	}
	
	@Override
	public Object read() throws IllegalFieldFormatException {
		int max_ = max * 100;
		return rand.nextInt(max_) / 100f;
	}

}
