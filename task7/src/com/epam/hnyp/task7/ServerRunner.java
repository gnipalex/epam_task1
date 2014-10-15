package com.epam.hnyp.task7;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.hnyp.task7.subtask1.server.Server;
import com.epam.hnyp.task7.subtask3.factory.RequestHandlerFactory;

public class ServerRunner {

	private static final Logger LOG = Logger.getLogger(ServerRunner.class);

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
			switch (line.charAt(0)) {
			case '1':
				chosenFactory = HandlerFactoriesFactory
						.newSimpleRequestHandlerFactory();
				break;
			case '2':
				chosenFactory = HandlerFactoriesFactory
						.newHttpRequestHandlerFactory();
				break;
			case '3':
				chosenFactory = HandlerFactoriesFactory
						.newCgiHttpRequestHandlerFactory();
				break;
			default:
				System.out
						.println("bad input, you must specify one of modes from list above");
				LOG.error("server work mode choose, bad args input");
			}
		}
		return chosenFactory;

	}

	public static void main(String[] args) {
		if (LOG.isInfoEnabled()) {
			LOG.info("started server runner");
		}
		RequestHandlerFactory reqFactory = chooseFactory();

		if (reqFactory == null) {
			System.out.println("abort");
			if (LOG.isInfoEnabled()) {
				LOG.info("RequestHandlerFactory was not specified, aborting server start");
			}
			return;
		}

		Thread serverThread = new Thread(new Server(reqFactory));
		serverThread.setDaemon(true);
		serverThread.start();

		System.out.println("Server started");
		if (LOG.isInfoEnabled()) {
			LOG.info("server daemon started at default port 3000");
		}

		Scanner sc = new Scanner(System.in);
		System.out.println("input any key to stop server");
		sc.nextLine();
		if (LOG.isInfoEnabled()) {
			LOG.info("server shutdown");
		}
	}
}
