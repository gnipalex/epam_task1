package com.epam.hnyp.task2.subtask3.model.helper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.hnyp.task2.subtask3.model.Product;

public class ProductHelper implements HelperNoReflection {
	private static final String fieldName = "name";

	private static final String fieldPrice = "price";

	protected Product newInstanceOfProduct() {
		return new Product();
	}
	
	@Override
	public Product make(String data) throws IllegalDataFormatException {
		Product p = newInstanceOfProduct();
		// parsing name
		Matcher matcher = Pattern.compile(makeReqularExpression(fieldName))
				.matcher(data);
		if (!matcher.find()) {
			throw new IllegalDataFormatException();
		}
		p.setName(matcher.group(1));
		// parsing price
		matcher = Pattern.compile(makeReqularExpression(fieldPrice)).matcher(
				data);
		if (!matcher.find()) {
			throw new IllegalDataFormatException();
		}
		try {
			p.setPrice(Integer.parseInt(matcher.group(1)));
		} catch (NumberFormatException e) {
			throw new IllegalDataFormatException();
		}

		// parsing id
//		matcher = Pattern.compile(makeReqularExpression(fieldId)).matcher(
//				data);
//		if (!matcher.find()) {
//			throw new IllegalDataFormatException();
//		}
//		try {
//			this.id = Long.parseLong(matcher.group(1));
//		} catch (NumberFormatException e) {
//			throw new IllegalDataFormatException();
//		}
		return p;
	}

	protected String makeReqularExpression(String fieldName) {
		return fieldName + ":(.+?)(?=;)|" + fieldName + ":(.+)";
	}

	@Override
	public Map<String, Class<?>> getFields() {
		Map<String, Class<?>> map = new LinkedHashMap<String, Class<?>>();
		map.put(fieldName, String.class);
		map.put(fieldPrice, Integer.class);
		return map;
	}
}
