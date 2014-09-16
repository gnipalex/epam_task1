package com.epam.hnyp.task2.subtask3.model.creator;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection.IllegalDataFormatException;

public class RandomGoodCreator implements GoodCreator {

	@Override
	public void createGood(Good g) throws GoodCreateException {
		StringBuilder stringData = new StringBuilder();
		Map<String, Class<?>> fields = g.getFields();
		Random rand = new Random(System.currentTimeMillis());
		for (Entry<String, Class<?>> e : fields.entrySet()) {
			if (e.getValue() == String.class) {
				stringData.append(makeParam(e.getKey(), e.getKey() + " " + rand.nextInt(99999)));
			} else if (e.getValue() == Integer.class) {
				stringData.append(makeParam(e.getKey(), String.valueOf(rand.nextInt(99))));
			} else if (e.getValue() == Double.class) {
				stringData.append(makeParam(e.getKey(), String.valueOf(rand.nextDouble() * 20 + 0.1)));
//			} else if (e.getValue() == Long.class) {
//				stringData.append(makeParam(e.getKey(), val)
			} else {
				throw new GoodCreateException("cannot fill field '" + e.getKey() + "' type of " + e.getValue().getName());
			}
		}
		try {
			g.make(stringData.toString());
		} catch (IllegalDataFormatException e) {
			throw new GoodCreateException("error while random filling of class " + g.getClass().getName());
		}
	}
	
	public String makeParam(String field, String val) {
		return field + ":" + val + ";";
	}

}
