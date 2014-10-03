package com.epam.hnyp.task2.subtask3.model.reader.console;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class StringConsoleFieldReader implements FieldReader{
	IOProvider io;
	
	public StringConsoleFieldReader(IOProvider io) {
		this.io = io;
	}

	@Override
	public String read() throws IllegalFieldFormatException {
		return io.readLine();
	}
	

}
