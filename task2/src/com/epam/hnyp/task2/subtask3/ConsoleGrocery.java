package com.epam.hnyp.task2.subtask3;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.command.MainMenuCommand;
import com.epam.hnyp.task2.subtask3.factory.GoodsInitializer;
import com.epam.hnyp.task2.subtask3.factory.ServicesContainer;
import com.epam.hnyp.task2.subtask3.factory.ServicesFactory;
import com.epam.hnyp.task2.subtask3.factory.ServicesFactoryInMemory;

public class ConsoleGrocery {
	public static void main(String[] args) {
		ServicesFactory factory = new ServicesFactoryInMemory();
		ServicesContainer servicesContainer = factory.buildServicesContainer(5);
		
		GoodsInitializer.fillGoods(servicesContainer.getGoodsService());
		
		AbstractCommand.services = servicesContainer;
		
		AbstractCommand main = new MainMenuCommand();
		main.execute(new String[0]);
	}
}
