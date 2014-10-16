package com.epam.hnyp.task7.subtask2.handler;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.epam.hnyp.task7.subtask4.command.Command;

public class HttpCommonRequestHandler implements Runnable {
	private static final Logger LOG = Logger.getLogger(HttpCommonRequestHandler.class);
	public static final String PATTERN_GET_HEADER = "^GET\\s(.+)\\sHTTP/1\\.[01]$";
	public static final String CGI_PATTERN = "^cgi/.+";
	public static final String CGI_COMMAND_PATTERN = "^cgi/([A-Za-z\\d-_\\./]+)";
	public static final String HTTP_NEW_LINE = "\r\n";
	private Socket socket;
	private Map<String, Command> commands;

	public HttpCommonRequestHandler(Socket socket, Map<String, Command> commands) {
		this.socket = socket;
		this.commands = commands;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			BufferedOutputStream bos = new BufferedOutputStream(
					socket.getOutputStream());
			
			String requestLine = br.readLine();
			if (!isRequestOfTypeGet(requestLine)) {
				StringBuilder strOutput = new StringBuilder();
				strOutput.append("HTTP/1.0 400 Bad Request");
				strOutput.append(HTTP_NEW_LINE);
				bos.write(strOutput.toString().getBytes("cp1251"));
				bos.flush();
				System.out.println("bad request header");
				LOG.error("request header differs from HTTP GET request");
				return;
			}
			
			System.out.println("Headers for request [" + requestLine + "]");
			System.out.println(readHeaders(br));
			
			String url = extractUrl(requestLine);
			if (isUrlOfCgi(url)) {
				processCgi(url, bos);
			} else {
				processHttp(url, bos);
			}
			
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
	
	private void processHttp(String url, BufferedOutputStream bos) throws IOException {
		StringBuilder strOutput = new StringBuilder();
		File requestedFile = new File(url);
		if (!requestedFile.exists() || !requestedFile.isFile()) {
			//error status 404
			strOutput.append("HTTP/1.0 404 Not Found").append(HTTP_NEW_LINE);
			strOutput.append(HTTP_NEW_LINE);
			strOutput.append("404 Not Found").append(HTTP_NEW_LINE);
			bos.write(strOutput.toString().getBytes("cp1251"));
			
			LOG.error("file '" + requestedFile.getName() + "' not found");
			
			return;
		}
		strOutput.append("HTTP/1.0 200 OK").append(HTTP_NEW_LINE);
		strOutput.append("Content-Type: text/html; charset=windows-1251").append(HTTP_NEW_LINE);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("all ok, file found and sending to client");
		}
		
		if (requestedFile.length() != 0) {
			strOutput.append("Content-Length: " + requestedFile.length());
			strOutput.append(HTTP_NEW_LINE).append(HTTP_NEW_LINE);
			bos.write(strOutput.toString().getBytes("cp1251"));
			try (FileInputStream fis = new FileInputStream(requestedFile)){
				final int buferSz = 1024;
				byte[] buf = new byte[buferSz];
				int actualRead = 0;
				while(fis.available() > 0) {
					actualRead = fis.read(buf);
					bos.write(buf, 0, actualRead);
				}
			} catch (IOException e) {
				LOG.error("error while writing requested file to output stream");
			}
		}
	}
	
	private void processCgi(String url, BufferedOutputStream bos) throws IOException {
		StringBuilder strOutput = new StringBuilder();
		
		String cgiCommand = extractCgiCommand(url);
		Command command = commands.get(cgiCommand);
		
		if (cgiCommand.isEmpty() || command == null) {
			LOG.error("command '" + cgiCommand + "' not found");
			strOutput.append("HTTP/1.0 404 Not Found").append(HTTP_NEW_LINE);
			strOutput.append(HTTP_NEW_LINE);
			strOutput.append("404 Not Found").append(HTTP_NEW_LINE);
			bos.write(strOutput.toString().getBytes("cp1251"));
			//bos.flush();
			return;
		}
		
		strOutput.append("HTTP/1.0 200 OK").append(HTTP_NEW_LINE);
		strOutput.append("Content-Type: text/html; charset=windows-1251").append(HTTP_NEW_LINE);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("all OK, processing command '" + cgiCommand + "'");
		}
		
		Map<String, String> requestParams = extractParams(url);
		String response = command.process(requestParams);
		byte[] responseBytes = response.getBytes("cp1251");
		strOutput.append("Content-Length: " + responseBytes.length).append(HTTP_NEW_LINE);
		strOutput.append(HTTP_NEW_LINE);
		
		bos.write(strOutput.toString().getBytes("cp1251"));
		bos.write(responseBytes);
	}
	
	private String readHeaders(BufferedReader br) throws IOException {
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
	
	public boolean isUrlOfCgi(String url) {
		return url.matches(CGI_PATTERN);
	}
	
	public String extractUrl(String header){
		Matcher matcher = Pattern.compile(PATTERN_GET_HEADER).matcher(header);
		if (matcher.find()) {
			return matcher.group(1).substring(1);
		}
		return "";
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
		Matcher m = Pattern.compile(CGI_COMMAND_PATTERN).matcher(url);
		if (m.find()) {
			return m.group(1);
		}
		return "";
	}
}
