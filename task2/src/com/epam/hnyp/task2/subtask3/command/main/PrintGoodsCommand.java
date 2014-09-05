package com.epam.hnyp.task2.subtask3.command.main;

import com.epam.hnyp.task2.subtask3.ConfigGrocery;
import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.util.Good;
import com.epam.hnyp.task2.subtask3.util.Store;

public class PrintGoodsCommand extends AbstractCommand {

	@Override
	public void execute(String... args) {
		 Store store = ConfigGrocery.STORE;
		 System.out.println("All goods from grocery");
		 //System.out.println("id\tname\t\tprice");
		 System.out.printf("%1$s\t%2$20s\t%3$s\n", "id", "name", "price");
		 System.out.println("-----------------------------------");
		 for (Good g : store.getAll()) {
			 //System.out.println(g.getId() + "\t" + g.getName() + "\t\t" + g.getPrice());
			 System.out.printf("%1$d\t%2$20s\t%3$d\n", g.getId(), g.getName(), g.getPrice());
		 }
		 System.out.println("-----------------------------------");
	}

	@Override
	public String about() {
		return "print all goods in store";
	}


}
