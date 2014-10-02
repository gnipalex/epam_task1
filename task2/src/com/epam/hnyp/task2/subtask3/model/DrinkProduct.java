package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;
import java.util.Formatter;

public class DrinkProduct extends Product implements Serializable {
	private static final long serialVersionUID = -7896820585762605712L;
	
	private double volume; 
	
	public DrinkProduct() {
	}
	
	public DrinkProduct(long id, String name, int price, double volume) {
		super(id, name, price);
		this.volume = volume;
	}

	public double getVolume() {
		return volume;
	}

	@ProductSetterAnnotation(friendlyMessage = "PROD_DRINK_VOLUME", type = Double.class)
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
}
