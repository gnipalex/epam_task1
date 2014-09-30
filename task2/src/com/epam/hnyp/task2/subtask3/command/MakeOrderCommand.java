package com.epam.hnyp.task2.subtask3.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;

public class MakeOrderCommand extends AbstractCommand {

	private ShopFacade shopFacade;

	public MakeOrderCommand(ShopFacade shopFacade) {
		this.shopFacade = shopFacade;
	}
	
	@Override
	public void execute() {
		if (shopFacade.cartSize() == 0) {
			System.out.println("Your cart is empty, buy something at first");
			return;
		}
		int totalPrice = shopFacade.getPriceForCart();
		System.out.println("You have " + shopFacade.cartSize() + 
				" products in cart. Total price = " + totalPrice);
		System.out.print("Please enter your name or just pass enter to cancel : ");
		
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		if (line.isEmpty()) {
			return;
		}
		String name = line;
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		while(true) {
			System.out.print("Please enter date of order(dd/MM/yyyy HH:mm) or just pass enter to cancel: ");
			line = sc.nextLine();
			if (line.isEmpty()) {
				return;
			}
			try {
				date = sdf.parse(line);
			} catch (ParseException e) {
				System.out.println("##date parse error##");
				continue;
			}
			break;
		}

		if (shopFacade.makeOrder(name, date)) {
			System.out.println("Success. Order created.");
		} else {
			System.out.println("Can't create order with date " + sdf.format(date) + "!!!!");
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
