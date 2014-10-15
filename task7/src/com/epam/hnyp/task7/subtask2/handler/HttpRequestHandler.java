package com.epam.hnyp.task7.subtask2.handler;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class HttpRequestHandler implements Runnable {
	private static final Logger LOG = Logger.getLogger(HttpRequestHandler.class);
	
	protected Socket socket;
	public static final String PATTERN_GET_HEADER = "^GET\\s(.+)\\sHTTP/1\\.[01]$"; 

	public HttpRequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String requestLine = br.readLine();
			if (!isRequestOfTypeGet(requestLine)) {;
				System.out.println("bad request header");
				LOG.error("request header differs from HTTP GET request");
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
				bw.newLine();
				bw.write("404 Not Found");
				bw.newLine();
				bw.flush();
				
				LOG.error("file '" + requestedFile.getName() + "' not found");
				
				return;
			}
			
			bw.write("HTTP/1.0 200 OK");
			bw.newLine();
			bw.write("Content-Type: text/html; charset=windows-1251"); //Content-Length
			bw.newLine();
			
			if (LOG.isInfoEnabled()) {
				LOG.info("all ok, file found and sending to client");
			}
			
			if (requestedFile.length() != 0) {
				//bw.write("Content-Length: " + requestedFile.length());
				bw.newLine();
				bw.newLine();
//				Scanner sc = new Scanner(requestedFile);
//				while (sc.hasNext()) {
//					String line = sc.nextLine();
//					bw.write(line);
//					bw.newLine();
//				}
//				sc.close();
				try (FileInputStream fis = new FileInputStream(requestedFile)){
					final int buferSz = 1024;
					byte[] buf = new byte[buferSz];
					int actualRead = 0;
					while(fis.available() > 0) {
						actualRead = fis.read(buf);
						bos.write(buf, 0, actualRead);
					}
				} catch (IOException e) {
					
				}
			}
			bw.newLine();
			bw.flush();
			bos.flush();
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
