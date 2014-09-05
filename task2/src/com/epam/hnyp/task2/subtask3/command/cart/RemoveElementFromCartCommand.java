package com.epam.hnyp.task2.subtask3.command.cart;

import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.ConfigGrocery;
import com.epam.hnyp.task2.subtask3.command.AbstractCommand;

public class RemoveElementFromCartCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
		System.out.print("Please enter id of good to remove or just pass enter to cancel: ");
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
		ConfigGrocery.CART.remove(id);
	}

	@Override
	public String about() {
		return "remove element by id";
	}

}
