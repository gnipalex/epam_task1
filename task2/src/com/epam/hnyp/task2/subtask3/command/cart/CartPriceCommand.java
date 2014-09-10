package com.epam.hnyp.task2.subtask3.command.cart;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;

public class CartPriceCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
		//int price = ConfigGrocery.STORE.getPriceForAll(ConfigGrocery.CART.getAllItems());
		//System.out.println("Total elements = " + ConfigGrocery.CART.size() + ", price --> " + price);
		int price = getShopService().getPriceForCart();
		System.out.println("Total elements = " + getShopService().getCurrentCart().size() + ", price --> " + price);
	}

	@Override
	public String about() {
		// TODO Auto-generated method stub
		return "calculate the price";
	}

}
