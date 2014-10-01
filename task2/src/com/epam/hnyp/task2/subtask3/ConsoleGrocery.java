package com.epam.hnyp.task2.subtask3;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.factory.CommandInitializer;
import com.epam.hnyp.task2.subtask3.factory.CommandInitializerImpl;
import com.epam.hnyp.task2.subtask3.factory.ProductsInitializer;
import com.epam.hnyp.task2.subtask3.factory.ServicesInMemoryInitializer;
import com.epam.hnyp.task2.subtask3.factory.ServicesInitializer;
import com.epam.hnyp.task2.subtask3.factory.ServicesInitializer.ServicesContainer;
import com.epam.hnyp.task2.subtask3.util.ConsoleIOProvider;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class ConsoleGrocery {
	public static void main(String[] args) {
		ServicesInitializer factory = new ServicesInMemoryInitializer();
		ServicesContainer servicesContainer = factory.buildServicesContainer(5);
		
		ProductsInitializer.fillProducts(servicesContainer.getProductsService(), 1);
		
		IOProvider ioProvider = new ConsoleIOProvider();
		
		CommandInitializer commandInitializer = new CommandInitializerImpl(servicesContainer.getShopFacade(), ioProvider);
		
		AbstractCommand shopCommand = commandInitializer.initMainCommand();
		shopCommand.execute();
	}
}
