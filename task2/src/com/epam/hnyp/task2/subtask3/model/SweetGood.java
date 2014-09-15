package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.hnyp.task2.subtask3.model.parser.DoubleFieldParser;
import com.epam.hnyp.task2.subtask3.model.parser.FieldParser;
import com.epam.hnyp.task2.subtask3.model.parser.StringFieldParser;
import com.epam.hnyp.task2.subtask3.model.parser.ParsableGoodNoReflection.IllegalDataFormatException;

public class SweetGood extends WeightableGood implements Serializable {
	private static final long serialVersionUID = 5307430294973106832L;
	
	private String fill;
	private static final String fieldFill = "fill";
	
	private static Map<String, FieldParser> PARSERS = new LinkedHashMap<>();
	static {
		PARSERS.put("Input fill : ", new StringFieldParser(fieldFill));
	}
	
	public SweetGood(long id, String name, int price, double weight, String fill) {
		super(id, name, price, weight);
		this.fill = fill;
	}

	public String getFill() {
		return fill;
	}

	public void setFill(String fill) {
		this.fill = fill;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\tfill = " + fill;
	}
	
	@Override
	protected String printOtherColumn() {
		StringBuilder str = new StringBuilder();
		str.append(super.printOtherColumn()).append("\n");
		Formatter fmt = new Formatter();
		fmt.format("%1$40s%2$20s", "", "fill=" + fill);
		str.append(fmt);
		return str.toString();
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
	public Map<String, FieldParser> getParsers() {
		Map<String, FieldParser> map = super.getParsers();
		map.putAll(PARSERS);
		return map;
	}
}
