package com.epam.hnyp.task2.subtask3.initializer;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
import com.epam.hnyp.task2.subtask3.command.AddProductCommand;
import com.epam.hnyp.task2.subtask3.command.AddToCartCommand;
import com.epam.hnyp.task2.subtask3.command.CartPriceCommand;
import com.epam.hnyp.task2.subtask3.command.EmptyCartCommand;
import com.epam.hnyp.task2.subtask3.command.FindOrderCommand;
import com.epam.hnyp.task2.subtask3.command.MainMenuCommand;
import com.epam.hnyp.task2.subtask3.command.MakeOrderCommand;
import com.epam.hnyp.task2.subtask3.command.OrdersByPeriodCommand;
import com.epam.hnyp.task2.subtask3.command.OrdersCommand;
import com.epam.hnyp.task2.subtask3.command.PrintProductsCommand;
import com.epam.hnyp.task2.subtask3.command.RemoveElementFromCartCommand;
import com.epam.hnyp.task2.subtask3.command.ShowPopularProductsCommand;
import com.epam.hnyp.task2.subtask3.command.ViewCartCommand;
import com.epam.hnyp.task2.subtask3.creator.AbstractProductCreator;
import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.util.IOProvider;

public class CommandInitializerUltra implements CommandInitializer  {

	private ShopFacade shopFacade;
	private IOProvider ioProvider;
	private AbstractProductCreator productCreator;
	
	public CommandInitializerUltra(ShopFacade shopFacade, IOProvider ioProvider, AbstractProductCreator productCreator) {
		this.productCreator = productCreator;
		this.shopFacade = shopFacade;
		this.ioProvider = ioProvider;
	}
	
	@Override
	public AbstractCommand initMainCommand() {
		AbstractCommand viewCartCommand = new ViewCartCommand(shopFacade, ioProvider);
		AbstractCommand addToCartCommand = new AddToCartCommand(shopFacade, ioProvider); 
		
		int i = 1;
		viewCartCommand.addCommand(String.valueOf(i++), new CartPriceCommand(shopFacade, ioProvider));
		viewCartCommand.addCommand(String.valueOf(i++), addToCartCommand);
		viewCartCommand.addCommand(String.valueOf(i++), new EmptyCartCommand(shopFacade, ioProvider));
		viewCartCommand.addCommand(String.valueOf(i++), new RemoveElementFromCartCommand(shopFacade, ioProvider));
		viewCartCommand.addCommand(String.valueOf(i++), new MakeOrderCommand(shopFacade, ioProvider));
		
		i = 1;
		AbstractCommand ordersCommand = new OrdersCommand(shopFacade, ioProvider);
		ordersCommand.addCommand(String.valueOf(i++), new FindOrderCommand(shopFacade, ioProvider));
		ordersCommand.addCommand(String.valueOf(i++), new OrdersByPeriodCommand(shopFacade, ioProvider));
		
		i = 1;
		AbstractCommand mainCommand = new MainMenuCommand(ioProvider);
		mainCommand.addCommand(String.valueOf(i++), new PrintProductsCommand(shopFacade, ioProvider));
		mainCommand.addCommand(String.valueOf(i++), new ShowPopularProductsCommand(shopFacade, ioProvider));
		mainCommand.addCommand(String.valueOf(i++), addToCartCommand);
		mainCommand.addCommand(String.valueOf(i++), viewCartCommand);
		mainCommand.addCommand(String.valueOf(i++), ordersCommand);
		
		mainCommand.addCommand(String.valueOf(i++), new AddProductCommand(shopFacade, ioProvider, productCreator));
		
		return mainCommand;
	}

}
