package com.epam.hnyp.task2.subtask3.command.cart;

import com.epam.hnyp.task2.subtask3.ConfigGrocery;
import com.epam.hnyp.task2.subtask3.command.AbstractCommand;

public class RemoveNonExistentCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
		ConfigGrocery.CART.removeAllNonExistent(ConfigGrocery.STORE);
	}

	@Override
	public String about() {
		return "remove nonexistent elements";
	}

}
