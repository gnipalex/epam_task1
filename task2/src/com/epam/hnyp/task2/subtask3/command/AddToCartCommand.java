package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class AddToCartCommand extends AbstractCommand{

	private ShopFacade shopFacade;
	private IOProvider ioProvider;
	
	public AddToCartCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}
	
	@Override
	public void execute() {
		ioProvider.getOutput().print("Please enter product id to add it to cart or just pass enter to cancel : ");
		Scanner sc = new Scanner(ioProvider.getInput());
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
			ioProvider.getOutput().println("product with id=[" + id + "] not found");
			return;
		}
		ioProvider.getOutput().println("product id=[" + id + "] added, now you have " + shopFacade.cartSize() + " items in your cart");
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
