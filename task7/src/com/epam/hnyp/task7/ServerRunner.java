package com.epam.hnyp.task7;

import java.util.Scanner;

import com.epam.hnyp.task7.subtask1.facade.ProductsFacade;
import com.epam.hnyp.task7.subtask1.factory.RequestHandlerFactory;
import com.epam.hnyp.task7.subtask1.factory.SimpleRequestHandlerFactory;
import com.epam.hnyp.task7.subtask1.server.Server;
import com.epam.hnyp.task7.subtask2.factory.HttpRequestHandlerFactory;

public class ServerRunner {

	private static Scanner scanner = new Scanner(System.in);

	private static RequestHandlerFactory chooseFactory() {
		System.out.println("Choose server work mode :");
		System.out.println("1 - simple command (task1)");
		System.out.println("2 - http get (task2)");
		System.out.println("3 - http get cgi/XXX (task4)");
		String line = null;
		RequestHandlerFactory chosenFactory = null;
		System.out.print("Choise : ");
		line = scanner.nextLine();
		if (!line.isEmpty()) {
			switch(line.charAt(0)) {
			case '1':
				chosenFactory = HandlerFactoriesFactory.newSimpleRequestHandlerFactory();
				break;
			case '2':
				chosenFactory = HandlerFactoriesFactory.newHttpRequestHandlerFactory();
				break;
			case '3':
				chosenFactory = HandlerFactoriesFactory.newCgiHttpRequestHandlerFactory();
				break;
				default:
					System.out.println("bad input, you must specify one of modes from list above");
			}
		}
		return chosenFactory;

	}

	public static void main(String[] args) {
		RequestHandlerFactory reqFactory = chooseFactory();
		
		if (reqFactory == null) {
			return;
		}

		Thread serverThread = new Thread(new Server(reqFactory));
		serverThread.setDaemon(true);
		serverThread.start();
		
		System.out.println("Server started");

		Scanner sc = new Scanner(System.in);
		System.out.println("input any key to stop server");
		sc.nextLine();
	}
}
