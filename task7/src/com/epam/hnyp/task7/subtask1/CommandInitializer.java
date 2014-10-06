package com.epam.hnyp.task7.subtask1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandInitializer {
	public static Map<String, Command> initCommands() {
		Map<String, Command> commands = new HashMap<String, Command>();
		
		commands.put("get", new GetCommand());
		
		return Collections.unmodifiableMap(commands);
	}
}
