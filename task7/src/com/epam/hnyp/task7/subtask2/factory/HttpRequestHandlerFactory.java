package com.epam.hnyp.task7.subtask2.factory;

import java.net.Socket;

import com.epam.hnyp.task7.subtask1.factory.RequestHandlerFactory;
import com.epam.hnyp.task7.subtask2.handler.HttpRequestHandler;

public class HttpRequestHandlerFactory implements RequestHandlerFactory {

	@Override
	public Runnable getRequestProcessor(Socket socket) {
		return new HttpRequestHandler(socket);
	}

}
