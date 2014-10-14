package com.epam.hnyp.task7.subtask4.command;

import java.util.Map;

import org.apache.log4j.Logger;

public class SayCommand implements Command {
	private static final Logger LOG = Logger.getLogger(SayCommand.class);
	public static final String PARAM_WORD = "word"; 
	
	@Override
	public String process(Map<String, String> params) {
		StringBuilder out = new StringBuilder();
		String word = params.get(PARAM_WORD);
		if (word == null || word.isEmpty()) {
			out.append("give me a word!!");
			LOG.error("parameter word is not specified");
		} else {
			out.append("hello!! I'm saying : '").append(word).append("'");
		}
		
		if (LOG.isInfoEnabled()) {
			LOG.info("response : " + out.toString());
		}
		
		return out.toString();
	}

}
