package com.epam.hnyp.task7.subtask2.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequestHandler implements Runnable {
	protected Socket socket;
	public static final String PATTERN_GET_HEADER = "^GET\\s(.+)\\sHTTP/1\\.[01]$"; 

	public HttpRequestHandler(Socket socket) {
		this.socket = socket;
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
			
			System.out.println("Headers for request [" + requestLine + "]");
			System.out.println(readHeaders(br));

			File requestedFile = new File(extractUrl(requestLine));
			if (!requestedFile.exists() || !requestedFile.isFile()) {
				//error status 404
				bw.write("HTTP/1.0 404 Not Found");
				bw.newLine();
				bw.flush();
				return;
			}
			
			bw.write("HTTP/1.0 200 OK");
			bw.newLine();
			bw.write("Content-Type: text/html; charset=windows-1251"); //Content-Length
			bw.newLine();
			
			if (requestedFile.length() == 0) {
				bw.write("Content-Length: 0");
				bw.newLine();
			} else {
				bw.write("Content-Length: " + requestedFile.length());
				bw.newLine();
				bw.newLine();
				Scanner sc = new Scanner(requestedFile);
				while (sc.hasNext()) {
					String line = sc.nextLine();
					bw.write(line);
					bw.newLine();
				}
				sc.close();
			}
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
	
	private String readHeaders( BufferedReader br) throws IOException {
		StringBuilder out = new StringBuilder();
		out.append("==>");
		out.append(System.lineSeparator());
		String headerLine = null;			
		while (!(headerLine = br.readLine()).isEmpty()) {
			out.append(headerLine);
			out.append(System.lineSeparator());
		}
		out.append("<==");
		out.append(System.lineSeparator());
		return out.toString();
	}
	
	public boolean isRequestOfTypeGet(String header) {
		return header.matches(PATTERN_GET_HEADER);
	}
	
	public String extractUrl(String header){
		Matcher matcher = Pattern.compile(PATTERN_GET_HEADER).matcher(header);
		if (matcher.find()) {
			return matcher.group(1).substring(1);
		}
		return "";
	}

}
