package com.epam.hnyp.task1.subtask1;

public abstract class StringInstrument extends MusicInstrument {
	public static final int MAX_STRINGS_COUNT = 300;
	private int stringCount;
	
	public StringInstrument(String vendor, int year, int stringCount) {
		super(vendor, year);
		if (stringCount < 1 || stringCount > MAX_STRINGS_COUNT) {
			throw new IllegalArgumentException();
		}
		this.stringCount = stringCount;
	}
	
	public StringInstrument(int stringCount){
		super();
		if (stringCount < 1 || stringCount > MAX_STRINGS_COUNT) {
			throw new IllegalArgumentException();
		}
		this.stringCount = stringCount;
	}

	public int getStringCount() {
		return stringCount;
	}

	public void setStringCount(int stringCount) {
		if (stringCount > 0 && stringCount < MAX_STRINGS_COUNT) {
			this.stringCount = stringCount;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			if (obj instanceof StringInstrument) {
//				if (obj.hashCode() != this.hashCode()) {
//					return false;
//				}
				StringInstrument si = (StringInstrument)obj;
				if (si.stringCount == this.stringCount) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + 17*stringCount;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString()).append(", string instrument (").append(stringCount).append(" strings)");
		return str.toString(); 
	}

}
