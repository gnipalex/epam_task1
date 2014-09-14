package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;
import java.util.Formatter;

public class DrinkGood extends Good implements Serializable {
	private static final long serialVersionUID = -7896820585762605712L;
	
	private double volume; 
	
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
}
