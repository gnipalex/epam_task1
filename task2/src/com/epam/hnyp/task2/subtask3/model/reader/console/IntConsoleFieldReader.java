package com.epam.hnyp.task2.subtask3.model.reader.console;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class IntConsoleFieldReader implements FieldReader {
	IOProvider io;
	
	public IntConsoleFieldReader(IOProvider io) {
		this.io = io;
	}

	@Override
	public Integer read() throws IllegalFieldFormatException {
		int v = 0;
		try {
			v = Integer.parseInt(io.readLine());
		} catch (NumberFormatException e) {
			throw new IllegalFieldFormatException();
		}
		return v; 
	}
}
