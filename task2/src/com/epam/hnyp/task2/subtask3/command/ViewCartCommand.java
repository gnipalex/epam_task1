package com.epam.hnyp.task2.subtask3.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Product;

public class ViewCartCommand extends AbstractCommand {

	private static final char QUIT_SYMBOL = 'm';
	
	private Map<String, AbstractCommand> commands = new LinkedHashMap<>();

	private ShopFacade shopFacade;

	public ViewCartCommand(ShopFacade shopFacade) {
		this.shopFacade = shopFacade;
	}
	
	@Override
	public void execute() {
		main: while (true) {
			System.out.println(">> Main Menu >> Cart");
			printCart();
			System.out.println("-----------------------------------------------");
			printCommands();
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
			cmd.execute();
			System.out.println();
		}

	}

	public void printCart() {
		System.out.println("Items in cart :");
		System.out.printf("%1$s\t%2$20s\t%3$s\t%4$s\n", "id", "name", "count", "cse");
		System.out.println("-----------------------------------------------");
		if (shopFacade.cartSize() == 0) {
			System.out.println("\t\t---empty---");
		}

		for (Entry<Long, Integer> e : shopFacade.getCartItems().entrySet()){
			Product g = shopFacade.getProductById(e.getKey());
			if (g != null) {
				System.out.printf("%1$d\t%2$20s\t%3$d\t%4$d\n", g.getId(), g.getName(), e.getValue() ,g.getPrice());
			} else {
				System.out.printf("%1$d ---------not found----------", e.getKey());
			}
		}
		
	}
	
	private void printCommands() {
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

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return commands;
	}

}
