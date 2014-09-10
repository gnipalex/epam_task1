package com.epam.hnyp.task2.subtask3.command.main;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.model.Good;

public class ShowPopularGoodsCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
		System.out.println("Popular goods :");
		System.out.printf("%1$s\t%2$20s\t%3$s\n", "id", "name", "price");
		System.out.println("-----------------------------------");
//		if (ConfigGrocery.CART.getLastGoods().isEmpty()) {
		if (getShopService().getPopularGoods().isEmpty()) {
			System.out.println("\t\t---empty---");
		}
//		for (Good g : ConfigGrocery.CART.getLastGoods()) {
		for (Good g : getShopService().getPopularGoods()) {
			System.out.printf("%1$d\t%2$20s\t%3$d\n", g.getId(), g.getName(),
					g.getPrice());
		}
		System.out.println("-----------------------------------");
	}

	@Override
	public String about() {
		return "show popular goods";
	}

}
