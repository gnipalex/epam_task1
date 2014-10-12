package com.epam.hnyp.task7.subtask4.command;

import java.util.Map;

public class SayCommand implements Command {

	public static final String PARAM_WORD = "word"; 
	
	@Override
	public String process(Map<String, String> params) {
		StringBuilder out = new StringBuilder();
		String word = params.get(PARAM_WORD);
		if (word == null || word.isEmpty()) {
			out.append("give me a word!!");
		} else {
			out.append("hello!! I'm saying : '").append(word).append("'");
		}
		return out.toString();
	}

}
