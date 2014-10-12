package com.epam.hnyp.task7;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.initializer.ProductsInitializer;
import com.epam.hnyp.task2.subtask3.repo.ProductRepo;
import com.epam.hnyp.task2.subtask3.repo.impl.ProductRepoInMemory;
import com.epam.hnyp.task2.subtask3.service.impl.ProductsServiceImpl;
import com.epam.hnyp.task7.subtask1.facade.ProductsShopFacade;
import com.epam.hnyp.task7.subtask1.factory.RequestHandlerFactory;
import com.epam.hnyp.task7.subtask1.factory.SimpleRequestHandlerFactory;
import com.epam.hnyp.task7.subtask2.factory.HttpRequestHandlerFactory;
import com.epam.hnyp.task7.subtask4.command.Command;
import com.epam.hnyp.task7.subtask4.command.CommandInitializer;
import com.epam.hnyp.task7.subtask4.factory.CgiHttpRequestHandlerFactory;

public class HandlerFactoriesFactory {
	
	public static RequestHandlerFactory newSimpleRequestHandlerFactory() {
		ProductRepo repo = new ProductRepoInMemory();
		ProductsServiceImpl prodService = new ProductsServiceImpl();
		prodService.setGoodRepo(repo);
		
		ProductsInitializer.fillProducts(prodService, 1);
		
		ProductsShopFacade prodFacade = new ProductsShopFacade();
		prodFacade.setProdService(prodService);
		
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
