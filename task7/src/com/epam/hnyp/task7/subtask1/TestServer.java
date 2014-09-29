package com.epam.hnyp.task7.subtask1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestServer extends Thread {
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

class RequestProcessor implements Runnable {
	private Socket socket;
	private Map<String, Command> commands = new HashMap<String, Command>();
	{
		commands.put("get", new GetCommand());
	}
	
	public RequestProcessor(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String line = br.readLine();
			String cmd = getCommand(line);
			String params = getParams(line);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
		
	}
	
	private String getCommand(String line) {
		Matcher m = Pattern.compile("(.+)\\s").matcher(line);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}
	
	private String getParams(String line) {
		Matcher m = Pattern.compile(".+\\s+(.*)").matcher(line);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}
}

interface Command {
	String process(String params);
}

class GetCommand implements Command {

	@Override
	public String process(String params) {
		return null;
	}
}


