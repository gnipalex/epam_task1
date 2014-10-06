package com.epam.hnyp.task7.subtask1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GetCommand implements Command  {

	@Override
	public String process(String params) {
		Matcher mItems = Pattern.compile("(?i)^\\s*item\\s*=\\s*([-\\d]+)").matcher(params);
		Matcher mCount = Pattern.compile("(?i)^\\s*count").matcher(params);
		if ()
	}
	
}
