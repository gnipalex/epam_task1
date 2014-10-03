package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class CartPriceCommand extends AbstractCommand {

	private ShopFacade shopFacade;
	private IOProvider ioProvider;
	
	public CartPriceCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}

	@Override
	public void execute() {
		int price = shopFacade.getPriceForCart();
		ioProvider.printLine("Total elements = "
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
