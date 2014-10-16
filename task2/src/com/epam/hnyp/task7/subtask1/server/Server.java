package com.epam.hnyp.task7.subtask1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.epam.hnyp.task7.subtask3.factory.RequestHandlerFactory;

public class Server {
	private static final Logger LOG = Logger.getLogger(Server.class); 
	
	public static final int DEFAULT_PORT = 3000;
	
	private final int port;
	private final RequestHandlerFactory procFactory;
	
	private boolean running;
	
	private Thread worker;
	
	public Server(RequestHandlerFactory procFactory) {
		this(DEFAULT_PORT, procFactory);
	}

	/**
	 * 
	 * @param port value from 0 to 65535
	 * @param procFactory
	 * @throws IllegalArgumentException if port is not in [0..65535]
	 */
	public Server(int port,
			RequestHandlerFactory procFactory) {
		if (port < 0 || port > 65535) {
			throw new IllegalArgumentException();
		}
		this.port = port;
		this.procFactory = procFactory;
	}
	
	/**
	 * Starts server at specified port. If server is already running - will no have effect.
	 */
	public synchronized void start() {
		if (!running) {
			running = true;
			worker = new Thread(new ServerRunnable());
			worker.setDaemon(true);
			worker.start();
		}
	}
	/**
	 * Stops running server. If server is not running - will no have effect.
	 */
	public synchronized void stop() {
		if (running) {
			worker.interrupt();
			running = false;
		}
	}
	
	public boolean isRunning() {
		return running;
	}
	
	private class ServerRunnable implements Runnable {

		private static final int ACCEPT_TIMEOUT = 100;
		
		@Override
		public void run() {
			ServerSocket serverSocket = null;
			try {
				serverSocket = new ServerSocket(port);
				serverSocket.setSoTimeout(ACCEPT_TIMEOUT);
				while (!Thread.currentThread().isInterrupted()) {
					Socket clientSocket = null;
					try {
						clientSocket = serverSocket.accept();
					} catch (SocketTimeoutException e) {
						continue;
					}
					clientSocket.setTcpNoDelay(true);
					Thread th = new Thread(procFactory.getRequestHandler(clientSocket));
					th.start();
				}
			} catch (IOException e) {
				LOG.error("unexpected I/O exception in server thread");
			} finally {
				if (serverSocket != null) {
					try {
						serverSocket.close();
					} catch (IOException e) {}
				}
			}
		}
		
	}
	
}


