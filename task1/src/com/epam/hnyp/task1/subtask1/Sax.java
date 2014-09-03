package com.epam.hnyp.task1.subtask1;

/**
 * Represents Sax - wind music instrument
 * @author Oleksandr_Hnyp
 *
 */
public class Sax extends WindInstrument {
	public Sax() {
	}
	
	public Sax(String vendor, int year, String material) {
		super(vendor, year, material);
	}
	
	@Override
	public void play() {
		System.out.println("sax : Kenny G - The Moment");
	}
	
	@Override
	public String toString() {
		return super.toString() + ", sax";
	}
}
