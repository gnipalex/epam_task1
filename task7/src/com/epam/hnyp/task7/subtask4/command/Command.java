package com.epam.hnyp.task7.subtask4.command;

import java.util.Map;

public interface Command {
	String process(Map<String, String> params);
}
