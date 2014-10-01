package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class PrintProductsCommand extends AbstractCommand {

	private ShopFacade shopFacade;
	private IOProvider ioProvider;

	public PrintProductsCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}

	@Override
	public void execute() {
		ioProvider.getOutput().println("All products from grocery");
		ioProvider.getOutput().printf("%1$10s%2$20s%3$10s%4$20s\n", "id", "name", "price",
				"other");
		ioProvider.getOutput().printf("%1$s%1$s%1$s%1$s%1$s%1$s\n", "----------");
		for (Product g : shopFacade.getAllProducts()) {
			ioProvider.getOutput().println(g.printTableRow());
		}
		ioProvider.getOutput().printf("%1$s%1$s%1$s%1$s%1$s%1$s\n", "----------");
	}

	@Override
	public String about() {
		return "print all products in store";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return null;
	}

}
