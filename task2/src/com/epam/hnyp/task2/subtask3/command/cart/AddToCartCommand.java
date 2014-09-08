package com.epam.hnyp.task2.subtask3.command.cart;

import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.ConfigGrocery;
import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.model.Good;

public class AddToCartCommand extends AbstractCommand{

	@Override
	public void execute(String... args) {
		System.out.print("Please enter good id to add it to cart or just pass enter to cancel : ");
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
		Good good = ConfigGrocery.STORE.get(id);
		if (good == null) {
			System.out.println("good with id=[" + id + "] not found");
			return;
		}
		ConfigGrocery.CART.add(good);
		System.out.println("good id=[" + id + "] added, now you have " + ConfigGrocery.CART.size() + " items in your cart");
	}

	@Override
	public String about() {
		return "add to cart";
	}
}
