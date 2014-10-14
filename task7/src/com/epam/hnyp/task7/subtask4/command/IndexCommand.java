package com.epam.hnyp.task7.subtask4.command;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.log4j.Logger;

public class IndexCommand implements Command {
	private static final Logger LOG = Logger.getLogger(IndexCommand.class);
	
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
			
			LOG.error("index page file '" + indexFileName + "' not found");
			
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
			
			LOG.error("error while reading index page file '" + indexFileName + "' not found");
			
			return out.toString();
		}
		
		if (LOG.isInfoEnabled()) {
			LOG.info("sent to response index page file");
		}
		
		return out.toString();
	}

}
