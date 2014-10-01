package com.epam.hnyp.task2.subtask3.command;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class OrdersCommand extends AbstractCommand {

	private static final char QUIT_SYMBOL = 'm';

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
			ioProvider.getOutput().println(">> Main Menu >> Orders");
			printOrders();
			printCommands();
			AbstractCommand cmd = null;
			while (true) {
				ioProvider.getOutput().print("Choice -> ");
				char key = 0;
				Scanner sc = new Scanner(ioProvider.getInput());
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
			System.out.println();
		}
	}

	private void printOrders() {
		SimpleDateFormat sdf_full = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ioProvider.getOutput().println("All orders:");
		ioProvider.getOutput().printf("%1$20s%2$20s%3$15s\n", "date", "customer", "products cnt");
		ioProvider.getOutput().println("---------------------------------------------------------");
		for (Order o : shopFacade.getAllOrders()) {
			ioProvider.getOutput().printf("%1$20s%2$20s%3$15d\n", sdf_full.format(o.getDate()), o.getCustomer(), o.getCountOfProducts());
		}
		ioProvider.getOutput().println("---------------------------------------------------------");

	}
	
	private void printCommands() {
		ioProvider.getOutput().println("Commands for orders:");
		for (Entry<String, AbstractCommand> c : commands.entrySet()) {
			ioProvider.getOutput().println(c.getKey() + "\t" + c.getValue().about());
		}
		ioProvider.getOutput().println(QUIT_SYMBOL + "\t" + "back to main menu");
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
