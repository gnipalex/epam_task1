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

public class WeightableGood extends Good implements Serializable {
	
	private static final long serialVersionUID = 2262341991941044041L;
	
	private double weight;
	private static final String fieldWeight = "weight";
	
//	private static Map<String, FieldParser> PARSERS = new LinkedHashMap<>();
//	static {
//		PARSERS.put("Input weight : ", new DoubleFieldParser(fieldWeight));
//	}
	
	public WeightableGood(long id, String name, int price, double weight) {
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
		fmt.format("%1$20s", "weight=" + weight);
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
//		this.weight = rand.nextDouble() * 20 + 0.1;
//	}
	
	@Override
	public Map<String, Class<?>> getFields() {
		Map<String, Class<?>> map = super.getFields();
		map.put(fieldWeight, Double.class);
		return map;
	}
}
