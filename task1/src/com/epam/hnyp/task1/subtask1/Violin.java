package com.epam.hnyp.task1.subtask1;

/**
 * Represents string instrument - violin
 * 
 * @author Oleksandr_Hnyp
 * 
 */
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
	 * Creates violin with params
	 * 
	 * @param vendor
	 * @param year
	 * @param stringCount
	 * @throws IllegalArgumentException
	 *             if stringCount < MIN_STRINGS || stringCount > MAX_STRINGS
	 */
	public Violin(String vendor, int year, int stringCount) {
		super(vendor, year, stringCount);
	}

	@Override
	protected void checkStringCount(int stringCount) {
		if (stringCount < MIN_STRINGS || stringCount > MAX_STRINGS) {
			throw new IllegalArgumentException("violin with " + stringCount
					+ " does not exist");
		}
	}

	/**
	 * @throws IllegalArgumentException
	 *             if stringCount < MIN_STRINGS OR stringCount > MAX_STRINGS
	 */
	@Override
	public void setStringCount(int stringCount) {
		if (stringCount < MIN_STRINGS || stringCount > MAX_STRINGS) {
			throw new IllegalArgumentException();
		}
		super.setStringCount(stringCount);
	}

	@Override
	public void play() {
		System.out.println("violin : Antonio Vivaldi - Summer");
	}

	@Override
	public String toString() {
		return super.toString() + ", violin";
	}
}
