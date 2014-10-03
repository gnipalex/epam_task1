package com.epam.hnyp.task2.subtask3.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class MainMenuCommand extends AbstractCommand {

	public static final char QUIT_SYMBOL = 'q';

	private Map<String, AbstractCommand> commands = new LinkedHashMap<>();

	private IOProvider ioProvider;

	public MainMenuCommand(IOProvider ioProvider) {
		this.ioProvider = ioProvider;
	}

	@Override
	public void execute() {
		print();
		char key = 0;
		do {
			key = 0;
			AbstractCommand cmd = null;
			ioProvider.print("Choice -> ");
			String line = ioProvider.readLine();
			if (!line.isEmpty()) {
				key = line.charAt(0);
				cmd = commands.get(String.valueOf(key));
			} else {
				continue;
			}
			if (cmd == null) {
				if (key != QUIT_SYMBOL) {
					ioProvider.printLine("oups, command not found :(");
				}
				continue;
			} else {
				cmd.execute();
				ioProvider.printLine();
				print();
			}
		} while (key != QUIT_SYMBOL);
	}

	public void print() {
		ioProvider.printLine(">> Main Menu");
		for (Entry<String, AbstractCommand> c : commands.entrySet()) {
			ioProvider.printLine(
					c.getKey() + "\t" + c.getValue().about());
		}
		ioProvider.printLine(QUIT_SYMBOL + "\t" + "quit the grocery");
	}

	@Override
	public String about() {
		return "main menu";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return commands;
	}

}
