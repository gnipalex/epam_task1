package com.epam.hnyp.task2.subtask3.command.cart;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;

public class EmptyCartCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
//		ConfigGrocery.CART = new CartImpl();
		getShopService().getCurrentCart().clear();
		System.out.println("Ñart has been cleared.");
	}

	@Override
	public String about() {
		return "empty cart";
	}
	
}
