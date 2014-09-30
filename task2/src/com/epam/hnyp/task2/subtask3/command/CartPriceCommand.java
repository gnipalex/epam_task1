package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;

public class CartPriceCommand extends AbstractCommand {

	private ShopFacade shopFacade;

	public CartPriceCommand(ShopFacade shopService) {
		this.shopFacade = shopService;
	}

	@Override
	public void execute() {
		int price = shopFacade.getPriceForCart();
		System.out.println("Total elements = "
				+ shopFacade.cartSize() + ", price --> "
				+ price);
	}

	@Override
	public String about() {
		return "calculate the price";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return null;
	}

}
