package com.epam.hnyp.task2.subtask3.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.command.cart.AddToCartCommand;
import com.epam.hnyp.task2.subtask3.command.main.AddGoodCommand;
import com.epam.hnyp.task2.subtask3.command.main.OrdersCommand;
import com.epam.hnyp.task2.subtask3.command.main.PrintGoodsCommand;
import com.epam.hnyp.task2.subtask3.command.main.ShowPopularGoodsCommand;
import com.epam.hnyp.task2.subtask3.command.main.ViewCartCommand;

public class MainMenuCommand extends AbstractCommand {

	private static final char QUIT_SYMBOL = 'q';

	private static Map<String, AbstractCommand> commands = new LinkedHashMap<>();
	static {
		commands.put("1", new PrintGoodsCommand());
		commands.put("2", new ShowPopularGoodsCommand());
		commands.put("3", new AddToCartCommand());
		commands.put("4", new ViewCartCommand());
		commands.put("5", new OrdersCommand());
		commands.put("6", new AddGoodCommand());
	}

	@Override
	public void execute(String... args) {
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
			cmd.execute(args);
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

}
