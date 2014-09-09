package com.epam.hnyp.task2.subtask3.model;

import java.util.Formatter;

public class Good {
	private String name;
	private int price;
	private long id;
	
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
		str.append(id).append("\t").append(name).append("\tprice = ").append(price);
		return str.toString();
	}
	/**
	 * Prints to string this object as table row :
	 * id(10s) - name(20s) - price(10s) - other(20s)
	 * @return
	 */
	public String printTableRow() {
		Formatter fmt = new Formatter();
		fmt.format("%1$10d%2$20s%3$10d%4$s", id, name, price, printOtherColumn());
		return fmt.toString();
	}
	
	/**
	 * Prints column other for good.
	 * Distance to this column 40 symbols. First element is appended to the end of row, others prints at new line.
	 * @return
	 */
	protected String printOtherColumn() {
		return "";
	}
}
