package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.hnyp.task2.subtask3.model.parser.FieldParser;
import com.epam.hnyp.task2.subtask3.model.parser.IntFieldParser;
import com.epam.hnyp.task2.subtask3.model.parser.LongFieldParser;
import com.epam.hnyp.task2.subtask3.model.parser.ParsableGoodNoReflection;
import com.epam.hnyp.task2.subtask3.model.parser.StringFieldParser;

public class Good implements Serializable, ParsableGoodNoReflection {

	private static final long serialVersionUID = 6101697799798820856L;

	private String name;
	private static final String fieldName = "name";

	private int price;
	private static final String fieldPrice = "price";

	private long id;
	private static final String fieldId = "id";

	private static final Map<String, FieldParser> PARSERS = new LinkedHashMap<>();
	static {
		PARSERS.put("Input 'id' : ", new LongFieldParser(fieldId));
		PARSERS.put("Input 'name' : ", new StringFieldParser(fieldName));
		PARSERS.put("Input 'price' : ", new IntFieldParser(fieldPrice));
	}

	public Good(long id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(id).append("\t").append(name).append("\tprice = ")
				.append(price);
		return str.toString();
	}

	/**
	 * Prints to string this object as table row : id(10s) - name(20s) -
	 * price(10s) - other(20s)
	 * 
	 * @return
	 */
	public String printTableRow() {
		Formatter fmt = new Formatter();
		fmt.format("%1$10d%2$20s%3$10d%4$s", id, name, price,
				printOtherColumn());
		return fmt.toString();
	}

	/**
	 * Prints column other for good. Distance to this column 40 symbols. First
	 * element is appended to the end of row, others prints at new line.
	 * 
	 * @return
	 */
	protected String printOtherColumn() {
		return "";
	}

	@Override
	public void make(String data) throws IllegalDataFormatException {
		// parsing name
		Matcher matcher = Pattern.compile(makeReqularExpression(fieldName))
				.matcher(data);
		if (!matcher.find()) {
			throw new IllegalDataFormatException();
		}
		this.name = matcher.group(1);

		// parsing price
		matcher = Pattern.compile(makeReqularExpression(fieldPrice)).matcher(
				data);
		if (!matcher.find()) {
			throw new IllegalDataFormatException();
		}
		try {
			this.price = Integer.parseInt(matcher.group(1));
		} catch (NumberFormatException e) {
			throw new IllegalDataFormatException();
		}

		// parsing price
		matcher = Pattern.compile(makeReqularExpression(fieldId)).matcher(
				data);
		if (!matcher.find()) {
			throw new IllegalDataFormatException();
		}
		try {
			this.id = Long.parseLong(matcher.group(1));
		} catch (NumberFormatException e) {
			throw new IllegalDataFormatException();
		}
	}

	public String makeReqularExpression(String fieldName) {
		return fieldName + ":(.+?)(?=;)|" + fieldName + ":(.+)";
	}

	@Override
	public Map<String, FieldParser> getParsers() {
		return new LinkedHashMap<>(PARSERS);
	}
}
