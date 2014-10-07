package com.epam.hnyp.task7.subtask1.command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.epam.hnyp.task7.subtask1.facade.ProductsFacade;

public class CommandInitializer {
	public static Map<String, Command> initCommands(ProductsFacade prodFacade) {
		Map<String, Command> commands = new HashMap<String, Command>();
		
		commands.put("get", new GetCommand(prodFacade));
		
		return Collections.unmodifiableMap(commands);
	}
}
