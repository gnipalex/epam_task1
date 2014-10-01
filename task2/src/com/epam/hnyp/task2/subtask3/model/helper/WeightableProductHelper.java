package com.epam.hnyp.task2.subtask3.model.helper;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.WeightableProduct;


public class WeightableProductHelper extends ProductHelper {
	private static final String fieldWeight = "weight";

	@Override
	protected Product newInstanceOfProduct() {
		return new WeightableProduct();
	}
	
	@Override
	public Product make(String data) throws IllegalDataFormatException {
		WeightableProduct p = (WeightableProduct)super.make(data);
		Matcher matcher = Pattern.compile(makeReqularExpression(fieldWeight))
				.matcher(data);
		if (!matcher.find()) {
			throw new IllegalDataFormatException();
		}
		try {
			p.setWeight(Double.parseDouble(matcher.group(1)));
		} catch (NumberFormatException e) {
			throw new IllegalDataFormatException();
		}
		return p;
	}
	
	@Override
	public Map<String, Class<?>> getFields() {
		Map<String, Class<?>> map = super.getFields();
		map.put(fieldWeight, Double.class);
		return map;
	}
}
