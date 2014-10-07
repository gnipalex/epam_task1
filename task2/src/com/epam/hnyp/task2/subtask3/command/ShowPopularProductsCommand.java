package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class ShowPopularProductsCommand extends AbstractCommand {

	private ShopFacade shopFacade;
	private IOProvider ioProvider;

	public ShowPopularProductsCommand(ShopFacade shopFacade, IOProvider ioProvider) {
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}
	
	@Override
	public void execute() {
		ioProvider.printLine("Popular products :");
		
		ioProvider.printLine(String.format("%1$s\t%2$20s\t%3$s", "id", "name", "price"));
		ioProvider.printLine("--------------------------------------");
		if (shopFacade.getPopularProducts().isEmpty()) {
			ioProvider.printLine("\t\t---empty---");
		}
		for (Product g : shopFacade.getPopularProducts()) {
			ioProvider.printLine(String.format("%1$d\t%2$20s\t%3$d", g.getId(), g.getName(),
					g.getPrice()));
		}
		ioProvider.printLine("--------------------------------------");
	}

	@Override
	public String about() {
		return "show popular goods";
	}

	@Override
	public Map<String, AbstractCommand> getCommandsMap() {
		return null;
	}

}
