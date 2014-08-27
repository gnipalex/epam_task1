package com.epam.hnyp.task1.subtask1;

public class Violin extends StringInstrument {
	public static final int MIN_STRINGS = 4;
	public static final int MAX_STRINGS = 10;
	public static final int CLASSIC_VIOLIN_STRINGS = 4;

	/**
	 * Creates ordinary violin with 4 strings
	 */
	public Violin() {
		super(CLASSIC_VIOLIN_STRINGS);
	}

	/**
	 * 
	 * @param vendor
	 * @param year
	 * @param stringCount
	 * @throws IllegalArgumentException
	 *             if stringCount < MIN_STRINGS || stringCount > MAX_STRINGS
	 */
	public Violin(String vendor, int year, int stringCount) {
		super(vendor, year, stringCount);
		if (stringCount < 4 || stringCount > 10) {
			throw new IllegalArgumentException("violin with " + stringCount
					+ " does not exist");
		}
	}

	@Override
	public void setStringCount(int stringCount) {
		if (!(stringCount < MIN_STRINGS || stringCount > MAX_STRINGS)) {
			super.setStringCount(stringCount);
		}
	}
	
	@Override
	public void play() {
		System.out.println("violin : Antonio Vivaldi - Summer");
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			if (obj instanceof Violin) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + ", violin";
	}
}
