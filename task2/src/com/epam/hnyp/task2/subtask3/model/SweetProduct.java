package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;
import java.util.Formatter;

public class SweetProduct extends WeightableProduct implements Serializable {
	private static final long serialVersionUID = 5307430294973106832L;

	private String fill;

	public SweetProduct() {
	}
	
	public SweetProduct(long id, String name, int price, double weight, String fill) {
		super(id, name, price, weight);
		this.fill = fill;
	}

	public String getFill() {
		return fill;
	}

	@ProductSetterAnnotation(friendlyMessage = "PROD_SWEET_FILL", type = String.class)
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
