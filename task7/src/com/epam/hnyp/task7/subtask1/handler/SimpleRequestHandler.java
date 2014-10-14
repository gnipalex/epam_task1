package com.epam.hnyp.task7.subtask1.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.epam.hnyp.task7.subtask1.facade.ProductsFacade;

public class SimpleRequestHandler implements Runnable {
	private static final Logger log = Logger.getLogger(SimpleRequestHandler.class);
	
	private Socket socket;
	private ProductsFacade prodFacade;
	
	public SimpleRequestHandler(Socket socket, ProductsFacade prodFacade) {
		this.socket = socket;
		this.prodFacade = prodFacade;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String line = br.readLine();
			if (log.isInfoEnabled()) {
				log.info("starting processing simple request --> '" + line + "'");
			}
			String key = extractCommand(line);
			String params = extractParams(line);
			
			if (!key.equals("get")) {
				bw.write("unknown command '" + key + "'");
				if (log.isInfoEnabled()) {
					log.info("unknown command '" + key + "'");
				}
			} else {
				String response = process(params);
				bw.write(response);
				if (log.isInfoEnabled()) {
					log.info("response --> '" + response + "'");
				}
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
	
	private String process(String params) {
		StringBuilder response = new StringBuilder();
		Matcher mItem = Pattern.compile("(?i)^\\s*item").matcher(params);
		Matcher mCount = Pattern.compile("(?i)^\\s*count").matcher(params);
		if (mItem.find()) {
			getItem(response, params);
		} else if (mCount.find()) {
			getCount(response);
		} else {
			response.append("#get : bad args");
			if (log.isInfoEnabled()) {
				log.info("command get : bad args");
			}
		}		
		return response.toString();
	}
	
	private void getItem(StringBuilder response, String params) {
		Matcher idMatcher = Pattern.compile("item\\s*=\\s*([-\\d]+)").matcher(params);
		if (!idMatcher.find()){
			response.append("#get item : bad format");
			return;
		}
		long id = 0;
		try {
			id = Long.parseLong(idMatcher.group(1));
		} catch (NumberFormatException e) {
			response.append("#get item : bad args format");
			return;
		}
		ProductsFacade.ProductInfo prodInfo = prodFacade.getProductInfo(id);
		if (prodInfo == null) {
			response.append("not found");
			return;
		} 
		response.append(prodInfo.getName()).append("|").append(prodInfo.getPrice());
	}
	
	private void getCount(StringBuilder response) {
		response.append(prodFacade.getCount());
	}
	
	
	private String extractCommand(String line) {
		Matcher m = Pattern.compile("[-\\\\/\\w]+").matcher(line);
		if (m.find()) {
			return m.group();
		}
		return "";
	}
	
	private String extractParams(String line) {
		Matcher m = Pattern.compile("\\w+\\s+(.*)").matcher(line);
		if (m.find()) {
			return m.group(1);
		}
		return "";
	}
}
