package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;
import java.util.Formatter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WeightableProduct extends Product implements Serializable {
	
	private static final long serialVersionUID = 2262341991941044041L;
	
	@ProductFieldAnnotation(friendlyMessage = "TOVAR_WEIGHTABLE_WEIGHT", type = Double.class)
	private double weight;
	private static final String fieldWeight = "weight";
	
	public WeightableProduct() {
	}
	
	public WeightableProduct(long id, String name, int price, double weight) {
		super(id, name, price);
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\tweight = " + weight;
	}
	
	@Override
	protected String printOtherColumn() {
		Formatter fmt = new Formatter();
		String s = String.format("weight=%1$.2f", weight);
		fmt.format("%1$20s", s);
		return fmt.toString();
	}
	
	@Override
	public void make(String data) throws IllegalDataFormatException {
		super.make(data);
		Matcher matcher = Pattern.compile(makeReqularExpression(fieldWeight))
				.matcher(data);
		if (!matcher.find()) {
			throw new IllegalDataFormatException();
		}
		try {
			this.weight = Double.parseDouble(matcher.group(1));
		} catch (NumberFormatException e) {
			throw new IllegalDataFormatException();
		}
	}
	
	@Override
	public Map<String, Class<?>> getFields() {
		Map<String, Class<?>> map = super.getFields();
		map.put(fieldWeight, Double.class);
		return map;
	}
}
