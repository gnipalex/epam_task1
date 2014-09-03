package com.epam.hnyp.task2.subtask3;

import java.util.LinkedHashMap;
import java.util.Map;

public interface Store extends Iterable<Good> {
//	private static Map<Long, Good> goods = new LinkedHashMap<>();
//	
//	static {
//		
//	}
	Good get(long id);
	
}
