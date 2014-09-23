package com.epam.hnyp.task2.subtask3.command.main;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.command.cart.AddToCartCommand;
import com.epam.hnyp.task2.subtask3.command.cart.CartPriceCommand;
import com.epam.hnyp.task2.subtask3.command.cart.EmptyCartCommand;
import com.epam.hnyp.task2.subtask3.command.cart.MakeOrderCommand;
import com.epam.hnyp.task2.subtask3.command.cart.RemoveElementFromCartCommand;
import com.epam.hnyp.task2.subtask3.model.Product;

public class ViewCartCommand extends AbstractCommand {

	private static final char QUIT_SYMBOL = 'm';
	
	private static Map<String, AbstractCommand> commands = new LinkedHashMap<>();
	static {
		commands.put("1", new CartPriceCommand());
		commands.put("2", new AddToCartCommand());
		commands.put("3", new EmptyCartCommand());
		commands.put("4", new RemoveElementFromCartCommand());
		commands.put("5", new MakeOrderCommand());
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
		System.out.println(">> Main Menu >> Cart");
		System.out.println("Items in cart :");
		System.out.printf("%1$s\t%2$20s\t%3$s\t%4$s\n", "id", "name", "count", "cse");
		System.out.println("-----------------------------------------------");
		if (getShopService().getCurrentCart().size() == 0) {
			System.out.println("\t\t---empty---");
		}

		for (Entry<Long, Integer> e : getShopService().getCurrentCart().getAllItems().entrySet()){
			Product g = getProductsService().get(e.getKey());
			if (g != null) {
				System.out.printf("%1$d\t%2$20s\t%3$d\t%4$d\n", g.getId(), g.getName(), e.getValue() ,g.getPrice());
			} else {
				System.out.printf("%1$d ---------not found----------", e.getKey());
			}
		}
		System.out.println("-----------------------------------------------");
		System.out.println("Cart commands :");
		for (Entry<String, AbstractCommand> c : commands.entrySet()) {
			System.out.println(c.getKey() + "\t" + c.getValue().about());
		}
		System.out.println(QUIT_SYMBOL + "\t" + "back to main menu");
	}

	@Override
	public String about() {
		return "show cart";
	}

}
