package com.epam.hnyp.task2.subtask3.util;

import java.io.InputStream;
import java.io.PrintStream;

public interface IOProvider {
	InputStream getInput();
	PrintStream getOutput();
}
