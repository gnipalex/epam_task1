package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;

public class AddToCartCommand extends AbstractCommand{

	private ShopFacade shopFacade;
	
	public AddToCartCommand(ShopFacade shopFacade) {
		this.shopFacade = shopFacade;
	}
	
	@Override
	public void execute() {
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
		if (!shopFacade.addToCart(id)) {
			System.out.println("product with id=[" + id + "] not found");
			return;
		}
		System.out.println("product id=[" + id + "] added, now you have " + shopFacade.cartSize() + " items in your cart");
	}

	@Override
	public String about() {
		return "add to cart";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return null;
	}
}
