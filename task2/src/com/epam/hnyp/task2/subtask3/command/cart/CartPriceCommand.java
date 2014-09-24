package com.epam.hnyp.task2.subtask3.command.cart;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;

public class CartPriceCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
		int price = getShopService().getPriceForCart();
		System.out.println("Total elements = " + getShopService().getCurrentCart().size() + ", price --> " + price);
	}

	@Override
	public String about() {
		return "calculate the price";
	}

}
