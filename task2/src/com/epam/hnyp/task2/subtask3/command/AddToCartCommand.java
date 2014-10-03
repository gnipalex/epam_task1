package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;

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
		ioProvider.print("Please enter product id to add it to cart or just pass enter to cancel : ");
		String line = ioProvider.readLine();
		if (line.isEmpty()) {
			return;
		}
		long id = 0;
		try { 
			id = Long.parseLong(line);		
		} catch (NumberFormatException e) {
			ioProvider.printLine("##wrong format of id##");
			return;
		}
		if (!shopFacade.addToCart(id)) {
			ioProvider.printLine("product with id=[" + id + "] not found");
			return;
		}
		ioProvider.printLine("product id=[" + id + "] added, now you have " + shopFacade.cartSize() + " items in your cart");
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
