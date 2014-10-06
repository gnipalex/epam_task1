package com.epam.hnyp.task7.subtask1;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {
	public static final int PORT = 3000;
	public static final int MAX_CONNECTIONS = 10;
	
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT, MAX_CONNECTIONS);
			ExecutorService execPool = Executors.newFixedThreadPool(MAX_CONNECTIONS);
			while (true) {
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


