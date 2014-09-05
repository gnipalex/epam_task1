package com.epam.hnyp.task2.subtask3.command.cart;

import com.epam.hnyp.task2.subtask3.ConfigGrocery;
import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.util.Cart;

public class EmptyCartCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
		ConfigGrocery.CART = new Cart();
		System.out.println("Ñart has been cleared.");
	}

	@Override
	public String about() {
		return "empty cart";
	}
	
}
