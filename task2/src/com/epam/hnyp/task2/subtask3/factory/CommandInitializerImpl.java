package com.epam.hnyp.task2.subtask3.factory;

import com.epam.hnyp.task2.subtask3.command.AbstractCommand;
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
import com.epam.hnyp.task2.subtask3.facade.ShopFacade;

public class CommandInitializerImpl implements CommandInitializer {

	private ShopFacade shopFacade;
	
	public CommandInitializerImpl(ShopFacade shopFacade) {
		this.shopFacade = shopFacade;
	}
	
	@Override
	public AbstractCommand initMainCommand() {
		AbstractCommand viewCartCommand = new ViewCartCommand(shopFacade);
		AbstractCommand addToCartCommand = new AddToCartCommand(shopFacade); 
		
		int i = 1;
		viewCartCommand.addCommand(String.valueOf(i++), new CartPriceCommand(shopFacade));
		viewCartCommand.addCommand(String.valueOf(i++), addToCartCommand);
		viewCartCommand.addCommand(String.valueOf(i++), new EmptyCartCommand(shopFacade));
		viewCartCommand.addCommand(String.valueOf(i++), new RemoveElementFromCartCommand(shopFacade));
		viewCartCommand.addCommand(String.valueOf(i++), new MakeOrderCommand(shopFacade));
		
		i = 1;
		AbstractCommand ordersCommand = new OrdersCommand(shopFacade);
		ordersCommand.addCommand(String.valueOf(i++), new FindOrderCommand(shopFacade));
		ordersCommand.addCommand(String.valueOf(i++), new OrdersByPeriodCommand(shopFacade));
		
		i = 1;
		AbstractCommand mainCommand = new MainMenuCommand();
		mainCommand.addCommand(String.valueOf(i++), new PrintProductsCommand(shopFacade));
		mainCommand.addCommand(String.valueOf(i++), new ShowPopularProductsCommand(shopFacade));
		mainCommand.addCommand(String.valueOf(i++), addToCartCommand);
		mainCommand.addCommand(String.valueOf(i++), viewCartCommand);
		mainCommand.addCommand(String.valueOf(i++), ordersCommand);

		return mainCommand;
	}

}
