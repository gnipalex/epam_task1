package com.epam.hnyp.task2.subtask3.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class ViewCartCommand extends AbstractCommand {

	public static final char QUIT_SYMBOL = 'm';

	private Map<String, AbstractCommand> commands = new LinkedHashMap<>();

	private ShopFacade shopFacade;
	private IOProvider ioProvider;

	public ViewCartCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
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
				if (cmd == null) {
					if (key != QUIT_SYMBOL) {
						ioProvider.printLine("oups, command not found :(");
					}
				} else {
					cmd.execute();
					ioProvider.printLine();
					print();
				}
			}
		} while (key != QUIT_SYMBOL);
	}

	public void print() {
		ioProvider.printLine(">> Main Menu >> Cart");
		printCart();
		printCommands();
	}

	public void printCart() {
		ioProvider.printLine("Items in cart :");
		ioProvider.printLine(String.format("%1$s\t%2$20s\t%3$s\t%4$s", "id",
				"name", "count", "cse"));
		ioProvider.printLine("-----------------------------------------------");
		if (shopFacade.cartSize() == 0) {
			ioProvider.printLine("\t\t---empty---");
		}
		for (Entry<Long, Integer> e : shopFacade.getCartItems().entrySet()) {
			Product g = shopFacade.getProductById(e.getKey());
			if (g != null) {
				ioProvider.printLine(String.format("%1$d\t%2$20s\t%3$d\t%4$d",
						g.getId(), g.getName(), e.getValue(), g.getPrice()));
			} else {
				ioProvider.printLine(String.format(
						"%1$d ---------not found----------", e.getKey()));
			}
		}
		ioProvider.printLine("-----------------------------------------------");
	}

	private void printCommands() {
		ioProvider.printLine("Cart commands :");
		for (Entry<String, AbstractCommand> c : commands.entrySet()) {
			ioProvider.printLine(c.getKey() + "\t" + c.getValue().about());
		}
		ioProvider.printLine(QUIT_SYMBOL + "\t" + "back to main menu");
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
