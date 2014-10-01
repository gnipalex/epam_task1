package com.epam.hnyp.task2.subtask3.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class MainMenuCommand extends AbstractCommand {

	private static final char QUIT_SYMBOL = 'q';
	
	private Map<String, AbstractCommand> commands = new LinkedHashMap<>();
	
	private IOProvider ioProvider;
	
	public MainMenuCommand(IOProvider ioProvider) {
		this.ioProvider = ioProvider;
	}
	
	@Override
	public void execute() {
		Scanner sc = new Scanner(ioProvider.getInput());
		main: while (true) {
			print();
			AbstractCommand cmd = null;
			while (true) {
				ioProvider.getOutput().print("Choice -> ");
				char key = 0;
				String line = sc.nextLine();
				if (line.isEmpty()) {
					continue;
				}
				key = line.charAt(0);
				if (key == QUIT_SYMBOL) {
					break main;
				}
				cmd = commands.get(String.valueOf(key));
				if (cmd == null) {
					ioProvider.getOutput().println("oups, command not found :(");
					continue;
				}
				break;
			}

			cmd.execute();
			ioProvider.getOutput().println();
		}
	}

	public void print() {
		ioProvider.getOutput().println(">> Main Menu");
		for (Entry<String, AbstractCommand> c : commands.entrySet()) {
			ioProvider.getOutput().println(c.getKey() + "\t" + c.getValue().about());
		}
		ioProvider.getOutput().println(QUIT_SYMBOL + "\t" + "quit the grocery");
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
