package com.epam.hnyp.task7.subtask1.factory;

import java.net.Socket;

import com.epam.hnyp.task7.subtask1.facade.ProductsFacade;
import com.epam.hnyp.task7.subtask1.handler.SimpleRequestHandler;

public class SimpleRequestHandlerFactory implements RequestHandlerFactory {
	private ProductsFacade prodFacade;

	public SimpleRequestHandlerFactory(ProductsFacade prodFacade) {
		this.prodFacade = prodFacade;
	}

	@Override
	public Runnable getRequestHandler(Socket socket) {
		return new SimpleRequestHandler(socket, prodFacade);
	}

}
