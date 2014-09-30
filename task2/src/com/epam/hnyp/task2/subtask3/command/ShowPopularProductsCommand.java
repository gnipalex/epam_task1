package com.epam.hnyp.task2.subtask3.command;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.model.Product;

public class ShowPopularProductsCommand extends AbstractCommand {

	private ShopFacade shopFacade;

	public ShowPopularProductsCommand(ShopFacade shopFacade) {
		this.shopFacade = shopFacade;
	}
	
	@Override
	public void execute() {
		System.out.println("Popular products :");
		System.out.printf("%1$s\t%2$20s\t%3$s\n", "id", "name", "price");
		System.out.println("-----------------------------------");
		if (shopFacade.getPopularProducts().isEmpty()) {
			System.out.println("\t\t---empty---");
		}
		for (Product g : shopFacade.getPopularProducts()) {
			System.out.printf("%1$d\t%2$20s\t%3$d\n", g.getId(), g.getName(),
					g.getPrice());
		}
		System.out.println("-----------------------------------");
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
