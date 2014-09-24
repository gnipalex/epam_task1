package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;
import java.util.Formatter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrinkProduct extends Product implements Serializable {
	private static final long serialVersionUID = -7896820585762605712L;
	
	private double volume; 
	private static final String fieldVolume = "volume";
	
	public DrinkProduct() {
	}
	
	public DrinkProduct(long id, String name, int price, double volume) {
		super(id, name, price);
		this.volume = volume;
	}

	public double getVolume() {
		return volume;
	}

	@ProductSetterAnnotation(friendlyMessage = "TOVAR_DRINK_VOLUME", type = Double.class)
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\tvol = " + volume;
	}
	
	@Override
	protected String printOtherColumn() {
		Formatter fmt = new Formatter();
		String s = String.format("volume=%1$.2f", volume);
		fmt.format("%1$20s", s);
		return fmt.toString();
	}
	
	@Override
	public void make(String data) throws IllegalDataFormatException {
		super.make(data);
		Matcher matcher = Pattern.compile(makeReqularExpression(fieldVolume))
				.matcher(data);
		if (!matcher.find()) {
			throw new IllegalDataFormatException();
		}
		try {
			this.volume = Double.parseDouble(matcher.group(1));
		} catch (NumberFormatException e) {
			throw new IllegalDataFormatException();
		}
	}
	
	@Override
	public Map<String, Class<?>> getFields() {
		Map<String, Class<?>> map = super.getFields();
		map.put(fieldVolume, Double.class);
		return map;
	}
}
