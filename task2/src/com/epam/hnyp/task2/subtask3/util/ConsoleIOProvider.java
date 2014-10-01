package com.epam.hnyp.task2.subtask3.util;

import java.io.InputStream;
import java.io.PrintStream;


public class ConsoleIOProvider implements IOProvider {

	@Override
	public InputStream getInput() {
		return System.in;
	}

	@Override
	public PrintStream getOutput() {
		return System.out;
	}

}
