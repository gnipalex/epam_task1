package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class EmptyCartCommand extends AbstractCommand {

	private ShopFacade shopFacade;
	private IOProvider ioProvider;
	
	public EmptyCartCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}
	
	@Override
	public void execute() {
		shopFacade.clearCart();
		ioProvider.printLine("Cart has been cleared.");
	}

	@Override
	public String about() {
		return "empty cart";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return null;
	}
	
}
