package com.epam.hnyp.task7.subtask4.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.hnyp.task7.subtask2.handler.HttpRequestHandler;
import com.epam.hnyp.task7.subtask4.command.Command;

public class CgiHttpRequestHandler extends HttpRequestHandler {
	private Map<String, Command> commands;
	
	public static final String CGI_CORRECT_URL_PATTERN = "^cgi/([A-Za-z\\d-_\\./]+)";
	
	public CgiHttpRequestHandler(Socket socket, Map<String, Command> commands) {
		super(socket);
		this.commands = commands;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String requestLine = br.readLine();
			if (!isRequestOfTypeGet(requestLine)) {
				System.out.println("bad request [" + requestLine + "]");
				bw.write("HTTP/1.0 400 Bad Request");
				bw.newLine();
				bw.flush();
				return;
			}
			
			String url = extractUrl(requestLine);
			String cgiCommand = extractCgiCommand(url);
			Command command = commands.get(cgiCommand);
			
			if (cgiCommand.isEmpty() || command == null) {
				bw.write("HTTP/1.0 404 Not Found");
				bw.newLine();
				bw.flush();
				return;
			}
			
			bw.write("HTTP/1.0 200 OK");
			bw.newLine();
			bw.write("Content-Type: text/html; charset=windows-1251");
			bw.newLine();
			bw.newLine();
			
			Map<String, String> requestParams = extractParams(url);
			
			bw.write(command.process(requestParams));
			bw.newLine();
			bw.flush();
		}  catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
	
	public Map<String, String> extractParams(String url) {
		Map<String, String> params = new HashMap<>();
		int start = url.indexOf('?');
		if (start < 0) {
			return params;
		}
		String onlyParams = url.substring(start + 1);
		if (!onlyParams.isEmpty()) {
			String[] tokens = onlyParams.split("&");
			for (String t : tokens) {
				String[] pair = t.split("=");
				if (pair.length == 1) {
					params.put(pair[0], null);
				} else {
					params.put(pair[0], pair[1]);
				}
			}
		}
		return params;
	}
	
	public String extractCgiCommand(String url) {
		Matcher m = Pattern.compile(CGI_CORRECT_URL_PATTERN).matcher(url);
		if (m.find()) {
			return m.group(1);
		}
		return "";
	}
}
