package com.epam.hnyp.task7.subtask1.factory;

import java.net.Socket;

public interface RequestProcessorFactory {
	Runnable getRequestProcessor(Socket socket);
}
