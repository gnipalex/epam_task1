package com.epam.hnyp.task2.subtask3.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MainMenuCommand extends AbstractCommand {

	private static final char QUIT_SYMBOL = 'q';
	
	private Map<String, AbstractCommand> commands = new LinkedHashMap<>();

	@Override
	public void execute() {
		main: while (true) {
			print();
			AbstractCommand cmd = null;
			while (true) {
				System.out.print("Choice -> ");
				char key = 0;
				Scanner sc = new Scanner(System.in);
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
					System.out.println("oups, command not found :(");
					continue;
				}
				break;
			}
			cmd.execute();
			System.out.println();
		}
	}

	public void print() {
		System.out.println(">> Main Menu");
		for (Entry<String, AbstractCommand> c : commands.entrySet()) {
			System.out.println(c.getKey() + "\t" + c.getValue().about());
		}
		System.out.println(QUIT_SYMBOL + "\t" + "quit the grocery");
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
