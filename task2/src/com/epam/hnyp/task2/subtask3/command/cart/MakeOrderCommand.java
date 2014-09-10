package com.epam.hnyp.task2.subtask3.command.cart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;

public class MakeOrderCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
//		if (ConfigGrocery.CART.size() == 0) {
		if (getShopService().getCurrentCart().size() == 0) {
			System.out.println("Your cart is empty, buy something at first");
			return;
		}
		//int totalPrice = ConfigGrocery.STORE.getPriceForAll(ConfigGrocery.CART.getAllItems());
		int totalPrice = getShopService().getPriceForCart();
//		System.out.println("You have " + ConfigGrocery.CART.size() + 
//				" goods in cart. Total price = " + totalPrice);
		System.out.println("You have " + getShopService().getCurrentCart().size() + 
				" goods in cart. Total price = " + totalPrice);
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
//		Order order = new Order(date, name, ConfigGrocery.CART);
//		ConfigGrocery.CART = new CartImpl();
//		boolean res = ConfigGrocery.STORE.saveOrder(order);
		boolean res = getShopService().makeOrder(name, date);
		if (res) {
			System.out.println("Success. Order created.");
		} else {
			System.out.println("Can't create order with date " + sdf.format(date) + "!!!!");
		}
	}

	@Override
	public String about() {
		return "buy all items";
	}

}
