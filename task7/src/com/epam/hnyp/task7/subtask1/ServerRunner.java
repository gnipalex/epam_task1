package com.epam.hnyp.task7.subtask1;

import java.util.Map;
import java.util.Scanner;

import com.epam.hnyp.task7.subtask1.command.Command;
import com.epam.hnyp.task7.subtask1.command.CommandInitializer;
import com.epam.hnyp.task7.subtask1.facade.ProductsFacade;
import com.epam.hnyp.task7.subtask1.factory.CommandRequestProcessorFactory;
import com.epam.hnyp.task7.subtask1.factory.RequestProcessorFactory;
import com.epam.hnyp.task7.subtask1.server.Server;

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
		
		Map<String, Command> commands = CommandInitializer.initCommands(facade);
		RequestProcessorFactory reqFactory = new CommandRequestProcessorFactory(commands);
		
		Thread serverThread = new Thread(new Server(reqFactory));
		serverThread.setDaemon(true);
		serverThread.start();

		Scanner sc = new Scanner(System.in);
		System.out.println("input any key to stop server");
		sc.nextLine();
	}
}
