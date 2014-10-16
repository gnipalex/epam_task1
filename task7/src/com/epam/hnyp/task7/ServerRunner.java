package com.epam.hnyp.task7;

import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.hnyp.task7.subtask1.server.Server;
import com.epam.hnyp.task7.subtask2.factory.HttpCommonRequestHandlerFactory;
import com.epam.hnyp.task7.subtask3.factory.RequestHandlerFactory;
import com.epam.hnyp.task7.subtask4.command.Command;
import com.epam.hnyp.task7.subtask4.command.CommandInitializer;

public class ServerRunner {

	private static final Logger LOG = Logger.getLogger(ServerRunner.class);

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		if (LOG.isInfoEnabled()) {
			LOG.info("started server runner");
		}
		
		Map<String, Command> commands = CommandInitializer.initCommands();
		RequestHandlerFactory handlerFactory = new HttpCommonRequestHandlerFactory(commands);;
		
		Server server = new Server(handlerFactory);
		server.start();
		
		System.out.println("Server started");
		if (LOG.isInfoEnabled()) {
			LOG.info("server started at default port 3000");
		}

		System.out.println("input any key to stop server");
		scanner.nextLine();
		
		server.stop();
		System.out.println("Server stopped");
		if (LOG.isInfoEnabled()) {
			LOG.info("server shutdown");
		}
	}
}
