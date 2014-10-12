package com.epam.hnyp.task7.subtask4.factory;

import java.net.Socket;
import java.util.Map;

import com.epam.hnyp.task7.subtask1.factory.RequestHandlerFactory;
import com.epam.hnyp.task7.subtask4.command.Command;
import com.epam.hnyp.task7.subtask4.handler.CgiHttpRequestHandler;

public class CgiHttpRequestHandlerFactory implements RequestHandlerFactory {

	private Map<String, Command> commands;
	
	public CgiHttpRequestHandlerFactory(Map<String, Command> commands) {
		this.commands = commands;
	}

	@Override
	public Runnable getRequestHandler(Socket socket) {
		return new CgiHttpRequestHandler(socket, commands);
	}

}
