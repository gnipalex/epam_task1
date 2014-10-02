package com.epam.hnyp.task2.subtask3.model.helper;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.SweetProduct;

public class SweetProductHelper extends WeightableProductHelper {

	private static final String fieldFill = "fill";

	@Override
	protected Product newInstanceOfProduct() {
		return new SweetProduct();
	}

	@Override
	public Product make(String data) throws IllegalDataFormatException {
		SweetProduct p = (SweetProduct)super.make(data);
		Matcher matcher = Pattern.compile(makeReqularExpression(fieldFill))
				.matcher(data);
		if (!matcher.find()) {
			throw new IllegalDataFormatException();
		}
		p.setFill(matcher.group(1));
		return p;
	}
	
	@Override
	public Map<String, Class<?>> getFields() {
		Map<String, Class<?>> map = super.getFields();
		map.put(fieldFill, String.class);
		return map;
	}
}
