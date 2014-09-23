package com.epam.hnyp.task2.subtask3.command.main;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.model.Product;

public class PrintProductsCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
		 System.out.println("All products from grocery");
		 System.out.printf("%1$10s%2$20s%3$10s%4$20s\n", "id", "name", "price", "other");
		 System.out.printf("%1$s%1$s%1$s%1$s%1$s%1$s\n","----------");
		 for (Product g : getProductsService().getAll()) {
			 System.out.println(g.printTableRow());
		 }
		 System.out.printf("%1$s%1$s%1$s%1$s%1$s%1$s\n","----------");
	}

	@Override
	public String about() {
		return "print all products in store";
	}


}
