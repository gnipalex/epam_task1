package com.epam.hnyp.task7.subtask1.factory;

import java.net.Socket;
import java.util.Map;

import com.epam.hnyp.task7.subtask1.command.Command;

public class CommandRequestProcessorFactory implements RequestProcessorFactory {
	private final Map<String, Command> commands;

	public CommandRequestProcessorFactory(Map<String, Command> commands) {
		this.commands = commands;
	}

	@Override
	public Runnable getRequestProcessor(Socket socket) {
		return new CommandRequestProcessor(socket, commands);
	}

}
