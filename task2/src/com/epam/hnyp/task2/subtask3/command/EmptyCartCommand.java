package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;

public class EmptyCartCommand extends AbstractCommand {

	private ShopFacade shopFacade;

	public EmptyCartCommand(ShopFacade shopService) {
		this.shopFacade = shopService;
	}
	
	@Override
	public void execute() {
		shopFacade.clearCart();
		System.out.println("Ñart has been cleared.");
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
