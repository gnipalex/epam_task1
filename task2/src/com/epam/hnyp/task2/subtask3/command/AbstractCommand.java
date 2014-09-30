package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;

public abstract class AbstractCommand {
	
	public abstract void execute();
	
	public abstract String about();
	
	public void addCommand(String key, AbstractCommand c) {
		Map<String, AbstractCommand> map = getCommandsMap();
		if (map != null) {
			map.put(key, c);
		}
	}
	
	public abstract Map<String, AbstractCommand> getCommandsMap();
}
