package com.epam.hnyp.task2.subtask3.command;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class OrdersCommand extends AbstractCommand {

	public static final char QUIT_SYMBOL = 'm';

	private Map<String, AbstractCommand> commands = new LinkedHashMap<>();
	
	private ShopFacade shopFacade;
	private IOProvider ioProvider;
	
	public OrdersCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}
	
	@Override
	public void execute() {
		main: while(true) {
			ioProvider.printLine(">> Main Menu >> Orders");
			printOrders();
			printCommands();
			AbstractCommand cmd = null;
			while (true) {
				ioProvider.print("Choice -> ");
				char key = 0;
				String line = ioProvider.readLine();
				if (line.isEmpty()) {
					continue;
				}
				key = line.charAt(0);
				if (key == QUIT_SYMBOL) {
					break main;
				}
				cmd = commands.get(String.valueOf(key));
				if (cmd == null) {
					ioProvider.printLine("oups, command not found :(");
					continue;
				}
				break;
			}
			cmd.execute();
			ioProvider.printLine();
		}
	}

	private void printOrders() {
		SimpleDateFormat sdf_full = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ioProvider.printLine("All orders:");
		ioProvider.printLine(String.format("%1$20s%2$20s%3$15s", "date", "customer", "products cnt"));
		ioProvider.printLine("---------------------------------------------------------");
		for (Order o : shopFacade.getAllOrders()) {
			ioProvider.printLine(String.format("%1$20s%2$20s%3$15d", sdf_full.format(o.getDate()), o.getCustomer(), o.getCountOfProducts()));
		}
		ioProvider.printLine("---------------------------------------------------------");

	}
	
	private void printCommands() {
		ioProvider.printLine("Commands for orders:");
		for (Entry<String, AbstractCommand> c : commands.entrySet()) {
			ioProvider.printLine(c.getKey() + "\t" + c.getValue().about());
		}
		ioProvider.printLine(QUIT_SYMBOL + "\t" + "back to main menu");
	}
	
	@Override
	public String about() {
		return "show orders";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return commands;
	}

}
