package com.epam.hnyp.task1.subtask1;

import java.util.Calendar;

public class MusicInstrument {
	private String vendor;
	private int year;

	/**
	 * Create default music instrument
	 */
	public MusicInstrument() {
		this("unknown", Calendar.getInstance().get(Calendar.YEAR));
	}

	/**
	 * Create parameterized music instrument
	 * 
	 * @param vendor
	 * @param year
	 * @throws IllegalArgumentException
	 *             if vendor is not specified or year isn't in 1900...nowadays
	 */
	public MusicInstrument(String vendor, int year) {
		if (vendor == null) {
			throw new IllegalArgumentException("vendor must be specified");
		}
		if (year < 1900 && year > Calendar.getInstance().get(Calendar.YEAR)) {
			throw new IllegalArgumentException(
					"year must be in 1900...nowadays");
		}
		this.vendor = vendor;
		this.year = year;
	}

	/**
	 * Plays melody using this instrument
	 */
	public void play() {
		System.out.println("music instrument : a e a e a ab ab");
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public int getYear() {
		return year;
	}

	/**
	 * Sets year
	 * 
	 * @param year
	 * @throws IllegalArgumentException
	 *             if year < 1900 OR year > current year
	 */
	public void setYear(int year) {
		if (!(year > 1900 && year <= Calendar.getInstance().get(Calendar.YEAR))) {
			throw new IllegalArgumentException();
		}
		this.year = year;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("music instrument : vendor ").append(vendor)
				.append(", year ").append(year);
		return str.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		MusicInstrument instr = (MusicInstrument) obj;
		if (instr.vendor.equals(vendor) && instr.year == year) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 7 * vendor.hashCode() + 13 * year;
	}
}
