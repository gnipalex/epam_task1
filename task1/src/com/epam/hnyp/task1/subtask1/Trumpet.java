package com.epam.hnyp.task1.subtask1;

/**
 * Represents wind instrument - trumpet
 * @author Oleksandr_Hnyp
 *
 */
public class Trumpet extends WindInstrument {
	public Trumpet() {
	}
	
	public Trumpet(String vendor, int year, String material) {
		super(vendor, year, material);
	}
	
	@Override
	public void play() {
		System.out.println("trumpet : turum turuuum tuum");
	}
	
	@Override
	public String toString() {
		return super.toString() + ", trumpet";
	}
}
