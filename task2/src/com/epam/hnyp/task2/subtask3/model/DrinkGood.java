package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection.IllegalDataFormatException;
import com.epam.hnyp.task2.subtask3.model.parser.DoubleFieldParser;
import com.epam.hnyp.task2.subtask3.model.parser.FieldParser;

public class DrinkGood extends Good implements Serializable {
	private static final long serialVersionUID = -7896820585762605712L;
	
	private double volume; 
	private static final String fieldVolume = "volume";
	
//	private static Map<String, FieldParser> PARSERS = new LinkedHashMap<>();
//	static {
//		PARSERS.put(fieldVolume, new DoubleFieldParser(fieldVolume));
//	}
	
	public DrinkGood(long id, String name, int price, double volume) {
		super(id, name, price);
		this.volume = volume;
	}

	public double getVolume() {
		return volume;
	}

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
		fmt.format("%1$20s", "volume=" + volume);
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
	
//	@Override
//	public Map<String, FieldParser> getParsers() {
//		Map<String, FieldParser> map = super.getParsers();
//		map.putAll(PARSERS);
//		return map;
//	}
	
//	@Override
//	public void makeRandom() {
//		super.makeRandom();
//		Random rand = new Random(System.currentTimeMillis());
// 		this.volume = 0.5 * rand.nextInt(20) + 0.5;
//	}
	
	@Override
	public Map<String, Class<?>> getFields() {
		Map<String, Class<?>> map = super.getFields();
		map.put(fieldVolume, Double.class);
		return map;
	}
}
