package com.epam.hnyp.task2.subtask3.model;

public class DrinkGood extends Good {
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
}
