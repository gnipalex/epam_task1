package com.epam.hnyp.task2.subtask3.model;

import java.util.Formatter;

public class SweetGood extends WeightableGood {
	private String fill;
	
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
}
