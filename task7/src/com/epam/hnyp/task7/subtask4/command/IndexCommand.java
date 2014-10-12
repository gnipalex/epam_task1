package com.epam.hnyp.task7.subtask4.command;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class IndexCommand implements Command {

	private String indexFileName;
	
	public IndexCommand(String indexFileName) {
		this.indexFileName = indexFileName;
	}

	@Override
	public String process(Map<String, String> params) {
		StringBuilder out = new StringBuilder();
		
		FileInputStream fis = null; 
		try {
			fis = new FileInputStream(indexFileName);
		} catch (FileNotFoundException e) {
			out.append("oups, index page file not found");
			return out.toString();
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				out.append(line);
				out.append("\n");
			}
		} catch (IOException e) {
			out.append("oups, server error");
			return out.toString();
		}
		
		return out.toString();
	}

}
