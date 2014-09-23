package com.epam.hnyp.task2.subtask3.command.cart;

import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;

public class AddToCartCommand extends AbstractCommand{

	@Override
	public void execute(String... args) {
		System.out.print("Please enter product id to add it to cart or just pass enter to cancel : ");
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		if (line.isEmpty()) {
			return;
		}
		long id = 0;
		try { 
			id = Long.parseLong(line);		
		} catch (NumberFormatException e) {
			System.out.println("##wrong format of id##");
			return;
		}
		if (!getShopService().addToCart(id)) {
			System.out.println("product with id=[" + id + "] not found");
			return;
		}
		System.out.println("product id=[" + id + "] added, now you have " + getShopService().getCurrentCart().size() + " items in your cart");
	}

	@Override
	public String about() {
		return "add to cart";
	}
}
