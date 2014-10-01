package com.epam.hnyp.task2.subtask3.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class ViewCartCommand extends AbstractCommand {

	private static final char QUIT_SYMBOL = 'm';
	
	private Map<String, AbstractCommand> commands = new LinkedHashMap<>();

	private ShopFacade shopFacade;
	private IOProvider ioProvider;

	public ViewCartCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}
	
	@Override
	public void execute() {
		main: while (true) {
			ioProvider.getOutput().println(">> Main Menu >> Cart");
			printCart();
			ioProvider.getOutput().println("-----------------------------------------------");
			printCommands();
			AbstractCommand cmd = null;
			while (true) {
				ioProvider.getOutput().print("Choice -> ");
				char key = 0;
				Scanner sc = new Scanner(ioProvider.getInput());
				String line = sc.nextLine();
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

	public void printCart() {
		ioProvider.getOutput().println("Items in cart :");
		ioProvider.getOutput().printf("%1$s\t%2$20s\t%3$s\t%4$s\n", "id", "name", "count", "cse");
		ioProvider.getOutput().println("-----------------------------------------------");
		if (shopFacade.cartSize() == 0) {
			ioProvider.getOutput().println("\t\t---empty---");
		}

		for (Entry<Long, Integer> e : shopFacade.getCartItems().entrySet()){
			Product g = shopFacade.getProductById(e.getKey());
			if (g != null) {
				ioProvider.getOutput().printf("%1$d\t%2$20s\t%3$d\t%4$d\n", g.getId(), g.getName(), e.getValue() ,g.getPrice());
			} else {
				ioProvider.getOutput().printf("%1$d ---------not found----------", e.getKey());
			}
		}
		
	}
	
	private void printCommands() {
		ioProvider.getOutput().println("Cart commands :");
		for (Entry<String, AbstractCommand> c : commands.entrySet()) {
			ioProvider.getOutput().println(c.getKey() + "\t" + c.getValue().about());
		}
		ioProvider.getOutput().println(QUIT_SYMBOL + "\t" + "back to main menu");
	}

	@Override
	public String about() {
		return "show cart";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return commands;
	}

}
