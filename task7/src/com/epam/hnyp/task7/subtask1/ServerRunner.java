package com.epam.hnyp.task7.subtask1;

import java.util.Scanner;

import com.epam.hnyp.task7.subtask1.facade.ProductsFacade;
import com.epam.hnyp.task7.subtask1.factory.RequestHandlerFactory;
import com.epam.hnyp.task7.subtask1.factory.SimpleRequestHandlerFactory;
import com.epam.hnyp.task7.subtask1.server.Server;
import com.epam.hnyp.task7.subtask2.factory.HttpRequestHandlerFactory;

public class ServerRunner {
	public static void main(String[] args) {
		ProductsFacade facade = new ProductsFacade() {
			@Override
			public int getCount() {
				return 99;
			}
			@Override
			public ProductInfo getProductInfo(long id) {
				if (id >= 0 && id < 10) {
					return new ProductInfo("aasasasas", 333);
				}
				return new ProductInfo("ZZZZZZ", 666);
			}		
		};
		
		//Map<String, Command> commands = CommandInitializer.initCommands(facade);
		RequestHandlerFactory reqFactory = new SimpleRequestHandlerFactory(facade);
		RequestHandlerFactory httpFactory = new HttpRequestHandlerFactory();
		
		//Thread serverThread = new Thread(new Server(reqFactory));
		Thread serverThread = new Thread(new Server(httpFactory));
		serverThread.setDaemon(true);
		serverThread.start();

		Scanner sc = new Scanner(System.in);
		System.out.println("input any key to stop server");
		sc.nextLine();
	}
}
