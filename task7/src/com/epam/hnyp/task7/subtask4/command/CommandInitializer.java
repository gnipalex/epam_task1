package com.epam.hnyp.task7.subtask4.command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandInitializer {
	
	public static final String INDEX_FILE_NAME = "cgi_index.html";
	
	public static Map<String, Command> initCommands() {
		Map<String, Command> commands = new HashMap<String, Command>();
		
		commands.put("index", new IndexCommand(INDEX_FILE_NAME));
		commands.put("sum", new SumCommand());
		commands.put("say", new SayCommand());
		
		return Collections.unmodifiableMap(commands);
	}
}
