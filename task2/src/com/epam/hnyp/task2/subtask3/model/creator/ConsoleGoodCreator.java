package com.epam.hnyp.task2.subtask3.model.creator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection;
import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection.IllegalDataFormatException;
import com.epam.hnyp.task2.subtask3.model.parser.DoubleFieldParser;
import com.epam.hnyp.task2.subtask3.model.parser.FieldParser;
import com.epam.hnyp.task2.subtask3.model.parser.FieldParser.IllegalFieldFormatException;
import com.epam.hnyp.task2.subtask3.model.parser.IntFieldParser;
import com.epam.hnyp.task2.subtask3.model.parser.LongFieldParser;
import com.epam.hnyp.task2.subtask3.model.parser.StringFieldParser;

public class ConsoleGoodCreator implements GoodCreator {

	private static final Map<Class<?>, FieldParser> PARSERS = new HashMap<Class<?>, FieldParser>();
	static {
		PARSERS.put(String.class, new StringFieldParser());
		PARSERS.put(Double.class, new DoubleFieldParser());
		PARSERS.put(Integer.class, new IntFieldParser());
		PARSERS.put(Long.class, new LongFieldParser());
	}
	
	@Override
	public void createGood(ParsableGoodNoReflection g) throws GoodCreateException {
		Scanner sc = new Scanner(System.in);
		Map<String, Class<?>> fields = g.getFields();
		StringBuilder stringData = new StringBuilder();
		for (Entry<String, Class<?>> e : fields.entrySet()) {
			//getting parser for field
			FieldParser parser = PARSERS.get(e.getValue());
			if (parser == null) {
				throw new GoodCreateException("parser not found for field '" + e.getKey() + "'");
			}
			while(true) {
				//parsing field with parser
				System.out.print("Please enter field '" + e.getKey() + "' : ");
				String line = sc.nextLine();
				try {
					String parsed = parser.parse(line);
					stringData.append(e.getKey()).append(":").append(parsed).append(";");
				} catch (IllegalFieldFormatException ex) {
					System.out.println("##field format error##");
					continue;
				}
				break;
			}
		}
		try {
			g.make(stringData.toString());
		} catch (IllegalDataFormatException e) {
			throw new GoodCreateException("error while filling fields of class " + g.getClass().getName());
		}
	}

}
