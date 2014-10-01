package com.epam.hnyp.task2.subtask3.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class MakeOrderCommand extends AbstractCommand {

	private ShopFacade shopFacade;
	private IOProvider ioProvider;
	
	public MakeOrderCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}
	
	@Override
	public void execute() {
		if (shopFacade.cartSize() == 0) {
			ioProvider.getOutput().println("Your cart is empty, buy something at first");
			return;
		}
		int totalPrice = shopFacade.getPriceForCart();
		ioProvider.getOutput().println("You have " + shopFacade.cartSize() + 
				" products in cart. Total price = " + totalPrice);
		ioProvider.getOutput().print("Please enter your name or just pass enter to cancel : ");
		
		Scanner sc = new Scanner(ioProvider.getInput());
		String line = sc.nextLine();
		if (line.isEmpty()) {
			return;
		}
		String name = line;
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		while(true) {
			ioProvider.getOutput().print("Please enter date of order(dd/MM/yyyy HH:mm) or just pass enter to cancel: ");
			line = sc.nextLine();
			if (line.isEmpty()) {
				return;
			}
			try {
				date = sdf.parse(line);
			} catch (ParseException e) {
				ioProvider.getOutput().println("##date parse error##");
				continue;
			}
			break;
		}

		if (shopFacade.makeOrder(name, date)) {
			ioProvider.getOutput().println("Success. Order created.");
		} else {
			ioProvider.getOutput().println("Can't create order with date " + sdf.format(date) + "!!!!");
		}
	}

	@Override
	public String about() {
		return "buy all items";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return null;
	}

}
