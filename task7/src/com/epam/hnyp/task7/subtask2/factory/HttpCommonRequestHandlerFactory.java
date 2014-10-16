package com.epam.hnyp.task7.subtask2.factory;

import java.net.Socket;
import java.util.Map;

import com.epam.hnyp.task7.subtask2.handler.HttpCommonRequestHandler;
import com.epam.hnyp.task7.subtask3.factory.RequestHandlerFactory;
import com.epam.hnyp.task7.subtask4.command.Command;

public class HttpCommonRequestHandlerFactory implements RequestHandlerFactory {
	private Map<String, Command> commands;
	
	public HttpCommonRequestHandlerFactory(Map<String, Command> commands) {
		this.commands = commands;
	}

	@Override
	public Runnable getRequestHandler(Socket socket) {
		return new HttpCommonRequestHandler(socket, commands);
	}

}
