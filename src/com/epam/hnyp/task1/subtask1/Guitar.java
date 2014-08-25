package com.epam.hnyp.task1.subtask1;

public class Guitar extends StringInstrument {
	public static final int MIN_GUITAR_STRINGS = 4;
	public static final int MAX_GUITAR_STRINGS = 12;
	public static final int CLASSIC_GUITAR_STRINGS = 6;
	public static final String DEFAULT_COLOR = "brown";
	
	private String color;
	private StringType stringType;

	public Guitar() {
		super(CLASSIC_GUITAR_STRINGS);
		color = DEFAULT_COLOR;
		stringType = StringType.NEULON;
	}

	public Guitar(String vendor, int year, int stringCount, String color,
			StringType stringType) {
		super(vendor, year, stringCount);
		if (stringCount < MIN_GUITAR_STRINGS || stringCount > MAX_GUITAR_STRINGS) {
			throw new IllegalArgumentException("guitar with " + stringCount
					+ " strings does not exist");
		}
		this.color = color;
		this.stringType = stringType;
	}

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

	@Override
	public void setStringCount(int stringCount) {
		if (!(stringCount < MIN_GUITAR_STRINGS || stringCount > MAX_GUITAR_STRINGS)) {
			super.setStringCount(stringCount);
		}
	}
	
	@Override
	public void play() {
		System.out.println("guitar : Metallica - Nothing Else Matters");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			if (obj instanceof Guitar) {
//				if (obj.hashCode() != this.hashCode()) {
//					return false;
//				}
				Guitar gu = (Guitar)obj;
				if (gu.color.equals(color) && gu.stringType == stringType) {
					return true;
				}
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
		str.append(super.toString()).append(" guitar, ").append(stringType).append(", color ").append(color);
		return str.toString();
	}
}
