package com.epam.hnyp.task1.subtask1;

/**
 * Represents abstract string instrument
 * 
 * @author Oleksandr_Hnyp
 * 
 */
public abstract class StringInstrument extends MusicInstrument {
	public static final int MAX_STRINGS_COUNT = 300;
	private int stringCount;

	/**
	 * Creates string instrument with params
	 * 
	 * @param vendor
	 * @param year
	 * @param stringCount
	 */
	public StringInstrument(String vendor, int year, int stringCount) {
		super(vendor, year);
		checkStringCount(stringCount);
		this.stringCount = stringCount;
	}

	public StringInstrument(int stringCount) {
		super();
		checkStringCount(stringCount);
		this.stringCount = stringCount;
	}
	
	protected void checkStringCount(int stringCount) {
		if (stringCount < 1 || stringCount > MAX_STRINGS_COUNT) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Gets string count for this instrument
	 * 
	 * @return string count
	 */
	public int getStringCount() {
		return stringCount;
	}

	/**
	 * Sets string count
	 * 
	 * @param stringCount
	 *            count of strings must be > 0 & < MAX_STRINGS_COUNT
	 * @throws IllegalArgumentException if stringCount <= 0 || stringCount > MAX_STRINGS_COUNT
	 */
	public void setStringCount(int stringCount) {
		if (!(stringCount > 0 && stringCount <= MAX_STRINGS_COUNT)) {
			throw new IllegalArgumentException();
		}
		this.stringCount = stringCount;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			StringInstrument si = (StringInstrument) obj;
			if (si.stringCount == this.stringCount) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + 17 * stringCount;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString()).append(", string instrument (")
				.append(stringCount).append(" strings)");
		return str.toString();
	}

}
