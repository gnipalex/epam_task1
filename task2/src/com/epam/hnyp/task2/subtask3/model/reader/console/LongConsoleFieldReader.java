package com.epam.hnyp.task2.subtask3.model.reader.console;

import java.io.InputStream;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;

public class LongConsoleFieldReader implements FieldReader {
	private InputStream stream;
	
	public LongConsoleFieldReader(InputStream stream) {
		this.stream = stream;
	}

	@Override
	public Object read() throws IllegalFieldFormatException {
		Scanner sc = new Scanner(stream);
		long v = 0;
		try {
			v = Long.parseLong(sc.nextLine());
		} catch (NumberFormatException e) {
			throw new IllegalFieldFormatException();
		}
		return v;
	}

}
