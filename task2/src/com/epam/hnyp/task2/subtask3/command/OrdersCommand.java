package com.epam.hnyp.task2.subtask3.command;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Order;

public class OrdersCommand extends AbstractCommand {

	private static final char QUIT_SYMBOL = 'm';

	private Map<String, AbstractCommand> commands = new LinkedHashMap<>();
	
	private ShopFacade shopFacade;

	public OrdersCommand(ShopFacade shopFacade) {
		this.shopFacade = shopFacade;
	}
	
	@Override
	public void execute() {
		main: while(true) {
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
		SimpleDateFormat sdf_full = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println(">> Main Menu >> Orders");
		System.out.println("All orders:");
		System.out.printf("%1$20s%2$20s%3$10s\n", "date", "customer", "products cnt");
		System.out.println("------------------------------------");
		for (Order o : shopFacade.getAllOrders()) {
			System.out.printf("%1$20s%2$20s%3$10d\n", sdf_full.format(o.getDate()), o.getCustomer(), o.getCountOfProducts());
		}
		System.out.println("------------------------------------");
		
		System.out.println("Commands for orders:");
		for (Entry<String, AbstractCommand> c : commands.entrySet()) {
			System.out.println(c.getKey() + "\t" + c.getValue().about());
		}
		System.out.println(QUIT_SYMBOL + "\t" + "back to main menu");
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
