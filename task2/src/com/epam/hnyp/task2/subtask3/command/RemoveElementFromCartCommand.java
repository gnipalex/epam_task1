package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class RemoveElementFromCartCommand extends AbstractCommand {

	private ShopFacade shopFacade;
	private IOProvider ioProvider;

	public RemoveElementFromCartCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}
	
	@Override
	public void execute() {
		ioProvider.print("Please enter id of product to remove or just pass enter to cancel: ");
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
		shopFacade.removeFromCart(id);
	}

	@Override
	public String about() {
		return "remove element by id";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return null;
	}

}
