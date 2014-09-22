package com.epam.hnyp.task1.subtask1;

/**
 * Represents string instrument - guitar
 * 
 * @author Oleksandr_Hnyp
 * 
 */
public class Guitar extends StringInstrument {
	public static final int MIN_GUITAR_STRINGS = 4;
	public static final int MAX_GUITAR_STRINGS = 12;
	public static final int CLASSIC_GUITAR_STRINGS = 6;
	public static final String DEFAULT_COLOR = "brown";

	private String color;
	private StringType stringType;

	/**
	 * Creates classic guitar with CLASSIC_GUITAR_STRINGS and StringType.NEULON
	 * strings
	 */
	public Guitar() {
		super(CLASSIC_GUITAR_STRINGS);
		color = DEFAULT_COLOR;
		stringType = StringType.NEULON;
	}

	/**
	 * Creates guitar with params
	 * 
	 * @param vendor
	 * @param year
	 * @param stringCount
	 * @param color
	 * @param stringType
	 * @throws IllegalArgumentException if stringCount < MIN_GUITAR_STRINGS or stringCount > MAX_GUITAR_STRINGS
	 */
	public Guitar(String vendor, int year, int stringCount, String color,
			StringType stringType) {
		super(vendor, year, stringCount);
		this.color = color;
		this.stringType = stringType;
	}

	@Override
	protected void checkStringCount(int stringCount) {
		if (stringCount < MIN_GUITAR_STRINGS
				|| stringCount > MAX_GUITAR_STRINGS) {
			throw new IllegalArgumentException("guitar with " + stringCount
					+ " strings does not exist");
		}
	};

	/**
	 * Represents types of guitar strings
	 * 
	 * @author Oleksandr_Hnyp
	 * 
	 */
	public static enum StringType {
		NEULON, METAL
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public StringType getStringType() {
		return stringType;
	}

	public void setStringType(StringType stringType) {
		this.stringType = stringType;
	}

	/**
	 * @throws IllegalArgumentException
	 *             if stringCount < MIN_GUITAR_STRINGS OR stringCount >
	 *             MAX_GUITAR_STRINGS
	 */
	@Override
	public void setStringCount(int stringCount) {
		super.setStringCount(stringCount);
	}

	@Override
	public void play() {
		System.out.println("guitar : Metallica - Nothing Else Matters");
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			Guitar gu = (Guitar) obj;
			if (gu.color.equals(color) && gu.stringType == stringType) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + color.hashCode() + stringType.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString()).append(" guitar, ").append(stringType)
				.append(", color ").append(color);
		return str.toString();
	}
}
