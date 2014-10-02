package com.epam.hnyp.task2.subtask3.model.reader.console;

import java.io.InputStream;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;

public class StringConsoleFieldReader implements FieldReader{
	private InputStream stream;
	
	public StringConsoleFieldReader(InputStream stream) {
		this.stream = stream;
	}

	@Override
	public Object read() throws IllegalFieldFormatException {
		Scanner sc = new Scanner(stream);
		return sc.nextLine();
	}
	

}
