package com.epam.hnyp.task7.subtask1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.hnyp.task7.subtask1.factory.RequestProcessorFactory;

public class Server implements Runnable {
	public static final int PORT = 3000;
	
	private final int port;
	private RequestProcessorFactory procFactory;
	
	public Server(RequestProcessorFactory procFactory) {
		this(PORT, procFactory);
	}

	/**
	 * 
	 * @param port value from 0 to 65535
	 * @param procFactory
	 * @throws IllegalArgumentException if port is not in [0..65535]
	 */
	public Server(int port,
			RequestProcessorFactory procFactory) {
		if (port < 0 || port > 65535) {
			throw new IllegalArgumentException();
		}
		this.port = port;
		this.procFactory = procFactory;
	}

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				clientSocket.setTcpNoDelay(true);
				Thread th = new Thread(procFactory.getRequestProcessor(clientSocket));
				th.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


