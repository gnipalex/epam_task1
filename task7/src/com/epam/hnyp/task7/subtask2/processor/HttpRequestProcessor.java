package com.epam.hnyp.task7.subtask2.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.regex.Pattern;

public class HttpRequestProcessor implements Runnable {
	private Socket socket;
	private static final String PATTERN_GET_HEADER = "^GET\\s(.+)\\sHTTP/1\\.[01]$"; 

	public HttpRequestProcessor(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (IOException e) {
			
		}
	}
	
	private boolean isRequestOfTypeGet(String header) {
		return header.matches(PATTERN_GET_HEADER);
	}

}
