package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.hnyp.task2.subtask3.model.ParsableGoodNoReflection.IllegalDataFormatException;
import com.epam.hnyp.task2.subtask3.model.reader.FieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.DoubleConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.console.StringConsoleFieldReader;
import com.epam.hnyp.task2.subtask3.model.reader.random.StringRandomFieldReader;

public class SweetGood extends WeightableGood implements Serializable {
	private static final long serialVersionUID = 5307430294973106832L;

	@GoodFieldAnnotation(friendlyMessage = "TOVAR_SWEET_FILL")
	private String fill;
	private static final String fieldFill = "fill";

	public SweetGood() {
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
		Matcher matcher = Pattern.compile(makeReqularExpression(fieldFill))
				.matcher(data);
		if (!matcher.find()) {
			throw new IllegalDataFormatException();
		}
		this.fill = matcher.group(1);
	}
	
	@Override
	public Map<String, Class<?>> getFields() {
		Map<String, Class<?>> map = super.getFields();
		map.put(fieldFill, String.class);
		return map;
	}
}
