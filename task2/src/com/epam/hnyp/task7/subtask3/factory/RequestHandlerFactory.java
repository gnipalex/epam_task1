package com.epam.hnyp.task7.subtask3.factory;

import java.net.Socket;

public interface RequestHandlerFactory {
	Runnable getRequestHandler(Socket socket);
}
