package com.epam.hnyp.task7.subtask2.factory;

import java.net.Socket;

import com.epam.hnyp.task7.subtask2.handler.HttpRequestHandler;
import com.epam.hnyp.task7.subtask3.factory.RequestHandlerFactory;

public class HttpRequestHandlerFactory implements RequestHandlerFactory {
	
	@Override
	public Runnable getRequestHandler(Socket socket) {
		return new HttpRequestHandler(socket);
	}

}
