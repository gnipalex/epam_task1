package com.epam.hnyp.task2.subtask3.model.reader.console;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class DoubleConsoleFieldReader implements FieldReader {
	IOProvider io;
	
	public DoubleConsoleFieldReader(IOProvider io) {
		this.io = io;
	}

	@Override
	public Double read() throws IllegalFieldFormatException {
		double v = 0;
		try {
			v = Double.parseDouble(io.readLine());
		} catch (NumberFormatException e) {
			throw new IllegalFieldFormatException();
		}
		return v;
	}
}
