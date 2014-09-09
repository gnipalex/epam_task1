package com.epam.hnyp.task2.subtask3.command.main;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.ConfigGrocery;
import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.command.order.FindOrderCommand;
import com.epam.hnyp.task2.subtask3.command.order.OrdersByPeriodCommand;
import com.epam.hnyp.task2.subtask3.util.Order;

public class OrdersCommand extends AbstractCommand {

	private static final char QUIT_SYMBOL = 'm';

	private static Map<String, AbstractCommand> commands = new LinkedHashMap<>();
	static {
		commands.put("1", new OrdersByPeriodCommand());
		commands.put("2", new FindOrderCommand());
	}
	
	@Override
	public void execute(String... args) {
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
			cmd.execute(new String[0]);
			System.out.println();
		}
	}

	public void print() {
		SimpleDateFormat sdf_full = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println(">> Main Menu >> Orders");
		System.out.println("All orders:");
		System.out.printf("%1$20s%2$20s%3$10s\n", "date", "customer", "goods cnt");
		System.out.println("------------------------------------");
		for (Order o : ConfigGrocery.STORE.getAllOrders()) {
			System.out.printf("%1$20s%2$20s%3$10d\n", sdf_full.format(o.getDate()), o.getCustomer(), o.getCart().size());
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

}
