package com.epam.hnyp.task2.subtask3.model.reader.random;

import java.util.Random;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;

public class StringRandomFieldReader implements FieldReader {
	private Random rand;
	private int symbolsCount;
	
	public StringRandomFieldReader(int symbolsCount, Random rand) {
		if (symbolsCount <=0) {
			throw new IllegalArgumentException();
		}
		this.symbolsCount = symbolsCount;
		this.rand = rand;
	}
	
	@Override
	public String read() throws IllegalFieldFormatException {
		StringBuilder str = new StringBuilder();
		for (int i=0; i < symbolsCount; i++){
			int r = rand.nextInt(9);
			str.append(String.valueOf(r));
		}
		return str.toString();
	}

}
