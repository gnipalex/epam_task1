package com.epam.hnyp.task7.subtask4.command;

import java.util.Map;

import org.apache.log4j.Logger;

public class SumCommand implements Command {
	private static final Logger LOG = Logger.getLogger(SumCommand.class);
	
	public static final String PARAM_A = "a";
	public static final String PARAM_B = "b"; 
	
	@Override
	public String process(Map<String, String> params) {
		StringBuilder out = new StringBuilder();
		
		String strA = params.get(PARAM_A), strB = params.get(PARAM_B);
		if (strA == null || strB == null) {
			LOG.error("some of arguments are not specified");
			out.append("bad args");
			return out.toString();
		}
		
		int a = 0, b = 0;
		try {
			a = Integer.parseInt(strA);
			b = Integer.parseInt(strB);
		} catch (NumberFormatException e) {
			LOG.error("some of arguments have illegal format");
			out.append("args illegal format");
			return out.toString();
		}
		
		out.append("sum of ").append(a).append(" and ").append(b).append(" = ").append(a + b);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("response : " + out.toString());
		}
		
		return out.toString();
	}

}
