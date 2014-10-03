package com.epam.hnyp.task2.subtask3.model.reader.console;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class LongConsoleFieldReader implements FieldReader {
	IOProvider io;
	
	public LongConsoleFieldReader(IOProvider io) {
		this.io = io;
	}

	@Override
	public Long read() throws IllegalFieldFormatException {
		long v = 0;
		try {
			v = Long.parseLong(io.readLine());
		} catch (NumberFormatException e) {
			throw new IllegalFieldFormatException();
		}
		return v;
	}
}
