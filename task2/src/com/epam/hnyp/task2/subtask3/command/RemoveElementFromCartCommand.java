package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;

public class RemoveElementFromCartCommand extends AbstractCommand {

	private ShopFacade shopFacade;

	public RemoveElementFromCartCommand(ShopFacade shopFacade) {
		this.shopFacade = shopFacade;
	}
	
	@Override
	public void execute() {
		System.out.print("Please enter id of product to remove or just pass enter to cancel: ");
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		if (line.isEmpty()) {
			return;
		}
		long id = 0;
		try {
			id = Long.parseLong(line);
		} catch (NumberFormatException e) {
			System.out.println("##wrong format of id##");
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
