package com.epam.hnyp.task7.subtask1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestProcessor implements Runnable {
	private Socket socket;
	private Map<String, Command> commands;
	
	public RequestProcessor(Socket socket, Map<String, Command> commands) {
		this.socket = socket;
		this.commands = commands;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String line = br.readLine();
			String key = getCommand(line);
			String params = getParams(line);
			
			Command command  = commands.get(key);
			if (command == null) {
				bw.write("command not found");
				return;
			}
			bw.write(command.process(params));
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
		Matcher m = Pattern.compile("[-\\\\/\\w]+").matcher(line);
		if (m.find()) {
			return m.group();
		}
		return null;
	}
	
	private String getParams(String line) {
		Matcher m = Pattern.compile("\\w+\\s+(.*)").matcher(line);
		if (m.find()) {
			m.group(1);
		}
		return "";
	}
}
