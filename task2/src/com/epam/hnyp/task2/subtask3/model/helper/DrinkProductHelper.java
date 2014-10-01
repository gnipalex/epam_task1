package com.epam.hnyp.task2.subtask3.model.helper;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.hnyp.task2.subtask3.model.DrinkProduct;
import com.epam.hnyp.task2.subtask3.model.Product;

public class DrinkProductHelper extends ProductHelper {

	private static final String fieldVolume = "volume";
	
	@Override
	protected Product newInstanceOfProduct() {
		return new DrinkProduct();
	}
	
	@Override
	public Product make(String data) throws IllegalDataFormatException {
		DrinkProduct p = (DrinkProduct)super.make(data);
		Matcher matcher = Pattern.compile(makeReqularExpression(fieldVolume))
				.matcher(data);
		if (!matcher.find()) {
			throw new IllegalDataFormatException();
		}
		try {
			p.setVolume(Double.parseDouble(matcher.group(1)));
		} catch (NumberFormatException e) {
			throw new IllegalDataFormatException();
		}
		return p;
	}
	
	@Override
	public Map<String, Class<?>> getFields() {
		Map<String, Class<?>> map = super.getFields();
		map.put(fieldVolume, Double.class);
		return map;
	}
}
