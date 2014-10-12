package com.epam.hnyp.task7.subtask4.command;

import java.util.Map;

public class SumCommand implements Command {
	public static final String PARAM_A = "a";
	public static final String PARAM_B = "b"; 
	
	@Override
	public String process(Map<String, String> params) {
		StringBuilder out = new StringBuilder();
		
		String strA = params.get(PARAM_A), strB = params.get(PARAM_B);
		if (strA == null || strB == null) {
			out.append("bad args");
			return out.toString();
		}
		
		int a = 0, b = 0;
		try {
			a = Integer.parseInt(strA);
			b = Integer.parseInt(strB);
		} catch (NumberFormatException e) {
			out.append("args illegal format");
			return out.toString();
		}
		
		out.append("sum of ").append(a).append(" and ").append(b).append(" = ").append(a + b);
		return out.toString();
	}

}
