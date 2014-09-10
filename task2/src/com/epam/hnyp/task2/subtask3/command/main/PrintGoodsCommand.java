package com.epam.hnyp.task2.subtask3.command.main;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.model.Good;

public class PrintGoodsCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
//		 Store store = ConfigGrocery.STORE;
		 System.out.println("All goods from grocery");
		 System.out.printf("%1$10s%2$20s%3$10s%4$20s\n", "id", "name", "price", "other");
		 System.out.printf("%1$s%1$s%1$s%1$s%1$s%1$s\n","----------");
//		 for (Good g : store.getAll()) {
		 for (Good g : getGoodsService().getAll()) {
			 System.out.println(g.printTableRow());
		 }
		 System.out.printf("%1$s%1$s%1$s%1$s%1$s%1$s\n","----------");
	}

	@Override
	public String about() {
		return "print all goods in store";
	}


}
