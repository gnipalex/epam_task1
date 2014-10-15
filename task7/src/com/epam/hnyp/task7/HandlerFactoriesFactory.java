package com.epam.hnyp.task7;

import java.util.Map;

import com.epam.hnyp.task7.subtask1.facade.ProductsFacade;
import com.epam.hnyp.task7.subtask1.factory.SimpleRequestHandlerFactory;
import com.epam.hnyp.task7.subtask2.factory.HttpRequestHandlerFactory;
import com.epam.hnyp.task7.subtask3.factory.RequestHandlerFactory;
import com.epam.hnyp.task7.subtask4.command.Command;
import com.epam.hnyp.task7.subtask4.command.CommandInitializer;
import com.epam.hnyp.task7.subtask4.factory.CgiHttpRequestHandlerFactory;

public class HandlerFactoriesFactory {
	
	//to remove
	public static RequestHandlerFactory newSimpleRequestHandlerFactory() {
		ProductsFacade prodFacade = new ProductsFacade() {
			@Override
			public int getCount() {
				return 2;
			}

			@Override
			public ProductInfo getProductInfo(long id) {
				return new ProductInfo("aaa", 321);
			}	
		};
		return new SimpleRequestHandlerFactory(prodFacade);
	}
	
	public static RequestHandlerFactory newHttpRequestHandlerFactory() {
		return new HttpRequestHandlerFactory();
	}
	
	public static RequestHandlerFactory newCgiHttpRequestHandlerFactory() {
		Map<String, Command> commands = CommandInitializer.initCommands();
		return new CgiHttpRequestHandlerFactory(commands);
	}
}
